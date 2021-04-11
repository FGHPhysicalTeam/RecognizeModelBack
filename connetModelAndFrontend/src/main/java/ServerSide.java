import java.net.*;
import java.io.*;

public class ServerSide extends Thread{
    private ServerSocket serverSocket;
    private Socket server;

    public ServerSide(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000000000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                server = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream(),"UTF-8"));
                String dotListType = br.readLine();
                String result = "";

                //1. 将点集存为本地图片
                DotToPic dotToPic = new DotToPic();
                String dotListX, dotListY = "";
                dotListX = br.readLine();
                dotListY = br.readLine();
                dotToPic.dottopic(dotListX, dotListY);
                //2. 调用python分类代码
                Java_Python_test java_python_test = new Java_Python_test(dotListType);
                result = java_python_test.runModel();

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(server.getOutputStream(),"UTF-8"));
                out.write(result);
                out.flush();
                server.close();

            } catch (SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
