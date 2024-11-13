import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;
// https://blog.csdn.net/yiminghd2861/article/details/115456154
// itext 简单方便
/**
 * 生成一个内容为“Hello World”的pdf文件
 * @author ym
 */
public class HelloWorld {

    public static void main(String[] args) {
        String FILE_DIR = "./"; // 项目根目录：盘符:/.../.../项目名称，注意：点号并不表示当前类文件所在的目录
        //Step 1—Create a Document.  

        try {

            //页面大小
            Rectangle rect = new Rectangle(PageSize.A4);
            //页面背景色
            rect.setBackgroundColor(BaseColor.ORANGE);
            Document document = new Document(rect);
            //Step 2—Get a PdfWriter instance.
            PdfWriter    writer= PdfWriter.getInstance(document, new FileOutputStream(FILE_DIR + "createSamplePDF.pdf"));
            // 设置密码为："World"
//            writer.setEncryption("Hello".getBytes(), "World".getBytes(),
//                    PdfWriter.ALLOW_SCREENREADERS,
//                    PdfWriter.STANDARD_ENCRYPTION_128);
            //PDF版本(默认1.4)
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
            //文档属性
            document.addTitle("Title@sample");
            document.addAuthor("Author@rensanning");
            document.addSubject("Subject@iText sample");
            document.addKeywords("Keywords@iText");
            document.addCreator("Creator@iText");



            //页边空白
            document.setMargins(10, 20, 30, 40);

            //Step 3—Open the Document.  
            document.open();
            //Step 4—Add content.  
            document.add(new Paragraph("你好,First page"));
           // document.add(new Paragraph(Document.));
            document.newPage();
            writer.setPageEmpty(false);

            document.newPage();
            document.add(new Paragraph("New page"));


            //Step 5—Close the Document.  
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
