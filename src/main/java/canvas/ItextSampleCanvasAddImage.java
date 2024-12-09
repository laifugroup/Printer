package canvas;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasAddImage {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_添加图片.pdf"));
        // 创建两张新页面
        pdfDocument.addNewPage();
        pdfDocument.addNewPage(new PageSize(600,200));
        // 加载本地图片资源
        ImageData image1 = ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG");
        // 获取文档第二页的页面大小
        Rectangle pageSize = pdfDocument.getPage(2).getPageSize();
        float width = pageSize.getWidth();
        float height = pageSize.getHeight();
        // 图片宽高
        float wImg = 60;
        float hImg = 40;
        // 为文档第二页构建画布
        PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument, 2);
        // 图片位于左下角
        Rectangle leftBottom = new Rectangle(0, 0, wImg, hImg);
        // 图片位于左上角
        Rectangle leftTop = new Rectangle(0, height - hImg, wImg, hImg);
        // 图片位于右上角
        Rectangle rightTop = new Rectangle(width - wImg, height - hImg, wImg, hImg);
        // 图片位于右下角
        Rectangle rightBottom = new Rectangle(width - wImg, 0, wImg, hImg);
        // 图片位于中间
        Rectangle center = new Rectangle(width/2-(wImg/2), height/2-(hImg/2), wImg, hImg);
        // 添加图片到画布
        pdfCanvas.addImageFittedIntoRectangle(image1, leftBottom, true);
        pdfCanvas.addImageFittedIntoRectangle(image1, leftTop, true);
        pdfCanvas.addImageFittedIntoRectangle(image1, rightTop, true);
        pdfCanvas.addImageFittedIntoRectangle(image1, rightBottom, true);
        pdfCanvas.addImageFittedIntoRectangle(image1, center, true);
        // 初始化文档并关闭写出
        Document document = new Document(pdfDocument);
        document.close();
    }


}
