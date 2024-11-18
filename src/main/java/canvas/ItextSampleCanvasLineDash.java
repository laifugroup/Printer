package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
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
public class ItextSampleCanvasLineDash {

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_虚线.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 标准的画一个矩形
        pdfCanvas.saveState()
                .setFillColor(ColorConstants.CYAN)      // 填充颜色
                .setStrokeColor(ColorConstants.RED)     // 路径描边颜色
                .setLineWidth(10)                       // 路径描边宽度
                .rectangle(new Rectangle(50, 30, 400, 50))
                .fillStroke()   // 填充路径
                .stroke().restoreState();
        // 使用虚线模式画一个矩形
        pdfCanvas.saveState()
                .setFillColor(ColorConstants.CYAN)
                .setStrokeColor(ColorConstants.RED)
                .setLineWidth(10)
                // 设置虚线模式； 线段长20，间隙长5，起始点5
                .setLineDash(new float[]{20, 10}, 10)
                //.setLineDash(20, 10, 10) // 上面的等同这个写法
                .rectangle(new Rectangle(50, 100, 400, 50))
                .fillStroke()   // 填充路径
                .stroke().restoreState();
        // 使用虚线模式画一个矩形
        pdfCanvas.saveState()
                .setFillColor(ColorConstants.CYAN)      // 填充颜色
                .setStrokeColor(ColorConstants.RED)     // 路径描边颜色
                .setLineWidth(10)                       // 路径描边宽度
                .setLineDash(new float[]{20, 10}, 0) // 线段长20，间隙长10，起始点0
                .rectangle(new Rectangle(50, 200, 400, 50))
                .fillStroke()   // 填充路径
                .stroke().restoreState();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
