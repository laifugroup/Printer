package basic;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.IOException;
import java.util.Arrays;

public class ItextGenPdfCopy {


    /***
     * 创建PDF文件，并从其它文件内读取了几张页复制了过来
     */
    public static void main(String[] args) throws IOException {
        // 读取一个PDF文件并构建PDF文档
        PdfDocument pdfRDoc = new PdfDocument(new PdfReader("./简单示例.pdf"));
        // 创建一个PDF文件并构建PDF文档
        PdfDocument pdfWDoc = new PdfDocument(new PdfWriter("./简单示例Copy.pdf"));
        // 创建3个新页
        pdfWDoc.addNewPage(PageSize.A8);
        pdfWDoc.addNewPage(PageSize.A7);
        pdfWDoc.addNewPage(PageSize.A6);
        // 从读取的Pdf中读取2~4（2，3，4）这几页，放到目标文档中的第2页前面
        pdfRDoc.copyPagesTo(2, 4, pdfWDoc, 2);
        // 从读取的Pdf中读取1,3,4这几页，放到目标文档中的末尾
        pdfRDoc.copyPagesTo(Arrays.asList(1, 3, 4), pdfWDoc);
        // 创建文档
        Document document = new Document(pdfWDoc);
        document.close();
    }

}
