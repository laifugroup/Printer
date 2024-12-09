import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.colors.Color
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.PageSize.A4
import com.itextpdf.kernel.pdf.EncryptionConstants
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.WriterProperties
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.borders.SolidBorder
import com.itextpdf.layout.element.Div
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.itextpdf.layout.properties.VerticalAlignment
import org.apache.pdfbox.pdmodel.PDPageContentStream
import java.nio.charset.Charset

fun main() {
    val wp=WriterProperties()
    // 我这里是只允许打印
    wp.setStandardEncryption(null, "123456".toByteArray(Charset.forName("UTF-8")), EncryptionConstants.ALLOW_PRINTING, EncryptionConstants.DO_NOT_ENCRYPT_METADATA)
    val writer=PdfWriter("./le_yuan.pdf",wp)

    val pdfDocument =  PdfDocument(writer) //PDDocument
    val pdfPage=  pdfDocument.addNewPage(A4)
    val document =  Document(pdfDocument,A4)

    val text2 =  Text("Hello World！")
        ////.setItalic()    // 倾斜
        .setBackgroundColor( DeviceRgb(255, 0, 0));
    // 添加一段中文（itext无法支持中文字体，需要设置字体）
    //val font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H")
    val font= PdfFontFactory.createFont("c://windows//fonts//msyh.ttc,0")
    //val bfChinese = BaseFont.createFont("c://windows//fonts//msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

    // 设置文本的字体、大小、颜色、背景颜色、对齐方式、垂直对其方式
    val title =  Paragraph("乐园老年社交平台")
        .setFont(font)
        .setFontSize(48f)
        .setFontColor(ColorConstants.RED)
        //.setBackgroundColor(DeviceRgb(187, 255, 255))
        .setTextAlignment(TextAlignment.CENTER)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
    document.add(title)

    // 画直线（普通）
    document.add( LineSeparator( SolidLine(3f).apply {
        this.color= ColorConstants.RED
    }))

    val station =  Paragraph("中大站")
        .setFont(font)
        .setFontSize(32f)
        .setFontColor(ColorConstants.RED)
        .setTextAlignment(TextAlignment.CENTER)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
    document.add(station)

    //.setFontColor(ColorConstants.RED)
    val content1 =  Paragraph("亲爱的乐园友友：")
        .setFont(font)
        .setFontSize(16f)
        ////.setBold() 
        .setWordSpacing(1.2f)
        .setMarginTop(16f)

    val content2 =  Paragraph("恭喜您在2024年乐园节好声音海选比赛中成功晋级！")
        .setFont(font)
        .setFontSize(14f)
        .setFirstLineIndent(14f*2)


    val content3 =  Paragraph("乐园好声音的舞台因您而更加闪耀，期待您在后续的比赛中精彩表现，取得好成绩！")
        .setFont(font)
        .setFontSize(14f)
        .setFirstLineIndent(14f*2)

    //.setFontColor(ColorConstants.RED)

    document.add(content1)
    document.add(content2)
    document.add(content3)

    val dvFu =  Div().apply {
        this.width = UnitValue.createPercentValue(100f)
        this.height=UnitValue.createPointValue(200f)
        this.setFixedPosition(1, pdfDocument.defaultPageSize.width-200f-20f, 32f, 200f)
        this.setBackgroundColor(DeviceRgb(166, 255, 255))
        this.setBorder(SolidBorder(2f))
        this.setVerticalAlignment(VerticalAlignment.MIDDLE)
        this.setHorizontalAlignment(HorizontalAlignment.CENTER)
    }

    val paragraph1 =  Paragraph()
    val text =  Text("乐元社交平台\n2024年11月21日")
       // //.setItalic()    // 倾斜

    paragraph1.add("乐元社交平台\n2024年11月21日")
    paragraph1
        .setFont(font)
        .setFontSize(18f)
        //.setVerticalAlignment(VerticalAlignment.MIDDLE)      // 段落内的内容垂直居中
    //    .setHorizontalAlignment(HorizontalAlignment.RIGHT) // 段落元素水平居中
        //.setRelativePosition(0f,0f,0f,0f)
    //paragraph1.setTextAlignment(TextAlignment.RIGHT)
    paragraph1.setTextAlignment(TextAlignment.CENTER)
    paragraph1.setVerticalAlignment(VerticalAlignment.MIDDLE)
    paragraph1.setBackgroundColor(DeviceRgb(255, 245, 238))

    dvFu.add(paragraph1)

    val image1 = Image(ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\webwxgetmsgimg.jpg"))
        .setWidth(128f)
        .setHeight(128f)
        .setOpacity(0.8f)
        .setFixedPosition(
            pdfDocument.defaultPageSize.width-200f+36f, // x坐标：div的左边界 + (200-128)/2
            68f  // y坐标：32 + (200-128)/2，使图片在div中垂直居中
        )
    dvFu.add(image1)

    document.add(dvFu)

    pdfDocument.close()
}