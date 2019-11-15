/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pallanguzhi;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WeaponMaster
 */
public class MovementAnimation implements Runnable{
    JToggleButton[] jtb;
    JLabel handPointer[]=new JLabel[14];
    ArrayList<Integer> tracker=new ArrayList<Integer>();
    ArrayList<Integer> currentState=new ArrayList<Integer>();
    
    MovementAnimation(JToggleButton[] jtb,JLabel handPointer[], ArrayList tracker, ArrayList currentState){
        this.currentState=currentState;
        this.jtb=jtb;
        this.handPointer=handPointer;
        this.tracker=tracker;
    }
    
    @Override
    public synchronized void run(){
        
        System.out.println("Track "+tracker +" Size "+tracker.size());
        System.out.println("currentState "+currentState+" Size "+currentState.size());
        System.out.println("Thread current "+Thread.currentThread());
        
        jtb[tracker.get(0)].setText(""+0);
        for (int j=0;j<currentState.size();j++){
            if (currentState.get(j)!=0){
                jtb[tracker.get(j)].setText(""+currentState.get(j));
            }else{
                jtb[tracker.get(j)].setText(""+0);
            }
            moveHandTo(tracker.get(j));
            
            try{
                Thread.sleep(500);
            }catch(InterruptedException ie){}
        }
    } 
    
    
    void moveHandTo(int pos){
        for (int i=0;i<14;i++){
            if (i==pos){
                if (i<7){
                    handPointer[i].setText("\\/");
                }else{
                    handPointer[i].setText("/\\");
                }
            }else{
                handPointer[i].setText("");
            }
        }
    }
    
}
