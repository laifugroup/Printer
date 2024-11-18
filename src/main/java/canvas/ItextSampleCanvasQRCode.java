package canvas;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.barcodes.BarcodeEAN;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.barcodes.qrcode.EncodeHintType;
import com.itextpdf.barcodes.qrcode.ErrorCorrectionLevel;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasQRCode {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档和文档的创建
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/简单示例_二维码.pdf"));
        Document document = new Document(pdfDocument);

        // 设置生成二维码的额外信息（重要）
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 设置字符集信息、纠错等级、版本
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //hints.put(EncodeHintType.MIN_VERSION_NR,1);
        // 构建二维码对象，并设置二维码的额外配置
        BarcodeQRCode qrCode = new BarcodeQRCode("蚂蚁小哥欢迎您", hints);
        // qrCode.setHints(hints); // 设置二维码的额外配置（这里在构造器上设置了）

        // ======================== 从二维码对象中构建一张图片对象 ========================
        // 生成PdfFormXObject对象并转换程Image
        PdfFormXObject pdfFormXObject = qrCode.createFormXObject(ColorConstants.RED, pdfDocument);
        Image imageA = new Image(pdfFormXObject);
        // 设置图片的一些样式
        imageA.setBorder(new SolidBorder(new DeviceRgb(102, 205, 170), 2))
                .setBorderRadius(new BorderRadius(5))
                .setWidth(200)
                .setHeight(200);
        // ======================== 将图片添加到画布并添加二维码logo ========================
        // 重新获取一个二维码图片资源，并设置宽高
        Image imageB = new Image(qrCode.createFormXObject(ColorConstants.RED, pdfDocument));
        imageB.setWidth(200).setHeight(200);
        // 加载一张二维码的中logo图片
        ImageData logo = ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG");
        // 为二维码中间添加logo
        Image image = addLogoToQRCode(imageB, logo, 30f, pdfDocument);
        // 为图片外面套个盒子并定位
        Div div = new Div()
                .setWidth(200).setHeight(200).add(image)
                .setBorderRadius(new BorderRadius(5))
                .setBorder(new SolidBorder(new DeviceRgb(102, 205, 170),2))
                .setFixedPosition(300,605,200);

        // 添加二维码并关闭文档
        document.add(imageA);
        document.add(div);
        document.close();
    }
    /***
     * 二维码图片中添加Logo图片
     * @param qrCode 二维码图片对象
     * @param logo Logo图片
     * @param logoSize Logo图片大小
     * @param pdfDocument PDF文档
     * @return 图片
     */
    public static Image addLogoToQRCode(Image qrCode, ImageData logo,
                                        float logoSize, PdfDocument pdfDocument) {
        // 获取图片的宽高信息，先获取是否更改过图片宽高；77代表宽，27代表高
        float width = qrCode.getImageScaledWidth();
        float height = qrCode.getImageScaledHeight();
        if (qrCode.getProperty(27) != null) {
            height = ((UnitValue) qrCode.getProperty(27)).getValue();
        }
        if (qrCode.getProperty(77) != null) {
            width = ((UnitValue) qrCode.getProperty(77)).getValue();
        }
        // 创建一个PdfFormXObject对象
        PdfFormXObject pdfFormXObject = new PdfFormXObject(new Rectangle(width, height));
        // 创建一个Canvas高级画布并将图片添加到PdfFormXObject对象上
        new Canvas(pdfFormXObject, pdfDocument).add(qrCode).close();
        // 创建画布PdfCanvas
        PdfCanvas pdfCanvas = new PdfCanvas(pdfFormXObject, pdfDocument);
        // 将logo图片添加到画布上的中间位置
        // 计算位置，width和height代表当前画布的宽高，
        Rectangle center = new Rectangle(width / 2 - (logoSize / 2),
                height / 2 - (logoSize / 2), logoSize, logoSize);
        pdfCanvas.addImageFittedIntoRectangle(logo, center, false);
        // 关闭画布
        pdfCanvas.release();
        return new Image(pdfFormXObject);
    }

}
