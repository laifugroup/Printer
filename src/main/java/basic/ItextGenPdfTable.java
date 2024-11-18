package basic;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;

public class ItextGenPdfTable {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        Document document = new Document(new PdfDocument(new PdfWriter("./基本示例_表格.pdf")));
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
        table.addCell(new Cell().add(new Paragraph("1")));
        table.addCell(new Cell().add(new Paragraph("2")));
        table.addCell(new Cell().add(new Paragraph("3")));
        table.addCell(new Cell().add(new Paragraph("4")));
        table.addCell(new Cell().add(new Paragraph("5")));
        table.addCell(new Cell().add(new Paragraph("6")));
        // 把元素添加到文档里，并关闭文档
        document.add(table);
        document.close();
    }



}
