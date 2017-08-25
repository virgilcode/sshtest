package org.virgil.jdk.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Created by Virgil on 2017/8/22.
 */
public class TcpServerSelector {
    //缓冲区长度
    private static final int BUFFSIZE = 256;
    //
    private static final int TIMEOUT = 3000;


    public static void main(String[] args) throws IOException {
//        if (args.length < 1) {
//            throw new IllegalArgumentException("port error");
//        }
        Selector selector = Selector.open();
        ServerSocketChannel listnChannel = ServerSocketChannel.open();
        listnChannel.socket().bind(new InetSocketAddress("127.0.0.1", 8090));
        listnChannel.configureBlocking(false);
        listnChannel.register(selector, SelectionKey.OP_ACCEPT);
        TCPProtocal protocal = new SelectorProtocal(BUFFSIZE);
        while (true) {
            if (selector.select(TIMEOUT) == 0) {
                System.out.println("waiting do something");
                continue;
            }
            System.out.println("client connect");
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isValid() && key.isReadable()) {
                    protocal.handleRead(key);
                }
                if (key.isValid() && key.isAcceptable()) {
                    protocal.handleAccept(key);
                }
                if (key.isValid() && key.isWritable()) {
                    protocal.handleWrite(key);
                }
                iterator.remove();
            }
        }
    }
}
