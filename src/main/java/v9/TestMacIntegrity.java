package v9;


import com.itextpdf.bouncycastleconnector.BouncyCastleFactoryCreator;
import com.itextpdf.kernel.mac.MacProperties;
import com.itextpdf.kernel.mac.MacProperties.MacDigestAlgorithm;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.security.Security;

import java.security.Security;
import com.itextpdf.bouncycastleconnector.BouncyCastleFactoryCreator;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.ReaderProperties;

public class TestMacIntegrity {
    public static void main(String[] args) throws Exception{
        //Initialize PDF writer
        byte[] USERPASSWORD = "12".getBytes();
        String inputfile = "./MacIntegrity.pdf";
        Security.addProvider(BouncyCastleFactoryCreator.getFactory().getProvider());
        try {
            PdfDocument test = new PdfDocument(
                    new PdfReader(inputfile,
                            new ReaderProperties().setPassword(USERPASSWORD)));
            System.out.println(inputfile + " is valid");
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}