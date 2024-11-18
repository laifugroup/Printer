package canvas;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
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
public class ItextSampleCanvasWaterPage {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_水印_page.pdf"));
        // 创建2张页面
        pdfDocument.addNewPage();
        pdfDocument.addNewPage();
        // 循环为页设置水印
        for (int i = 0; i < pdfDocument.getNumberOfPages(); i++) {
            PdfPage page = pdfDocument.getPage(i + 1);
            setPageWatermark(page, "@蚂蚁小哥", 140);
        }
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }
    /***
     * 为PDF页绘画水印
     * @param pdfPage PDF页对象
     * @param msg 水印信息
     * @param interval 水印间隔
     * @throws IOException 这里的异常是无法获取字体异常
     */
    public static void setPageWatermark(PdfPage pdfPage, String msg, int interval) throws IOException {
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        float width = pdfPage.getPageSize().getWidth();
        float height = pdfPage.getPageSize().getHeight();
        // 开始绘画水印
        for (int x = 0; x < pdfPage.getPageSize().getWidth(); x += interval) {
            for (int y = 0; y < pdfPage.getPageSize().getHeight(); y += interval) {
                Canvas canvas = new Canvas(pdfCanvas, new Rectangle(0, 0, width, height))
                        .setFontColor(ColorConstants.GRAY, .1f) // 颜色和透明度
                        .setFont(font)      // 文字样式
                        .setFontSize(20)    // 字体大小（具体可以改）
                        .showTextAligned(msg, x, y, TextAlignment.CENTER,
                                VerticalAlignment.MIDDLE, 19.5f);
                canvas.close();
            }
        }
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
    }


}
