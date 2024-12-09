package org.example;


import com.itextpdf.commons.utils.FileUtil;
import com.itextpdf.forms.fields.properties.SignedAppearanceText;
import com.itextpdf.forms.form.element.SignatureFieldAppearance;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.crypto.DigestAlgorithms;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.borders.SolidBorder;
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
 * Basic example of the signature appearance customizing during the document signing.
 */
public class PadesSignatureAppearanceExample {
    private static final String CERT_PATH = "C:\\Users\\leyuan\\Desktop\\server.keystore";

    public static final String SRC = "C:\\Users\\leyuan\\IdeaProjects\\Printer\\stamp_example.pdf";
    public static final String DEST = "target/padesSignatureAppearanceExample.pdf";

    private static final char[] PASSWORD ="111111".toCharArray();

    public static final String IMAGE_PATH = "C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\zstbxmz.png";
    public static final String BOLD = "C:\\Users\\leyuan\\IdeaProjects\\Printer\\src\\main\\resources\\font\\Open-Sans-Bold.ttf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        Security.addProvider(new BouncyCastleProvider());

        new PadesSignatureAppearanceExample().signSignature(SRC, DEST);
    }

    /**
     * Basic example of the signature appearance customizing during the document signing.
     *
     * @param src  source file
     * @param dest destination file
     *
     * @throws Exception in case some exception occurred.
     */
    public void signSignature(String src, String dest) throws Exception {
        PdfPadesSigner padesSigner = new PdfPadesSigner(new PdfReader(FileUtil.getInputStreamForFile(src)),
                FileUtil.getFileOutputStream(dest));
        // We can pass the appearance through the signer properties.
        SignerProperties signerProperties = createSignerProperties();

        padesSigner.signWithBaselineBProfile(signerProperties, getCertificateChain(CERT_PATH,PASSWORD), getPrivateKey(CERT_PATH,PASSWORD));
    }

    /**
     * Creates properties to be used in signing operations. Also creates the appearance that will be passed to the
     * PDF signer through the signer properties.
     *
     * @return {@link SignerProperties} properties to be used for main signing operation.
     *
     * @throws IOException in case an I/O error occurs when reading the file.
     */
    protected SignerProperties createSignerProperties() throws IOException {
        SignerProperties signerProperties = new SignerProperties().setFieldName("Signature1");

        // Create the appearance instance and set the signature content to be shown and different appearance properties.
        SignatureFieldAppearance appearance = new SignatureFieldAppearance(signerProperties.getFieldName())
                .setContent(new SignedAppearanceText().setReasonLine("Customized reason: Reason").setLocationLine("Customized location: Location"), ImageDataFactory.create(IMAGE_PATH))
                .setBorder(new SolidBorder(ColorConstants.DARK_GRAY, 2))
                .setFont(PdfFontFactory.createFont(BOLD, PdfEncodings.IDENTITY_H));

        // Note that if SignedAppearanceText is set as a content, description text will be generated automatically, but
        // any `visual` values can be changed by using the appropriate setters. This won't affect the signature dictionary.

        // Set created signature appearance and other signer properties.
        signerProperties
                .setSignatureAppearance(appearance)
                .setPageNumber(1)
                .setPageRect(new Rectangle(50, 650, 200, 100))
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