package canvas;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.IOException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasWater {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_水印.pdf"));
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        // 为图片添加水印并加入到文档
        Image image = new Image(ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG"));
        image.setWidth(550).setHeight(300);
        Image watermarkedImage = getWatermarkedImage(pdfDocument, image, "@蚂蚁小哥", 70);
        document.add(watermarkedImage);
        document.close();
    }
    /***
     * 为图片添加水印
     * @param pdfDocument PDF文档信息
     * @param img 图片信息
     * @param msg 水印信息
     * @param interval 水印间隔
     * @return 添加水印后的图片
     * @throws IOException 这里的异常是无法获取字体异常
     */
    public static Image getWatermarkedImage(PdfDocument pdfDocument, Image img,
                                            String msg, int interval) throws IOException {
        // 获取图片的宽高信息，先获取是否更改过图片宽高；77代表宽，27代表高
        float width = img.getImageScaledWidth();
        float height = img.getImageScaledHeight();
        if (img.getProperty(27) != null) {
            height = ((UnitValue)img.getProperty(27)).getValue();
        }
        if (img.getProperty(77) != null) {
            width = ((UnitValue)img.getProperty(77)).getValue();
        }
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 创建PDF表单对象包装器
        PdfFormXObject pdfFormXObject = new PdfFormXObject(new Rectangle(width, height));
        // 创建一个画布并将图片添加到PdfFormXObject对象上
        new Canvas(pdfFormXObject, pdfDocument).add(img).close();
        // =============== 开始绘制水印 ===============
        for (int x = 0; x < width; x += interval) {
            for (int y = 0; y < height; y += interval) {
                new Canvas(pdfFormXObject, pdfDocument)
                        .setFontColor(ColorConstants.WHITE, .3f) // 颜色和透明度
                        .setFont(font)      // 文字样式
                        .setFontSize(18)    // 字体大小（具体可以改）
                        .showTextAligned(msg, x, y, TextAlignment.CENTER,
                                VerticalAlignment.MIDDLE, 19.5f);
            }
        }
        // 转换成图片资源返回
        return new Image(pdfFormXObject);
    }


}
