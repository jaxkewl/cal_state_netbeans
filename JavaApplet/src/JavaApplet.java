/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;



//package javaapplet;

/**
 *
 * @author martin
 */
public class JavaApplet extends JApplet {

public void paint(Graphics g) {
    g.setColor(Color.GREEN);
    g.drawRect(20,20,100,70);
    g.setColor(Color.MAGENTA);
    g.drawString("this is my applet",30,50);

}
    
public static void main(String[] args) {
    
    
}
}
