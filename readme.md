### itext8


Itext例子
````

https://kb.itextpdf.com/itext/support-for-message-authentication-code-mac-integr

````

````
Itext基础操作

https://blog.csdn.net/xiaofeng_yang/article/details/139902089


````


生成数字文件

````

keytool -genkeypair -alias whj -keypass 111111 -storepass 111111 -dname "C=CN,ST=SD,L=QD,O=haier,OU=dev,CN=haier.com" -keyalg RSA -keysize 2048 -validity 3650 -keystore .\server.keystore

````


转换为p12格式,在命令行输入

````

keytool -importkeystore -srckeystore .\server.keystore -destkeystore .\whj.p12 -srcalias whj -destalias serverkey -srcstoretype jks -deststoretype pkcs12 -srcstorepass 111111 -deststorepass 111111 -noprompt

````


数字签名

````
https://kb.itextpdf.com/itext/itext-core-signature-appearance-improvements


https://kb.itextpdf.com/itext/digital-signatures-chapter-1


https://kb.itextpdf.com/itext/using-itext-7-and-aws-kms-to-digitally-sign-a-pd-4

````
