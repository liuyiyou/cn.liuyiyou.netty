package cn.liuyiyou.netty.definitive.guide.chapter2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 代码清单2.2：伪异步I/O 的TimeServer
 *
 * @author: liuyiyou.cn
 * @date: 2019/1/29
 * @version: V1.0
 */
public class TimeServer2 {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        int port = 8080;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = serverSocket.accept();
                singleExecutor.excute(new TimeServerHandler(socket));
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
