package basic;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

public class ItextPdfImage {

    public static void main(String[] args) throws IOException {
        // 加载图片资源，并设置图片大小
        Image image = new Image(ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG"));
        image.setWidth(140);
        image.setHeight(100);
        // 创建PDF，并构建PDF文档，初始化一张A6的页面（默认是A4）
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter("./简单示例_image.pdf"));
        PageSize pageSize = PageSize.A6;
        pdfDoc.addNewPage(pageSize);

        // 获取文档first页面的 PdfCanvas 画布
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        // 设置背景颜色
        canvas.saveState()
                .setFillColor(new DeviceRgb(200, 145, 0))
                // 前2个参数代表页面的x和y，此时我定位在页面的左下角
                // 后两个参数代表填充大小
                .rectangle(0, 0, pageSize.getWidth() - 10, pageSize.getHeight() - 10)
                .fill()
                .restoreState();

        // 构建文档
        Document document = new Document(pdfDoc);
        // 若设置0则代表当前的页里面的内容都贴着边缘
        //document.setMargins(0, 0, 0, 0);
        // 设置四边的边距为100
        document.setMargins(50, 50, 50, 50);
        // 插入段落（块级元素）、图片
        document.add(new Paragraph("Hello World！"));
        document.add(image);
        // 关闭文档
        document.close();
    }


}
