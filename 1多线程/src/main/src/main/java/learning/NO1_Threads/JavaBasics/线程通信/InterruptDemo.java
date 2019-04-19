package learning.NO1_Threads.JavaBasics.线程通信;

/**
 * @Author by xiongYiMing on 2019/3/18 17:03
 */
public class InterruptDemo {
    public static void main(String[] args) throws Exception{
        StopThread thread = new StopThread();
        thread.start();
        // 休眠1秒，确保i变量自增成功
        Thread.sleep(1000);
        // 暂停线程
        //thread.stop(); // 错误的终止
         thread.interrupt(); // 正确终止
        Thread.sleep(1000);
        // 此时 第二个interrupt 打断了第二个sleep, 所以不用等待10秒钟,才返回所有结果
         thread.interrupt(); // 再次interrupt
        while (thread.isAlive()) {
            // 确保线程已经终止
        } // 输出结果
        thread.print();

    }
}
