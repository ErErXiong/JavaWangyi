package learning.NO2_NIO.net.practice;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @Author by xiongYiMing on 2019/4/2 16:17
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8080);
        // 使用socket创建outputStream
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("请输入");
        // 使用scanner输入数据
        Scanner scanner = new Scanner(System.in);
        String mag = scanner.nextLine();
        // 写入格式化的字节流
        outputStream.write(mag.getBytes(Charset.forName("UTF-8")));
        scanner.close();
        socket.close();
    }
}
