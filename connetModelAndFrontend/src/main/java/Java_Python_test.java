/**
 * Created by fengyushan on 2020/11/10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Java_Python_test {
    public String runModel() {
        // TODO Auto-generated method stub

        Process proc;
        try {
            proc = Runtime.getRuntime().exec("D:\\anaconda\\envs\\charPicToLabel\\python.exe D:\\pycharmProjects\\charPicToLabel\\charPicToLabel.py");

            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String result = in.readLine();
            System.out.println(result);
            in.close();
            proc.waitFor();

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}



