package com.company;

import javax.swing.JFrame;
import java.awt.*;

public class Main {

// Build -> Build Artifacts -> Build
     public static void main(String[] args) {

         JFrame frame = new JFrame("File Renamer");
         frame.setContentPane(new Layout().getPanel());
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.pack();
         frame.setMinimumSize(new Dimension(520, 400));
         frame.setVisible(true);
         frame.setLocationRelativeTo(null);

    }


}
