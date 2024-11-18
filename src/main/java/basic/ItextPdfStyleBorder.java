package basic;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;

import java.io.IOException;

public class ItextPdfStyleBorder {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./简单示例_border.pdf"));
        // 初始化文档
        Document document = new Document(pdf);
        // 创建第一个段落，并设置段落内容
        Paragraph paragraph1 =
                new Paragraph("Thank you for reading Ant Brother's blog");
        // 设置边框样式和圆角样式
        Border border = new SolidBorder(new DeviceRgb(255, 0, 0), 10, .3f);
        BorderRadius borderRadius = new BorderRadius(25);
        // 段落设置
        paragraph1
                .setWidth(450)
                .setHeight(120)
                .setFontSize(30)
                .setBorder(border)              // 边框
                .setBorderRadius(borderRadius)  // 圆角
                .setBackgroundColor(new DeviceRgb(187, 255, 255));
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.close();
    }



}
