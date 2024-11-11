import com.spire.pdf.*;

import java.awt.print.*;

public class Print {

    public static void main(String[] args) {
//加载文档
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("C:\\Users\\Test1\\Desktop\\Sample.pdf");
        PrinterJob loPrinterJob = PrinterJob.getPrinterJob();
        PageFormat loPageFormat= loPrinterJob.defaultPage();

        Paper loPaper = loPageFormat.getPaper();
            //删除默认页边距
        loPaper.setImageableArea(0,0,loPageFormat.getWidth(),loPageFormat.getHeight());
            //设置打印份数
        loPrinterJob.setCopies(1);
        loPageFormat.setPaper(loPaper);
        loPrinterJob.setPrintable(pdf,loPageFormat);

        try {
            loPrinterJob.print();
        }
        catch (PrinterException e) {
            e.printStackTrace();
        }

 }

}
