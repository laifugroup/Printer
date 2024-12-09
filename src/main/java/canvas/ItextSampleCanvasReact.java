package canvas;

import com.itextpdf.kernel.colors.DeviceRgb;
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
import java.util.Random;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasReact {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_矩形.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage(new PageSize(600, 150));
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 画一个简单的矩形
        pdfCanvas.saveState()
                .rectangle(new Rectangle(400,10,150,100))
                .setLineWidth(3)
                .setFillColor(new DeviceRgb(255, 255, 0))  // 填充色
                .setStrokeColor(new DeviceRgb(0, 255, 0))  // 矩形轮廓描边颜色
                .fillStroke()   // 填充+构建轮廓
                .stroke().restoreState();
        // 画一组带圆角的矩形
        for (int i = 1; i <= 10; i++) {
            // 随机数 0~255
            int random1 = new Random().nextInt(255);
            int random2 = new Random().nextInt(255);
            int random3 = new Random().nextInt(255);
            pdfCanvas.saveState()
                    .roundRectangle(35 * i, 10, 30, 30, 5) // 构建圆角矩形
                    .setLineWidth(3)    // 线宽
                    .setFillColor(new DeviceRgb(random1, random2, random3))    // 填充色
                    .setStrokeColor(new DeviceRgb(random1, random2, random3))  // 矩形轮廓描边颜色
                    .fillStroke()   // 填充+构建轮廓
                    .stroke().restoreState();
        }
        // 为当前页面画个轮廓（不要填充哟）
        float width = pdfPage.getPageSize().getWidth();
        float height = pdfPage.getPageSize().getHeight();
        pdfCanvas.saveState()
                .rectangle(5, 5, width - 10, height - 10)
                .setLineWidth(2)
                .setStrokeColor(new DeviceRgb(255, 0, 0))
                .stroke().restoreState();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
