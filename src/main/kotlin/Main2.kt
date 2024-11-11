
import com.spire.pdf.PdfDocument;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

fun main(args: Array<String>) {
    //Create a PrinterJob object which is initially associated with the default printer

    val printerJob = PrinterJob.getPrinterJob()

    // Create a PageFormat object and set it to a default size and orientation
    val pageFormat = printerJob.defaultPage()

    //Return a copy of the Paper object associated with this PageFormat
    val paper = pageFormat.paper

    //Set the imageable area of this Paper
    paper.setImageableArea(0.0, 0.0, pageFormat.width, pageFormat.height)

    //Set the Paper object for this PageFormat
    pageFormat.paper = paper

    //Create a PdfDocument object
    val pdf = PdfDocument()

    //Load a PDF file
    pdf.loadFromFile("C:\\Users\\leyuan\\Desktop\\222.pdf")

    //Call painter to render the pages in the specified format
    printerJob.setPrintable(pdf, pageFormat)

    //Execute printing
    try {
        printerJob.print()
    } catch (e: PrinterException) {
        e.printStackTrace()
    }
}