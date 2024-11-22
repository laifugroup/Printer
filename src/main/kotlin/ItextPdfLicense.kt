import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.AreaBreak
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.itextpdf.layout.properties.VerticalAlignment
import com.itextpdf.styledxmlparser.jsoup.nodes.Element
import kotlin.coroutines.CoroutineContext

/**
 *
 * https://gitee.com/wanghengjie563135/pdf
 * 签章
 * https://github.com/cat1004/PDF/blob/master/pdf/src/main/java/com/pdf/wordtopdf/Demo.java
 *
 */
fun main() {
    println("pdfbox! 222")
    pdfBoxGen()
}

fun pdfBoxGen(){
    // 创建并初始化一个PDF文档
    val pdf =  PdfDocument( PdfWriter("./基本示例_定位_pos_1.pdf"))

    pdf.addNewPage()
    // 初始化文档
    val document =  Document(pdf)


    document.setMargins(0f,0f,0f,0f)

    // 创建盒子，子元素
    val dvZi =  Div().apply {
        this.width=UnitValue.createPointValue(200f)
        this.height=UnitValue.createPointValue(200f)
       // this.setRelativePosition(10f, 10f, 10f, 10f)
        this.setBackgroundColor(DeviceRgb(187, 187, 255))
        this.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        this.setVerticalAlignment(VerticalAlignment.BOTTOM)
    }
    val xm =  Paragraph("setRelative");
    xm
       // .setWidth(90f)
        .setBorder(SolidBorder(2f))  // 边框
        //.setVerticalAlignment(VerticalAlignment.MIDDLE)      // 段落内的内容垂直居中
        .setHorizontalAlignment(HorizontalAlignment.RIGHT) // 段落元素水平居中
    dvZi.add(xm)

    //   laDivPrint.setPosition(PdfDiv.PositionType.RELATIVE);
    // 创建盒子，父元素 把子元素装进去
    val dvFu =  Div().apply {
        this.width=UnitValue.createPointValue(200f)
        this.height=UnitValue.createPointValue(200f)
       // this.setFixedPosition(1, 16f, 16f, 200f)
        this.setBackgroundColor(DeviceRgb(166, 255, 255))
        this.setBorder(SolidBorder(2f))
        this.setHorizontalAlignment(HorizontalAlignment.RIGHT)
        this.setVerticalAlignment(VerticalAlignment.BOTTOM)
    }
    val paragraph =  Paragraph()

    val text =  Text("Hello World！")
        .setItalic()    // 倾斜
        .setBackgroundColor( DeviceRgb(255, 0, 0));
    paragraph.add(text)
    paragraph
        //.setWidth(90f)
        .setBorder(SolidBorder(2f))  // 边框
        .setVerticalAlignment(VerticalAlignment.MIDDLE)      // 段落内的内容垂直居中
       .setHorizontalAlignment(HorizontalAlignment.RIGHT); // 段落元素水平居中
    paragraph.setTextAlignment(TextAlignment.RIGHT)

    dvFu.add(paragraph)
    // 把盒子添加到文档里，并关闭文档
    document.add(paragraph)
    document.add(dvFu)
    document.add(dvZi)

    document.close()
}
