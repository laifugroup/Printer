package v9;


import java.security.Security;
import com.itextpdf.bouncycastleconnector.BouncyCastleFactoryCreator;
import com.itextpdf.kernel.mac.MacProperties;
import com.itextpdf.kernel.mac.MacProperties.MacDigestAlgorithm;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;

public class MACSign {
        public static void main(String[] args) throws Exception{
            byte[] USERPASSWORD = "".getBytes();
            byte[] MASTERPASSWORD = "master".getBytes();
            Security.addProvider(BouncyCastleFactoryCreator.getFactory().getProvider());
            //Initialize PDF writer with MacProperties
            WriterProperties writerProperties =
                    new WriterProperties().setPdfVersion(PdfVersion.PDF_2_0).setStandardEncryption(
                            USERPASSWORD,
                            MASTERPASSWORD,
                            0,
                            EncryptionConstants.ENCRYPTION_AES_256,
                            new MacProperties(MacDigestAlgorithm.SHA_256));
            PdfWriter writer = new PdfWriter("./MacIntegrity.pdf", writerProperties);
            //Initialize PDF document
            PdfDocument pdf = new PdfDocument(writer);
            // Initialize document
            Document document = new Document(pdf);
            //Add paragraph to the document
            document.add(new Paragraph("Hello Mac Integrity!"));
            //Close document
            document.close();
        }
    }