package PR1;

public class Customer implements Runnable{


    @Override
    public void run() {
        try {
            Admin admin = new Admin();
            System.err.printf("Покупець з потоку %s зайшов на сайт і вибирає товар \n", Thread.currentThread().getName());
            Thread.sleep(20);

            if (Admin.numProduct != 0) {
                System.err.printf("Покупець з потоку %s замовив товар \n", Thread.currentThread().getName());
                Thread.sleep(20);
                Admin.availableProducts.acquire();
                Admin.numProduct--;
            }
            else {
                System.err.printf("Покупець з потоку %s не зміг замовити товар, бо він відсутній \n", Thread.currentThread().getName());
                Thread.sleep(20);
                admin.addProduct();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
