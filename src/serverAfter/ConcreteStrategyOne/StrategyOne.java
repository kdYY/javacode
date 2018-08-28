package serverAfter.ConcreteStrategyOne;

import serverAfter.CommonBuilder.InsertCheckCode;
import serverAfter.CommonBuilder.encryptedCode;
import serverAfter.JarProduct;
import serverAfter.Strategy;

//构造具体策略，返回产物
public class StrategyOne implements Strategy {

    String[] classPath;
    String classToChange;
    String methodName;
    String insertWord;
    String outPutPath;
    JarProduct product;

    public StrategyOne(JarProduct product,String[] classPath, String classToChange, String methodName, String insertWord, String outPutPath) {
        this.product = product;
        this.classPath = classPath;
        this.classToChange = classToChange;
        this.methodName = methodName;
        this.insertWord = insertWord;
        this.outPutPath = outPutPath;
    }

    //可以控制根据builder顺序构建策略,真正的执行委托给Context
    public JarProduct BuildProduct(){
        InsertCheckCode insertCheckCode = new InsertCheckCode(product,classPath,classToChange,methodName,insertWord,outPutPath);
        insertCheckCode.BuildPartProduct();
        JarProduct product_insert_after = insertCheckCode.getProduct();
        encryptedCode code = new encryptedCode();
        code.BuildPartProduct();
        code.getProduct();
        return insertCheckCode.getProduct();
    }


}
