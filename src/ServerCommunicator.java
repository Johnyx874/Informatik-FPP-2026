import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ServerCommunicator {

    private Socket socket;
    private PrintWriter out;
    private Random random;

    public ServerCommunicator() throws Exception {
        socket = new Socket("127.0.0.1", 5000);
        out = new PrintWriter(socket.getOutputStream(), true);
        random = new Random();
    }

    public void sendValues(float value) {
        //float value = random.nextInt(21) + 40;
        out.println(value);
    }
}