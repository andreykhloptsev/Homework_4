package ru.geekbrains.level_3.hw_4;



public class ABCApp {

    public static void main(String[] args) {
        ABC obj = new ABC();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (obj.getCounter()<5) {
                    obj.A();
                }
            }
        });

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (obj.getCounter()<5) {
                    obj.B();
                }
                return;
            }
        });

        Thread th3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (obj.getCounter()<5) {
                    obj.C();
                }
                return;
            }
        });

        th1.start();
        th2.start();
        th3.start();
    }



}

class ABC{
     private byte counter =0;
     public enum Order {A,B,C};
     Order order = Order.A;

    public byte getCounter() {
        return counter;
    }
    public synchronized void A() {
        if (order==Order.A) {
            System.out.print('A');
            order= Order.B;
            notifyAll();
        }try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public synchronized void B() {
        if (order==Order.B) {
            System.out.print('B');
            order= Order.C;
            notifyAll();
        }try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void C() {
        if (order==Order.C) {
            System.out.print('C');
            counter++;
            order= Order.A;
            notifyAll();
        }try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
