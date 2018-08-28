package serverAfter.client;

import serverAfter.commonBuilder.InsertCheckCode;
import serverAfter.concreteStrategy.StrategyOne;
import serverAfter.Context;
import serverAfter.JarProduct;

import java.io.File;
import java.util.ArrayList;

public class AppLicense {
    public static void main(String[] args) throws Exception {
        //解压jar,封装product
        String unJarpath = "C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec";
        String jarName = "sim-manager-0.1.0-SNAPSHOT-exec";
        String licPath = "C:\\Users\\yaokaidong\\Desktop\\license.lic";

        File srcFile = new File(unJarpath+".jar");//jar存放的位置
        File desFile = new File(unJarpath); //unJarpath
        File compressJar = new File("E:\\");;

        JarProduct product = new JarProduct();
        product.setProductName("某部门产品jar");
        product.setProductVersion("2.0");
        product.setJarName(jarName);
        product.setJarPath(unJarpath);

        //需要lib和classes的路径

        ArrayList<String> alljar = new ArrayList<String>();
        alljar.add(unJarpath);
        alljar.add(unJarpath + File.separator+"BOOT-INF\\lib");
        alljar.add(unJarpath + File.separator+"BOOT-INF\\classes");


        String classToChange = "cn.sinobest.sinogear.SinoGearExampleApp";
        String methodName = "main";
        String insertword = "    \ttry {\n" +
                "    \t\tif (!cn.sinobest.ggjs.tool.LicenceManager.getInstance().isValid()) {\n" +
                "\tlogger.error(\"***********the license file is invalid (authentication is invalid or license file is expired), the system will exit!!!\");"+
                "        \t\tSystem.exit(-1);\n" +
                "    \t\t}\n" +
                "    \t} catch (Throwable e) {\n" +
                "\tlogger.error(\"***********the license file is invalid (authentication is invalid or license file is expired), the system will exit!!!\");"+
                "    \t\tSystem.exit(-1);\n" +
                "    \t}";
        String outPutPath = unJarpath+File.separator+"BOOT-INF\\classes";

        StrategyOne strategyOne = new StrategyOne();
        InsertCheckCode insertBuilder = new InsertCheckCode(product,alljar,classToChange,methodName,insertword,outPutPath);
        strategyOne.addBulilder(insertBuilder);
        strategyOne.setLicenseFilePath(licPath);
        strategyOne.setLicenseSavePath("C:\\Users\\yaokaidong\\Desktop\\sim-manager-0.1.0-SNAPSHOT-exec\\BOOT-INF\\classes\\license.lic");
        strategyOne.setCheckJarPath("C:\\Users\\yaokaidong\\Desktop\\sinobest-licence-strategy-basic-verify-0.0.1-SNAPSHOT.jar");

        Context context = new Context(strategyOne);
        context.licenseRun(srcFile,desFile,compressJar);
    }

}
