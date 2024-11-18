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
import com.itextpdf.layout.properties.HorizontalAlignment;
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
public class ItextSampleCanvasProd {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_画布.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // ============== 创建一个画布A在画布内构建一个1个段落，并且文字居中 ==============
        // 画布A的大小是200pt*100pt；并且当前画布位于页面的(20,20)的位置
        Canvas canvasA = new Canvas(pdfCanvas, new Rectangle(20, 20, 200, 100));
        //创建一个段落
        Paragraph paragraph = new Paragraph("Hello World！")
                .setWidth(160)
                .setHeight(50)
                .setBorderRadius(new BorderRadius(30))
                .setBackgroundColor(new DeviceRgb(255, 0, 0), .3f)
                .setFontColor(new DeviceRgb(0, 0, 255))
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // 垂直居中
                .setHorizontalAlignment(HorizontalAlignment.CENTER) // 水平居中
                .setFontSize(16);
        // 创建一个DIV对象，并且覆盖画布A所有位置，在把段落设置进去
        Div divA = new Div();
        divA.add(paragraph)
                .setWidth(200)
                .setHeight(100)
                .setBorderRadius(new BorderRadius(20))
                .setTextAlignment(TextAlignment.CENTER) // 文本居中
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // 垂直居中
                .setBackgroundColor(ColorConstants.GREEN, .3f);
        // 添加到画布中，并强制刷新和关闭
        canvasA.add(divA);
        canvasA.flush();
        canvasA.close();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
