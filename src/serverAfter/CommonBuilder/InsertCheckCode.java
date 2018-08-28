package serverAfter.CommonBuilder;

import javassist.*;
import serverAfter.JarProduct;
import serverAfter.StrategyBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//插入校验代码
public class InsertCheckCode extends StrategyBuilder {

    ArrayList<String> classPath;
    String classToChange;
    String methodName;
    String insertWord;
    String outPutPath;


    public InsertCheckCode(JarProduct product, ArrayList<String> classPath, String classToChange, String methodName, String insertWord, String outPutPath) {
        this.product = product;
        this.classPath = classPath;
        this.classToChange = classToChange;
        this.methodName = methodName;
        this.insertWord = insertWord;
        this.outPutPath = outPutPath;
    }

    public JarProduct buildPartProduct() throws Exception{
        //对产品代码进行插入校验,并将新的文件输出
        insert(classPath,classToChange,methodName,insertWord,outPutPath);
        this.product.writeLogForProduct("add InsertCheckCode--");
        return this.product;
    }

    /**
     * 根据classPath构建CtClass
     * @param classPath
     * @param classToChange
     * @return
     */
    public CtClass init(ArrayList<String> classPath, String classToChange)throws Exception{
        try {
            File file = new File(classPath.get(1));
            File[] classPaths = file.listFiles();
            for (File file2 : classPaths) {
                classPath.add(file2.getAbsolutePath());
                System.out.println(file2.getAbsolutePath());
            }
            ClassPool.getDefault().insertClassPath(classPath.get(0));
            for (int i = 1; i < classPath.size(); i++) {
                ClassPool.getDefault().appendClassPath(classPath.get(i));
            }
            return ClassPool.getDefault().get(classToChange);
        } catch (NotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     *
     * @param classPath
     * @param classToChange 要修改的类路径
     * @param methodName 要修改的方法名
     * @param insertWord   插入的代码
     * @param outPutPath    新类输出的路径
     * @throws Exception
     */
    public  void insert(ArrayList<String> classPath,String classToChange,String methodName, String insertWord,String outPutPath) throws Exception{
        try {
            CtClass class0 = init(classPath,classToChange);
            CtMethod method = class0.getDeclaredMethod(methodName);
            method.insertBefore(insertWord);
            //将类进行冻结
            class0.defrost();
            //输出到文件
            class0.writeFile(outPutPath);
        } catch (NotFoundException e1) {
            throw new RuntimeException(e1.getMessage());
        } catch (CannotCompileException e2) {
            throw new RuntimeException(e2.getMessage());
        } catch (IOException e3) {
            throw new RuntimeException(e3.getMessage());
        }
    }

}
