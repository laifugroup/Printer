package basic;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;

import java.io.IOException;

public class ItextGenPdfTableCap {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        Document document = new Document(new PdfDocument(new PdfWriter("./基本示例_表格_合并.pdf")));
        // 一定要记住我创建的是5列的表格
        // 构建一个使用百分比创建的表格，并占满当前页所有宽
        Table table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        // 构建表格标题
        Div div = new Div().add(new Paragraph("标题：演示单元格合并"))
                .setBorder(new DashedBorder(1))
                .setTextAlignment(TextAlignment.CENTER)
                .setFont(PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H"))
                .setFontColor(DeviceRgb.RED);
        table.setCaption(div, CaptionSide.TOP);
        // 添加表头
        table.addHeaderCell(new Cell().add(new Paragraph("header1")));
        table.addHeaderCell(new Cell().add(new Paragraph("header2")));
        table.addHeaderCell(new Cell().add(new Paragraph("header3")));
        table.addHeaderCell(new Cell().add(new Paragraph("header4")));
        table.addHeaderCell(new Cell().add(new Paragraph("header5")));
        // 添加页脚（故意放中间，其实这个页脚是一定会在最后面的）
        table.addFooterCell(new Cell(1,5).add(new Paragraph("footer1")));
        // 创建单元格（单元格高以当前行最高的单元格高为准）
        // # 在1行上合并了2个单元格
        Cell top1 = new Cell(1, 2);
        top1.addStyle(new Style().setBackgroundColor(ColorConstants.RED, .2f)
                .setHeight(30));
        table.addCell(top1);
        // # 在1行上合并了3个单元格
        Cell top2 = new Cell(1, 3).add(new Paragraph("Top2"));
        top2.setBackgroundColor(ColorConstants.YELLOW);
        table.addCell(top2);
        // # 在2行上合并1个单元格
        Cell sn = new Cell(2, 1).add(new Paragraph("S/N"));
        sn.setBackgroundColor(ColorConstants.YELLOW);
        table.addCell(sn);
        // # 在1行上合并3个单元格
        Cell name = new Cell(1, 2).add(new Paragraph("Name"));
        name.setBackgroundColor(ColorConstants.CYAN);
        table.addCell(name);
        // # 在2行上合并2个单元格
        Cell age = new Cell(2, 2).add(new Paragraph("Age"));
        age.setBackgroundColor(ColorConstants.GRAY);
        table.addCell(age);
        // # 把空出来的单元格补上，看的出效果
        table.addCell(new Cell().add(new Paragraph("sup1")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("sup2")).setBorder(Border.NO_BORDER));
        // # 在3行上合并4个单元格，并在里面设置一个盒子（一般用于布局）
        Div div1 = new Div();
        div1.add(new Paragraph("Hello World！"))
                .setWidth(120)
                .setHeight(40)
                .setHorizontalAlignment(HorizontalAlignment.CENTER) // 左右居中
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // 文字上下居中
                .setTextAlignment(TextAlignment.CENTER) // 文字左右居中
                .setBackgroundColor(new DeviceRgb(25, 255, 200));
        Cell firstname = new Cell(3, 4).add(div1);
        firstname.setVerticalAlignment(VerticalAlignment.MIDDLE); // 单元格内容上下居中
        table.addCell(firstname);
        // # 把空出来的单元格补上，看的出效果，要不然合并的多行单元格高就和1行高一样
        table.addCell(new Cell().add(new Paragraph("sup1")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("sup2")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("sup3")).setBorder(Border.NO_BORDER));
        // 其它合并
        table.addCell(new Cell(1, 2).add(new Paragraph("fo1"))
                .setBackgroundColor(ColorConstants.GRAY));
        table.startNewRow(); // 强行换一行新的单元行
        table.addCell(new Cell(1, 3).add(new Paragraph("fo2"))
                .setBackgroundColor(ColorConstants.GRAY));
        table.startNewRow(); // 强行换一行新的单元行
        table.addCell(new Cell(1, 4).add(new Paragraph("fo3"))
                .setBackgroundColor(ColorConstants.GRAY));
        // 把元素添加到文档里，并关闭文档
        document.add(table);
        document.close();
    }


}
