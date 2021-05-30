package mc322.lab07;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;

public class Main{
      public static void main(String[] argv) throws Exception {
            JFrame f = new JFrame("Dungeon & Wumpus");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setSize(1024, 720);
            f.setLocationRelativeTo(null);

            Tabuleiro tabuleiro = new Tabuleiro();
            f.add(tabuleiro);

            f.setVisible(true);
      }
}



