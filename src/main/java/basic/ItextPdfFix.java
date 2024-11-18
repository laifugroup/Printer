package basic;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

/**
 *
 *
 * '设置元素的绝对定位信息（定位从页的左下角开始）：'
 *     setFixedPosition(float left, float bottom, float width)
 *     setFixedPosition(float left, float bottom, UnitValue width)
 *     setFixedPosition(int pageNumber, float left, float bottom, float width)
 *     setFixedPosition(int pageNumber, float left, float bottom, UnitValue width)
 *     参数说明：
 *         left：元素左边缘距离页面左边缘的距离。（相当于X）
 *         bottom：元素底部距离页面底部的距离。（相当于Y）
 *         width：元素的宽度，可以是磅(pt)、百分比。
 *         pageNumber：元素所在的页码，页码从1开始计数。
 *     注意：
 *         1：把整个页面看做成一个坐标系，坐标系的开始是从左下角开始(0,0)。
 *
 * '设置元素的相对定位信息（定位找到上一级元素的左上角开始）：'
 *     setRelativePosition(float left, float top, float right, float bottom)
 *         上下左右四边的定位值
 *
 */
public class ItextPdfFix {


    public static void main(String[] args) throws IOException, IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./基本示例_定位_fix.pdf"));
        pdf.addNewPage(new PageSize(500, 200));
        // 初始化文档
        Document document = new Document(pdf);
        // 创建第一个段落，并设置段落内容
        Paragraph paragraph1 = new Paragraph("感谢您阅读蚂蚁小哥的博客！");
        // 获取页面的大小
        Rectangle pageSize = pdf.getPage(1).getPageSize();
        // 需要将元素定位到页面的中间（计算参数）
        float elHeight = 50;
        float elWidth = 200;
        float left = pageSize.getWidth() / 2 - elWidth / 2;
        float bottom = pageSize.getHeight() / 2 - elHeight / 2;
        // 设置样式
        paragraph1
                .setFontSize(14)    // 字体大小
                .setHeight(50)     // 元素高
                // 设置绝对定位
                .setFixedPosition(1, left, bottom, elWidth)
                // 字体颜色、字体、背景颜色设置
                .setFontColor(new DeviceRgb(255, 0, 0), .8f)
                .setFont(PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H"))
                .setBackgroundColor(new DeviceRgb(187, 255, 255));
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.close();
    }

}
