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
        transform2();
    }

    public static void transform1() throws Exception {
        String command = "C:\\Users\\SSAFY\\AppData\\Local\\Programs\\Python\\Python38\\python.exe";  // 명령어
        String arg1 = "C:\\Users\\SSAFY\\Desktop\\PyTorch-Multi-Style-Transfer\\experiments\\main.py eval --content-image images/content/venice-boat.jpg --style-image images/21styles/candy.jpg --model models/21styles.model --content-size 1024 --cuda=0"; // 인자
//        String arg1 = "C:\\Users\\SSAFY\\Desktop\\PyTorch-Multi-Style-Transfer\\experiments\\test.py";
        ProcessBuilder builder = new ProcessBuilder(command, arg1);
        Process process = builder.start();
        InputStream input = process.getInputStream();
        int exitVal = process.waitFor();  // 자식 프로세스가 종료될 때까지 기다림
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr")); // 서브 프로세스가 출력하는 내용을 받기 위해
        while ((line = br.readLine()) != null) {
            System.out.println(">>>  " + line); // 표준출력에 쓴다
        }
        if(exitVal != 0) {
            // 비정상 종료
            System.out.println("서브 프로세스가 비정상 종료되었다.");
        }
    }

    public static void transform2() throws Exception {
        System.out.println("Python Call");
        String[] command = new String[12];
        command[0] = "python";
//        command[1] = "C:/Users/SSAFY/AppData/Local/Programs/Python/Python38/python.exe";
//      main.py eval --content-image images/content/venice-boat.jpg --style-image images/21styles/candy.jpg --model models/21styles.model --content-size 1024 --cuda=0"
        command[1] = "C:/Users/SSAFY/Desktop/PyTorch-Multi-Style-Transfer/experiments/test.py"; // 인자
//        command[2] = "eval";
//        command[3] = "--content-image";
//        command[4] = "images/content/venice-boat.jpg";
//        command[5] = "--style-image";
//        command[6] = "images/21styles/candy.jpg";
//        command[7] = "--model";
//        command[8] = "models/21styles.model";
//        command[9] = "--content-size";
//        command[10] = "1024";
//        command[11] = "--cuda=0";

        CommandLine commandLine = CommandLine.parse(command[0]);
        for (int i = 1, n = command.length; i < n; i++) {
            commandLine.addArgument(command[i]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.out.println("outputStream = " + outputStream);
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        System.out.println("pumpStreamHandler = " + pumpStreamHandler);
        DefaultExecutor executor = new DefaultExecutor();
        System.out.println("executor = " + executor);
        System.out.println("outputStream = " + outputStream);

        executor.setStreamHandler(pumpStreamHandler);
        int result = executor.execute(commandLine);
        System.out.println("result: " + result);
        System.out.println("output: " + outputStream.toString());
    }
}
