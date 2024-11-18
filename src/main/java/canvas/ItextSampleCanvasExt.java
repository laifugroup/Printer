package canvas;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
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
public class ItextSampleCanvasExt {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_扩展_水印.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 创建画笔的扩展信息，并添加到画布中
        PdfExtGState pdfExtGState = new PdfExtGState();
        pdfExtGState.setLineWidth(1)        // 设置线粗
                .setStrokeOpacity(.3f)  // 设置描边透明度
                .setFillOpacity(.3f)    // 设置填充透明度
                .setLineJoinStyle(PdfCanvasConstants.LineCapStyle.ROUND);
        // 设置画布的一些信息
        pdfCanvas.setExtGState(pdfExtGState);
        pdfCanvas.setFillColor(new DeviceRgb(255, 0, 0));  // 填充色
        pdfCanvas.setStrokeColor(new DeviceRgb(0, 0, 255));// 矩形轮廓描边颜色
        // 创建文本和矩形
        pdfCanvas.beginText().moveText(20, 40)
                .setFontAndSize(font, 60)
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .showText("您好，蚂蚁小哥！")
                .endText();
        // 创建矩形
        pdfCanvas.saveState()
                .rectangle(300, 10, 150, 100)
                .setLineWidth(5)
                .fillStroke()   // 填充+构建轮廓
                .stroke().restoreState();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
