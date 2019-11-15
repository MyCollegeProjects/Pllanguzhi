/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pallanguzhi;

/**
 *
 * @author WeaponMaster
 */
public class Kani {
    static int thinkMove(int grid[], int player){
        int t[], populateP1[]=new int[7], count=0;
        t=grid;
        for (int k=7;k<14;k++){
            CoreGame p=new CoreGame();
            int p1=k;
            p.gamePlay(t, p1);
            populateP1[count]=p.score;
            count++;
        }
        
        int max, wPos=0;
        max=populateP1[0];
        System.out.println("Kani");
        for (int j=0;j<7;j++){
            int temp=j+7;
            System.out.println(temp+" "+populateP1[j]);
            if (max<populateP1[j]){
                max=populateP1[j];
                wPos=j;
            }
        }
        wPos+=7;
        System.out.println("Pos "+wPos);
        return wPos;
    }
}
