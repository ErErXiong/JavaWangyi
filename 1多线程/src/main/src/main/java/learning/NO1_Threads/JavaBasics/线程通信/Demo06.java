package learning.NO1_Threads.JavaBasics.线程通信;

/**  suspend/resume
 * @Author by xiongYiMing on 2019/3/5 14:57
 */
public class Demo06 {
    static Object leehomTicket;
    public static void main(String[] args) throws InterruptedException {
        Demo06 demo06 = new Demo06();
        demo06.suspengResume();
    }

    /** 正常使用过期的 suspend/resume
       * @param
      @Return: void
      @Author: xiongYiMing , 2019-03-05 04:57:27
      @Modify:
    */
    public void suspengResume() throws InterruptedException {
        Thread consume = new Thread(()->{
            System.out.println("1. 检票");
            while(leehomTicket == null){
                // 获取当前线程
                Thread.currentThread().suspend();
                System.out.println("3. 成功进入场馆！");
            }
            System.out.println("4. 观看演出");
        });
        consume.start();
        Thread.sleep(1000);
        // 出售ticket
        leehomTicket = new Object();
        System.out.println("2. 出售ticket");
        consume.resume();
        //
    }
    public  void suspengResumeLock() throws InterruptedException {
        Thread consume = new Thread(()->{
            System.out.println("1. 检票");
            while(leehomTicket == null){
                // 加锁
                synchronized(this){

                }
                // 获取当前线程
                Thread.currentThread().suspend();
                System.out.println("3. 成功进入场馆！");
            }
            System.out.println("4. 观看演出");
        });
        consume.start();
        Thread.sleep(1000);
        // 出售ticket
        leehomTicket = new Object();
        System.out.println("2. 出售ticket");
        consume.resume();
        //
    }


}
