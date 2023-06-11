import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Question2Server {

    private static List<Client> clientList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("채팅 활성화.");

            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    DataInputStream dis = new DataInputStream(client.getInputStream());

                    Thread msgRead = new Thread(() -> {
                        try {
                            String nickName = dis.readUTF();
                            clientList.add(new Client(client, nickName));
                            while (true) {
                                sendMsg(client, dis.readUTF());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    msgRead.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMsg(Socket writer, String msg) {
        for (Client client : clientList) {
            if (!client.equals(writer)) {
                try {
                    DataOutputStream dos = new DataOutputStream(client.socket.getOutputStream());
                    dos.writeUTF(client.nickName + ": " + msg);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class Client {
    Socket socket;
    String nickName;

    public Client(Socket socket, String nickName) {
        this.socket = socket;
        this.nickName = nickName;
    }
}
