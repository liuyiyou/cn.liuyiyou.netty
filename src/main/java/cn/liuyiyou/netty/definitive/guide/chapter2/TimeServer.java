package cn.liuyiyou.netty.definitive.guide.chapter2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 代码清单2.1：同步阻塞I/O 的TimeServer
 *
 * @author: liuyiyou.cn
 * @date: 2019/1/29
 * @version: V1.0
 */
public class TimeServer {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
