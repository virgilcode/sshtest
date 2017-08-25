package org.virgil.jdk.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Virgil on 2017/8/22.
 */
public class SelectorProtocal implements TCPProtocal {
    private int buffsize;

    public SelectorProtocal(int buffsize) {
        this.buffsize = buffsize;
    }

    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(buffsize));
    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        //获取信道关联的附件
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        int read = channel.read(byteBuffer);
        if (read != -1) {
            //缓存区读入了数据 信道感兴趣设置为可读可写
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } else {
            channel.close();
        }

    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(buf);
        if (!buf.hasRemaining()) {
            key.interestOps(SelectionKey.OP_READ);
        }
        buf.compact();

    }
}
