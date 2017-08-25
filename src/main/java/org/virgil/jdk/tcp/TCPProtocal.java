package org.virgil.jdk.tcp;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * Created by Virgil on 2017/8/22.
 */
public interface TCPProtocal {
    void handleAccept(SelectionKey key) throws IOException;

    void handleRead(SelectionKey key) throws IOException;

    void handleWrite(SelectionKey key) throws IOException;
}
