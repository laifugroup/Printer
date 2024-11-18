package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
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
public class ItextSampleCanvasTextMut {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_画文字_多行.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        pdfCanvas.beginText()
                .moveText(30, 120)            // 设置文本在画布的指定位置
                .setFontAndSize(font, 16)     // 设置文本的样式和大小
                .setCharacterSpacing(5)            // 设置字符间的间距
                .setColor(new DeviceRgb(0, 0, 255), true) // 设置填充色
                .setLeading(20) // 设置垂直文本的间距（多行文本一定要设置）
                .showText("披着西装穿草鞋——土洋结合")        // 第一行文本显示
                .newlineText()                             // 使文本换行
                .showText("鸡披袍子狗戴帽——衣冠禽兽")        // 第二行文本显示
                .newlineShowText("三个小鬼丢了两——丢魂落魄") // 第三行文本显示
                .newlineShowText("坟地里睡个酒鬼——醉生梦死") // 第四行文本显示
                .endText();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
