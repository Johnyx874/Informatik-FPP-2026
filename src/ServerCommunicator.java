import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Random;

public class ServerCommunicator {

    private Socket socket;
    private PrintWriter out;
    private Random random;

    public ServerCommunicator() throws Exception {

        random = new Random();

        System.out.println("Connecting to Python server...");

        long timeout = System.currentTimeMillis() + 10_000; // 10 seconds

        while (true) {
            try {
                socket = new Socket("127.0.0.1", 5000);
                System.out.println("Successfully connected to Python server.");
                break;
            } catch (ConnectException e) {

                if (System.currentTimeMillis() >= timeout) {
                    throw new RuntimeException("Failed to connect to the Python server within 10 seconds.");
                }

                System.out.println("Python server not ready yet. Retrying...");
                Thread.sleep(50);
            }
        }

        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendValues(float value) {
        out.println(value);
    }
}