package basic;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ITextPDFMeta {
        public static void main(String[] args) throws IOException {
            create();
            select();
        }

        // 设置PDF元数据
        public static void create() throws IOException {
            // 创建一个PDF文件并构建PDF文档
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./简单示例meta.pdf"));
            // 获取文档信息对象
            PdfDocumentInfo docInfo = pdfDoc.getDocumentInfo();
            // 设置元数据信息
            docInfo.setCreator("tom").setAuthor("mayi")
                    .setSubject("sub1").setTitle("tile 1");
            // 创建文档
            Document document = new Document(pdfDoc);
            // 这个文档我就没创建任何内容，就一张空白页面....
            // 关闭文档
            document.close();
        }

        // 获取PDF元数据
        public static void select() throws IOException {
            // 读取一个PDF文件并构建PDF文档
            PdfDocument pdfDoc = new PdfDocument(new PdfReader("./简单示例meta.pdf"));
            // 获取文档信息对象
            PdfDocumentInfo docInfo = pdfDoc.getDocumentInfo();
            // 具体请参考API，有好多元数据获取
            System.out.println("author: " + docInfo.getAuthor());
            System.out.println("title: " + docInfo.getTitle());
            // 打印：作者：蚂蚁小哥
            //      标题：元数据案例
        }
    }

//    PdfDocumentInfo方法说明（set和get是对应的，这里我只说set）：
//    setAuthor(String author)：设置文档的作者信息。
//    setTitle(String title)：设置文档的标题或名称。
//    setCreator(String creator)：设置文档的创建者信息。
//    setKeywords(String keywords)：设置文档的关键字信息（描述内容的关键字，有助于文档的分类和搜索）。
//    setProducer(String producer)：设置文档的生成器信息（生成文档的应用程序的名称）。
//    setSubject(String subject)：设置文档的主题信息（文档所涉及的主题或主要内容的描述）。
//    setTrapped(PdfName trapped)：设置文档的“trapped”状态，用于指示文档是否处于受限制状态。
//    True：指示文档已经进行了印前处理。False：指示文档未经过印前处理。
//    额外信息的设置（2种类型任意设置）
//    setMoreInfo (String key, String value)
//    setMoreInfo (Map<String，String> moreInfo)
