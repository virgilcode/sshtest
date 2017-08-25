package org.virgil.jdk;

import java.io.Serializable;

/**
 * Created by Virgil on 2017/8/25.
 */
public class Inner implements Serializable {
    public String name = "";

    public Inner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
