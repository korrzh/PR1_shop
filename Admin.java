package PR1;

import java.util.concurrent.Semaphore;

public class Admin {
    static final Semaphore availableProducts = new Semaphore(2);
    static int numProduct = 2;

    public  void addProduct () {
        numProduct += 1;
        availableProducts.release();
        System.err.println("Адміністратор додав товар");
    }

}
