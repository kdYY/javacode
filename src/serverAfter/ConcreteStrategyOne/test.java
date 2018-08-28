package serverAfter.ConcreteStrategyOne;

import serverAfter.CommonBuilder.InsertCheckCode;
import serverAfter.Context;
import serverAfter.JarProduct;

public class test {
    public static void main(String[] args) {


        //解压jar,封装product

        JarProduct product = new JarProduct();
        product.setProductName("某部门的产品代码");
        product.setProductVersion("2.0");
        product.setJarName("SDI");
        product.setJarPath("/a/a/bb");
        String[] classPaths = {"C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec",
                "C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec\\BOOT-INF\\classes",
                "C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec\\BOOT-INF\\lib"};
        String classToChange = "cn.sinobest.sinogear.SinoGearExampleApp";
        String methodName = "main";
        String insertword = "    \ttry {\n" +
                "    \t\tif (!cn.sinobest.sonar.utils.LicenseChecker.isCerterfied()) {\n" +
                "    \t\t\tlogger.error(\"***********the license file is invalid (authentication is invalid or license file is expired), the system will exit!!!\");\n" +
                "        \t\tSystem.exit(-1);\n" +
                "    \t\t}\n" +
                "    \t} catch (Throwable e) {\n" +
                "    \t\tlogger.error(\"***********load license file fail (file not found or file format is damaged), the system will exit!!!\");\n" +
                "    \t\tSystem.exit(-1);\n" +
                "    \t}";
        String outPutPath = "C:\\Users\\yaokaidong\\Desktop\\kdtest";

        StrategyOne strategyOne = new StrategyOne(product,classPaths,classToChange,methodName,insertword,outPutPath);
        strategyOne.BuildProduct();


        Context context = new Context(strategyOne);
        //打包jar
        context.LicenseRun();


        ;
    }
}
