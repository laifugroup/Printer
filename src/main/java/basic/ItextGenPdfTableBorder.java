package basic;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DoubleBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;

public class ItextGenPdfTableBorder {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        Document document = new Document(new PdfDocument(new PdfWriter("./基本示例_表格_外边框.pdf")));
        // 构建一个使用百分比创建的表格，并占满当前页所有宽
        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        // 添加单元格
        for (int aw = 0; aw < 10; aw++) {
            table.addCell(new Cell().add(new Paragraph("hi" + aw))
                    .setBorder(Border.NO_BORDER));
        }
        table.setBorder(new DoubleBorder(2));
        // 把元素添加到文档里，并关闭文档
        document.add(table);
        document.close();
    }


}
