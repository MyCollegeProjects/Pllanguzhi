/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pallanguzhi;
import pallanguzhi.Rules;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author WeaponMaster
 */

public class ButtonControls extends Pallanguzhi implements ActionListener{
    static int count=0;
    
    static void resetGame(){
        Rules.resetGrid(jtb, soliCount);
        player_a.score=0;
        player_b.score=0;
        playerALabel[1].setText(""+player_a.score);
        playerBLabel[1].setText(""+player_b.score);
    }
    
    static void bottomDisable(){
        for (int i=7;i<14;i++){
            if (jtb[i].isEnabled()){
                jtb[i].setEnabled(false);
            }
        }
    }
    
    static void bottomEnable(){
        for (int i=7;i<14;i++){
            if (!jtb[i].isEnabled()){
                jtb[i].setEnabled(true);
            }
        }
    }
    
    static void fileMenu(JMenuItem jmi[]){
        jmi[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                soliCount=6;
                Rules.resetGrid(jtb, soliCount);
                player_a.score=0;
                player_b.score=0;
                playerALabel[1].setText(""+player_a.score);
                playerBLabel[1].setText(""+player_b.score);
                cdl.show(cardPanel, "GamePanel");
            }
        });
        
        jmi[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                soliCount=12;
                resetGame();
                cdl.show(cardPanel, "GamePanel");
            }
        });
        
        jmi[2].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                gamerID=1;
                resetGame();
                bottomEnable();
                playerBButton.setText("Play");
                playerAButton.setText("Play");
                if (!playerAButton.isEnabled()||!playerBButton.isEnabled()){
                    playerAButton.setEnabled(true);
                    playerBButton.setEnabled(true);
                }
            }
        });
        
        jmi[3].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                gamerID=2;
                resetGame();
                bottomDisable();
                Rules.resetGrid(jtb, soliCount);
                playerBButton.setText("Innisai");
                playerAButton.setText("Play");
                if (!playerAButton.isEnabled()){
                    playerAButton.setEnabled(true);
                }
                if (playerBButton.isEnabled()){
                    playerBButton.setEnabled(false);
                }
            }
        });
        
        jmi[4].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                gamerID=3;
                resetGame();
                bottomDisable();
                Rules.resetGrid(jtb, soliCount);
                playerBButton.setText("Kani");
                playerAButton.setText("Play");
                if (!playerAButton.isEnabled()){
                    playerAButton.setEnabled(true);
                }
                if (playerBButton.isEnabled()){
                    playerBButton.setEnabled(false);
                }
            }
        });
        
        jmi[5].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                gamerID=4;
                resetGame();
                bottomDisable();
                playerBButton.setText("Rani");
                playerAButton.setText("Play");
                if (!playerAButton.isEnabled()){
                    playerAButton.setEnabled(true);
                }
                if (playerBButton.isEnabled()){
                    playerBButton.setEnabled(false);
                }
            }
        });
        
        jmi[6].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                gamerID=5;
                resetGame();
                bottomDisable();
                Rules.resetGrid(jtb, soliCount);
                playerAButton.setText("Innisai");
                playerBButton.setText("Innisai");
                if (playerAButton.isEnabled()||playerBButton.isEnabled()){
                    playerAButton.setEnabled(false);
                    playerBButton.setEnabled(false);
                }
            }
        });
    }
    
    static void helpMenu(JMenuItem jmi[]){
        jmi[0].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(mainPanel, "Pallanguzhi - Version 1.1 coded by Gokula Krishna");
            }
        });
    }
    
    static void aboutMenu(JMenuItem jmi[]){
        jmi[1].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                TutorialPage tp=new TutorialPage();
                cardPanel.add(tp.jp, "TutorialPage");
                cdl.show(cardPanel, "TutorialPage");
            }
        });
    }
    
    static void playerAControl(JButton jbtn){
        jbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int []ar=new int[14];
                ArrayList tracker=new ArrayList(), currentState=new ArrayList();
                ar=getButtonNumbers(jtb);
                for (int i=0;i<7;i++){
                    if (jtb[i].isSelected()){
                        jtb[i].setSelected(false);
                        ar=player_a.gamePlay(ar, i);
                        tracker=player_a.returnList();
                        currentState=player_a.returnState();
                        
                        player_a.printGrid(ar);
                        System.out.println(player_a.score);
                        
                        /*
                         * MultiThreading for time count payload
                         */
                        
                        Runnable ma=new MovementAnimation(jtb, handPointer, tracker, currentState);
                        Thread t=new Thread(ma);
                        t.start();
                        
                        playerALabel[1].setText(""+player_a.score);
                        if (Rules.endOfGameReport(ar)==1){
                            JOptionPane.showMessageDialog(mainPanel, "Game over! Player 1");
                            Rules.resetGrid(jtb, soliCount);
                            player_a.score=0;
                            player_b.score=0;
                            playerALabel[1].setText(""+player_a.score);
                            playerBLabel[1].setText(""+player_b.score);
                        }
                        break;
                    }
                }
                
                int move;
                switch(gamerID){
                    case 3:
                        ar=getButtonNumbers(jtb);
                        move=Kani.thinkMove(ar, 1);
                        System.out.println("Move given "+move);
                        ar=player_b.gamePlay(ar, move);
                        player_b.printGrid(ar);
                        for (int j=0;j<14;j++){
                            jtb[j].setText(""+ar[j]);
                        }
                        playerBLabel[1].setText(""+player_b.score);
                        break;
                }
            }
        });
    }
    
    static void playerBControl(JButton jbtn){
        
        jbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int []ar=new int[14];
                ar=getButtonNumbers(jtb);
                ArrayList tracker=new ArrayList(), currentState=new ArrayList();
                for (int i=7;i<14;i++){
                    if (jtb[i].isSelected()){
                        jtb[i].setSelected(false);
                        ar=player_b.gamePlay(ar, i);
                        tracker=player_b.returnList();
                        currentState=player_b.returnState();
                        
                        player_b.printGrid(ar);
                        System.out.println(player_b.score);
                        
                        Runnable ma=new MovementAnimation(jtb, handPointer, tracker, currentState);
                        Thread t=new Thread(ma);
                        t.start();
                        
                        playerBLabel[1].setText(""+player_b.score);
                        if (Rules.endOfGameReport(ar)==2){
                            JOptionPane.showMessageDialog(mainPanel, "Game over! Player 2");
                            Rules.resetGrid(jtb, soliCount);
                            player_a.score=0;
                            player_b.score=0;
                            playerALabel[1].setText(""+player_a.score);
                            playerBLabel[1].setText(""+player_b.score);
                        }
                        break;
                    }
                }
            }
        });
    }    
    
    static void resetBtn(JButton jbtn){
        jbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                resetGame();
            }
        });
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}