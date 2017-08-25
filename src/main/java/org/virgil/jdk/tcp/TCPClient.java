package org.virgil.jdk.tcp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Virgil on 2017/8/22.
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        String server = "127.0.0.1";
        byte[] argument = "fuck pg13".getBytes();
        int serverPort = 8090;
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        try {
            if (!socketChannel.connect(new InetSocketAddress(server, 8090))) {
                while (!socketChannel.finishConnect()) {
                    //未完成连接时 可以做其他
                    System.out.println("unfinished connect ...");
                }
            }
            System.out.println("");
            ByteBuffer writeBuf = ByteBuffer.wrap(argument);
            ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
            int totalBytesRe = 0;
            int bytesRe;
            while (totalBytesRe < argument.length) {
                if (writeBuf.hasRemaining()) {
                    socketChannel.write(writeBuf);
                }
                if ((bytesRe = socketChannel.read(readBuf)) == -1) {
                    throw new SocketException("Connection closed prematurely");
                }
                totalBytesRe += bytesRe;
                System.out.printf("... ");
            }
            System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRe));
            socketChannel.close();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
