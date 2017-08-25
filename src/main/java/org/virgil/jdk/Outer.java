package org.virgil.jdk;

import java.io.*;

/**
 * Created by Virgil on 2017/8/25.
 * 序列化方式实现的深拷贝
 */
public class Outer implements Serializable {
    public Inner inner;

    @Override
    protected Outer clone(){
        Outer outer = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            outer = (Outer) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return outer;
    }

    public static void main(String[] args){
        Inner inner=new Inner("vvvvv");
        Outer out1=new Outer();
        out1.inner=inner;
        Outer out2=out1.clone();
        System.out.println(out1.inner.toString());
        System.out.println(out2.inner.toString());
        out2.inner.name="pg";
        System.out.println(out1.inner.toString());
        System.out.println(out2.inner.toString());

    }
}
