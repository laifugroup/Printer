package basic;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.FileNotFoundException;

public class ITextGenPDFBasic {
        // 基本入门案例
        public static void main(String[] args) {
            Document document = null;
            try {
                // 创建并初始化一个PDF文档
                PdfDocument pdf = new PdfDocument(new PdfWriter("./简单示例Basic.pdf"));

                pdf.addNewPage();
                pdf.addNewPage(PageSize.A4);

                // 初始化文档
                document = new Document(pdf);

                // 添加一段英文
                Paragraph paragraph1 = new Paragraph("Thank you for reading Ant Brother's blog!");
                document.add(paragraph1);

                // 添加一段中文（itext无法支持中文字体，需要设置字体）
                PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");
                // 设置文本的字体、大小、颜色、背景颜色、对齐方式、垂直对其方式
                Paragraph paragraph2 = new Paragraph("感谢您阅读蚂蚁小哥的博客！")
                        .setFont(font)
                        .setFontSize(18)
                        .setFontColor(new DeviceRgb(255, 0, 0))
                        .setBackgroundColor(new DeviceRgb(187, 255, 255))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.BOTTOM);
                document.add(paragraph2);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            } finally {
                //关闭文档
                if (document != null) {
                    document.close();
                }
            }
        }
    }
