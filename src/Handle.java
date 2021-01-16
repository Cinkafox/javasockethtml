import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Handle extends Thread {
    public Socket socket;
    public Handle(Socket socket) {
        this.socket = socket;
    }
    public void run(){
        try(OutputStream out = socket.getOutputStream(); InputStream in = socket.getInputStream();){

            String mainoutput = TXT(URL(in));
            out.write(Parser(mainoutput).getBytes());
            out.flush();
            Stop(in,out);
        }catch(IOException e){
            System.out.println("Exception error!");
        }
    }
    public void Stop(InputStream in,OutputStream out) throws IOException {
        out.close();
        in.close();
        System.out.println("Stream stoped:");
    }

    public String Parser(String in){
        System.out.println("Parsing:");
        String out = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + in.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        return out + in;
    }

    public String URL(InputStream in){
        Scanner scanner = new Scanner(in);
        String line = scanner.nextLine();
        return line.split(" ")[1].substring(1);
    }

    public String TXT(String filedir){
        File file = new File(filedir + ".html");
        String text = "";

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                text = text + sc.nextLine() + "\n";
            }
            sc.close();
            return text;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            return "Not found";
        }

    }

}
