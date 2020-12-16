import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;


public class DotToPic {
    public void dottopic(String dotlist) {
        int[] xPoint, yPoint;
        int xMax, yMax, xMin, yMin, size;
        int[][] pic;
        BufferedImage img;
        String[] points;
        String dotlistx = dotlist.substring(0, dotlist.length()/2);
        String dotlisty = dotlist.substring(dotlist.length()/2, dotlist.length());
        System.out.println("dotlistx: " + dotlistx);
        System.out.println("dotlisty: " + dotlisty);
//        String dotlistx="391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,391,390,392,392,394,398,402,408,410,416,421,422,426,428,430,432,432,433,433,433,434,434,434,434,434,434,434,434,434,434,434,434,434,433,432,430,429,427,425,422,420,417,414,412,410,408,406,405,404,403,402,401,401,401,400,400,400,399,399,399,398,398,398,398,397,397,397,397,397,397,397,397,398,399,400,400,402,404,406,410,414,419,425,432,438,444,449,454,456,460,463,465,467,468,469,470,471,472,472,473,473,474,475,475,476,476,477,477,477,478,478,478,478,478,478,478,477,476,475,473,471,469,466,463,460,456,452,448,444,439,435,431,426,423,419,417,412,409,406,403,400,397,396,394,391,389,387,386,384,383,383,382,382,381,381,381,380,380,380,380,";
//        String dotlisty="101,104,109,116,127,138,151,164,177,190,202,207,218,227,234,236,241,246,248,251,252,253,253,254,254,254,254,253,109,109,109,109,109,109,109,109,109,109,109,109,109,109,109,109,110,111,113,114,116,118,120,122,124,126,127,129,131,132,133,136,137,139,141,143,145,148,150,152,155,158,159,161,163,164,165,166,166,167,167,167,167,168,168,168,168,168,168,168,168,168,168,168,168,168,168,167,167,167,167,167,167,166,166,166,166,166,166,166,166,166,166,166,166,167,168,169,170,171,172,173,173,174,175,176,177,178,179,181,182,183,185,186,188,189,191,192,192,193,194,195,195,196,197,199,200,202,204,207,209,212,215,217,220,223,225,228,230,233,235,237,238,238,240,241,242,243,244,245,245,246,246,247,247,247,247,247,247,247,247,247,247,246,246,245,245,245,";

        try {
            points = dotlistx.split(",");
            xPoint = new int[points.length];
            for (int i = 0; i < xPoint.length; i++)
                xPoint[i] = Integer.parseInt(points[i]);
            points = dotlisty.split(",");
            yPoint = new int[points.length];
            for (int i = 0; i < yPoint.length; i++)
                yPoint[i] = Integer.parseInt(points[i]);

            xMax = yMax = 0;
            xMin = xPoint[0];
            yMin = yPoint[0];
            for (int x : xPoint) {
                xMax = Math.max(x, xMax);
                xMin = Math.min(x, xMin);
            }
            for (int y : yPoint) {
                yMax = Math.max(y, yMax);
                yMin = Math.min(y, yMin);
            }
            size = Math.max(xMax - xMin + 1, yMax - yMin + 1);

            pic = new int[28][28];
            for (int i = 0; i < xPoint.length; i++)
                pic[(xPoint[i] - xMax + size - 1) * 28 / size][(yPoint[i] - yMax + size - 1) * 28 / size] = 255;
            img = new BufferedImage(28, 28, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < 28; i++)
                for (int j = 0; j < 28; j++)
                    img.setRGB(i, j, new Color(pic[i][j], pic[i][j], pic[i][j]).getRGB());
            ImageIO.write(img, "png", new File("D:\\ideaProjects\\untitled_1\\src\\pic\\test.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
