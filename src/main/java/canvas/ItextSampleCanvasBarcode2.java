package canvas;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.IOException;

/**
 *
 *
 * Canvas
 * https://blog.csdn.net/xiaofeng_yang/article/details/139902405
 *
 *
 */
public class ItextSampleCanvasBarcode2 {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档和文档的创建
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./canvas/简单示例_一维码2.pdf"));
        Document document = new Document(pdfDocument);
        // 创建一个Code 128类型的高密度条形码
        Barcode128 code128 = new Barcode128(pdfDocument);
        code128.setCode("12345678*abcABC*");                // 设置条形码的数据
        code128.setCodeSet(Barcode128.Barcode128CodeSet.B); // 设置Code128的密度类型
        code128.setBaseline(code128.getSize() + 10);          // 设置文本基于条形码基线位置
        code128.setTextAlignment(Barcode128.ALIGN_RIGHT);   // 设置数值的堆区方式，默认居中
        code128.fitWidth(220);      // 设置条形码宽度
        code128.setBarHeight(60);   // 设置条形码高度
        code128.setSize(16);        // 设置条形码文字大小
        // 转换为formXObject对象和Image对象
        PdfFormXObject formXObject = code128
                .createFormXObject(ColorConstants.BLUE, ColorConstants.RED, pdfDocument);
        Image image = new Image(formXObject);
        // 添加条形码
        document.add(image);
        // 关闭文档
        document.close();
    }
//        '关于Barcode39补充方法说明：'
//    setCodeSet(Barcode128CodeSet codeSet)
//    设置要使用的代码集；可选值从Barcode128.Barcode128CodeSet枚举获取如下：
//    A：标准数字和大写字母，控制符，特殊字符；
//    B：标准数字和大写字母，小写字母，特殊字符；
//    C：只能是双位数值，[00]-[99]的数字对集合，共100个
//    AUTO：自动识别A、B、C三种模式


}
