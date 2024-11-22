//package all;
//
//public class SealSample {
//    public static void main(String[] args) throws Exception {
//     /*===========================================================
//  核心代码片段：在电子发票PDF版式文件模板上绘制发票监制章
//  author: 海之边  qq-3094353627
//  ===========================================================*/
////31.1 绘制外边框
//        canvas.setLineWidth(PDFTool.millimetreToPixle(1.0f));
//        canvas.setColorStroke(getStampColor());
//        canvas.setColorFill(getStampColor());
//        float fStampOneX = PDFTool.millimetreToPixle(90.5f);
//        float fStampOneY = PDFTool.millimetreToPixle(140.0f - 8.5f);
//        float fStampTwoX = PDFTool.millimetreToPixle(119.5f);
//        float fStampTwoY = PDFTool.millimetreToPixle(140.0f - 27.5f);
//        canvas.ellipse(fStampOneX, fStampOneY, fStampTwoX, fStampTwoY);
//        canvas.stroke();
//
////31.2 绘制内边框
//        canvas.setLineWidth(PDFTool.millimetreToPixle(0.2f));
//        fStampOneX = PDFTool.millimetreToPixle(91.4f + 0.1f);
//        fStampOneY = PDFTool.millimetreToPixle(140.0f - 9.4f - 0.1f);
//        fStampTwoX = PDFTool.millimetreToPixle(118.6f - 0.1f);
//        fStampTwoY = PDFTool.millimetreToPixle(140.0f - 26.6f + 0.1f);
//        canvas.ellipse(fStampOneX, fStampOneY, fStampTwoX, fStampTwoY);
//        canvas.stroke();
//
//
////31.3 绘制"国家税务总局"文本
//        Font fontZhengKai = PDFTool.getFontZhengKai();
//        BaseFont fontBaseZK = PDFTool.getBaseFontZhengKai();
//        fontZhengKai.setSize(7.0f);
//        fontZhengKai.setColor(getStampColor());
//        p = new Paragraph("国家税务总局", fontZhengKai);
//        p.setAlignment(Element.ALIGN_CENTER);
//        table = new PdfPTable(1);
//        table.setTotalWidth(PDFTool.millimetreToPixle(30.0f));
//        table.setSpacingAfter(0.0f);
//
//
//        cell = new PdfPCell(p);
//        cell.setBorder(Rectangle.NO_BORDER);
//        cell.setFixedHeight(PDFTool.millimetreToPixle(20.0f));
//        cell.setUseAscender(true);
//        cell.setUseDescender(true);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        table.addCell(cell);
//        tableX = PDFTool.millimetreToPixle(90.0f);
//        tableY = PDFTool.millimetreToPixle(140.0f - 8.0f - 0.0f);
//        table.writeSelectedRows(0, -1, tableX, tableY, canvas);
//
////椭圆中心点
//        float fCenterX = PDFTool.millimetreToPixle(105.0f);
//        float fCenterY = PDFTool.millimetreToPixle(122.0f);
//        float fA = PDFTool.millimetreToPixle(13.4f);
//        float fB = PDFTool.millimetreToPixle(8.4f);
//
////平分弧
//        int splitCount = 9;
//        float startAngle = -10.0f;
//        float endAngle = 190.0f;
//        List<PointF> lstEqualPnt = EllipseTool.calcEllipseEqualPoint(fA - 7.0f,
//                fB - 7.0f, startAngle, endAngle, splitCount);
//        System.out.println("end");
//        PointF pConvert = new PointF();
//        String sTitle = "全国统一发票监制章";
//        int idx = 0;
//        canvas.setFontAndSize(PDFTool.getBaseFontZhengKai(), 7);
//        canvas.setColorFill(getStampColor());
//        for (PointF pnt : lstEqualPnt) {
//            String sCur = sTitle.substring(8 - idx, 9 - idx);
//            idx++;
//
//            //计算切线度数
//            float fTanAngleArc = EllipseTool.calcEllipseTangentLineDegree(fA - 7.0f,
//                    fB - 7.0f, pnt);
//
//            //坐标点转换
//            pConvert.X = pnt.X + fCenterX;
//            pConvert.Y = pnt.Y + fCenterY;
//
//            //输出文字
//            canvas.beginText();
//            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, sCur, (float)pConvert.X,
//                    (float)pConvert.Y, (float)(fTanAngleArc * 180 / Math.PI));
//            canvas.endText();
//        }
//
////绘制下方文字
//        java.awt.Font f1 = new java.awt.Font(PDFTool.getFontZhengKai().getFamilyname(),
//                java.awt.Font.PLAIN, 7);
//        java.awt.FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f1);
//        int fontWid = fm.stringWidth("税");
//
//        sTitle = "新疆维吾尔自治区税务局";
//        int iSplitCnt = sTitle.length() - 1;
//        float fTotalArcLen = iSplitCnt * (fontWid + 2);
////计算第四象限结束度数(度)
//        float fEndAngle = EllipseTool.calcLowerArcLenFourQuadrantStartAngle(fA - 2.0f,
//                fB - 2.0f, fTotalArcLen);
////计算第三象限起始度数(度)
//        float fStartAngle = 270.0f - (fEndAngle - 270.0f);
//
////计算平分点
//        lstEqualPnt = EllipseTool.calcEllipseEqualPoint(fA - 2.0f, fB - 2.0f, fStartAngle,
//                fEndAngle, iSplitCnt);
//
////添加起始平分点
//        PointF pntStart = EllipseTool.calcEllipsePoint(fA - 2.0f, fB - 2.0f,
//                (float) (fStartAngle * Math.PI / 180.f));
//        lstEqualPnt.add(0, pntStart);
//        PointF pntEnd = EllipseTool.calcEllipsePoint(fA - 2.0f, fB - 2.0f,
//                (float) (fEndAngle * Math.PI / 180.f));
//        lstEqualPnt.add(pntEnd);
//
//        idx = 0;
//        canvas.setFontAndSize(PDFTool.getBaseFontZhengKai(), 7);
//        canvas.setColorFill(getStampColor());
//        for (PointF pnt : lstEqualPnt) {
//            String sCur = sTitle.substring(idx, idx + 1);
//            idx++;
//
//            //计算切线度数
//            float fTanAngleArc = EllipseTool.calcEllipseTangentLineDegree(fA - 2.0f,
//                    fB - 2.0f, pnt);
//
//            //坐标点转换
//            pConvert.X = pnt.X + fCenterX;
//            pConvert.Y = pnt.Y + fCenterY;
//
//
//            //输出文字
//            canvas.beginText();
//            canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, sCur, (float)pConvert.X,
//                    (float)pConvert.Y, (float)(fTanAngleArc * 180 / Math.PI));
//            canvas.endText();
//        }
//    }
//}