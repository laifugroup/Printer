package all;
import org.apache.pdfbox.io.IOUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Description: 企业签章业务
 * @Package: com.resrun.service.image
 * @ClassName: EntSealGenerateService
 * @copyright 北京资源律动科技有限公司
 */
public class EntSealGenerateService {


    public static void main(String[] args) {
        EntSealGenerateService ent=new EntSealGenerateService();
        byte[] bytes =   ent.generateEntSeal("纸上谈兵项目组","官方认证","610000122");
        try {
            // 使用ImageIO读取字节数组为BufferedImage对象（前提是字节数组数据符合相应图片格式规范）
            BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(bytes));
            // 定义要保存的图片文件路径和格式，这里以保存为PNG格式为例，你可以根据实际情况修改格式（比如 "jpg"等）
            File outputFile = new File("./output_image.png");
            // 将BufferedImage对象写入到文件中，通过ImageIO的write方法
            ImageIO.write(image, "png", outputFile);
            System.out.println("图片已成功保存为: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description #生成企业签章
     * @Param [topText, middleText]
     * @return byte[]
     **/
    public byte[] generateEntSeal(String topText,String middleText,String securityCode){

        byte[] bytes = export2pic("png",topText, middleText,securityCode);
        return bytes;
    }




    /**
     * 印章名称距中心点偏移量,按照y轴方向
     */
    private int nameOffset = 50;
    /**
     * 印章宽度
     */
    private int width = 200;
    /**
     * 印章高度
     */
    private int height = 200;
    /**
     * 印章中心标志(默认为五角星)外接圆半径
     */
    private float radius = 30;
    /**
     * 印章名称颜色
     */
    private Color nameColor = Color.RED;
    /**
     * 印章所属单位
     */
//    private String firm;
    /**
     * 印章所属单位颜色
     */
    private Color firmColor = Color.RED;
    private float firmScale = 0.7F;
    /**
     * 边框线宽
     */
    private float borderWidth = 5F;
    /**
     * 边框颜色
     */
    private Color borderColor = Color.RED;
    /**
     * 印章标记(默认为五角星)线宽
     */
    private float signBorderWidth = 3F;
    /**
     * 印章标记颜色
     */
    private Color signBorderColor = Color.RED;
    /**
     * 印章标记填充颜色
     */
    private Color signFillColor = Color.RED;


    public void draw(Graphics2D g2d,String topText,String middleText,String botttomText) {
        // 把绘制起点挪到圆中心点
        g2d.translate(width / 2, height / 2);

        Stroke stroke = g2d.getStroke();// 旧的线性
        // 填充五角星
        Polygon polygon = getPentaclePoints(radius);
        if (signFillColor != null) {
            g2d.setColor(signFillColor);
            g2d.fill(polygon);
        }
        // 绘制五角星边框
        g2d.setStroke(new BasicStroke(signBorderWidth));
        g2d.setColor(signBorderColor);
        g2d.draw(polygon);

        // 绘制印章边框
        g2d.setFont(nameFont);
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderWidth));
        g2d.drawOval(-width / 2, -height / 2, width, height);
        g2d.setStroke(stroke);

        // 绘制印章名称
        g2d.setFont(nameFont);
        g2d.setColor(nameColor);   //g2d.setStroke(new BasicStroke(10F));
        FontMetrics fm = g2d.getFontMetrics();
        int w = fm.stringWidth(middleText);// 名称宽度
        int h = fm.getHeight();// 名称高度
        int y = fm.getAscent() - h / 2;// 求得中心线经过字体的高度的一半时的字体的起绘点
        g2d.drawString(middleText, -w / 2, y + nameOffset);

        // 绘制印章单位
        g2d.setFont(getFirmFont(topText));
        g2d.setColor(firmColor);
        fm = g2d.getFontMetrics();
        h = fm.getHeight();// 字高度

        int count = topText.length();// 字数
        int r = width / 2;// 半径,就假设此印章是个矩形,方便计算
        float angle;
        float start;

        if(count>1 && count <=10){
            angle = 20f;// 字间角度
            start = 90+(360 - angle*(count-1))/2;// 以x轴正向为0,顺时针旋转
        }else if(count > 10){
            angle = (360 - firmAngle) / (count-1);// 字间角度
            start = 90+(360 - angle*(count-1))/2;// 以x轴正向为0,顺时针旋转
        }else{
            angle = 0f;// 字间角度
            start = 90+(360 - angle*(2-1))/2;// 以x轴正向为0,顺时针旋转
        }
        double vr = Math.toRadians(90);// 垂直旋转弧度
        char[] chars = topText.toCharArray();
        for (int i = 0; i < count; i++) {
            char c = chars[i];// 需要绘制的字符
            int cw = fm.charWidth(c);// 此字符宽度
            float a = start + angle * i;// 现在角度
            double radians = Math.toRadians(a);
            g2d.rotate(radians);// 旋转坐标系,让要绘制的字符处于x正轴
            float x = r - h;// 绘制字符的x坐标为半径减去字高度
            g2d.translate(x, 0);// 移动到此位置,此时字和x轴垂直
            g2d.rotate(vr);// 旋转90度,让字平行于x轴
            g2d.scale(firmScale, 1);// 缩放字体宽度
            g2d.drawString(String.valueOf(c), -cw / 2, 0);// 此点为字的中心点
            // 将所有设置还原,等待绘制下一个
            g2d.scale(1 / firmScale, 1);
            g2d.rotate(-vr);
            g2d.translate(-x, 0);
            g2d.rotate(-radians);
        }


// 绘制印章底部文本（botttomText）开始
        g2d.setFont(getFirmFont(botttomText).deriveFont(Font.PLAIN, 12f)); // 调整字体大小为12
        g2d.setColor(firmColor);
        fm = g2d.getFontMetrics();
        h = fm.getHeight(); // 字高度

        int bottomCount = botttomText.length(); // 字数
        int bottomR = (int) (width / 2 - h * 1.2f); // 半径减去字体高度的一半
        float bottomAngle = 10f; // 字间角度
        float bottomStart = 180 - (bottomAngle * (bottomCount - 1)) / 2; // 从180度开始，集中在底部

        double bottomVr = Math.toRadians(90); // 垂直旋转弧度
        char[] bottomChars = botttomText.toCharArray();
        for (int i = 0; i < bottomCount; i++) {
            char c = bottomChars[i]; // 需要绘制的字符
            int bottomCw = fm.charWidth(c); // 此字符宽度
            float a = bottomStart + bottomAngle * i; // 现在角度
            double bottomRadians = Math.toRadians(a);
            g2d.rotate(bottomRadians); // 旋转坐标系,让要绘制的字符处于x正轴
            float bottomX = bottomR; // 绘制字符的x坐标为调整后的半径
            g2d.translate(bottomX, h); // 移动到此位置,确保字在底部
            g2d.rotate(bottomVr); // 旋转90度,让字平行于x轴
            g2d.drawString(String.valueOf(c), -bottomCw / 2, 0); // 此点为字的中心点
            // 将所有设置还原,等待绘制下一个
            g2d.rotate(-bottomVr);
            g2d.translate(-bottomX, -h); // 注意这里的y坐标调整
            g2d.rotate(-bottomRadians);
        }
// 绘制印章底部文本（botttomText）结束
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 获取具有指定半径外接圆的五角星顶点
     *
     * @param radius
     *            圆半径
     */
    private Polygon getPentaclePoints(float radius) {
        if (radius <= 0)
            return null;
        float lradius = radius * 0.381966f;// 根据radius求内圆半径
        double halfpi = Math.PI / 180f;
        Point[] points = new Point[10];
        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 1)
                points[i] = new Point(
                        (int) (Math.sin(halfpi * 36 * i) * radius),
                        (int) (Math.cos(halfpi * 36 * i) * radius));
            else
                points[i] = new Point(
                        (int) (Math.sin(halfpi * 36 * i) * lradius),
                        (int) (Math.cos(halfpi * 36 * i) * lradius));
        }
        Polygon polygon = new Polygon();
        for (Point p : points) {
            polygon.addPoint(p.x, p.y);
        }
        return polygon;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    private Font nameFont = new Font("宋体", Font.PLAIN, 19);

    private Font getFirmFont(String topText){
        Font font = null;
        int len = topText.length();
        System.out.println(len);
        if(len==1){
            setFirmAngle(355);
            font = new Font("宋体", Font.PLAIN, 25);
        }else if(len>1 && len<=3){
            setFirmAngle(310);
            font = new Font("宋体", Font.PLAIN, 25);
        }else if(len>3 && len<=6){
            setFirmAngle(250);
            font = new Font("宋体", Font.PLAIN, 25);
        }else if(len>6 && len <=10){
            setFirmAngle(200);
            font = new Font("宋体", Font.PLAIN, 25);
        }else if(len>10 && len<=13){
            setFirmAngle(180);
            font = new Font("宋体", Font.PLAIN, 25);
        }
        else if(len>13 && len<=20){
            font = new Font("宋体", Font.PLAIN, 25);
            setFirmAngle(120);
        }else if(len>20 && len <= 25){
            font = new Font("宋体", Font.PLAIN, 23);

            setFirmAngle(80);
        }else if(len>25 && len < 30){
            setFirmAngle(80);
            font = new Font("宋体", Font.PLAIN, 19);
        }else if(len>=30 && len <= 40){
            setFirmAngle(80);
            font = new Font("宋体", Font.PLAIN, 19);
        }else{
            setFirmAngle(10);
            font = new Font("宋体", Font.PLAIN, 17);
        }
        return font;
    }



    private int firmAngle;
    public void setFirmAngle(int firmAngle){
        this.firmAngle = firmAngle;
    }


    /**
     * 导出此印章为透明背景的图片字节数组.
     *
     * @param format
     *            图片类型,如果为null,则默认为png
     * @return 数组
     * @throws FileNotFoundException
     * @throws IOException
     *             写出图像数据出现问题
     */
    public byte[] export2pic(String format,String topText,String middleText,String bottomText)  {
        int fix = 5;// 宽高修正,如果宽高就为图片宽高,可能边框线被切割
        BufferedImage bi = new BufferedImage(getWidth() + fix * 2, getHeight()
                + fix * 2, 3);


        Graphics2D g2d = bi.createGraphics();
        //防锯齿状毛刺算法
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(fix, fix);
        draw(g2d,topText,middleText,bottomText);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, format == null ? "png" : format, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(topText+"：生成企业签章失败",e);
        }finally {
            try {
                if(baos!=null)
                    baos.close();
            } catch (IOException e) {
            }
        }

    }
}
