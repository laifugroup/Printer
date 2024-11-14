import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
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
            rect.setBackgroundColor(BaseColor.WHITE);
            Document document = new Document(rect);
            //Step 2—Get a PdfWriter instance.
            PdfWriter    writer= PdfWriter.getInstance(document, new FileOutputStream(FILE_DIR + "createSamplePDF.pdf"));
            // 设置密 码为："World"
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


            //BaseFont bfChinese = BaseFont.createFont("STSongStd-Ligth","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
             BaseFont bfChinese = BaseFont.createFont("c://windows//fonts//msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            //页边空白
            document.setMargins(8, 8, 8, 8);

            //Step 3—Open the Document.  
            document.open();
            //Step 4—Add content.
            //public Font(     com. itextpdf. text. pdf. BaseFont bf,
            //    float size,
            //    int style )

            Font font=new Font(bfChinese,48.0f);
            font.setColor(BaseColor.RED);
            font.setStyle(Font.FontStyle.NORMAL.getValue());
            Paragraph title=new Paragraph("乐园老年社交平台",font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(15f);
                title.setSpacingBefore(15f);
            document.add(title);


            //直线
            Paragraph p1 = new Paragraph();
            p1.add(new Chunk(new LineSeparator(font)));
            //p1.add("R");
            document.add(p1);

            //Image对象
            document.newPage();
            ClassLoader.class.getResource("111.PNG");
            String path = Objects.requireNonNull(HelloWorld.class.getClassLoader().getResource("111.PNG")).getPath();

            Image img = Image.getInstance(path);
            img.setAlignment(Image.LEFT | Image.TEXTWRAP);
            img.setBorder(Image.BOX);
            img.setBorderWidth(10);
            img.setBorderColor(BaseColor.WHITE);
            img.scaleToFit(200, 200);//大小
            //img.setRotationDegrees(-30);//旋转
            document.add(img);

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
