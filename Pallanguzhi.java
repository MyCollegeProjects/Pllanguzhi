/*
 * Traditional Pallanguzhi game
 * Designed by Gokula Krishna
 * Get more of mini projects at facebook.com/Gna
 * Contact Author: facebook.com/gokulak1
 * Mail: krishnagokul5e@gmail.com
 */
package pallanguzhi;

import pallanguzhi.Rules;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author WeaponMaster
 */
public class Pallanguzhi{
    
    static final CardLayout cdl=new CardLayout();
    static JToggleButton jtb[]=new JToggleButton[14];
    static JPanel mainPanel, gamePanel, cardPanel;
    static boolean running;
    static final CoreGame player_a=new CoreGame(), player_b=new CoreGame();
    static int gamerID, soliCount=6;
    static JButton playerAButton;
    static JButton playerBButton;
        
    //Label creation
    static final JLabel header=new JLabel("Player 1 Select 1st row and player 2 select 2nd row then hit the play button below. Your Game will be tracked below"), 
            footer=new JLabel("An GNA production. Follow us on facebook.com/Gna"),
            playerALabel[]=new JLabel[2],
            playerBLabel[]=new JLabel[2];
    
    static JLabel handPointer[]=new JLabel[14];
    
    static int[] getButtonNumbers(JToggleButton jtb[]){        
        int grid[]=new int[14];
        for (int i=0;i<14;i++){
            grid[i]=Integer.parseInt(jtb[i].getText());
        }        
        return grid;
    }
    
    static void showPanel(){
        
        String plaf="javax.swing.plaf.nimbus.NimbusLookAndFeel";
        
        JFrame jf=new JFrame("Pallanguzhi");
        mainPanel=new JPanel();
        gamePanel=new JPanel();
        cardPanel=new JPanel();
        
        UIManager.LookAndFeelInfo[] infos=UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info:infos){
            System.out.println(info.getName()+" "+info.getClassName());
        }
        try{
            UIManager.setLookAndFeel(plaf);
            SwingUtilities.updateComponentTreeUI(jf);
        }catch(Exception e){}
        
        //Layout Creation
        BorderLayout bl=new BorderLayout();
        GridBagLayout gbl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        
        //Setting Layout
        cardPanel.setLayout(cdl);
        mainPanel.setLayout(bl);
        gamePanel.setLayout(gbl);
        cardPanel.add(gamePanel, "GamePanel");
        
        //Menu Components
        JMenuBar jmb=new JMenuBar();
        JMenu jm[]=new JMenu[3];
        JMenuItem jmi_file[]=new JMenuItem[8], jmi_about[]=new JMenuItem[4], jmi_help[]=new JMenuItem[5];
        
        //Menu Labels
        jm[0]=new JMenu("File");
        jm[1]=new JMenu("About game");
        jm[2]=new JMenu("Help");
        
        jmi_file[0]=new JMenuItem("New 6 - Soli game");
        jmi_file[1]=new JMenuItem("New 12 - Soli game");
        jmi_file[2]=new JMenuItem("2p (Default)");
        jmi_file[3]=new JMenuItem("1p vs Innisai (CPU Easy)");
        jmi_file[4]=new JMenuItem("1p vs Kani (CPU Medium)");
        jmi_file[5]=new JMenuItem("1p vs Rani (CPU Hard)");
        jmi_file[6]=new JMenuItem("CPU vs CPU (Demo Play)");
        jmi_file[7]=new JMenuItem("Exit");
        
        jm[0].add(jmi_file[0]);
        jm[0].add(jmi_file[1]);
        jm[0].add(new JSeparator());
        jm[0].add(jmi_file[2]);
        jm[0].add(jmi_file[3]);
        jm[0].add(jmi_file[4]);
        jm[0].add(jmi_file[5]);
        jm[0].add(jmi_file[6]);
        jm[0].add(new JSeparator());
        jm[0].add(jmi_file[7]);

        jmi_about[0]=new JMenuItem("Electronic version");
        jmi_about[1]=new JMenuItem("Tutorial");
        jmi_about[2]=new JMenuItem("External Links");
        jmi_about[3]=new JMenuItem("Pictures");
        
        jm[1].add(jmi_about[0]);
        jm[1].add(new JSeparator());
        jm[1].add(jmi_about[1]);
        jm[1].add(jmi_about[2]);
        jm[1].add(jmi_about[3]);
        
        jmi_help[0]=new JMenuItem("Version details");
        jmi_help[1]=new JMenuItem("About author");
        jmi_help[2]=new JMenuItem("Report issue");
        
        jm[2].add(jmi_help[0]);
        jm[2].add(new JSeparator());
        jm[2].add(jmi_help[1]);
        jm[2].add(jmi_help[2]);
        
        //Adding menu components
        for (int i=0;i<3;i++){
            jmb.add(jm[i]);
        }
        
        //Adding Menu to Layout
        mainPanel.add(jmb, bl.NORTH);
        
        playerALabel[0]=new JLabel("Player A Score");
        playerALabel[1]=new JLabel("0");
        
        playerBLabel[0]=new JLabel("Player B Score");
        playerBLabel[1]=new JLabel("0");
        
        
        
        //Up or down Button style
        playerAButton=new JButton("Play");
        
        playerBButton=new JButton("Play");
        
        //Extra buttons
        JButton rst=new JButton("Reset"), save=new JButton("Save Game");
        
        //Player A Control
        
        gbc.weightx=1;
        gbc.weighty=1;
        
        gbc.gridx=1;
        gbc.gridy=5;
        gbc.gridwidth=4;
        gamePanel.add(header, gbc);
        
        gbc.gridwidth=1;
        gbc.gridx=0;
        gbc.gridy=6;
        gamePanel.add(playerALabel[0], gbc);
        
        gbc.gridx=0;
        gbc.gridy=7;
        gamePanel.add(playerALabel[1], gbc);
        
        gbc.gridx=0;
        gbc.gridy=8;
        gamePanel.add(playerAButton, gbc);
        
        for (int i=0;i<7;i++){
            gbc.gridx=i;
            gbc.gridy=1;
            gamePanel.add(handPointer[i], gbc);
            
            gbc.gridx=i;
            gbc.gridy=2;
            gamePanel.add(jtb[i], gbc);
        }
        
        //Player B Control
        
        gbc.gridx=6;
        gbc.gridy=6;
        gamePanel.add(playerBLabel[0], gbc);
        
        gbc.gridx=6;
        gbc.gridy=7;
        gamePanel.add(playerBLabel[1], gbc);
        
        gbc.gridx=6;
        gbc.gridy=8;
        gamePanel.add(playerBButton, gbc);
        
        for (int i=0;i<7;i++){
            gbc.gridx=i;
            gbc.gridy=3;
            gamePanel.add(jtb[13-i], gbc);
            
            gbc.gridx=i;
            gbc.gridy=4;
            gamePanel.add(handPointer[13-i], gbc);
        }
        
        gbc.gridx=1;
        gbc.gridy=8;
        gamePanel.add(rst, gbc);
        
        gbc.gridx=2;
        gbc.gridy=8;
        gamePanel.add(save, gbc);
        
        //Adding game panel to main panel
        mainPanel.add(cardPanel, bl.CENTER);
        cdl.show(cardPanel, "GamePanel");
        
        footer.setHorizontalAlignment(footer.CENTER);
        
        //Footer
        mainPanel.add(footer, bl.SOUTH);
        
        //Adding main panel to Frame
        jf.add(mainPanel);
        
        ButtonControls.playerAControl(playerAButton);
        ButtonControls.playerBControl(playerBButton);
        
        ButtonControls.fileMenu(jmi_file);
        ButtonControls.aboutMenu(jmi_about);
        ButtonControls.helpMenu(jmi_help);
        ButtonControls.resetBtn(rst);
        
        jf.setSize(1000, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rules.initBtn(soliCount);
        Rules.initHand();
        //initTimer();
        showPanel();
    }

}