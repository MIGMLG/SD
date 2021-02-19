package pt.ipp.estg;

import java.io.Serializable;

public class Key implements Serializable {

    private String Key;

    public Key(String key) {
        Key = key;
    }

    public String getKey() {
        return Key;
    }

    @Override
    public String toString() {
        return "Key{" +
                "Key='" + Key + '\'' +
                '}';
    }

}
