package com.nvxist.Frames;

import java.awt.*;
import java.awt.image.BufferedImage;

public class getFrames {
    Robot robot;

    {
        try {
            /*
            * Get image Frames in buffer
            * */
            robot = new Robot();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRec = new Rectangle(dimension);
            BufferedImage image = robot.createScreenCapture(screenRec);

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
