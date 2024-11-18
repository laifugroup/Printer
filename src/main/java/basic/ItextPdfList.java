package basic;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.ListNumberingType;
import com.itextpdf.layout.properties.ListSymbolAlignment;

import java.io.IOException;

public class ItextPdfList {

    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档，
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./基本示例_列表.pdf"));
        // 中文字体对象
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 创建文档
        Document document = new Document(pdfDocument);

        // 一个普通的段落
        Paragraph paragraph1 = new Paragraph("新闻点击量排行榜：").setFont(font);
        // 创建列表A
        List listA = new List();
        listA.setFont(font);
        listA.add("34项重罪指控成立 特朗普成美国史上首位被判有罪的前总统");
        listA.add("谁还不是个宝宝呢！儿童节做个快乐的超龄儿童");
        listA.add(new ListItem("郑州：宋陵千年石像见证又一个丰收季"));
        listA.add(new ListItem("大熊猫“金喜”“茱萸”正式与西班牙公众见面"));
        // 一个普通的段落
        Paragraph paragraph2 = new Paragraph("列表的其它应用：").setFont(font);
        List listB = new List().setFont(font)
                .setMarginLeft(12 * 2)      // 列表左边边距设置2个字符的位置
                //.setListSymbol("■")       // 自定义了个图片（设置的图标，字体不支持也不行）
                .setListSymbol(ListNumberingType.DECIMAL) // 使用系统自带的枚举符号
                .setSymbolIndent(10)        // 设置列表里的子元素距离符号的间距
                .setItemStartIndex(5)       // 设置元素的索引从5开始
                .setListSymbolAlignment(ListSymbolAlignment.RIGHT);
        listB.add("34项重罪指控成立 特朗普成美国史上首位被判有罪的前总统");
        listB.add("谁还不是个宝宝呢！儿童节做个快乐的超龄儿童");
        // 单独设置个 ListItem（正常不会这样，最多加个颜色）
        ListItem listItem = new ListItem("郑州：宋陵千年石像见证又一个丰收季")
                .setListSymbol("■：");
        listItem.setFontColor(new DeviceRgb(255, 0, 0));
        listItem.setBackgroundColor(ColorConstants.GREEN);
        listB.add(listItem);
        listB.add(new ListItem("大熊猫“金喜”“茱萸”正式与西班牙公众见面"));
        // 添加元素到文档中
        document.add(paragraph1);
        document.add(listA);
        document.add(paragraph2);
        document.add(listB);
        document.close();
    }


}
