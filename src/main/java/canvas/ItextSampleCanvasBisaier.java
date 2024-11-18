package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

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
public class ItextSampleCanvasBisaier {

    public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_贝塞尔曲线.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage(new PageSize(600, 210));
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 画几个小框，可以明确知道下面画的曲线的起始和终点位置
        pdfCanvas.saveState()
                // 在300，150的坐标点上画个小框
                .rectangle(new Rectangle(300, 150, 2, 2))
                // 在200, 150的坐标点上画个小框
                .rectangle(new Rectangle(200, 150, 2, 2))
                // 在50, 50的坐标点上画个小框
                .rectangle(new Rectangle(50, 50, 2, 2))
                .stroke().restoreState();
        // 画贝塞尔曲线
        pdfCanvas.saveState()
                // 从坐标(100,100)开始绘画贝塞尔曲线
                .moveTo(100, 100)
                .curveTo(0, 0, 300, 150)
                .stroke()
                // 从坐标(50,50)开始绘画贝塞尔曲线
                .moveTo(50, 50)
                .setLineWidth(3)
                .setStrokeColor(ColorConstants.RED)
                .curveTo(0, 0, 350, 50, 200, 150)
                .stroke()
                // 从坐标(50,50)开始绘画贝塞尔曲线
                .moveTo(50, 50)
                .setLineWidth(2)
                .setStrokeColor(ColorConstants.BLUE)
                .curveFromTo(200, 150, 150, 200)
                .stroke().restoreState();
        // 循环绘画贝塞尔曲线
        for (int i = 10; i < 100; i += 10) {
            pdfCanvas.saveState()
                    .moveTo(400, 100)
                    .curveTo(450, 0, 500, 100 + i)
                    .stroke().restoreState();
        }
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }


}
