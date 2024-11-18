package basic;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Div;

import java.io.IOException;


public class ItextPdfPosition {


    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./基本示例_定位_pos.pdf"));
        pdf.addNewPage(new PageSize(600, 600));
        // 初始化文档
        Document document = new Document(pdf);
        // 创建盒子，子元素
        Div dvZi = new Div();
        // 设置样式
        dvZi
                .setWidth(50)      // 元素款
                .setHeight(50)      // 元素高
                .setRelativePosition(50, 20, 0, 0) // 相对定位
                .setBackgroundColor(new DeviceRgb(187, 255, 255));
        // 创建盒子，父元素
        Div dvFu = new Div();
        // 把子元素装进去
        dvFu.add(dvZi);
        dvFu.setHeight(200)
                .setFixedPosition(1, 100, 50, 200) // 绝对定位
                .setBorder(new SolidBorder(2));
        // 把盒子添加到文档里，并关闭文档
        document.add(dvFu);
        document.close();
    }

}
