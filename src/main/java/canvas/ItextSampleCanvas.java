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

import java.io.IOException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvas {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例.pdf"));
        // 创建页面，并获取页面的宽高
        PdfPage pdfPage = pdfDocument.addNewPage(new PageSize(600, 200));
        float width = pdfPage.getPageSize().getWidth();
        float height = pdfPage.getPageSize().getHeight();
        // ============================= 使用画布(PdfCanvas)画格子 =============================
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvasA = new PdfCanvas(pdfPage);
        // 为画布创建坐标系点每个间隔10*10
        PdfExtGState pdfExtGState = new PdfExtGState();
        pdfExtGState.setStrokeOpacity(.3f); // 描边透明度
        for (int x = 0; x < width; x += 10) {
            for (int y = 0; y < height; y += 10) {
                pdfCanvasA.saveState().setStrokeColor(ColorConstants.GRAY)
                        .setExtGState(pdfExtGState)                   // 设置补充信息
                        .setLineDash(1, 1, 0)   // 设置虚线模式
                        .moveTo(x, y).lineTo(x, y + 10)
                        .moveTo(x - 10, y).lineTo(x + 10, y)
                        .stroke().restoreState();
            }
        }
        // ============================= 使用画布(Canvas)画矩形 =============================
        // 创建了一个高级的画布，画布大小为页面四边往内30pt
        Canvas canvas = new Canvas(pdfPage, new Rectangle(30, 30, width - 60, height - 60));
        // 获取当前画布的大小
        Rectangle rootArea = canvas.getRootArea();
        // 添加一个矩形，后面添加到画布
        Div div = new Div().setWidth(rootArea.getWidth()).setHeight(rootArea.getHeight())
                .setBorder(new DashedBorder(ColorConstants.RED, 2))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)  // 居中对齐
                .setBorderRadius(new BorderRadius(10));
        // 添加一个文本到矩形中
        Paragraph paragraph = new Paragraph("Canvas is your favorite")
                .setHeight(60)
                .setBold()      // 加粗
                .setFont(PdfFontFactory.createFont())   // 字体样式
                .setFontSize(30)// 字体大小
                .setBorderRadius(new BorderRadius(20))  // 边框圆角20pt
                .setTextAlignment(TextAlignment.CENTER) // 文本左右居中
                // 关于文本的居中对齐，受字体的影响，上面会多几磅(pt)，实在难受可以使用画布
                .setVerticalAlignment(VerticalAlignment.MIDDLE)  // 居中对齐
                .setBackgroundColor(ColorConstants.GRAY, .2f)// 背景色和0.2的透明
                .setFontColor(ColorConstants.RED); // 字体红色
        // 添加到画布中并关闭
        div.add(paragraph);
        canvas.add(div);
        canvas.close();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
