package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;

import java.io.IOException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasTextMore {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_画文字_更多效果.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 为画布创建坐标系点每个间隔10*10
        PdfExtGState pdfExtGState = new PdfExtGState();
        pdfExtGState.setStrokeOpacity(.3f); // 描边透明的
        for (int x = 0; x < 700; x += 10) {
            for (int y = 0; y < 400; y += 10) {
                pdfCanvas.saveState().setStrokeColor(ColorConstants.GRAY)
                        .setExtGState(pdfExtGState)                   // 设置补充信息
                        .setLineDash(1, 1, 0)   // 设置虚线模式
                        .moveTo(x, y).lineTo(x, y + 10)
                        .moveTo(x - 10, y).lineTo(x + 10, y)
                        .stroke().restoreState();
            }
        }
        // ===== 未发生改变的文本信息 =====
        pdfCanvas.beginText().moveText(30, 40)
                .setFontAndSize(font, 20)
                .showText("您好，蚂蚁小哥；Hello World！")
                .endText();
        // ===== 文本的偏移（可以画如楼梯状文本段落） =====
        pdfCanvas.beginText().moveText(380, 20)
                .setFontAndSize(font, 12)
                .showText("我是蚂蚁小哥A！")
                .moveTextWithLeading(10, 10)
                .newlineShowText("我是蚂蚁小哥B！")
                .moveTextWithLeading(10, 10)
                .newlineShowText("我是蚂蚁小哥C！")
                .endText();
        // ===== 关于文本的缩放模式 =====
        pdfCanvas.beginText()
                .moveText(30, 60)
                .setFontAndSize(font, 20)
                .setHorizontalScaling(80)   // 设置缩放80，水平增大或缩小，高度不变
                .showText("缩放，蚂蚁小哥；Hello World！")
                .endText();
        // ===== 关于文本的垂直偏移 =====
        pdfCanvas
                .beginText()
                .moveText(30, 40)
                .setFontAndSize(font, 20)
                .setFillColor(ColorConstants.RED)
                // 文本原本是和原文在一个位置，但是设置了Rise下移了20磅，正数上升
                .setTextRise(-20)
                .setHorizontalScaling(100)
                .showText("您好，蚂蚁小哥；Hello World！")
                .endText();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
