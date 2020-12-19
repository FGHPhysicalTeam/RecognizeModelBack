import java.io.IOException;

public class Entrance {
    //服务器端入口
    public static void main(String args[]){
        try
        {
            GlobalVariable globalVariable = new GlobalVariable();
            int port = globalVariable.getPort();
            Thread t = new ServerSide(port);
            t.run();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
