package serializable;

import org.junit.Test;

import java.io.*;

public class se1 implements Serializable {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "se1{" +
                "name='" + name + '\'' +
                '}';
    }

    @Test
    public void run() {

        try {
            FileOutputStream out = new FileOutputStream(new File("/out.txt"));
            ObjectOutputStream ob_out = new ObjectOutputStream(out);
            se1 se = new se1();
            se.setName("test kd");
            ob_out.writeObject(se);
            ob_out.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }

    @Test
    public void run2() {

        try {
            FileInputStream in = new FileInputStream(new File("/out.txt"));
            ObjectInputStream ob_in = new ObjectInputStream(in);
            se1 se = (se1) ob_in.readObject();
            System.out.println(se.toString());
            ob_in.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }

    }

}
