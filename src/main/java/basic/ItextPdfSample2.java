package basic;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;

import java.io.IOException;

public class ItextPdfSample2 {
    public static void main(String[] args) throws IOException {
        // 创建并初始化一个PDF文档并构建文档
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter("./基本示例_季度考试成绩表.pdf"));
        Document document = new Document(pdfDocument);
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 构建一个使用指定百分比创建的表格；一定要记住我创建的是4列的表格
        UnitValue lie1 = new UnitValue(UnitValue.PERCENT, 20);
        UnitValue lie2 = new UnitValue(UnitValue.PERCENT, 20);
        UnitValue lie3 = new UnitValue(UnitValue.PERCENT, 20);
        UnitValue lie4 = new UnitValue(UnitValue.PERCENT, 20);
        UnitValue lie5 = new UnitValue(UnitValue.PERCENT, 20);
        Table table = new Table(new UnitValue[]{lie1, lie2, lie3, lie4, lie5})
                .setWidth(400).setBorder(new SolidBorder(ColorConstants.GRAY, 1));
        // ============================== 构建表格表头 ==============================
        Div div = new Div().add(new Paragraph("季度考试成绩表"))
                .setHeight(40).setBold().setFont(font).setFontSize(20)
                .setBorder(new SolidBorder(ColorConstants.GRAY, 1))
                .setBorderBottom(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(DeviceRgb.RED);
        table.setCaption(div, CaptionSide.TOP);
        // ============================== 构建表格标题 ==============================
        // 设置标题样式（语文、数学、英语）
        Style styleHeader = new Style()
                .setBold().setWidth(50).setHeight(20).setFont(font)
                .setTextAlignment(TextAlignment.CENTER)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorderRadius(new BorderRadius(10))
                .setFontColor(ColorConstants.WHITE)
                .setPaddingBottom(2)
                .setBackgroundColor(new DeviceRgb(0, 128, 128));
        // 设置单元格其它样式Cell
        Style styleCell = new Style()
                .setHeight(30)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBorder(new SolidBorder(ColorConstants.GRAY, 1));
        // 单元格对象
        Cell cellH1 = new Cell(1, 2);
        Cell cellH2 = new Cell().addStyle(styleCell);
        Cell cellH3 = new Cell().addStyle(styleCell);
        Cell cellH4 = new Cell().addStyle(styleCell);
        // 单元格里的内容信息
        Paragraph ph2 = new Paragraph("语 文").addStyle(styleHeader);
        Paragraph ph3 = new Paragraph("数 学").addStyle(styleHeader);
        Paragraph ph4 = new Paragraph("英 语").addStyle(styleHeader);
        // 内容信息添加到单元格里，并将单元格添加到表格中
        // 标题的第一列不用设置任何东西，它需要使用渲染器渲染出指定内容到单元格里，
        cellH1.setNextRenderer(setHeaderCellRenderer(cellH1));   // 设置渲染器
        table.addHeaderCell(cellH1);
        table.addHeaderCell(cellH2.add(ph2));
        table.addHeaderCell(cellH3.add(ph3));
        table.addHeaderCell(cellH4.add(ph4));
        // ============================== 构建表格内容 ==============================
        Paragraph paragraph = new Paragraph("上半年")
                .setFont(font).setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(new Cell(2, 1).add(paragraph));
        table.addCell(new Cell().add(new Paragraph("一季度").setFont(font)));
        table.addCell(new Cell().add(new Paragraph("26")));
        table.addCell(new Cell().add(new Paragraph("33")));
        table.addCell(new Cell().add(new Paragraph("45")));
        table.addCell(new Cell().add(new Paragraph("二季度").setFont(font)));
        table.addCell(new Cell().add(new Paragraph("26")));
        table.addCell(new Cell().add(new Paragraph("33")));
        table.addCell(new Cell().add(new Paragraph("45")));
        // 后面的就不写了，主要看标题部分，以及重点的标题斜线部分，后面的都是重复的不写了
        // 关于内容的部分，有些样式丑的可以借助画布拓展，比如“上半年”，完全可以使用画布把文字树立着
        // 把元素添加到文档里，并关闭文档
        document.add(table);
        document.close();
    }
    /***
     * 根据现有的单元格绘制指定样式的单元格
     * @param cell 单元格
     * @return CellRenderer渲染器
     */
    public static CellRenderer setHeaderCellRenderer(Cell cell) throws IOException {
        // 中文显示字体
        PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
        // 构建单元格渲染器
        return new CellRenderer(cell) {
            /***
             * 这里我就重写drawBorder，不重写draw了，因为我现在只是想单单画个线条
             * @param drawContext 绘制上下文对象
             */
            @Override
            public void drawBorder(DrawContext drawContext) {
                // 获取当前元素的位置信息和大小信息
                Rectangle rect = getOccupiedAreaBBox();
                // 获取画布PdfCanvas画布，这种画布常用于图形绘画
                PdfCanvas pdfCanvas = drawContext.getCanvas();
                // 对这个元素绘画一个斜线，左上角到右下角
                pdfCanvas.saveState()
                        .setStrokeColor(ColorConstants.GRAY)
                        .moveTo(rect.getLeft(), rect.getTop())
                        .lineTo(rect.getRight(), rect.getBottom())
                        .fillStroke()
                        .stroke().restoreState();
                // 在画布上写文字
                Canvas canvas = new Canvas(pdfCanvas, rect);
                // 基本文字样式
                canvas.setFont(font).setFontSize(14).setBold().setFontColor(ColorConstants.GRAY)
                        // 写文字
                        .showTextAligned("科 目", rect.getRight() - 20,
                                rect.getTop() - 5, TextAlignment.RIGHT,
                                VerticalAlignment.TOP, 0)
                        .showTextAligned("时 间", rect.getLeft() + 20,
                                rect.getBottom() + 5, TextAlignment.LEFT);
                // 关闭画布
                canvas.close();
                // 别忘了哟
                super.drawBorder(drawContext);
            }
        };
    }


}
