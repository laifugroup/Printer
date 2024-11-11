import java.awt.print.PrinterJob
import java.io.FileInputStream
import javax.print.Doc
import javax.print.DocFlavor
import javax.print.PrintService
import javax.print.SimpleDoc
import javax.print.attribute.*
import javax.print.attribute.standard.Copies
import javax.print.attribute.standard.MediaSizeName
import javax.print.attribute.standard.Sides
import javax.print.event.PrintJobEvent
import javax.print.event.PrintJobListener


fun main() {
    println("Hello World! 222")
    print0()
    println("Hello World! 111")
}

// https://blog.csdn.net/qq_52010446/article/details/131465685
// https://jingyan.baidu.com/article/b2c186c86f9197c46ff6ff49.html

// https://blog.idrsolutions.com/how-to-print-pdf-files-from-java/

fun print0(){
    //  var printerJob: PrinterJob = PrinterJob.getPrinterJob()
    val printServices: Array<PrintService> = PrinterJob.lookupPrintServices()
    val fis = FileInputStream("C:\\Users\\leyuan\\Desktop\\222.pdf")
    val flavor: DocFlavor = DocFlavor.BYTE_ARRAY.AUTOSENSE
    val attributeSet= HashDocAttributeSet()
    val byteArray = "我是乐园".toByteArray(Charsets.UTF_8)
    val pdfDoc: Doc = SimpleDoc(byteArray, flavor, attributeSet)
    println( printServices[5].isDocFlavorSupported(DocFlavor.BYTE_ARRAY.AUTOSENSE))
    val printJob = printServices[5].createPrintJob()
    printJob.addPrintJobListener(PDFPrintJobListener())
    printJob.print(pdfDoc, getPrintRequestAttributeSet())
   fis.close()
}


private fun getPrintRequestAttributeSet(): PrintRequestAttributeSet {
    val printRequestAttributeSet: PrintRequestAttributeSet = HashPrintRequestAttributeSet()
    printRequestAttributeSet.add(Copies(1)) //份数
    printRequestAttributeSet.add(MediaSizeName.ISO_A4) //纸张
    // printRequestAttributeSet.add(Finishings.STAPLE);//装订
    printRequestAttributeSet.add(Sides.ONE_SIDED) //单双面
    return printRequestAttributeSet
}

private class PDFPrintJobListener : PrintJobListener {
    override fun printDataTransferCompleted(printJobEvent: PrintJobEvent) {
        println("printDataTransferCompleted=$printJobEvent")
    }

    override fun printJobCompleted(printJobEvent: PrintJobEvent) {
        println("printJobCompleted=$printJobEvent")
    }

    override fun printJobFailed(printJobEvent: PrintJobEvent) {
        println("printJobEvent=$printJobEvent")
    }

    override fun printJobCanceled(printJobEvent: PrintJobEvent) {
        println("printJobFailed=$printJobEvent")
    }

    override fun printJobNoMoreEvents(printJobEvent: PrintJobEvent) {
        println("printJobNoMoreEvents=$printJobEvent")
    }

    override fun printJobRequiresAttention(printJobEvent: PrintJobEvent) {
        println("printJobRequiresAttention=$printJobEvent")
    }
}



