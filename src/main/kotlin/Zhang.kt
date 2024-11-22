import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.layout.Document
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val dest = "./company_stamp.pdf"

    try {
        // 创建 PDF 文档
        val writer = PdfWriter(dest)
        val pdfDoc = PdfDocument(writer)
        val document = Document(pdfDoc)
        pdfDoc.addNewPage(PageSize.A4)

        // 获取 PdfCanvas
        val canvas = PdfCanvas(pdfDoc.firstPage)

        // 绘制印章外边框
        val centerX = 205.00
        val centerY = 240.00
        val a = 40f // 长半轴
        val b = 20f // 短半轴

        // 绘制外椭圆
        canvas.setLineWidth(1f)
        canvas.setStrokeColor(ColorConstants.RED)
        canvas.ellipse(centerX - a, centerY - b, centerX + a, centerY + b)
        canvas.stroke()

        // 绘制内椭圆
        canvas.setLineWidth(0.5f)
        canvas.ellipse(centerX - a + 2, centerY - b + 2, centerX + a - 2, centerY + b - 2)
        canvas.stroke()

        // 设置中文字体
        val font= PdfFontFactory.createFont("c://windows//fonts//msyh.ttc,0")
        canvas.setFontAndSize(font, 7f)
        canvas.setFillColor(ColorConstants.RED)

        // 文本内容
        val title = "全国统一发票监制章"
        val lowerText = "新疆维吾尔自治区税务局"

        // 在上半部分均匀分散文本
        drawTextOnEllipse(canvas, title, centerX.toFloat(), centerY.toFloat(), a, b, 0, 1)

        // 在下半部分均匀分散文本
        drawTextOnEllipse(canvas, lowerText, centerX.toFloat(), centerY.toFloat(), a, b, 1, 2)

        // 关闭文档
        document.close()
        println("PDF 创建成功，文件路径: $dest")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// 在椭圆上均匀分散文本
fun drawTextOnEllipse(canvas: PdfCanvas, text: String, centerX: Float, centerY: Float, a: Float, b: Float, startQuadrant: Int, endQuadrant: Int) {
    val numChars = text.length
    val angleStep = PI / (2 * numChars) // 每个字符的角度间隔

    for (i in text.indices) {
        val angle = angleStep * (i + numChars * startQuadrant / 2) // 根据象限分配角度
        val x = centerX + a * cos(angle)
        val y = centerY + b * sin(angle)

        // 绘制文本
        canvas.beginText()
        canvas.setTextMatrix(x.toFloat(), y.toFloat())
        canvas.showText(text[i].toString())
        canvas.endText()
    }
}