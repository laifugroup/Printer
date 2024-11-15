import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;
// https://blog.csdn.net/yiminghd2861/article/details/115456154
// itext 简单方便
//
// 密码操作等
// https://blog.csdn.net/xiaofeng_yang/article/details/139902089
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

            //用户密码  可以为null或长度为零，等于省略用户密码
            String userPassword="Hello";
            //  ownerPassword：所有者密码。如果为null或为空，iText将生成一个随机字符串用作所有者密码。 所有者密码可以查询到用户密码
            String ownerPassword="World";

//            writer.setEncryption(userPassword.getBytes(StandardCharsets.UTF_8), ownerPassword.getBytes(StandardCharsets.UTF_8),
//                    PdfWriter.ALLOW_SCREENREADERS,
//                    PdfWriter.ENCRYPTION_AES_256);
            //PDF版本(默认1.4)
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
            //文档属性
            document.addTitle("Title@sample");
            document.addAuthor("Author@rensanning");
            document.addSubject("Subject@iText sample");
            document.addKeywords("Keywords@iText");
            document.addCreator("Creator@iText");



           // Image image =  Image.getInstance(ImageDataFactory.create("D:\\images\\pg2.jpg").getUrl());





            //BaseFont bfChinese = BaseFont.createFont("STSongStd-Ligth","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
             BaseFont bfChinese = BaseFont.createFont("c://windows//fonts//msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            //页边空白
            document.setMargins(16f, 16f, 16f, 16f);

            //Step 3—Open the Document.  
            document.open();
            //Step 4—Add content.
            //public Font(     com. itextpdf. text. pdf. BaseFont bf,
            //    float size,
            //    int style )
//
            Font font=new Font(bfChinese,48);
            font.setColor(BaseColor.RED);
            font.setStyle(Font.FontStyle.NORMAL.getValue());
            Paragraph title=new Paragraph("纸上谈兵",font);
                title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

//            // 获取文档first页面的 PdfCanvas 画布
//            PdfCanvas canvas = new PdfCanvas(page);
//            // 设置背景颜色
//            canvas.saveState()
//                    .setFillColor(new DeviceRgb(200, 145, 0))
//                    // 前2个参数代表页面的x和y，此时我定位在页面的左下角
//                    // 后两个参数代表填充大小
//                    .rectangle(0, 0, pageSize.getWidth() - 10, pageSize.getHeight() - 10)
//                    .fill()
//                    .restoreState();

            // 设置文本的字体、大小、颜色、背景颜色、对齐方式、垂直对其方式
//            Paragraph paragraph2 = new Paragraph("感谢您阅读蚂蚁小哥的博客！");
//            paragraph2 .setFont(font);
//            paragraph2 .setFontSize(18);
//            paragraph2 .setFontColor(new DeviceRgb(255, 0, 0));
//            paragraph2 .setBackgroundColor(new DeviceRgb(187, 255, 255));
//            paragraph2  .setTextAlignment(TextAlignment.CENTER);
//            paragraph2  .setVerticalAlignment(VerticalAlignment.BOTTOM);

            //直线

            Paragraph p1 = new Paragraph();

            p1.add(new Chunk(new LineSeparator(font)));
            document.add(p1);


            Font numbersFont=new Font(bfChinese,24);
            numbersFont.setColor(BaseColor.RED);
            numbersFont.setStyle(Font.FontStyle.NORMAL.getValue());
            Paragraph numbersParagraph=new Paragraph("中大站",numbersFont);
            numbersParagraph.setAlignment(Element.ALIGN_CENTER);
            numbersParagraph.setSpacingAfter(16f);
            numbersParagraph.setSpacingBefore(16f);
            document.add(numbersParagraph);

            Paragraph empty=new Paragraph("\n");
            empty.setLeading(48);
            document.add(empty);

            Font contentFont=new Font(bfChinese,20);
            contentFont.setColor(BaseColor.BLACK);
            contentFont.setStyle(Font.FontStyle.NORMAL.getValue());

            Paragraph content=new Paragraph("亲爱的谈兵用户：",contentFont);

            document.add(content);


            Font content1Font=new Font(bfChinese,18);
            content1Font.setColor(BaseColor.BLACK);
            content1Font.setStyle(Font.FontStyle.NORMAL.getValue());
            Paragraph content1=new Paragraph("恭喜您在中大站地20210124001场次的战斗中获得了胜利。对你表示祝贺!",content1Font);
                content1.setFirstLineIndent(32);
                content1.setSpacingBefore(4);
            document.add(content1);



            //正文


//            ClassLoader.class.getResource("bg_hometop.png");
//
//            String path = Objects.requireNonNull(HelloWorld.class.getClassLoader().getResource("bg_hometop.png")).getPath();
//
//            //Image对象
//            Image img = Image.getInstance(path);
//            img.setPaddingTop(48);
//
//            img.setAlignment(Image.ALIGN_CENTER);
//            img.setBorder(Image.BOX);
//            img.setBorderWidth(10);
//            img.setBorderColor(BaseColor.WHITE);
//            img.scaleToFit(800, 200);//大小
//            //img.setRotationDegrees(-30);//旋转
//            document.add(img);


            Font font2=new Font(bfChinese,16);
            font2.setColor(BaseColor.BLACK);
            font2.setStyle(Font.FontStyle.NORMAL.getValue());
            Paragraph p2 =new Paragraph("纸上谈兵项目组\n2024年11月01日",font2);
            p2.setAlignment(Paragraph.ALIGN_RIGHT);
//https://kb.itextpdf.com/itext/itext-7-jump-start-tutorial-chapter-3
            Paragraph p222 = new Paragraph()
                    .setFont(timesNewRoman)
                    .setFontSize(7)
                    .setFontColor(ColorConstants.GRAY)
                    .add(author);

            PdfContentByte canvas = writer.getDirectContent();
            ColumnText.showTextAligned(canvas, Element.ALIGN_BASELINE, p2, 0, 0, 0);


//            PdfContentByte canvas = writer.getDirectContent();
//          /// ColumnText.showTextAligned(canvas, Element.ALIGN_BOTTOM, p2, 0, 100, 0);
//            canvas.showTextAligned();
//            canvas.showTextAligned(PdfContentByte.ALIGN_RIGHT,p2,1,1,1);
            //document.add(p2);


            //章
            String path2 = Objects.requireNonNull(HelloWorld.class.getClassLoader().getResource("111.PNG")).getPath();
            Image office = Image.getInstance(path2);

            office.setAlignment(Image.ALIGN_BOTTOM|Image.TEXTWRAP);
            office.setBorder(Image.BOX);
            office.setBorderWidth(10);
            office.setBorderColor(BaseColor.WHITE);
            office.scaleToFit(200, 200);//大小
            //img.setRotationDegrees(-30);//旋转
            document.add(office);




//            PdfContentByte cb = writer.getDirectContent();
//            // 添加一些内容以便页面有高度
//           // document.add(new Paragraph("Sample content"));
//            // 定位到页面底部
//            cb.beginText();
//            cb.setFontAndSize(BaseFont.createFont(), 12);
//            // 获取页面底部的位置
//            float pageHeight = document.getPageSize().getHeight();
//           // cb.goToText(pageHeight - 20, -20); // 偏移量可以根据需要调整
//
//            // 添加文本到底部
//            cb.showText("This is the bottom footer");
//            cb.endText();
//            //cb.showTextAligned(PdfContentByte.ALIGN_RIGHT,"1111",1,1,1);
//            cb.stroke();


           // document.newPage();

           // document.add(new Paragraph(Document.));
            //document.newPage();
            //writer.setPageEmpty(false);

            //document.newPage();
            //document.add(new Paragraph("New page"));







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
