package pt.ipp.estg;

import java.util.ArrayList;

public class SynchronizedUserDataList {

    private ArrayList<UserData> data = new ArrayList<>();

    public synchronized void addUser(UserData userData) {
        data.add(userData);
    }

    public synchronized ArrayList<UserData> getList() {
        return data;
    }

}
