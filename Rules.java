/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pallanguzhi;

import javax.swing.JToggleButton;
import javax.swing.JLabel;

/**
 *
 * @author WeaponMaster
 */
public class Rules extends Pallanguzhi{
    
    static int endOfGameReport(int []grid){
        int holeCount[]=new int[2];
        holeCount[0]=holeCount[1]=0;
        for (int i=0;i<7;i++){
            if (grid[i]==0){
                holeCount[0]++;
            }
        }
        
        for (int i=7;i<14;i++){
            if (grid[i]==0){
                holeCount[1]++;
            }
        }
        
        if (holeCount[0]>=7){
            return 1;
        }else if (holeCount[1]>=7){
            return 2;
        }
        
        return 0;
    }
    
    static void initBtn(int soli){
        for (int i=0;i<14;i++){
            jtb[i]=new JToggleButton(""+soli);
        }        
    }
    
    static void initHand(){
        for (int i=0;i<14;i++){
            if (i<7){
                handPointer[i]=new JLabel("V");
            }else{
                handPointer[i]=new JLabel("^");
            }
        }
    }
    
    static void resetGrid(JToggleButton jtb[], int val){
        for (int i=0;i<14;i++){
            jtb[i].setText(""+val);
        }
    }
}
