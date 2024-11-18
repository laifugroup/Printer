package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasTransform {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_变换.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 为画布创建坐标系点每个间隔10*10
        for (int x = 0; x < 700; x += 10) {
            for (int y = 0; y < 400; y += 10) {
                pdfCanvas.saveState().setStrokeColor(ColorConstants.GRAY)
                        .rectangle(new Rectangle(x, y, 1, 1))
                        .stroke().restoreState();
            }
        }
        // 绘画一个普通的矩形（就是图上面的黑色矩形）
        pdfCanvas.saveState()
                .setLineWidth(1)
                .rectangle(new Rectangle(10, 10, 50, 50))
                .stroke().restoreState();
        // 矩形缩放2倍
        // 可以看出不光矩形的宽和高都缩放了2倍（变成100*100），并且矩形的位移10*10也被放大成20*20
        pdfCanvas.saveState()
                .setLineWidth(1)
                .setStrokeColor(ColorConstants.BLUE)
                .concatMatrix(2, 0, 0, 2, 0, 0)
                .rectangle(new Rectangle(10, 10, 50, 50))
                .stroke().restoreState();
        // 旋转5度角
        double angle = Math.toRadians(5);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        pdfCanvas.saveState()
                .setLineWidth(1)
                .setStrokeColor(ColorConstants.GREEN)
                .concatMatrix(cos, sin, -sin, cos, 0, 0)
                .rectangle(new Rectangle(10, 10, 50, 50))
                .stroke().restoreState();
        // 绘画一段普通文本
        pdfCanvas.beginText().moveText(200, 50)
                .setFontAndSize(font, 18)
                .showText("蚂蚁小哥")
                .endText();
        // 对文本进行缩放2倍，.5f则是缩放0.5倍，1是不缩放
        pdfCanvas.beginText().moveText(200, 50)
                .setColor(ColorConstants.GREEN, true)
                .setTextMatrix(2, 0, 0, 2, 200, 50)
                .setFontAndSize(font, 18)
                .showText("蚂蚁小哥")
                .endText();
        // 对文本进行水平和垂直倾斜；具体需要计算度数（0.364f为45度角）
        pdfCanvas.beginText().moveText(200, 50)
                .setColor(ColorConstants.RED, true)
                .setTextMatrix(1, 0.364f, 0.364f, 1, 200, 50)
                .setFontAndSize(font, 18)
                .showText("蚂蚁小哥")
                .endText();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
