package basic;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.IOException;

public class ItextPdfDiv {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        Document document = new Document(new PdfDocument(new PdfWriter("./基本示例_div.pdf")));
        // 构建一个盒子容器
        Div divA = new Div();
        divA
                .setWidth(300)  // 设置元素宽
                .setHeight(100) // 设置元素高
                .setBorder(new DashedBorder(2))     // 设置边框
                .setBorderRadius(new BorderRadius(10))    // 设置圆角
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // 元素内的内容垂直居中
                .setTextAlignment(TextAlignment.CENTER)   // 文本元素水平居中（可以继承到子类）
                .setHorizontalAlignment(HorizontalAlignment.CENTER); // 设置div元素水平居中
        // 构建一个段落
        Paragraph paragraph = new Paragraph("Hello World！");
        paragraph
                .setWidth(150)      // 宽
                .setHeight(50)     // 高
                .setBorder(new SolidBorder(2))  // 边框
                .setVerticalAlignment(VerticalAlignment.MIDDLE)      // 段落内的内容垂直居中
                .setHorizontalAlignment(HorizontalAlignment.CENTER); // 段落元素水平居中
        // 段落内的文本居中
        divA.add(paragraph);
        // 把元素添加到文档里，并关闭文档
        document.add(divA);
        document.close();
    }

//    关于DIV元素中特有的方法：
//    setFillAvailableArea(boolean fillArea)
//    若当前Div元素未设置高，那么它会占据当前页面可用区域中的所有剩余空间。
//    setFillAvailableAreaOnSplit(boolean fillAreaOnSplit)
//    定义分区是否应占用可用区域中的所有剩余空间，以防该区域已被分割，
//    并且它是该区域分割部分中的最后一个元素。

}
