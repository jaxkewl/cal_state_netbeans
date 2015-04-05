// Fig. 6.34: LogoAnimator.java
// LogoAnimator is a JavaBean containing an animated logo.
package java2hw8;

// Java core packages
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

// Java extension packages
import javax.swing.*;

public class Java2HW8 extends JPanel
        implements ActionListener, Serializable {

    protected ImageIcon images[];
    protected int totalImages = 30, currentImage;
    protected Timer animationTimer;
    private int delay;

    // load images and start animation
    public Java2HW8() {
        images = new ImageIcon[totalImages];
        delay = 50;
        URL url;
        setBackground(Color.GRAY);
        // load animation frames
        for (int i = 0; i < images.length; ++i) {
            url = Java2HW8.class.getResource(
                    "images/deitel" + i + ".png");
            images[i] = new ImageIcon(url);
        }

        startAnimation();
    }

    // render one frame of the animation
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw current animation frame
        images[currentImage].paintIcon(this, g, 0, 0);
        currentImage = (currentImage + 1) % totalImages;
    }

    // start Timer that drives animation
    public void startAnimation() {
        // if animationTimer is null, restart animation
        if (animationTimer == null) {
            currentImage = 0;
            animationTimer = new Timer(delay, this);
            animationTimer.start();
        } else // continue from last image displayed
        if (!animationTimer.isRunning()) {
            animationTimer.restart();
        }
    }

    // repaint when Timer event occurs
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    // stop Timer that drives animation
    public void stopAnimation() {
        animationTimer.stop();
    }

    public int getDelay() {
        return delay;
    }

    //increase the delay time between each animation picture
    public void increaseDelay() {
        delay += 5;

        //set the new delay
        animationTimer.setDelay(delay);
    }

    //decrease the delay between each animation picture showing
    public void decreaseDelay() {
        if (delay <= 5) {
            //dont go any lower than 1
            delay = 1;
        } else {
            delay -= 5;
        }

        //set the new delay
        animationTimer.setDelay(delay);
    }

    // get animation preferred width and height
    public Dimension getPreferredSize() {
        return new Dimension(160, 80);
    }

    // get animation minimum width and height
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    // execute bean as standalone application
    public static void main(String args[]) {
        // create new LogoAnimator
        Java2HW8 animation = new Java2HW8();

        // create new JFrame with title "Animation test" 
        JFrame application = new JFrame("Animator test");
        application.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        // add LogoAnimator to JFrame
        application.getContentPane().add(animation,
                BorderLayout.CENTER);

        // set the window size and validate layout
        application.pack();
        application.setVisible(true);
    }

}  // end class LogoAnimator

/**
 * *************************************************************
 * (C) Copyright 2002 by Deitel & Associates, Inc. and * Prentice Hall. All
 * Rights Reserved. * * DISCLAIMER: The authors and publisher of this book have
 * * used their best efforts in preparing the book. These * efforts include the
 * development, research, and testing of * the theories and programs to
 * determine their effectiveness. * The authors and publisher make no warranty
 * of any kind, * expressed or implied, with regard to these programs or to *
 * the documentation contained in these books. The authors * and publisher shall
 * not be liable in any event for * incidental or consequential damages in
 * connection with, or * arising out of, the furnishing, performance, or use of
 * * these programs. *
 * *************************************************************
 */
