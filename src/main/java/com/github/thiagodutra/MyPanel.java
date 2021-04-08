/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.thiagodutra;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author tdutra
 */
public class MyPanel extends JPanel {

    private BufferedImage buffer = new BufferedImage(2000, 2000, BufferedImage.TYPE_INT_RGB);
    
    public MyPanel(){
        clear();
        repaint();
    }
    
    @Override
    public void paint (Graphics graphic) {
        Graphics2D graphics = (Graphics2D) graphic;
        graphics.drawImage(buffer, null, 0, 0);
    }
    
    public void clear(){
        for( int y = 0; y < buffer.getHeight(); y++){
            for( int x = 0; x < buffer.getWidth(); x++){
                buffer.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
    }
    
    public void update(){
        repaint();
    }
    
    public BufferedImage getBuffer(){
        return this.buffer;
    }
}