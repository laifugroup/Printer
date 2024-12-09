import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.PageSize.A4
import com.itextpdf.kernel.pdf.*
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.LineSeparator
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import java.nio.charset.Charset

fun main() {
    val wp=WriterProperties()
    // 我这里是只允许打印
    wp.setStandardEncryption(null, "123456".toByteArray(Charset.forName("UTF-8")), EncryptionConstants.ALLOW_PRINTING, EncryptionConstants.DO_NOT_ENCRYPT_METADATA)
    val writer=PdfWriter("./le_yuan.pdf",wp)

    val pdfDocument =  PdfDocument(writer) //PDDocument
    val pdfPage=  pdfDocument.addNewPage(A4)
    // 获取文档信息对象
    val docInfo: PdfDocumentInfo = pdfDocument.getDocumentInfo()
    // 设置元数据信息
    docInfo.setCreator("纸上谈兵")
            .setAuthor("纸上谈兵项目组")
            .setSubject("纸上谈兵是一款现实战略游戏")
            .setTitle("纸上谈兵挑战赛")
    val document =  Document(pdfDocument,A4)

    val text2 =  Text("Hello World！")
       // //.setItalic()    // 倾斜
        .setBackgroundColor( DeviceRgb(255, 0, 0));
    // 添加一段中文（itext无法支持中文字体，需要设置字体）
    val font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H")
    //val font= PdfFontFactory.createFont("c://windows//fonts//msyh.ttc,0")
    //val bfChinese = BaseFont.createFont("c://windows//fonts//msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

    // 设置文本的字体、大小、颜色、背景颜色、对齐方式、垂直对其方式
    val title =  Paragraph("纸上谈兵挑战赛")
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

    val station =  Paragraph("小工具副业开发")
        .setFont(font)
        .setFontSize(32f)
        .setFontColor(ColorConstants.RED)
        .setTextAlignment(TextAlignment.CENTER)
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
    document.add(station)

    //.setFontColor(ColorConstants.RED)
    val content1 =  Paragraph("亮  swift UI学习1%：")
        .setFont(font)
        .setFontSize(16f)
       // //.setBold() 
        .setWordSpacing(1.2f)
        .setMarginTop(16f)

    val content2 =  Paragraph("恭喜您在2024年度“纸上谈兵”第241204001次挑战赛中获得胜利！")
        .setFont(font)
        .setFontSize(14f)
        .setFirstLineIndent(14f*2)


    val content3 =  Paragraph("“纸上谈兵”因您而更加闪耀，期待您在后续的比赛中精彩表现，取得好成绩！")
        .setFont(font)
        .setFontSize(14f)
        .setFirstLineIndent(14f*2)

    //.setFontColor(ColorConstants.RED)

    document.add(content1)
    document.add(content2)
    document.add(content3)

    val width=28.346f * 4.0f * 1.6f //40mm*1.2倍
    val marginBottom= 36f
    val marginRight= 36f

    val text =  Text("纸上谈兵项目组\n2024年12月04日")
        //.setFixedPosition()
        ////.setItalic()   // 倾斜

    val platform = Paragraph()
    platform.add(text)
    platform
        .setFont(font)
        .setFontSize(18f)
        .setTextAlignment(TextAlignment.CENTER) // 文本右对齐
        //.setBackgroundColor(DeviceRgb(177, 245, 238))
    // 计算文本的宽度
    val textWidth = width //platform.width // 获取段落的宽度
    // 设置平台的位置为页面的右下角
    val padding=0f
    platform.setFixedPosition(1, pdfDocument.defaultPageSize.width - textWidth-marginRight-padding, marginBottom+width/3, textWidth) // 根据需要调整位置


    val sign = Image(ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\zstbxmz.png"))
        .setWidth(width)
        .setHeight(width)
        //.setRotationAngle(45.00)
        .setOpacity(0.8f)
        .setFixedPosition(
            pdfDocument.defaultPageSize.width-marginRight-width, // x坐标：div的左边界 + (200-128)/2
            marginBottom  // y坐标：32 + (200-128)/2，使图片在div中垂直居中
        )

    document.add(platform)
    document.add(sign)

    pdfDocument.close()
}