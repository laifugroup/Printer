import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.PageSize
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Canvas
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.HorizontalAlignment
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.VerticalAlignment
import java.io.File
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    val pdfFile = File("./a_seal.pdf")
    val pdfWriter = PdfWriter(pdfFile)
    val pdfDocument = PdfDocument(pdfWriter)
    val page = pdfDocument.addNewPage(PageSize(200f, 200f))
    val pdfCanvas = PdfCanvas(page)

    // Draw the outer red circle (2px)
    pdfCanvas.circle(100.00, 100.00, 96.00)
    pdfCanvas.setLineWidth(2f)
    pdfCanvas.setStrokeColor(com.itextpdf.kernel.colors.ColorConstants.RED)
    pdfCanvas.stroke()

    // Draw the inner red circle (1px)
    pdfCanvas.circle(100.00, 100.00, 92.00)
    pdfCanvas.setLineWidth(1f)
    pdfCanvas.setStrokeColor(com.itextpdf.kernel.colors.ColorConstants.RED)
    pdfCanvas.stroke()

    // Add the text and anti-counterfeiting number
    val canvas = Canvas(pdfCanvas, page.pageSize)
    val textList = listOf("成", "都", "文", "化", "科", "技", "有")
    val antiCounterfeitingNumber = "12323232"
    val font = PdfFontFactory.createFont("c://windows//fonts//msyh.ttc,0")

    // Add the text
    for (i in 0 until 7) {
        val angle = i * PI / 2.4 // 270 degrees / 7 characters
        val x = 100f + 80f * cos(angle)
        val y = 100f + 80f * sin(angle)

        val paragraph = Paragraph(textList[i]).setFont(font)
        paragraph.setHorizontalAlignment(HorizontalAlignment.CENTER)
        paragraph.setVerticalAlignment(VerticalAlignment.MIDDLE)
        canvas.showTextAligned(paragraph, x.toFloat(), y.toFloat(), i, TextAlignment.CENTER, VerticalAlignment.MIDDLE, angle.toFloat())
    }

    // Add the anti-counterfeiting number
    val antiCounterfeitingNumberParagraph = Paragraph(antiCounterfeitingNumber)
    antiCounterfeitingNumberParagraph.setHorizontalAlignment(HorizontalAlignment.CENTER)
    antiCounterfeitingNumberParagraph.setVerticalAlignment(VerticalAlignment.BOTTOM)
    canvas.add(antiCounterfeitingNumberParagraph)

    pdfDocument.close()
}