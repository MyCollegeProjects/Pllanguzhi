/*
 * Prototype 1 for pallanguzhi game
 * Developed by Gokula Krishna
 * Facebook: facebook.com/gokulak1
 */

package pallanguzhi;
import java.util.ArrayList;
/**
 *
 * @author S. Gokula Krishna
 */

public class CoreGame {
    int ptr, hold, ar[]=new int[14], dir, score, player, count;
    ArrayList<Integer> tracker, currentState;
                CoreGame(){
                    tracker=new ArrayList<Integer>();
                    currentState=new ArrayList<Integer>();
                    ptr=0;
                    score=0;
                    hold=0;
                    dir=1;
                    player=0;
                    count=0;
                    for (int i=0;i<14;i++)
                        ar[i]=6;
                }
                
                void printGrid(int grid[]){
                     for (int i=0;i<7;i++)
                     System.out.print(grid[i] + " ");
                     
                     System.out.println();
                     
                     for (int i=0;i<7;i++)
                     System.out.print(grid[13-i]+" ") ;
                     System.out.println();
                }
                
                int IncrementPtr(int p){
                    if (p>=13){
                               p=-1;
                    }
                    p++;
                    
                    return p;
                } 
                
                int DecrementPtr(int p){
                    if (p<=0){
                              p=14;
                    }
                    p--;
                    return p;
                }
                
                void distribute(){
                     hold=ar[ptr];
                     ar[ptr]=0;
                     
                     while (hold!=0){
                         count++;
                         
                         
                         if (dir==0){
                                     ptr=IncrementPtr(ptr);
                         }else if (dir==1){
                               ptr=DecrementPtr(ptr);
                         }
                         
                         ar[ptr]++;
                         hold--;
                         
                         printGrid(ar);
                         System.out.println();
                         
                         if (tracker.size()>2){
                             if (tracker.get(tracker.size()-1)!=ptr){
                                 tracker.add(ptr);
                                 currentState.add(ar[ptr]);
                             }
                         }else{
                             tracker.add(ptr);
                             currentState.add(ar[ptr]);
                         }                  
                     }
                     
                     if (tracker.size()>2){
                             if (tracker.get(tracker.size()-1)!=DecrementPtr(ptr)){
                                 tracker.add(DecrementPtr(ptr));
                                 currentState.add(0);
                             }
                         }else{
                             tracker.add(DecrementPtr(ptr));
                             currentState.add(0);
                         }
                }
                
                int[] gamePlay(int []t, int position){
                     for (int k=0;k<14;k++)
                     ar[k]=t[k];
                     
                     ptr=position;
                     int tPtr=ptr;
                     tracker.add(ptr);
                     currentState.add(0);
                     
                     while (ar[tPtr]!=0){
                           distribute();
                           
                           if (dir==0){
                                       tPtr=IncrementPtr(ptr);
                                       ptr=IncrementPtr(ptr);
                           }else if (dir==1){
                                 tPtr=DecrementPtr(ptr);
                                 ptr=DecrementPtr(ptr);
                           }
                           
                     }
                     tracker.add(DecrementPtr(ptr));
                     currentState.add(0);
                     
                     if (dir==0){
                                 tPtr=IncrementPtr(tPtr);
                                 score+=ar[tPtr];
                                 ar[tPtr]=0;
                     }else if(dir==1){
                           tPtr=DecrementPtr(tPtr);
                           score+=ar[tPtr];
                           ar[tPtr]=0;
                     }
                     return ar;
                }
                
                int check(int []grid, int pos){
                    if (grid[pos]==0)
                    return 1;
                    else
                    return 0;
                }
                
                ArrayList returnList(){
                    return tracker;
                }
                
                ArrayList returnState(){
                    return currentState;
                }
}