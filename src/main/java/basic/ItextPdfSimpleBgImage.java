package basic;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.gradients.AbstractLinearGradientBuilder;
import com.itextpdf.kernel.colors.gradients.GradientColorStop;
import com.itextpdf.kernel.colors.gradients.StrategyBasedLinearGradientBuilder;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.*;
import com.itextpdf.styledxmlparser.css.util.CssGradientUtil;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * 主要类：BackgroundImage.Builder
 * '常用方法：'
 *     setImage(PdfXObject image)
 *         设置背景图片的
 *     setLinearGradientBuilder(AbstractLinearGradientBuilder linearGradientBuilder)
 *         设置线性渐变的
 *     setBackgroundClip(BackgroundBox clip)
 *     setBackgroundOrigin(BackgroundBox origin)
 *         第一个方法是用于指定背景图片绘制区域；
 *         第二个方法是用于指定背景图片定位区域；
 *         具体可选参数如下：
 *             BackgroundBox.BORDER_BOX：边框开始
 *             BackgroundBox.PADDING_BOX：内边框开始
 *             BackgroundBox.CONTENT_BOX：内容区开始
 *     setBackgroundSize(BackgroundSize backgroundSize)
 *         用于指定背景图片的大小。它控制背景图片的尺寸。
 *     setBackgroundRepeat(BackgroundRepeat repeat)
 *         设置图片的排列重复方式。
 *     setBackgroundPosition(BackgroundPosition position)
 *         设置背景的定位方式。
 *     setBackgroundBlendMode(BlendMode blendMode)
 * '注意事项：背景图片和线性渐变这两个方法不能同时指定，一次背景只能选一个。'
 * '关于BackgroundSize对象说明：'
 *     创建对象：BackgroundSize bs = new BackgroundSize();
 *     常用方法：
 *         bs.setBackgroundSizeToCover();
 *             表示背景图片尽可能覆盖整个背景区域，可能会被裁剪；
 *         bs.setBackgroundSizeToContain();
 *             表示背景图片尽可能放在背景区域内，保持完整且不会被裁剪。
 *         bs.setBackgroundSizeToValues(u1, u2);
 *             通过设置2个值（宽度、高度）来控制图片；传入UnitValue对象
 *             创建一个50%的值
 *                 new UnitValue(UnitValue.PERCENT, 50);
 *             创建一个50磅的值
 *                 new UnitValue(UnitValue.POINT ,  50);
 * '关于BackgroundRepeat对象说明：'
 *     创建对象的3种构造器：
 *         BackgroundRepeat()
 *             默认重复铺满整个空间
 *         BackgroundRepeat(repeat)
 *             一起设置X、Y轴的重复排列方式
 *         BackgroundRepeat(xAxisRepeat,yAxisRepeat)
 *             单独设置X轴和Y轴的重复排列方式
 *     常量参数选择（BackgroundRepeat.BackgroundRepeatValue）：
 *         NO_REPEAT：背景不会重复，而是以其原始大小显示一次。
 *         REPEAT：背景会重复，将铺满整个可用空间上。
 *         ROUND：
 *             表示背景将拉伸或压缩的舍入值。最初可用空间由模块除以背景的大小，如果结果小于背景大小的一半，
 *             则背景被拉伸，使得当重复时它将占据所有空间，否则背景被压缩以在可用空间中再适合一个背景。
 *         SPACE：
 *             会铺满，第一个和最后一个背景被附加到可用空间的相对边缘，并且白色空间均匀地分布在背景之间。
 *             假如背景就四张图片，则会再四角显示，其它区域空白
 * '关于BackgroundPosition对象说明：'
 *     创建对象：BackgroundPosition bgp = new BackgroundPosition();
 *     有两种定位方式：
 *         通过关键字设置位置（写两个值，用空格隔开）：设置居中
 *             bgp.setPositionX(BackgroundPosition.PositionX.CENTER);
 *             bgp.setPositionY(BackgroundPosition.PositionY.CENTER);
 *             PositionX可选值：LEFT,RIGHT,CENTER
 *             PositionY可选值：TOP,BOTTOM,CENTER
 *         通过长度指定坐标位置：设置居中
 *             UnitValue xShift = new UnitValue(UnitValue.POINT, 50);
 *             UnitValue yShift = new UnitValue(UnitValue.POINT, 50);
 *             bgp.setXShift(xShift);
 *             bgp.setYShift(yShift);
 *             说明：
 *                 UnitValue.POINT为磅单位
 *                 UnitValue.PERCENT为百分比单位
 *     注意：若是渐变效果的图片定位貌似只能使用UnitValue(UnitValue.POINT, 50)方式；具体没太深入研究
 * '关于线性渐变的渐变方向（可以使用常量值或者使用角度的方式）：'
 *     使用常量的渐变方向：
 *         具体的常量都在这个常量类里：StrategyBasedLinearGradientBuilder.GradientStrategy
 *             TO_BOTTOM、TO_BOTTOM_LEFT、TO_BOTTOM_RIGHT、
 *             TO_LEFT、TO_RIGHT、TO_TOP、TO_TOP_LEFT、TO_TOP_RIGHT
 *     使用角度的渐变方向：
 *         需要调用 setGradientDirectionAsCentralRotationAngle(double radians) 方法设置
 *         旋转角度 = 旋转角度 * 圆周率 / 180度
 */
public class ItextPdfSimpleBgImage {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./简单示例_bg_image.pdf"));
        // 初始化文档
        Document document = new Document(pdf);
        // 创建第一个段落（背景图片）
        Paragraph paragraph1 = new Paragraph("Thank you for reading Ant Brother's blog");
        paragraph1(paragraph1);
        // 创建第二个段落（线性渐变）
        Paragraph paragraph2 = new Paragraph("Thank you for reading Ant Brother's blog");
        paragraph2(paragraph2);
        // 创建第三个段落（使用CSS的线性渐变）
        Paragraph paragraph3 = new Paragraph("Thank you for reading Ant Brother's blog");
        paragraph3(paragraph3);
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.close();
    }
    /***
     * 设置第一段内容样式（背景图片）
     * @param paragraph 段落
     */
    public static void paragraph1(Paragraph paragraph) throws MalformedURLException {
        // 创建ImageData对象（图片二进制数组、url方式都行）、并构建成图像
        ImageData imageData = ImageDataFactory.create("C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\111.PNG");
        // 创建PDF图像对象
        PdfImageXObject pdfImageXObject = new PdfImageXObject(imageData);
        // 背景图片大小
        BackgroundSize bs = new BackgroundSize();
        UnitValue uWitch = new UnitValue(UnitValue.POINT, 150);
        UnitValue uHeight = new UnitValue(UnitValue.POINT, 32);
        bs.setBackgroundSizeToValues(uWitch, uHeight);
        // 设置背景排列方式
        BackgroundRepeat bgr =
                new BackgroundRepeat(BackgroundRepeat.BackgroundRepeatValue.SPACE);
        // 构建图片背景，并设置一些其它样式
        BackgroundImage build = new BackgroundImage.Builder()
                .setImage(pdfImageXObject)
                .setBackgroundClip(BackgroundBox.CONTENT_BOX)   // 在内容区绘制背景
                .setBackgroundOrigin(BackgroundBox.CONTENT_BOX) // 在内容区定位背景
                .setBackgroundSize(bs)      // 设置背景图片大小
                .setBackgroundRepeat(bgr)   // 背景排列方式
                .build();
        // 设置段落样式
        paragraph.setWidth(400)
                .setHeight(80)
                .setBackgroundImage(build)
                .setBorder(new SolidBorder(ColorConstants.PINK, 10, .3f));
    }
    /***
     * 设置第二段内容样式（背景-线性渐变）
     * @param paragraph 段落
     */
    public static void paragraph2(Paragraph paragraph) {
        // 创建线性渐变，使用代码的方式，设置颜色并设置渐变方向
        AbstractLinearGradientBuilder gradientBuilder =
                new StrategyBasedLinearGradientBuilder()
                        .setGradientDirectionAsCentralRotationAngle(90 * Math.PI / 180)
                        .addColorStop(new GradientColorStop(ColorConstants.RED.getColorValue()))
                        .addColorStop(new GradientColorStop(ColorConstants.GREEN.getColorValue()))
                        .addColorStop(new GradientColorStop(ColorConstants.BLUE.getColorValue()));
        // 创建背景的大小
        BackgroundSize backgroundSize = new BackgroundSize();
        // 创建2个值（百分比）
        UnitValue unitValue1 = new UnitValue(UnitValue.PERCENT, 50);
        UnitValue unitValue2 = new UnitValue(UnitValue.PERCENT, 50);
        backgroundSize.setBackgroundSizeToValues(unitValue1, unitValue2);
        // 设置背景定位方式（这就代表居中）
        BackgroundPosition bgp = new BackgroundPosition();
        bgp.setXShift(new UnitValue(UnitValue.POINT, 50));
        bgp.setYShift(new UnitValue(UnitValue.POINT, 20));
        // 创建背景样式
        BackgroundImage build = new BackgroundImage.Builder()
                .setBackgroundSize(backgroundSize)          // 背景大小
                .setLinearGradientBuilder(gradientBuilder)  // 线性渐变
                .setBackgroundPosition(bgp)                 // 背景定位
                .setBackgroundBlendMode(BlendMode.HARD_LIGHT)
                .build();
        // 设置段落样式
        paragraph.setWidth(200)
                .setHeight(80)
                .setBorder(new SolidBorder(10))
                .setBackgroundImage(build);
    }
    /***
     * 设置第三段内容样式（背景使用CSS的方式来创建）
     * @param paragraph 段落
     */
    public static void paragraph3(Paragraph paragraph) {
        // 使用CSS的线性渐变
        String gradientValue = "linear-gradient(to left top, blue 50%, yellow 50%)";
        // 设置渐变线性
        StrategyBasedLinearGradientBuilder linearGradientBuilder
                = CssGradientUtil.parseCssLinearGradient(gradientValue, 5, 5);
        // 设置背景
        BackgroundImage build = new BackgroundImage.Builder()
                .setLinearGradientBuilder(linearGradientBuilder)
                .build();
        paragraph.setHeight(60)
                .setFontSize(30)
                .setBackgroundImage(build)
                .setBorder(new SolidBorder(ColorConstants.PINK, 10, .3f));
    }


}
