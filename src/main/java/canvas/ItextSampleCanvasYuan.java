package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
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
public class ItextSampleCanvasYuan {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_圆.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage(new PageSize(600, 150));
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 画2个圆
        pdfCanvas.saveState().circle(75, 75, 50)
                .setFillColor(ColorConstants.YELLOW)
                .fillStroke().stroke().restoreState();
        pdfCanvas.saveState().circle(0, 0, 50)
                .setFillColor(ColorConstants.BLUE)
                .fillStroke().stroke().restoreState();
        // 绘制椭圆
        pdfCanvas
                .ellipse(200, 15, 300, 60)
                .setLineWidth(3)
                .setStrokeColor(new DeviceRgb(0, 0, 255))
                .setFillColor(new DeviceRgb(255, 0, 0))
                .fillStroke()
                .stroke();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }


}
