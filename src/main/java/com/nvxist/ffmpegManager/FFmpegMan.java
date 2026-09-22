package com.nvxist.ffmpegManager;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FFmpegMan {
    static OutputStream os;
    static Process process;

    public static void startEncoding() throws IOException, InterruptedException {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-y",
                "-f", "image2pipe",
                "-framerate", "30",
                "-i", "-",
                "-c:v", "h264_qsv",       // Intel QuickSync Hardware Acceleration
                "-preset", "fast",
                "-pix_fmt", "yuv420p",
                timestamp + ".mp4"
        );

        pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        process = pb.start();
        os = process.getOutputStream();
    }

    public static void stopEncoding() throws IOException, InterruptedException {
        if (os != null) {
            os.flush();
            os.close(); // Closing the stream tells FFmpeg that the input is done
        }
        int exitCode = process.waitFor();
        System.out.println("FFmpeg exited with code: " + exitCode);
    }

    public static OutputStream getOs() {
        return os;
    }
}