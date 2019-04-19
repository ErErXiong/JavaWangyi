package learning.NO2_NIO.net.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author by xiongYiMing on 2019/4/2 16:16
 */
public class BIOService {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);  // 服务器为ServerSocket,需要参数:端口号
        while (!serverSocket.isClosed()){
            System.out.println("启动服务端");
            Socket socket = serverSocket.accept();
            System.out.println("收到连接:" + socket.toString());
            InputStream inputStream = socket.getInputStream();
            // 新建InputStreamReader时,需要设置字符格式
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                // 当读取数据为null时,退出
                if (msg.length() == 0) {
                    break;
                }
                System.out.println(msg);
            }
            socket.close();
        }
        serverSocket.close();
    }
}
