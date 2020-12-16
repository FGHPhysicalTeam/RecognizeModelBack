import java.net.*;
import java.io.*;

public class ServerSide extends Thread{
    private ServerSocket serverSocket;

    public ServerSide(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("服务器地址" + serverSocket.getLocalSocketAddress() + "...");
                Socket server = serverSocket.accept();
                System.out.println("客户机地址" + server.getRemoteSocketAddress());
                BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream(),"UTF-8"));
                String tmp1 = br.readLine();
                System.out.println("客户端请求：" + tmp1);
                String result = "";

                System.out.println("true");
                //1. 将点集存为本地图片

                DotToPic dotToPic = new DotToPic();
                dotToPic.dottopic(tmp1);
                //2. 调用python分类代码
                Java_Python_test java_python_test = new Java_Python_test();
                result = java_python_test.runModel();
                System.out.println("the result is:" + result);

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
