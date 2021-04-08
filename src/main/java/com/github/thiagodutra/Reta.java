/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.thiagodutra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author tdutra
 */
public class Reta {

    int x1, y1, x2, y2;
    double u1, u2;
    private static final Logger logger = LoggerFactory.getLogger(Reta.class);

    public Reta() {
    }

    public Reta(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return this.x1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getY1() {
        return this.y1;
    }

    public int getY2() {
        return this.y2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void dda(int x1, int y1, int x2, int y2, BufferedImage buffer, int color) {
        logger.info(String.format("DDA algorithm has started with values x1:%d y1:%d x2:%d y2:%d", x1,y1,x2,y2));
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        logger.info(String.format("deltaX=%d : deltaY=%d", deltaX,deltaY));
        double x = x1, y = y1;

        buffer.setRGB((int) Math.round(x), (int) Math.round(y), color);

        int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));
        double xIncr = ((double) deltaX) / ((double) steps);
        double yIncr = ((double) deltaY) / ((double) steps);
        logger.info(String.format("xIncr=%f : yIncr=%f", xIncr,yIncr));
        for (int k = 1; k <= steps; k++) {
            x += xIncr;
            y += yIncr;
            buffer.setRGB((int) Math.round(x), (int) Math.round(y), color);
        }
    }

    public void bresenham(int x1, int y1, int x2, int y2, BufferedImage buffer) {
        logger.info(String.format("Bresenham algorithm has started with values x1:%d y1:%d x2:%d y2:%d", x1,y1,x2,y2));
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        int x = x1;
        int y = y1;
        logger.info(String.format("deltaX=%d : deltaY=%d", deltaX,deltaY));
        int xIncr, yIncr, p, c1, c2;

        //colore(x, y, cor);
        buffer.setRGB(x, y, Color.BLACK.getRGB());

        if (deltaX < 0) {
            deltaX = -deltaX;
            xIncr = -1;
        } else {
            xIncr = 1;
        }

        if (deltaY < 0) {
            deltaY = -deltaY;
            yIncr = -1;
        } else {
            yIncr = 1;
        }

        if (deltaX > deltaY) {
            logger.info(String.format("deltaX=%d > deltaY=%d", deltaX,deltaY));
            p = 2 * deltaY - deltaX;
            c1 = 2 * deltaY;
            c2 = 2 * (deltaY - deltaX);

            for (int k = 1; k <= deltaX; k++) {
                x += xIncr;
                if (p < 0) {
                    p += c1;
                } else {
                    p += c2;
                    y += yIncr;
                }

                //colore(x, y, cor);
                buffer.setRGB(x, y, Color.BLACK.getRGB());
            }
        }
        else {
            logger.info(String.format("deltaX=%d < deltaY=%d", deltaX,deltaY));
            p = 2 * deltaX - deltaY;
            c1 = 2 * deltaX;
            c2 = 2 * (deltaX - deltaY);

            for (int k = 1; k <= deltaY; k++) {
                y += yIncr;
                if (p < 0) {
                    p += c1;
                } else {
                    p += c2;
                    x += xIncr;
                }
                //colore(x, y, cor);
                buffer.setRGB(x, y, Color.BLACK.getRGB());
            }
        }
    }
    public static void boundaryFill4(int x, int y, int corBorda, int corNova, BufferedImage buffer) {
        int corAtual = buffer.getRGB(x, y);
        if (corAtual != corBorda && corAtual != corNova) {
            buffer.setRGB(x, y, corNova);
            boundaryFill4(x + 1, y, corBorda, corNova, buffer);
            boundaryFill4(x - 1, y, corBorda, corNova, buffer);
            boundaryFill4(x, y + 1, corBorda, corNova, buffer);
            boundaryFill4(x, y - 1, corBorda, corNova, buffer);
        }

    }

    public static void floodFill4(int x, int y, int oldColor, int corNova, BufferedImage buffer) {

        int actualColor = buffer.getRGB(x, y);

        if (actualColor == oldColor) {
            buffer.setRGB(x, y, corNova);
            floodFill4(x + 1, y, oldColor, corNova, buffer);
            floodFill4(x - 1, y, oldColor, corNova, buffer);
            floodFill4(x, y + 1, oldColor, corNova, buffer);
            floodFill4(x, y - 1, oldColor, corNova, buffer);
        }
    }
}