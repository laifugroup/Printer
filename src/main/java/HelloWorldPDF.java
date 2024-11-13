
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;

public class HelloWorldPDF {

    public static final String CREATED_PDF = "C:\\Users\\leyuan\\Desktop\\HelloWorld.pdf";

    public static void main(String[] args) {
        try {
            PDDocument pdDoc = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);

            pdDoc.addPage(page);
            // add page to the PDF document
            pdDoc.addPage(page);
            PDFont pdfFont=PDType0Font.load(pdDoc, new File("C:\\Windows\\Fonts\\simhei.TTF"));

            //PDType1Font pdfFont=new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            // For writing to a page content stream
            try ( PDPageContentStream cs = new PDPageContentStream(pdDoc, page)) {

                int fontSize = 56;

                cs.beginText();
                // setting font family and font size
                //cs.setFont(PDType1Font.COURIER, 15);
                cs.setFont(pdfFont, fontSize);
                // color for the text
                cs.setNonStrokingColor(Color.RED);
                // starting position
                cs.newLineAtOffset(20, PDRectangle.A4.getHeight()-100);
                cs.showText("乐园老年社交平台");
                // go to next line
                cs.newLine();
                cs.endText();


                String text="乐园老年社交平台";
                Point2D.Float center = new Point2D.Float(0, 100);
                addCenteredText(text, pdfFont, fontSize, cs, page, center);


                PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\leyuan\\Desktop\\111.png", pdDoc);
                cs.drawImage(pdImage,0,PDRectangle.A4.getHeight()+20);
            }


           // addImageToPage(pdDoc, page);

            // save PDF document
            pdDoc.save(CREATED_PDF);
            pdDoc.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    static void addCenteredText(String text, PDFont font, int fontSize, PDPageContentStream content, PDPage page, Point2D.Float offset) throws IOException {
        content.setFont(font, fontSize);
        content.beginText();

        // Rotate the text according to the page orientation
        boolean pageIsLandscape = isLandscape(page);
        Point2D.Float pageCenter = getCenter(page);

        // We use the text's width to place it at the center of the page
        float stringWidth = getStringWidth(text, font, fontSize);
        if (pageIsLandscape) {
            float textX = pageCenter.x - stringWidth / 2F + offset.x;
            float textY = pageCenter.y - offset.y;
            // Swap X and Y due to the rotation
            content.setTextMatrix(Matrix.getRotateInstance(Math.PI / 2, textY, textX));
        } else {
            float textX = pageCenter.x - stringWidth / 2F + offset.x;
            float textY = pageCenter.y + offset.y;
            content.setTextMatrix(Matrix.getTranslateInstance(textX, textY));
        }

        content.showText(text);
        content.endText();
    }

    static boolean isLandscape(PDPage page) {
        int rotation = page.getRotation();
        final boolean isLandscape;
        if (rotation == 90 || rotation == 270) {
            isLandscape = true;
        } else if (rotation == 0 || rotation == 360 || rotation == 180) {
            isLandscape = false;
        } else {
            //LOG.warn("Can only handle pages that are rotated in 90 degree steps. This page is rotated {} degrees. Will treat the page as in portrait format", rotation);
            isLandscape = false;
        }
        return isLandscape;
    }

    static Point2D.Float getCenter(PDPage page) {
        PDRectangle pageSize = page.getMediaBox();
        boolean rotated = isLandscape(page);
        float pageWidth = rotated ? pageSize.getHeight() : pageSize.getWidth();
        float pageHeight = rotated ? pageSize.getWidth() : pageSize.getHeight();

        return new Point2D.Float(pageWidth / 2F, pageHeight / 2F);
    }

    static float getStringWidth(String text, PDFont font, int fontSize) throws IOException {
        return font.getStringWidth(text) * fontSize / 1000F;
    }

    private static void addImageToPage(PDDocument pdDoc, PDPage pdPage) throws Exception{
        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\leyuan\\Desktop\\111.jpg", pdDoc);
        PDPageContentStream contentStream = new PDPageContentStream(pdDoc, pdPage);
        contentStream.drawImage(pdImage,0,0);
        contentStream.close();
    }

}