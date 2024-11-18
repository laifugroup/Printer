package basic;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.IOException;

public class ItextGenPdfTableItem {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        Document document = new Document(new PdfDocument(new PdfWriter("./基本示例_表格_添加元素.pdf")));
        // 构建表格（lie1~3代表表格列的宽度百分比，此时正好有3列）
        UnitValue lie1 = new UnitValue(UnitValue.PERCENT, 20);
        UnitValue lie2 = new UnitValue(UnitValue.PERCENT, 30);
        UnitValue lie3 = new UnitValue(UnitValue.PERCENT, 50);
        UnitValue[] col = {lie1, lie2, lie3};
        Table table = new Table(col);
        table.useAllAvailableWidth();   // 表格占用当前页面的所有宽度
        // 添加表头
        table.addHeaderCell(new Cell().add(new Paragraph("header1")));
        table.addHeaderCell(new Cell().add(new Paragraph("header2")));
        table.addHeaderCell(new Cell().add(new Paragraph("header3")));

        // 添加数据行
        // 表格添加第一格元素（使用Cell方式）
        table.addCell(new Cell().add(new Paragraph("1")));
        // 表格添加第二格元素（使用Image格式）
        // 创建ImageData对象（图片二进制数组、url方式都行）、并构建成图像
        ImageData imageData = ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG");
        Image image = new Image(imageData);
        image.setWidth(70);
        image.setHeight(40);
        table.addCell(image);
        // 表格添加第三格元素（使用String格式）
        table.addCell("String Type");
        // 表格添加第四格元素（使用BlockElement格式）
        Div div = new Div()
                .setBorder(new SolidBorder(1))
                .setWidth(70).setHeight(40)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBackgroundColor(new DeviceRgb(255, 25, 14), .5f)
                .add(new Paragraph("Hello"));
        table.addCell(div);
        // 单元格添加第五格元素(使用Image格式)
        Image image1 = new Image(ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG"));
        image1.setWidth(40);
        image1.setHeight(60);
        Cell cell = new Cell().add(image1).setPaddingLeft(20);
        table.addCell(cell);
        // 单元格添加第六格元素（使用BlockElement格式）
        table.addCell(new Cell().add(new Paragraph("6")));
        // 把元素添加到文档里，并关闭文档
        document.add(table);
        document.close();
    }


}
