package basic;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;

/**
 * '1：字体加粗'
 *     setBold()
 * '2：字体样式'
 *     setFont(PdfFont font)
 * '3：字体大小'
 *     setFontSize(float fontSize)
 * '4：字体颜色'
 *     setFontColor(Color fontColor)
 *     setFontColor(Color fontColor, float opacity)
 *         参数说明：
 *             fontColor：颜色对象
 *             opacity：透明度；0~1，如：.5f为半透明
 * '5：字体族'
 *     setFontFamily(String... fontFamilyNames)
 *     setFontFamily(List﹤String﹥ fontFamilyNames)
 * '6：设置文本的字距调整：'
 *     setFontKerning(FontKerning fontKerning)
 *         启用或禁用紧排。一些字体可以指定紧排对，即字形对，在紧排对之间调整水平空间的量。
 *         这种调整通常是负的，例如，在“AV”对中，字形通常会彼此移动得更近。
 *             可选值：FontKerning.YES、FontKerning.NO
 * '7：文本元素的书写系统'
 *     setFontScript(Character.UnicodeScript script)
 * '8：文本首字符缩进：'
 *      setFirstLineIndent(float indent)
 *         正常缩进2个单位，字体默认12pt，所以缩进设置 12 * 2。
 * '9：设置文本间距：'
 *      setCharacterSpacing(float charSpacing)
 *         字符间距，正常也不会这么弄，除非如一个成语或者单词特意让字符间隔。
 *      setWordSpacing(float wordSpacing)
 *         设置只适用于单词间的间距，可不是每个字符间距哟，是单词与单词间距。
 * '10：设置文本的渲染模式：'
 *     setTextRenderingMode(int textRenderingMode)
 *         设置文本在PDF页面上的呈现方式。可以通过传入不同的参数来实现不同的效果，
 *         例如正常显示、描边、填充等。具体渲染默认如下：
 *         PdfCanvasConstants.TextRenderingMode常量：
 *             FILL (0)：填充
 *                 文本以实心形式呈现，即填充字体轮廓内部。
 *             STROKE (1)：描边
 *                 文本以轮廓形式呈现，只显示文字的边缘，字体内部不填充。
 *             FILL_STROKE (2)：填充加描边
 *                 文本同时以实心和轮廓形式呈现，即填充字体轮廓内部并显示文字的边缘。
 *             INVISIBLE (3)：不可见
 *                 文本不可见，即不会在页面上显示。
 *             FILL_CLIP (4)：填充并裁剪
 *                 文本以实心形式呈现，并将其添加到裁剪路径中，用于裁剪其他元素。
 *             STROKE_CLIP (5)：描边并裁剪
 *                 文本以轮廓形式呈现，并将其添加到裁剪路径中，用于裁剪其他元素。
 *             FILL_STROKE_CLIP (6)：填充加描边并裁剪
 *                 文本同时以实心和轮廓形式呈现，并将其添加到裁剪路径中，用于裁剪其他元素。
 *             CLIP (7)：裁剪
 *                 将文本添加到裁剪路径中，用于裁剪其他元素。
 * '11：设置文本的基础方向：'
 *     setBaseDirection(BaseDirection baseDirection)
 *         即文本的书写方向。可以将文本方向设置为从左到右或者从右到左，以适应不同的语言和排版需求。
 *         BaseDirection枚举：
 *             NO_BIDI：无双向性
 *                 文本没有双向性，即不需要考虑文字的书写方向。
 *             DEFAULT_BIDI：默认双向性
 *                 文本使用默认的双向性，即根据文本内容自动确定文字的书写方向。
 *             LEFT_TO_RIGHT：从左到右
 *                 文本的书写方向从左到右，适用于大部分的拉丁字母语言和数字。
 *             RIGHT_TO_LEFT：从右到左
 *                 文本的书写方向从右到左，适用于一些阿拉伯字母语言、希伯来语等从右向左书写的语言。
 * '12：文本对齐方式：'
 *     setTextAlignment(TextAlignment alignment)
 *         可选值：TextAlignment.xx
 *             LEFT（左对齐）：文本左边缘与包含区域的左边缘对齐。
 *             RIGHT（右对齐）：文本右边缘与包含区域的右边缘对齐。
 *             CENTER（居中对齐）：文本在包含区域内水平居中对齐。
 *             JUSTIFIED（两端对齐）：文本在包含区域内两端对齐，
 *                 即使需要在单词之间插入额外的空格或拉伸单词以填满行宽度。
 *             JUSTIFIED_ALL（完全两端对齐）：类似于JUSTIFIED，但不仅在行末进行对齐，
 *                 而是在每个段落中的所有行末进行对齐。
 * '13：文本设置下划线（通过参数可以设置删除线、下划线、上划线...）：'
 *     setUnderline()：默认就是下划线
 *     setLineThrough()：设置文本删除线
 *     setUnderline(float thickness, float yPosition)
 *     setUnderline(Color color, float thickness, float thicknessMul,
 *             float yPosition, float yPositionMul, int lineCapStyle)
 *     setUnderline(Color color, float opacity, float thickness, float thicknessMul,
 *             float yPosition, float yPositionMul, int lineCapStyle)
 *         参数说明：
 *             color：下划线的颜色
 *             opacity：下划线的透明度（0~1；.5f表示半透明）
 *             thickness：表示下划线的粗细
 *             thicknessMul：表示与字体大小相关的粗细调整因子。
 *                 如果你希望下划线的粗细随着字体大小的变化而变化，可以使用这个参数来实现。
 *                 例如，如果你希望下划线的粗细是字体大小的一半，可以将thicknessMul设置为0.5f。
 *             yPosition 表示下划线相对于基线的垂直偏移量。
 *             yPositionMul：表示与字体大小相关的垂直偏移量调整因子。
 *                 如果你希望下划线的位置随着字体大小的变化而变化，可以使用这个参数来实现。
 *                 例如，希望下划线的位置是字体大小的固定偏移量加上字体大小的一半，可以设置为0.5f。
 *             lineCapStyle 参数表示线端的样式，如圆头、方头等。
 *                 可选值：
 *                 LineCapStyle.BUTT：直角线（在开头和结尾初超出一点点）
 *                 LineCapStyle.ROUND：圆角线
 *                 LineCapStyle.PROJECTING_SQUARE：直角线（在开头和结尾处超出比BUTT多些）
 */
public class ItextPdfFont {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        PdfDocument pdf = new PdfDocument(new PdfWriter("./基本示例_font.pdf"));
        Document document = new Document(pdf);
        // 创建第一个段落，并设置段落内容
        Paragraph paragraph1 =
                new Paragraph("Thank you for reading Ant Brother's blog!");
        // 设置第一段文字样式
        paragraph1////.setBold()        // 加粗文本
                //.setItalic()       // 设置文本斜体
                .setFontSize(26)   // 设置文字大小 20pt
                .setLineThrough()  // 设置文本删除线
                .setTextAlignment(TextAlignment.CENTER) // 设置字体居中
                .setFontColor(new DeviceRgb(0, 0, 255), .5f) // 设置文本颜色
                .setBorder(new SolidBorder(2)); // 元素边框为了和下面区分，后面有介绍
        // 创建第二个段落，并设置段落内容
        String str = "Thank you for reading Ant Brother's blog! " +
                "I hope you are happy every day and make big money every day!";
        Paragraph paragraph2 = new Paragraph(str);
        paragraph2
                .setFontSize(20)    // 设置字体大小
                .setFirstLineIndent(20 * 2); // 字体大小 * 2；代表缩进2个字符
        // 把段落添加到文档里，并关闭文档
        document.add(paragraph1);
        document.add(paragraph2);
        document.close();
    }
}
