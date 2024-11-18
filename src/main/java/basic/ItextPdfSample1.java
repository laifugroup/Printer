package basic;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.colors.gradients.AbstractLinearGradientBuilder;
import com.itextpdf.kernel.colors.gradients.GradientColorStop;
import com.itextpdf.kernel.colors.gradients.StrategyBasedLinearGradientBuilder;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;

import java.io.IOException;

public class ItextPdfSample1 {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        Document document = new Document(new PdfDocument(new PdfWriter("./基本示例_例子_学生证.pdf")));
        // 创建线性渐变，使用代码的方式，设置颜色并设置渐变方向
        AbstractLinearGradientBuilder gradientBuilder =
                new StrategyBasedLinearGradientBuilder()
                        .setGradientDirectionAsCentralRotationAngle(60 * Math.PI / 180)
                        .addColorStop(new GradientColorStop(new DeviceRgb(51, 153, 255).getColorValue()))
                        .addColorStop(new GradientColorStop(new DeviceRgb(255, 250, 250).getColorValue()));
        BackgroundImage build = new BackgroundImage.Builder()
                .setLinearGradientBuilder(gradientBuilder)  // 线性渐变
                .setBackgroundBlendMode(BlendMode.HARD_LIGHT)
                .build();
        // 构建一个只有两列的表格并设置常用样式
        float[] col = {90, 110};
        Table table = new Table(col)
                .setWidth(300)
                .setHeight(170)
                .setFixedLayout()   // 表格固定布局
                .setFont(PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H"))
                .setBackgroundImage(build);
        // 设置标题
        Paragraph hd = new Paragraph("哈芙加里墩职业技术学院")
                .setBold()
                .setFontSize(18)
                .setMarginRight(30)
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontColor(new DeviceRgb(102, 0, 0));
        table.addHeaderCell(new Cell(1, 2).add(hd).setBorder(Border.NO_BORDER));
        // 设置左边图片头像
        Image image = new Image(ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG"));
        image.setWidth(80).setHeight(100)
                .setHorizontalAlignment(HorizontalAlignment.CENTER); // 行居中
        Cell cell = new Cell(4, 1)  // 合并单元格
                .add(image)
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        // 设置基本信息
        // 学生证字段
        Paragraph p1 = new Paragraph("学  生  证")
                .setBold()
                .setFontSize(26)
                .setPaddings(-5, 0, 0, 20)
                .setStrokeWidth(3f)                  // 设置字体元素的轮廓大小
                .setStrokeColor(ColorConstants.BLUE) // 设置字体元素的轮廓颜色
                .setFontColor(ColorConstants.BLUE, .5f);
        Cell cell1 = new Cell().add(p1)
                .setBorder(Border.NO_BORDER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell1);
        // 学生证右边信息
        // ## 设置一些通用的Style样式
        Style style = new Style().setBold()
                .setPaddingLeft(20).setPaddingTop(-5)
                .setFontColor(new DeviceRgb(51, 100, 255));
        // 姓名
        Paragraph xm = new Paragraph("姓  名：张三丰").addStyle(style);
        table.addCell(new Cell().add(xm).setBorder(Border.NO_BORDER));
        // 班级
        Paragraph bj = new Paragraph("班  级：初二四班").addStyle(style);
        table.addCell(new Cell().add(bj).setBorder(Border.NO_BORDER));
        // 学号
        Paragraph bh = new Paragraph("编  号：No20240901042").addStyle(style);
        table.addCell(new Cell().add(bh).setBorder(Border.NO_BORDER));
        // 把元素添加到文档里，并关闭文档
        document.add(table);
        document.close();
    }


}
