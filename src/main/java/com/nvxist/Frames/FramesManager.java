package com.nvxist.Frames;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class FramesManager {

    static Scanner sc = new Scanner(System.in);
    static volatile boolean running = true;

    public static void startRecordingFrames(OutputStream os) {
        try {
            Robot robot = new Robot();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRec = new Rectangle(dimension);

            // Input thread to stop recording
            Thread inputThread = new Thread(() -> {
                while (running) {
                    String input = sc.nextLine();
                    if ("yes".equalsIgnoreCase(input.trim())) {
                        System.out.println("Stopping recording...");
                        running = false;
                        break;
                    }
                }
            });
            inputThread.start();

            // 30 Frames Per Second = 1 frame every ~33.3 milliseconds
            long targetTimePerFrame = 1000 / 30;

            while (running) {
                long startTime = System.currentTimeMillis();

                // Capture and write frame
                BufferedImage frame = robot.createScreenCapture(screenRec);
                ImageIO.write(frame, "jpg", os);

                // Calculate how long it took to capture and encode the frame
                long processTime = System.currentTimeMillis() - startTime;

                // Only sleep for the remaining time to maintain a stable framerate
                long sleepTime = targetTimePerFrame - processTime;

                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            }

        } catch (AWTException | IOException | InterruptedException e) {
            throw new RuntimeException("Error during screen recording", e);
        }
    }
}