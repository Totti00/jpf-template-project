package es1;

import java.util.ArrayList;
import java.util.Map;

public class ModelCheckingData {
    private static ModelCheckingData instance;

    public static ModelCheckingData getInstance() {
        synchronized (ModelCheckingData.class) {
            if (instance != null) {
                throw new IllegalStateException("Already initialized.");
            }
            return instance;
        }
    }

    private ModelCheckingData() {}

    public File getData() {
        File[] files = new File[] {
                new FileImpl("a.java"),
                new FileImpl("b.java"),
                new FileImpl("c.java")
        };
        return new DirectoryImpl(files);
    }

    public void checkResults(ArrayList<Map.Entry<String, Integer>> list) {
        Map.Entry<String, Integer> e0 = list.get(0);
        Map.Entry<String, Integer> e1 = list.get(1);
        Map.Entry<String, Integer> e2 = list.get(2);

        assert ((e0.getKey().equals("a.java") && e0.getValue() == 3) &&
                (e0.getKey().equals("b.java") && e0.getValue() == 3) &&
                (e0.getKey().equals("c.java") && e0.getValue() == 3));
    }
}
