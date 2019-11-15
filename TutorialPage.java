/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pallanguzhi;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
/**
 *
 * @author WeaponMaster
 */

public class TutorialPage {
    JScrollPane jsp;
    public JPanel jp;
    
    TutorialPage(){
        ImageIcon icon=new ImageIcon("C:\\Users\\WeaponMaster\\Downloads\\pallanguzhi.jpg");
        jp=new JPanel();
        JLabel gameImg=new JLabel(icon);
        String str="<html><h3>Pallanguzhi</h3>"
                + "Pallanguzhi is an ancient Mancla game (game played with property)<br />"
                + "Rules"
                + "<ol>"
                + "<li>There are 6 beads (or soli) in each cups</li>"
                + "<li>Every time the player needs to choose a cup and take all the beads in his hands</li>"
                + "<li>Every time the player needs to choose a cup and take all the beads in his hands</li>"
                + "</ol>"
                + "</html>";
        JLabel jlab=new JLabel(str);
        jlab.setHorizontalAlignment(SwingConstants.CENTER);
        jsp=new JScrollPane();
        jp.setLayout(new BorderLayout());
        jp.add(gameImg, BorderLayout.NORTH);
        jp.add(jlab, BorderLayout.CENTER);
    }
    
}
