import java.net.*;
import java.io.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        String hostName = "127.0.0.1";
        int portNumber = 7777;

        if (args.length == 2) {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        } else {
            System.out.println("Usage: java EchoClient <host> <port>");
            System.out.println("Default: 127.0.0.1 7777\n");
        }

        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Δώσε αριθμητική πράξη (π.χ. 12+7): ");
            String userInput = stdIn.readLine();
            out.println(userInput);

            String response = in.readLine();
            if (response != null) {
                System.out.println("Απάντηση από server: " + response);
            } else {
                System.out.println("Ο server δεν επέστρεψε απάντηση.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
