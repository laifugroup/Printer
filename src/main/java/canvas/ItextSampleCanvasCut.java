package canvas;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;

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
public class ItextSampleCanvasCut {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_裁剪.pdf"));
        // 创建一个新页
        PdfPage pdfPage = pdfDocument.addNewPage();
        // 通过PDF页来构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 为画布创建坐标系点每个间隔10*10
        PdfExtGState pdfExtGState = new PdfExtGState();
        pdfExtGState.setStrokeOpacity(.3f); // 描边透明的
        for (int x = 0; x < pdfPage.getPageSize().getWidth(); x += 10) {
            for (int y = 0; y < pdfPage.getPageSize().getHeight(); y += 10) {
                pdfCanvas.saveState().setStrokeColor(ColorConstants.GRAY)
                        .setExtGState(pdfExtGState)                   // 设置补充信息
                        .setLineDash(1, 1, 0)   // 设置虚线模式
                        .moveTo(x, y).lineTo(x, y + 10)
                        .moveTo(x - 10, y).lineTo(x + 10, y)
                        .stroke().restoreState();
            }
        }
        // 绘画裁剪区域，只有裁剪区域内的内容才可以展现
        pdfCanvas.circle(110, 110, 60).eoClip().endPath();
        // 创建四个圆，分别构成一个“田”字型排列
        pdfCanvas.saveState()
                .setFillColor(new DeviceRgb(85, 107, 47))  // 设置路径填充色
                .setStrokeColor(new DeviceRgb(85, 107, 47))// 设置描边填充色
                .circle(60, 60, 50)
                .circle(160, 60, 50)
                .circle(160, 160, 50)
                .circle(60, 160, 50)
                .setLineWidth(2)
                .fillStroke()   // 填充+构建轮廓
                .stroke().restoreState();
        // 构建文字“招财进宝”
        pdfCanvas.beginText()
                .setFontAndSize(font, 20)
                .setFillColor(new DeviceRgb(255, 215, 0))
                // 将 招 移动到指定位置
                .moveText(75, 130).showText("招")
                // 将 财 移动到指定位置
                .moveTextWithLeading(50, 0) .newlineShowText("财")
                // 将 进 移动到指定位置
                .moveTextWithLeading(-50, -25).newlineShowText("进")
                // 将 宝 移动到指定位置
                .moveTextWithLeading(50, 0).newlineShowText("宝")
                .endText();
        // 释放画布。使用完画布后，请使用此方法。
        pdfCanvas.release();
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }



}
