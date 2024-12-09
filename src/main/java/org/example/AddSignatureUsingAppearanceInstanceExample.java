package org.example;

import com.itextpdf.commons.utils.FileUtil;
import com.itextpdf.forms.form.element.SignatureFieldAppearance;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.crypto.DigestAlgorithms;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
//import com.itextpdf.signatures.DigestAlgorithms;
import com.itextpdf.signatures.PdfPadesSigner;
import com.itextpdf.signatures.PrivateKeySignature;
import com.itextpdf.signatures.SignerProperties;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * Basic example of creating the signature field via signature appearance layout element.
 */
public class AddSignatureUsingAppearanceInstanceExample {
    private static final String CERT_PATH = "C:\\Users\\leyuan\\Desktop\\server.keystore";

    public static final String SRC = "C:\\Users\\leyuan\\IdeaProjects\\Printer\\stamp_example.pdf";
    public static final String DEST = "./target/signSignatureAddedUsingAppearance.pdf";
    public static final String DOC_TO_SIGN = "target/signatureAddedUsingAppearance.pdf";

    private static final String SIGNATURE_NAME = "Signature1";

    private static final char[] PASSWORD = "111111".toCharArray();

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        Security.addProvider(new BouncyCastleProvider());

        new AddSignatureUsingAppearanceInstanceExample().createAndSignSignature(SRC, DOC_TO_SIGN, DEST, SIGNATURE_NAME);
    }

    /**
     * Basic example of creating the signature field via signature appearance layout element and signing it.
     *
     * @param src           source file
     * @param doc           destination for the file with added signature field
     * @param dest          final destination file
     * @param signatureName the name of the signature field to be added and signed
     *
     * @throws Exception in case some exception occurred.
     */
    public void createAndSignSignature(String src, String doc, String dest, String signatureName) throws Exception {
        addSignatureFieldAppearanceToTheDocument(src, doc, signatureName);

        PdfPadesSigner padesSigner = new PdfPadesSigner(new PdfReader(FileUtil.getInputStreamForFile(doc)),
                FileUtil.getFileOutputStream(dest));
        // We can pass the appearance through the signer properties.
        SignerProperties signerProperties = createSignerProperties(signatureName);

        padesSigner.signWithBaselineBProfile(signerProperties, getCertificateChain(CERT_PATH,PASSWORD), getPrivateKey(CERT_PATH,PASSWORD));
    }

    /**
     * Basic example of creating the signature field via signature appearance layout element.
     *
     * @param src           source file
     * @param dest          destination for the file with added signature field
     * @param signatureName the name of the signature field to be added
     *
     * @throws IOException in case an I/O error occurs.
     */
    protected void addSignatureFieldAppearanceToTheDocument(String src, String dest, String signatureName)
            throws IOException {
        try (Document document = new Document(new PdfDocument(new PdfReader(src), new PdfWriter(dest)))) {
            Table table = new Table(2);
            Cell cell = new Cell(0, 2).add(new Paragraph("Test signature").setFontColor(ColorConstants.WHITE));
            cell.setBackgroundColor(ColorConstants.GREEN);
            table.addCell(cell);
            cell = new Cell().add(new Paragraph("Signer"));
            cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(cell);

            // Add signature field to the table.
            cell = new Cell().add(
                    new SignatureFieldAppearance(signatureName)
                            .setContent("Sign here")
                            .setHeight(50)
                            .setWidth(100)
                            .setInteractive(true));
            table.addCell(cell);

            document.add(table);
        }
    }

    /**
     * Creates properties to be used in signing operations. Also creates the appearance that will be passed to the
     * PDF signer through the signer properties.
     *
     * @param signatureName the name of the signature field to be signed
     *
     * @return {@link SignerProperties} properties to be used for main signing operation.
     */
    protected SignerProperties createSignerProperties(String signatureName) {
        SignerProperties signerProperties = new SignerProperties().setFieldName(signatureName);

        // Create the appearance instance and set the signature content to be shown and different appearance properties.
        SignatureFieldAppearance appearance = new SignatureFieldAppearance(signerProperties.getFieldName())
                .setContent("Signer", "Signature description. " +
                        "Signer is replaced to the one from the certificate.")
                .setBackgroundColor(ColorConstants.YELLOW);

        // Set created signature appearance and other signer properties.
        signerProperties
                .setSignatureAppearance(appearance)
                .setReason("Reason")
                .setLocation("Location");
        return signerProperties;
    }

    /**
     * Creates signing chain for the sample. This chain shouldn't be used for the real signing.
     * @param certificatePath
     * @param password
     * @return the chain of certificates to be used for the signing operation.
     */
    private static Certificate[] getCertificateChain(String certificatePath, char[] password) throws Exception {
        Certificate[] certChain = null;

        KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(new FileInputStream(certificatePath), password);

        Enumeration<String> aliases = p12.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (p12.isKeyEntry(alias)) {
                certChain = p12.getCertificateChain(alias);
                break;
            }
        }
        return certChain;
    }

    /**
     * Creates private key for the sample. This key shouldn't be used for the real signing.
     * @param certificatePath
     * @param password
     * @return {@link PrivateKey} instance to be used for the main signing operation.
     */
    private static PrivateKeySignature getPrivateKey(String certificatePath, char[] password) throws Exception {
        PrivateKey pk = null;

        KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(new FileInputStream(certificatePath), password);

        Enumeration<String> aliases = p12.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (p12.isKeyEntry(alias)) {
                pk = (PrivateKey) p12.getKey(alias, password);
                break;
            }
        }
        Security.addProvider(new BouncyCastleProvider());
        return new PrivateKeySignature(pk, DigestAlgorithms.SHA512, BouncyCastleProvider.PROVIDER_NAME);
    }
}