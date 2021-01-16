import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args)throws IOException {
        System.out.println("Starting:");
        ServerSocket serversocket = new ServerSocket(8080);
        while(true){
            System.out.println("Handle Start:");
            Socket socket = serversocket.accept();
            Handle handle = new Handle(socket);
            handle.start();
            System.out.println("Handle done:");
        }
    }
}
