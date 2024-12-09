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
public class ItextSampleCanvasPath {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_路径填充.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 使用画布线条画一个 "▲"
        // 使用fill()来当路径填充，这种方式没有路径描边，只有路径填充效果
        pdfCanvas.saveState().moveTo(50, 50).setLineWidth(5)
                // 画 /、\、——
                .lineTo(75, 100).lineTo(100, 50).lineTo(50, 50)
                .setStrokeColor(new DeviceRgb(0, 0, 255))   // 路径描边颜色
                .setFillColor(new DeviceRgb(255, 0, 0))     // 设置路径填充色
                .fill()
                .stroke().restoreState();
        // 使用画布线条画一个 "▲"
        // 使用fillStroke()来当路径填充，这种方式有路径描边和路径填充2种效果的
        // 需要注意的是fillStroke()路径填充，它路径是不会自闭和的，比如画三角形时，会有一角没闭合
        pdfCanvas.saveState().moveTo(150, 50).setLineWidth(5)
                // 画 /、\
                .lineTo(175, 100).lineTo(200, 50).lineTo(150, 50)
                .setStrokeColor(new DeviceRgb(0, 0, 255))   // 路径描边颜色
                .setFillColor(new DeviceRgb(255, 0, 0))     // 设置路径填充色
                .resetFillColorRgb() // 路径填充颜色重置，（默认就说黑色，后面再设置颜色会覆盖）
                .fillStroke()
                .stroke().restoreState();
        // 使用画布线条画一个 "▲"
        // 使用closePathFillStroke()会填充路径描边和路径填充2种效果，这方法还有自动闭合路径
        // 闭合的路径也不会存在豁角，即使图形的闭合线没画，他会自动连接开始和接收线的两端，并且自闭和
        pdfCanvas.saveState().moveTo(250, 50).setLineWidth(5)
                // 画 /、\、——
                .lineTo(275, 100).lineTo(300, 50)/*.lineTo(250, 50)*/
                .setStrokeColor(new DeviceRgb(0, 0, 255))   // 路径描边颜色
                .setFillColor(new DeviceRgb(255, 0, 0))     // 设置路径填充色
                .resetStrokeColorRgb() // 路径描边颜色重置，（默认黑色，需要在closePathStroke上面）
                .closePathStroke()  // 关闭路径填充颜色，就没有任何颜色了
                .closePathFillStroke()
                .stroke().restoreState();
        // 使用画布线条画一个 "▲"
        pdfCanvas.saveState()
                .moveTo(350, 50).setLineWidth(5)
                // 画 /、\、——
                .lineTo(375, 100).lineTo(400, 50).lineTo(350, 50)
                .setStrokeColor(new DeviceRgb(0, 0, 255))   // 路径描边颜色
                .setFillColor(new DeviceRgb(255, 255, 0))     // 设置路径填充色
                .closePathFillStroke()
                .stroke() // 打断画下一笔
                .rectangle(new Rectangle(370, 50, 50, 50))
                .setLineWidth(2) // 设置线粗，要不然还是用上面的线粗5
                .setStrokeColor(new DeviceRgb(99, 0, 50))
                .closePathStroke()
                .fill() // 闭合
                .stroke().restoreState();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }


}
