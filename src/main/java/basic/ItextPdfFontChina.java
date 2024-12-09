package basic;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class ItextPdfFontChina {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./基本示例_font_chinese.pdf"));
        Document document = new Document(pdf);
        // 创建第一个段落，并设置段落内容
        Paragraph paragraph1 =
                new Paragraph("感谢您阅读蚂蚁小哥的博客！希望你每天都快乐，每天都赚大钱！");
        // 设置字体样式
        // 添加一段中文（itext无法支持中文字体，需要设置字体）
        // 使用Asian包提供的字体（中文简体）
        // PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 使用系统自带的（或者指定字体路径）
        //String fontPath1 = "D:\\No.300-ShangShouGuHuangTi-2.ttf"; // 网上下载的
        String fontPath2 = "C:\\Windows\\Fonts\\simfang.ttf";   // win自带的
        PdfFont font = PdfFontFactory.createFont(
                fontPath2,
                PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED, true);
        // 设置样式
        paragraph1
                //.setBold() 
                .setFontSize(30)
                .setFont(font)
                .setFontScript(Character.UnicodeScript.HAN)
                .setFontColor(new DeviceRgb(255, 0, 0), .2f)
                .setBorder(new SolidBorder(2));
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.close();
    }

}
