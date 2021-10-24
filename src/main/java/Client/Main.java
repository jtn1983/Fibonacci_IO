package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 25555);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)
        ) {
            String count;
            while (true) {
                System.out.println("Enter count of Fibonacci: ");
                count = scanner.nextLine();
                if ("end".equals(count)) break;
                out.println(count);
                System.out.println("Server: " + in.readLine());
            }
        }
    }
}
