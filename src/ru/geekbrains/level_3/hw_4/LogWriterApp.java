package ru.geekbrains.level_3.hw_4;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


//совершенно не понял смысл задания и выполнил так как сложилось у меня в голове
//три потока, пишут логи, поэтому порядок не важен
//единственное что нужно - синхронизировать запись
public class LogWriterApp {
    public static byte counter=0;

    public static void main(String[] args) {
        File file = new File("log.txt");
        System.out.println(file.delete());

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter<10){
                    logWriter("LogWriter1");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (counter==10) System.out.println("end");
            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter<10) {
                    logWriter("LogWriter2");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (counter==10) System.out.println("end");
            }
        });
        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (counter<10) {
                    logWriter("LogWriter3");
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (counter==10) System.out.println("end");
            }
        });

                th1.start();
                th2.start();
                th3.start();

    }

    public static synchronized void logWriter(String text){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("log.txt",true))) {
            for (int i = 0; i <10 ; i++) {
                dos.writeUTF(text);
            }
            counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
