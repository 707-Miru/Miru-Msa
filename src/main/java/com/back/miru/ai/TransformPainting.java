package com.back.miru.ai;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TransformPainting {

    public static void main(String[] args) throws Exception {
        transform();
    }

    public static void transform() throws Exception {
        String root = "C:/Users/SSAFY/Desktop/PyTorch-Multi-Style-Transfer/experiments/";
        String[] command = new String[12];
        command[0] = "python";
        command[1] = root + "main.py";
        command[2] = "eval";
        command[3] = "--content-image";
        command[4] = root + "images/content/venice-boat.jpg";
        command[5] = "--style-image";
        command[6] = root + "images/21styles/candy.jpg";
        command[7] = "--model";
        command[8] = root + "models/21styles.model";
        command[9] = "--content-size";
        command[10] = "1024";
        command[11] = "--cuda=0";

        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            commandLine.addArgument(command[i]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(pumpStreamHandler);
        int result = executor.execute(commandLine);
        System.out.println("result: " + result);
        System.out.println("output: " + outputStream);
    }
}
