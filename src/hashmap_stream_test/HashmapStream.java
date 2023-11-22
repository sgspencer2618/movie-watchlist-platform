package hashmap_stream_test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class HashmapStream {
    public static void main(String[] args) {
//        HashMap<Integer, String> m1 = new HashMap<>();
//        m1.put(1, "A");
//        m1.put(2, "B");
//        m1.put(3, "C");
//
//        // Serialize the HashMap to a file
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("hashmap.ser"))){
//            oos.writeObject(m1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        HashMap<Integer, String> m2 = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hashmap.ser"))){
            m2 = (HashMap<Integer, String>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, String> entry : m2.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
