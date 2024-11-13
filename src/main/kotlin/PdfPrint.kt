import org.apache.pdfbox.Loader
import org.apache.pdfbox.cos.COSDocument
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.printing.PDFPageable
import java.awt.print.PrinterJob
import java.io.File
import javax.print.PrintService
import javax.print.attribute.HashPrintRequestAttributeSet
import javax.print.attribute.PrintRequestAttributeSet
import javax.print.attribute.standard.Copies
import javax.print.attribute.standard.MediaSizeName
import javax.print.attribute.standard.Sides

//pdfbox
//work good!
fun main() {
    println("pdfbox! shot")
    pdfBoxPrint()
    println("pdfbox! over")
}

// https://blog.51cto.com/u_16213457/10234099

fun pdfBoxPrint(){

    val pdfDoc=Loader.loadPDF(File("C:\\Users\\leyuan\\Desktop\\222.pdf"))

    val printerJob: PrinterJob = PrinterJob.getPrinterJob()
        //默认打印机即可
    //指定打印机
    //val printServices: Array<PrintService> = PrinterJob.lookupPrintServices()
    //val printService= printServices.first { it.name.contains("Canon") }
    //printerJob.printService=printService
    printerJob.setPageable(PDFPageable(pdfDoc))
    printerJob.print()
    pdfDoc.close()
}







private fun getPrintRequestAttributeSet(): PrintRequestAttributeSet {
    val printRequestAttributeSet: PrintRequestAttributeSet = HashPrintRequestAttributeSet()
    printRequestAttributeSet.add(Copies(1)) //份数
    printRequestAttributeSet.add(MediaSizeName.ISO_A4) //纸张
    // printRequestAttributeSet.add(Finishings.STAPLE);//装订
    printRequestAttributeSet.add(Sides.ONE_SIDED) //单双面
    return printRequestAttributeSet
}
