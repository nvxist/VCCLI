package com.nvxist;

import com.nvxist.Frames.FramesManager;
import com.nvxist.ffmpegManager.FFmpegMan;

import java.io.IOException;
import java.io.OutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FFmpegMan.startEncoding();
        OutputStream os = FFmpegMan.getOs();
        FramesManager.startRecordingFrames(os);
        System.out.println("I'm making your video now");
        FFmpegMan.stopEncoding();
        System.out.println("Done!");
    }
}