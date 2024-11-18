package canvas;

import com.itextpdf.barcodes.BarcodeEAN;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.BorderRadius;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.IOException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasBarcode {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档和文档的创建
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/基本示例_一维码.pdf"));
        PdfPage pdfPage = pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        // 创建第一个EAN13的条形码
        BarcodeEAN b1EAN = new BarcodeEAN(pdfDocument);
        b1EAN.setCode("6921168504022");       // 设置条形码的代码
        b1EAN.fitWidth(200);                  // 设置条形码宽
        b1EAN.setBarHeight(60);               // 设置条形码高
        b1EAN.setSize(12);                    // 设置条形码文本大小（根据宽高设置）
        b1EAN.setBaseline(b1EAN.getSize());   // 设置文本基于条形码基线位置
        b1EAN.setCodeType(BarcodeEAN.EAN13);  // 设置条形码为EAN13（其实默认EAN13）
        // ======= 将条形码转换为Image对象 ======
        // 创建出PdfFormXObject对象并直接加入到文档中；
        // 对了PdfFormXObject对象可以交给PdfCanvas画布哟，处理一翻在转回图片
        PdfFormXObject formXObject =
                b1EAN.createFormXObject(ColorConstants.BLUE, ColorConstants.BLACK, pdfDocument);
        Image imageA = new Image(formXObject);
        // 别忘咯，Image继承了AbstractElement，正常的一些样式它也有。
        imageA.setBackgroundColor(new DeviceRgb(0, 0, 0), .1f)
                //.setBorder(new SolidBorder(2))
                .setBorderRadius(new BorderRadius(3))
                .setPadding(3);
        document.add(imageA);
        // ======= 将条形码布局到页面的任意地方 ======
        Image imageB = new Image(b1EAN.createFormXObject(pdfDocument));
        imageB.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 创建一个盒子，将条形码放里面
        Div div = new Div();
        div.setBorder(new DottedBorder(new DeviceRgb(195, 98, 21), 3))
                .setBorderRadius(new BorderRadius(5))
                .add(imageB)
                // 使用绝对定位
                .setFixedPosition(
                        imageB.getImageWidth() + 50,
                        pdfPage.getPageSize().getTop() - 120,
                        imageB.getImageWidth())
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBackgroundColor(new DeviceRgb(187, 255, 255))
                .setWidth(imageB.getImageWidth() + 30f)
                .setHeight(imageB.getImageHeight() + 20f);
        document.add(div);
        // 关闭文档
        document.close();
    }


}
