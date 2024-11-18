package basic;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class ItextPdfStyle {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./基本示例_style.pdf"));
        Document document = new Document(pdf);
        // 创建第一个段落，并设置段落内容
        Paragraph paragraph1 =
                new Paragraph("Thank you for reading Ant Brother's blog");
        paragraph1
                .setFontSize(20)
                .setUnderline(1.5f, 20 / 4f)
                .setBorder(new SolidBorder(1));
        // 创建第二个段落，并设置段落内容
        Paragraph paragraph2 =
                new Paragraph("Thank you for reading Ant Brother's blog");
        paragraph2
                .setFontSize(20)
                // 这里的加载因子需要调试，具体我也不知道咋计算，
                // 反正这个要是设置好，他会根据字体大小自动变粗线
                .setUnderline(ColorConstants.BLUE, .5f, .1f,
                        20 / 4f, .05f, PdfCanvasConstants.LineCapStyle.ROUND)
                .setBorder(new SolidBorder(1));
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.add(paragraph2);
        document.close();
    }


}
