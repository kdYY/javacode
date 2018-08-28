package serverAfter.concreteStrategy;

import serverAfter.commonBuilder.InsertCheckCode;
import serverAfter.JarProduct;
import serverAfter.Strategy;
import serverAfter.utils.FileUtil;

import java.io.File;

//自定义license策略
public class StrategyOne extends Strategy {

    String LicenseFilePath;
    String LicenseSavePath;
    String CheckJarPath;

    public String getLicenseSavePath() {
        return LicenseSavePath;
    }

    public void setLicenseSavePath(String licenseSavePath) {
        LicenseSavePath = licenseSavePath;
    }

    public String getCheckJarPath() {
        return CheckJarPath;
    }

    public void setCheckJarPath(String checkJarPath) {
        CheckJarPath = checkJarPath;
    }

    public String getLicenseFilePath() {
        return LicenseFilePath;
    }

    public void setLicenseFilePath(String licenseFilePath) {
        LicenseFilePath = licenseFilePath;
    }

    //可以控制根据builder顺序构建策略,真正的执行委托给Context
    public JarProduct buildProduct() throws Exception{
        if(builderList.isEmpty()){
            throw new RuntimeException("commonbuilderList is empty");
        }
        //这个builder只有一个
        InsertCheckCode insertCheckCode = (InsertCheckCode)builderList.get(0);
        String Jarpath = insertCheckCode.getProduct().getJarPath();
        //放置license文件
        FileUtil.copyFile(new File(LicenseFilePath),new File(LicenseSavePath));
        //放入新的jar包  解压文件\BOOT-INF\lib
        FileUtil.copyFile(new File(CheckJarPath),new File(Jarpath + File.separator+"\\BOOT-INF\\lib\\sinobest-licence-strategy-basic-verify-0.0.1-SNAPSHOT.jar"));
        //执行插入license校验代码 没有放入新的jar包，代码将会插入不进去
        insertCheckCode.buildPartProduct();

        return insertCheckCode.getProduct();
    }


}
