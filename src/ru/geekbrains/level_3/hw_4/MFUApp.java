package ru.geekbrains.level_3.hw_4;


public class MFUApp {

    public static void main(String[] args) {
        MFU mfu = new MFU();
        Thread th1= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mfu.print();
                    System.out.println("Первый поток");
                    mfu.scan();
                    mfu.print();
                    System.out.println("Первый поток");
                }
            }
        });
        Thread th2= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mfu.print();
                    System.out.println("Второй поток");
                }
            }
        });

        th1.start();
        th2.start();

    }



}


class MFU{
   private int printpage=0;
   private int scanpage=0;
   private Object printMonitor = new Object();
   private Object scanMonitor = new Object();

    public void scan(){
        synchronized (scanMonitor) {
            scanpage++;
            System.out.println("Отсканировано " + scanpage + " страниц");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void print(){
        synchronized (printMonitor) {
            printpage++;
            System.out.println("Напечатано " + printpage + " страниц");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}