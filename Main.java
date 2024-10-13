package PR1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static boolean isAvailableHours = true;

    static List<Thread> list = new ArrayList<Thread>();

    public static synchronized boolean isOpen () {
        return isAvailableHours;
    }

    public static synchronized void closeShop () {
        isAvailableHours = false;
        System.err.println("=============Магазин закрили================");
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable shop = () -> {
            int customers = 0;
            while (isOpen()) {
                Thread thr = new Thread(new Customer(), String.valueOf(customers));
                thr.start();
                list.add(thr);
//                new Thread(new Customer(), String.valueOf(customers)).start();
                customers++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };


        Thread shopThread = new Thread(shop, "Магазин");
        shopThread.start();
        Thread.sleep(6000);
        closeShop();

        shopThread.join();

        for(Thread thr: list){
            thr.join();
        }

    }

}
