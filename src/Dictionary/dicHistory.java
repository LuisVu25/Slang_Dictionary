package Dictionary;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class dicHistory {
    ArrayList<String[]> history;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public dicHistory() {
        history = new ArrayList<>();
    }

    public void add(String word, String definition) {
        String[] h = new String[3];
        h[0] = word;
        h[1] = definition;
        h[2] = dtf.format(LocalDateTime.now());
        history.add(h);
    }

    public String save(String filepath) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filepath)));
            oos.writeObject(history);

            oos.close();
        } catch (IOException e) {
            return e.getMessage();
        }

        return "";
    }

    public String load(String filepath) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filepath)));

            history = (ArrayList<String[]>) ois.readObject();

            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            return e.getMessage();
        }

        return "";
    }

    public void clear() {
        history.clear();
    }

    public String[][] to2DArray() {
        return history.toArray(String[][]::new);
    }
}
