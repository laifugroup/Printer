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
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.ParagraphRenderer;

import java.io.IOException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasRender {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档和文档的创建
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_选择器.pdf"));
        Document document = new Document(pdfDocument);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 创建段落并为段落定义Style样式
        Style style = new Style()
                .setWidth(300)
                .setHeight(120)
                .setBorder(new SolidBorder(2))
                .setBorderRadius(new BorderRadius(10))
                .setMargin(0);   // 干掉它自带的margin样式，默认段落上下是有4pt的外边距
        Paragraph paragraph = new Paragraph("Hello World！").addStyle(style);
        // 创建段落渲染器并重写对应方法（最好使用对应元素的渲染器，如Div则使用DivRenderer）
        ParagraphRenderer pRenderer = new ParagraphRenderer(paragraph) {
            /***
             * 重新方法，这个方法老牛逼了，这个方法可以对这个元素进行绘画和定制。
             * @param drawContext 绘制上下文对象
             */
            @Override
            public void draw(DrawContext drawContext) {
                // 获取当前元素的位置信息和大小信息
                Rectangle rect = getOccupiedAreaBBox();
                // 获取画布PdfCanvas画布，这种画布常用于图形绘画
                PdfCanvas pdfCanvas = drawContext.getCanvas();
                // 创建画笔的扩展信息，并添加到画布中（为中间长方形设置补充样式）
                PdfExtGState pdfExtGState = new PdfExtGState();
                pdfExtGState.setLineWidth(1)        // 设置线粗
                        .setFillOpacity(.5f)    // 设置填充透明度
                        .setLineJoinStyle(PdfCanvasConstants.LineCapStyle.ROUND);
                // 对这个元素绘画一个双对角线“X”
                pdfCanvas.saveState()
                        .setLineWidth(2)                    // 设置线宽
                        .setStrokeColor(ColorConstants.RED) // 设置描边颜色
                        // 绘画元素内的“X”；-3和+3是微调画笔，因为它是有个圆角
                        .moveTo(rect.getLeft() + 3, rect.getTop() - 3)
                        .lineTo(rect.getRight() - 3, rect.getBottom() + 3)
                        .moveTo(rect.getRight() - 3, rect.getTop() - 3)
                        .lineTo(rect.getLeft() + 3, rect.getBottom() + 3)
                        .fillStroke()
                        .stroke()   // 打断上一次画笔，开始下一个画笔
                        // 绘制中间的长方形
                        .moveTo(rect.getX(), rect.getY())
                        // 计算中间长方形坐标点和大小
                        .rectangle(
                                new Rectangle(rect.getX() + rect.getWidth() / 4,
                                        rect.getY() + rect.getHeight() / 4,
                                        rect.getWidth() / 2,
                                        rect.getHeight() / 2)
                        )
                        .setFillColor(ColorConstants.BLACK)   // 设置填充色
                        .setExtGState(pdfExtGState)
                        .fillStroke()
                        .stroke().restoreState();
                // 通过高级画布设置文字信息
                // 设置pdfCanvas和当前高级画布的范围Rectangle
                Canvas canvas = new Canvas(pdfCanvas, rect);
                canvas.setFont(font)
                        //.setBold() 
                        .setFontColor(ColorConstants.RED)
                        .setFontSize(30);
                // 为画布设置文字
                canvas.showTextAligned("无法查看",
                        rect.getX() + rect.getWidth() / 2,
                        rect.getY() + rect.getHeight() / 2 + 5,
                        TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
                // 关闭高级画布
                canvas.close();
                // 一定要留着，要不然如上的段落一点样式都没了，留着它，让它绘画段落特有的样式
                // 比如我们通过方法设置的边框、文字等等，不加就代表这个元素就执行我们的绘图样式。
                super.draw(drawContext);
            }
        };
        // 将渲染器设置到段落中
        paragraph.setNextRenderer(pRenderer);
        // 添加元素并关闭文档
        document.add(paragraph);
        document.close();
    }



}
