package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
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
public class ItextSampleCanvasArc {

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_弧形.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage(new PageSize(600, 150));
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 通过弧度画一个圈
        pdfCanvas.saveState()
                .setLineWidth(5)
                // 开始画 0~90 度的弧（红色）
                .arc(0, 0, 200, 100, 0, 90)
                .setStrokeColor(ColorConstants.RED)
                .stroke()   // 截断，代表上面绘画路径结束了
                // 开始画 90~180 度的弧（蓝色）
                .arc(0, 0, 200, 100, 90, 90)
                .setStrokeColor(ColorConstants.BLUE)
                .stroke()
                // 开始画 180~270 度的弧（黄色）
                .arc(0, 0, 200, 100, 180, 90)
                .setStrokeColor(ColorConstants.YELLOW)
                .stroke()
                // 开始画 270~360 度的弧（青色）
                .arc(0, 0, 200, 100, 270, 90)
                .setStrokeColor(ColorConstants.CYAN)
                .stroke().restoreState();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
