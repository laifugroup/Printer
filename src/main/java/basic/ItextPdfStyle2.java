package basic;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;

public class ItextPdfStyle2 {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter(".//基本示例_style2.pdf"));
        Document document = new Document(pdf);
        // 创建第一个段落
        Paragraph paragraph1 = new Paragraph();
        // 我们也可以不用直接加入到段落，而是先通过new Text()创建文件，并加入
        // new Text()的牛逼之处就是可以对里面的每个字符设置样式
        paragraph1.add(new Text("蚂蚁小").setFontSize(25).setCharacterSpacing(25 * 2));
        paragraph1.add(new Text("哥").setFontSize(25));
        paragraph1.setBold()
                .setFontSize(25)
                .setFont(PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H"))
                .setTextAlignment(TextAlignment.CENTER)
                .setBorder(new SolidBorder(1));
        // 创建第二个段落，并设置段落内容
        Paragraph paragraph2 =
                new Paragraph("蚂蚁小哥");
        paragraph2
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(60)
                .setFont(PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H"))
                .setFontColor(new DeviceRgb(255, 0, 255), .5f)
                .setStrokeWidth(3f) // 设置字体元素的轮廓大小
                .setStrokeColor(ColorConstants.BLUE) // 设置字体元素的轮廓颜色
                // 开启文本渲染模式（开启填充加描边）
                .setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.FILL_STROKE)
                .setBorder(new SolidBorder(1));
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.add(paragraph2);
        document.close();
    }




}
