package basic;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class ItextPdfSimpleBg {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./简单示例_bg_simple.pdf"));
        // 初始化文档
        Document document = new Document(pdf);
        // 创建第一个段落，并设置段落内容
        Paragraph paragraph1 =
                new Paragraph("Thank you for reading Ant Brother's blog");
        // 段落设置
        paragraph1
                .setWidth(450)
                .setHeight(120)
                .setFontSize(30)
                .setBackgroundColor(new DeviceRgb(255, 0, 0), .2f);
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.close();
    }

//    设置背景（有如下几种方法）：
//    setBackgroundColor(color)
//    setBackgroundColor(color, opacity)
//    setBackgroundColor(color, extraLeft, extraTop, extraRight, extraBottom)
//    setBackgroundColor(color, opacity, extraLeft, extraTop, extraRight, extraBottom)
//    参数说明：
//    color：背景颜色
//    opacity：透明度；可选值0~1，0代表透明、1代表不透明、.5f代表半透明
//    extraLeft, extraTop, extraRight, extraBottom：
//    四个参数分别表示文本框的额外左内边距、上内边距、右内边距和下内边距。
//    它们用于指定文本框边界与背景颜色边界之间的额外空间，以便在文本框周围留出一定的间距。
//    这些额外的内边距可以帮助控制文本框的布局和外观。
//    示例：
//    setBackgroundColor(new DeviceRgb(255, 0, 0),.2f,40,20,30,40);



}
