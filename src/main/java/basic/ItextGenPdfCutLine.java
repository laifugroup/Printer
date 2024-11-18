package basic;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;

import java.io.IOException;

public class ItextGenPdfCutLine {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档
        Document document = new Document(new PdfDocument(new PdfWriter("./基本示例_分割线.pdf")));
        document.add(new Paragraph("SolidLine："));
        document.add(new LineSeparator(new SolidLine(3)));
        document.add(new Paragraph("DashedLine："));
        document.add(new LineSeparator(new DashedLine(3)));
        document.add(new Paragraph("DottedLine："));
        document.add(new LineSeparator(new DottedLine(3)));
        // 设置其它样式
        DottedLine dottedLine = new DottedLine(3);
        dottedLine.setColor(ColorConstants.RED);
        dottedLine.setGap(10);
        LineSeparator lineSeparator = new LineSeparator(dottedLine);
        // 以块级元素设置分割线
        lineSeparator.setWidth(200).setMarginTop(20)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 把元素添加到文档里，并关闭文档
        document.add(lineSeparator);
        document.close();
    }



}
