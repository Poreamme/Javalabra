
package UI;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import java.awt.*;
import java.awt.Event.*;

public class MainView extends JFrame{
    private JButton startButton = new JButton("Start");
    private JButton profilesButton = new JButton("Profiles");
    private JButton exitButton = new JButton("Exit");
    
    private JTextArea textArea = new JTextArea(10, 10);
    
    public MainView(){
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        ParallelGroup buttonLineX = layout.createParallelGroup();
        buttonLineX.addComponent(startButton);
        buttonLineX.addComponent(profilesButton);
        buttonLineX.addComponent(exitButton);
        
        SequentialGroup mainGroupX = layout.createSequentialGroup();
        mainGroupX.addGroup(buttonLineX);
        mainGroupX.addComponent(textArea);
        
        ParallelGroup mainViewX = layout.createParallelGroup();
        mainViewX.addGroup(mainGroupX);
        
        layout.setHorizontalGroup(mainGroupX);
        
        
        SequentialGroup buttonLineY = layout.createSequentialGroup();
        buttonLineY.addComponent(startButton);
        buttonLineY.addComponent(profilesButton);
        buttonLineY.addComponent(exitButton);
        
        ParallelGroup mainGroupY = layout.createParallelGroup();
        mainGroupY.addGroup(buttonLineY);
        mainGroupY.addComponent(textArea);
        
        SequentialGroup mainViewY = layout.createSequentialGroup();
        mainViewY.addGroup(mainGroupY);
        
        layout.setVerticalGroup(mainViewY);
        
        this.pack();
        this.setSize(450, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
    }
    
    public static void main(String[] args){
        MainView afweg = new MainView();
        afweg.setVisible(true);
    }
    
}
