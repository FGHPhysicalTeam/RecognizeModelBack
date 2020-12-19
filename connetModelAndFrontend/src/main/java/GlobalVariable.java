/**
 * Created by fengyushan on 2020/12/16.
 * 存储全局变量
 * 需要修改
 */
public class GlobalVariable {
    private String pythonAddress = "/Users/fengyushan/PicToLabel/bin/python";
    private String charPicToLabel = "/Users/fengyushan/githubProject/RecognizeModelBack/PicToLabel/charPicToLabel.py";
    private String geoPicToLabel = "/Users/fengyushan/githubProject/RecognizeModelBack/PicToLabel/geoPicToLabel.py";
    private String picAddress = "/Users/fengyushan/githubProject/RecognizeModelBack/connetModelAndFrontend/src/pic/test.png";
    private int port = 6066;
    public String getPythonAddress(){
        return pythonAddress;
    }
    public String getCharPicToLabel(){
        return charPicToLabel;
    }
    public String getGeoPicToLabel(){
        return geoPicToLabel;
    }
    public String getPicAddress(){
        return picAddress;
    }
    public int getPort(){
        return port;
    }
}
