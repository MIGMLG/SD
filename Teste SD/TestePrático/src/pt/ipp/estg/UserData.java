package pt.ipp.estg;

import java.io.Serializable;

public class UserData implements Serializable {

    private int id;
    private String password;
    private String number;

    public UserData(int id, String password, String number) {
        this.id = id;
        this.password = password;
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }
}
