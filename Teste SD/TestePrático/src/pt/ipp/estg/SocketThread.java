package pt.ipp.estg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SocketThread extends Thread {

    private Socket socket = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    public SocketThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            UserData userData = null;
            while ((userData = (UserData) in.readObject()) != null) {
                System.out.println("Dados: " + userData.toString());
                boolean loginResult = login(userData);
                if(loginResult){
                    boolean keyResult = verifyKey(userData);
                    if(keyResult) {
                        out.writeObject("Autenticado!");
                    } else {
                        out.writeObject("Nao Autenticado!");
                    }
                }
            }
            in.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Connection Closed!");
        }
    }

    private boolean login(UserData userData) throws IOException {
        ArrayList<UserData> userDataArrayList = Main.dataList.getList();
        for (UserData user : userDataArrayList) {
            if(user.getId() == userData.getId()) {
                if(userData.getPassword().equals(user.getPassword())){
                    System.out.println("Login com sucesso!");
                    return true;
                } else {
                    System.out.println("Mete a palavra passe correta oh burro!");
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean verifyKey(UserData user) throws IOException, ClassNotFoundException {
        Key userKey = new Key("" + user.getId() + user.getPassword());
        out.writeObject(userKey);
        int counter = 0;

        Key key = null;
        while ((key = (Key) in.readObject()) != null) {
            if(counter <= 3) {
                if(userKey.getKey().equals(key.getKey())){
                    System.out.println("Autenticado!");
                    return true;
                } else {
                    System.out.println("Chave diferentes!");
                    counter++;
                    return false;
                }
            } else {
                System.out.println("Errou 3 vezes na chave!");
                break;
            }
        }
        return false;
    }

    public void close() {
        try {
            System.out.println("Socket fechado por inatividade!");
            in.close();
            socket.close();
            this.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
