echo 'SHA-1 ���� ȹ��'
SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_111\bin
SET PATH=PATH;%JAVA_HOME%

keytool -list -v -keystore release.jks

pause