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
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
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
public class ItextSampleCanvasText {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_画文字.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 画一个矩形，给下面的文本使用
        pdfCanvas.saveState()
                .rectangle(new Rectangle(20, 30, 500, 100))
                .setLineWidth(5)                    // 设置线宽
                .setStrokeColor(ColorConstants.RED) // 描边颜色
                .setFillColor(ColorConstants.GRAY)  // 填充颜色
                .setLineJoinStyle(1)                // 设置矩形连接点为圆角
                .fillStroke()                       // 设置路径填充
                .stroke().restoreState();
        pdfCanvas.beginText()   // 开启文本
                .moveText(100, 64)           // 设置文本在画布的指定位置
                .setFontAndSize(font, 36)    // 设置文本的样式和大小
                // 设置文本的呈现方式，这里我设置的是既有描边也有填充
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .setStrokeColor(ColorConstants.GREEN)   // 描边绿色
                .setFillColor(ColorConstants.RED)       // 填充红色
                .showText("您好，蚂蚁小哥！Hello World!")
                .endText();     // 关闭文本
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }


}
