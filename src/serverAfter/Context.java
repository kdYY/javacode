package serverAfter;

import serverAfter.utils.FileUtil;

import java.io.File;
import java.io.IOException;

//接口API
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    //引用策略方法(license校验方法)
    public JarProduct licenseRun(File src,File des,File doneJar) throws Exception {
        //解压jar包
        JarProduct product = null;
        try {
            FileUtil.unJar(src,des);
            //根据用户选择执行相应策略方法
            product = strategy.buildProduct();
            //打jar包
            //FileUtil.compressJarInRar(des,doneJar);
        } catch (IOException e) {
            throw  new RuntimeException("unjar error");
        }
        return product;
    }


}
