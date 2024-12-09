package basic;

import com.itextpdf.kernel.pdf.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ItextPdfEncryption {
        // 需要进行加密操作的PDF文件
        public static final String pdfPathA = "./PDF文件A.pdf";
        public static final String pdfPathB = "./PDF文件B.pdf";
        public static final String pdfPathC = "./PDF文件C.pdf";
        public static final String pdfPathD = "./PDF文件D.pdf";
        // 源PDF文件，无任何加密的文档。
        public static final String notEncPath = "./简单示例.pdf";
        public static void main(String[] args) throws IOException {
             encryptionATest();
            // encryptionBTest();
            // readATest();
            // encryptionPDF();
            // updatePwd();
        }
        /***
         * 创建一个PDF，并将设置这个PDF的访问密码和操作密码
         * 执行代码会创建一个 PDF文件A.pdf 的文件，这个pdf打开需要输入“123” 用户密码
         * 当需要编辑PDF内容时需要输入 “456” 所有者密码
         */
        public static void encryptionATest() throws IOException {
            // 创建写文档的一些配置信息，并设置加密的信息
            WriterProperties wProps = new WriterProperties();
            wProps.setStandardEncryption(
                    "123".getBytes(StandardCharsets.UTF_8),
                    "456".getBytes(StandardCharsets.UTF_8),
                    EncryptionConstants.ALLOW_PRINTING,
                    EncryptionConstants.EMBEDDED_FILES_ONLY
            );
            // 创建一个PDF文件并构建PDF文档（PDF文件A.pdf）
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfPathA, wProps));
            // TODO 因为这里我只是测试文档加密，我就不用Document对象为这个文档写入内容了，直接关闭。
            // 关闭文档
            pdfDoc.close();
        }
        /***
         * 创建一个PDF，并将设置这个PDF的操作密码(所有者密码)
         * 执行代码会创建一个 PDF文件B.pdf 的文件，打开无需密码
         * 当需要编辑PDF内容时需要输入 “456” 所有者密码
         */
        public static void encryptionBTest() throws IOException {
            // 创建写文档的一些配置信息，并设置加密的信息
            WriterProperties wProps = new WriterProperties();
            wProps.setStandardEncryption(
                    null,
                    "456".getBytes(StandardCharsets.UTF_8),
                    EncryptionConstants.ALLOW_PRINTING,
                    EncryptionConstants.EMBEDDED_FILES_ONLY
            );
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfPathB, wProps));
            pdfDoc.close();
        }
        /***
         * 通过所有者密码可以获取文档的密码，所以文档密码可不能忘了
         */
        public static void readATest() throws IOException {
            // 创建读文档的一些配置信息，并设置访问的密码信息
            ReaderProperties rProps = new ReaderProperties();
            // 千万需要注意，这个所有者密码获取的字节编码可不能错了
            rProps.setPassword("456".getBytes(StandardCharsets.UTF_8));
            // 构建PDF文档对象，并读取信息
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(pdfPathA, rProps));
            // 获取的密码
            byte[] bytes = pdfDoc.getReader().computeUserPassword();
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
            pdfDoc.close();
        }
        /***
         * 将一个没有密码的PDF文件复制并写出一个带有密码的PDF文件
         */
        public static void encryptionPDF() throws IOException {
            // 创建写文档的一些配置信息，并设置加密的信息
            WriterProperties wProps = new WriterProperties();
            wProps.setStandardEncryption(
                    "123".getBytes(StandardCharsets.UTF_8),
                    "456".getBytes(StandardCharsets.UTF_8),
                    EncryptionConstants.ALLOW_PRINTING,
                    EncryptionConstants.EMBEDDED_FILES_ONLY
            );
            // 读取没有密码的PDF
            PdfReader pdfReader = new PdfReader(notEncPath);
            // 构建一个全新的PDF
            PdfWriter pdfWriter = new PdfWriter(pdfPathC, wProps);
            // 将读取的文件重新构建一份，并通过pdfWriter写出去。
            PdfDocument pdfDoc = new PdfDocument(pdfReader, pdfWriter);
            pdfDoc.close();
        }
        /***
         * 修改带密码的PDF文件，修改其实就是将带有密码的PDF读入，再通过写出的方式，写出一个新文件
         * 这种间接达到修改密码，到时候再将读取的文件删除即可
         */
        public static void updatePwd() throws IOException {
            // 通过 所有者密码读取 PDF文件
            PdfReader pdfReader = new PdfReader(pdfPathC, new ReaderProperties()
                    .setPassword("456".getBytes(StandardCharsets.UTF_8)));
            // 构建一个全新的PDF，并设置全新的密码
            PdfWriter pdfWriter = new PdfWriter(pdfPathD,
                    new WriterProperties().setStandardEncryption(
                            "111".getBytes(StandardCharsets.UTF_8),
                            "222".getBytes(StandardCharsets.UTF_8),
                            EncryptionConstants.ALLOW_PRINTING,
                            EncryptionConstants.EMBEDDED_FILES_ONLY));
            // 将带密码的文件重新写出去，原来的文件没动，间接达到修改密码
            PdfDocument pdfDoc = new PdfDocument(pdfReader, pdfWriter);
            pdfDoc.close();
        }
    }
