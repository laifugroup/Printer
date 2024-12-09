package basic;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;

public class ItextPdfBlock {

    public static void main(String[] args) throws IOException {
        // 图片路径并加载到Image对象中
        String imgPathA = "C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG";
        String imgPathB = "C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\bg_hometop.png";
        Image imageA = new Image(ImageDataFactory.create(imgPathA));
        // 设置图片宽（pt）、设置图片宽（pt）
        imageA.setWidth(120);
        imageA.setHeight(80);
        Image imageB = new Image(ImageDataFactory.create(imgPathB));
        imageB.setWidth(120);
        imageB.setHeight(80);
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./基本示例_块.pdf"));
        // 初始化文档
        Document document = new Document(pdf);
        // 创建第一个段落并添加内容
        Paragraph paragraph = new Paragraph();
        paragraph.add("This is a beautiful picture！");
        paragraph.add("\n"); // 在此处强制换行
        paragraph.add(imageA);
        paragraph.add("\n");
        // 这里我单独设置了个Text传入进去
        Text text = new Text("This is a Not good-looking picture！")
                //.setItalic()    // 倾斜
                .setBackgroundColor(new DeviceRgb(255, 0, 0));
        paragraph.add(text);
        paragraph.add("\n");
        paragraph.add(imageB);
        // 设置样式
        paragraph.setHeight(210)
                .setFirstLineIndent(100) // 设置缩进 120pt（120/12），文字默认12，缩减10个字符
                .setTextAlignment(TextAlignment.CENTER) // 居中
                .setBorder(new SolidBorder(2))  // 边框
                .setBackgroundColor(new DeviceRgb(187, 255, 255));
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph);
        document.close();
    }


}
