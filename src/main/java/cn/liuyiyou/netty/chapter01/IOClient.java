package cn.liuyiyou.netty.chapter01;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author: liuyiyou@yanglaoban.com
 * @date: 2018/9/26
 * @version: V1.0
 * @Copyright: 2018 yanglaoban.com Inc. All rights reserved.
 */
public class IOClient {

    public static void main(String[] args) {
       new Thread(new ServerThread("server")).start();
    }
}


class ServerThread implements Runnable{
    private String name;

    public ServerThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            while (true) {
                socket.getOutputStream().write((new Date() + ":hello world").getBytes());
                Thread.sleep(2000L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
