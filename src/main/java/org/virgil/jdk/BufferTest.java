package org.virgil.jdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Virgil on 2017/8/21.
 */
public class BufferTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("D:/test/test.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(128);
        int read = inChannel.read(buf);
        System.out.println(read);
        while (read != -1) {
            //ready for read
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            read = inChannel.read(buf);
        }
        aFile.close();
    }


}
