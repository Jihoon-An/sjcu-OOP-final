import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Question2Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket clientSocket = new Socket("localhost", 8080);
            System.out.println("가장 처음 입력하는 메세지는 닉네임으로 인식됩니다.");

            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String msg = dis.readUTF();
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            while (true) {
                dos.writeUTF(scanner.nextLine());
                dos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
