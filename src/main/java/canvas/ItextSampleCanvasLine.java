package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
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

import java.io.IOException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasLine {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_线条.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 画直线（普通）
        pdfCanvas.saveState()
                .moveTo(50, 50)   // 将画笔移到指定位置
                .setLineWidth(2)          // 线粗
                .setStrokeColor(new DeviceRgb(255, 0, 0)) // 描边颜色
                .lineTo(100, 100)   // 画笔在画布上绘制线到指定位置
                .stroke().restoreState();
        // 使用画布线条画一个 "L"
        pdfCanvas.saveState()
                .moveTo(120, 100)   // 从这个点开始下笔
                .lineTo(120, 50)    // 画 |
                .lineTo(150, 50)    // 画 ——
                .setStrokeColor(new DeviceRgb(255, 0, 255))
                .stroke().restoreState();
        // 使用画布线条画一个 "▲"（填充已闭合）
        pdfCanvas.saveState()
                .moveTo(200, 50)     // 从这个点开始下笔
                .setLineWidth(5)     // 设置线粗5磅
                .lineTo(225, 100)    // 画 /
                .lineTo(250, 50)     // 画 \
                // .lineTo(200, 50)     // 画 ——
                // 最后的 "——" 可以不用画，让closePath()帮我们关闭路径
                .closePath()            //  关闭路径（结束点连向开始点）
                .setStrokeColor(new DeviceRgb(0, 0, 255)) // 线条颜色
                .setFillColor(new DeviceRgb(255, 0, 0))   // 设置填充色
                .closePathFillStroke()     // 设置路径闭合及轮廓和填充（那些路径属性必须在闭合前设置完）
                .stroke().restoreState();
        // 使用画布线条画一个 "▲"（填充未闭合）
        pdfCanvas.saveState()
                .moveTo(400, 50)     // 从这个点开始下笔
                .setLineWidth(5)     // 设置线粗5磅
                .lineTo(425, 100)    // 画 /
                .lineTo(450, 50)     // 画 \
                .lineTo(400, 50)     // 画 ——
                .setStrokeColor(new DeviceRgb(0, 0, 255))   // 线条颜色
                .setFillColor(new DeviceRgb(255, 0, 0))     // 设置填充色
                .fillStroke()     // 设置路径轮廓及填充（那些路径属性必须在闭合前设置完）
                .stroke().restoreState();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
