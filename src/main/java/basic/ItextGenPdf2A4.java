package basic;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.IOException;

public class ItextGenPdf2A4 {
    /***
     * 创建PDF文件，创建了几张空白页
     */
    public static void main(String[] args) throws IOException {
        // 创建一个PDF文件并构建PDF文档
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./简单示例_A4.pdf"));
        // 下面一共创建了 5 页，执行完代码会有五页
        // 创建两张A4大小的页（默认A4）
        pdfDoc.addNewPage();
        pdfDoc.addNewPage(PageSize.A4);
        // 创建一张A8大小的页
        pdfDoc.addNewPage(PageSize.A8);
        // 创建一张 400*200 磅大小的页
        pdfDoc.addNewPage(new PageSize(400, 200));
        // 创建一张 100*100磅 大小的页，并插入到页2的前面（这时就是两张A4页的中间咯）
        pdfDoc.addNewPage(2, new PageSize(100, 100));
        // 创建文档
        Document document = new Document(pdfDoc);
        document.close();
    }



}
