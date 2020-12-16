import java.io.IOException;

public class Entrance {
    public static void main(String args[]){
        try
        {
            int port = 6066;
            Thread t = new ServerSide(port);
            t.run();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
