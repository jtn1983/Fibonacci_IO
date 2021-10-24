package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(25555);
        try (Socket socket = serverSocket.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                if (line.equals("end")) break;
                try {
                    out.println(fibonacci(Integer.parseInt(line)));
                }catch (NumberFormatException e) {
                    out.println("Input digit or end");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static String fibonacci(int count) {
        int previousNumber = 0;
        int nextNumber = 1;
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= count; ++i) {
            str.append(previousNumber);
            str.append(" ");
            int sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }
        return str.toString();
    }
}
