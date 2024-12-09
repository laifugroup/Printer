//package sign;
//
//
//
//import com.itextpdf.kernel.pdf.PdfReader;
//
//import java.io.*;
//import java.security.GeneralSecurityException;
//import java.security.KeyStore;
//import java.security.PrivateKey;
//import java.security.cert.Certificate;
//
///**
// * @Description
// * @Author 无为青年
// * @Date 2023/12/11 16:21
// * @Version 1.0
// **/
//public class PdfSign {
//
//    public static void main(String[] args) throws IOException, GeneralSecurityException {
//        sign(new FileInputStream("D:\\IdeaWorkSpace\\v1\\v2\\src\\main\\resources\\pdf\\模版-无公章.pdf"),
//                new FileOutputStream("D:\\IdeaWorkSpace\\v1\\v2\\src\\main\\resources\\pdf\\out.pdf"),
//                new FileInputStream("D:\\IdeaWorkSpace\\v1\\v2\\src\\main\\resources\\whj.p12"),
//                "111111".toCharArray(),
//                "理由",
//                "位置",
//                "D:\\IdeaWorkSpace\\v1\\v2\\src\\main\\resources\\pdf\\chapter.png");
//    }
//
//
//    /**
//     * 在已经生成的pdf上添加电子签章，生成新的pdf并将其输出出来
//     * @param src
//     * @param dest
//     * @param p12Stream
//     * @param password
//     * @param reason
//     * @param location
//     * @param chapterPath
//     * @throws GeneralSecurityException
//     * @throws IOException
//     */
//    public static void sign(InputStream src,   // 需要签章的pdf文件路径
//                            OutputStream dest,  // 签完章的pdf文件路径
//                            InputStream p12Stream, // p12证书路径
//                            char[] password,
//                            String reason,  // 签名的原因，显示在pdf签名属性中，随便填
//                            String location, // 签名的地点，显示在pdf签名属性中，随便填
//                            String chapterPath) // 签名图片的路径
//            throws GeneralSecurityException, IOException {
//
//        // 读取keystore，获得私钥和证书链
//        // 获取PKCS12类型的KeyStore实例
//        KeyStore ks = KeyStore.getInstance("PKCS12");
//        // 加载p12证书，使用密码进行解密
//        ks.load(p12Stream, password);
//        // 获取证书别名
//        String alias = (String) ks.aliases().nextElement();
//        // 通过别名和密码获取私钥
//        PrivateKey pk = (PrivateKey) ks.getKey(alias, password);
//        // 获取证书链
//        Certificate[] chain = ks.getCertificateChain(alias);
//
//        // 读取源PDF文件
//        PdfReader reader = new PdfReader(src);
//        // 创建一个PdfStamper对象，用于对PDF进行签名操作
//
//        PdfStamper stamper = PdfStamper.createSignature(reader, dest, '\0', null, false);
//        // 获取签名外观对象
//        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
//        // 设置签名原因
//        appearance.setReason(reason);
//        // 设置签名地点
//        appearance.setLocation(location);
//        // 设置签名的位置和页面
//        appearance.setVisibleSignature(new Rectangle(300, 600, 630, 500), 1, "sig1");
//
//        // 添加签名图片
//        // 获取签名图片
//        Image image = Image.getInstance(chapterPath);
//        // 设置签名图片
//        appearance.setSignatureGraphic(image);
//        // 设置认证级别，不允许签名后再修改
//        appearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
//        // 设置签名的渲染模式为图片
//        appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
//
//        // 创建摘要和签名对象
//        ExternalDigest digest = new BouncyCastleDigest();
//        // 使用私钥进行签名，采用SHA-256算法
//        ExternalSignature signature = new PrivateKeySignature(pk, DigestAlgorithms.SHA256, null);
//
//        // 使用MakeSignature.signDetached方法进行签名操作，附加签名
//        MakeSignature.signDetached(appearance, digest, signature, chain, null, null, null, 0, MakeSignature.CryptoStandard.CADES);
//    }
//}