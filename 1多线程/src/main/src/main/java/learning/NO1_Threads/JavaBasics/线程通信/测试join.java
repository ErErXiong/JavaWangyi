package learning.NO1_Threads.JavaBasics.线程通信;

/**
 * @Author by xiongYiMing on 2019/3/18 14:37
 */
public class 测试join {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println("i");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    System.out.println("****");
                }
            }
        });
        try {
            thread.start();
            thread1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
