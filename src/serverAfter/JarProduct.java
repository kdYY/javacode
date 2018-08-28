package serverAfter;

//无论用什么策略，最后的产出物都是一个jar
public  class JarProduct {

    protected String ProductName;
    protected String ProductVersion;

    protected String JarName;
    protected String JarPath;

    protected final StringBuffer logForProduct = new StringBuffer();

    //记录策略修改日志log
    public String writeLogForProduct(String log){
        logForProduct.append(log);
        return logForProduct.toString();
    }
    public String getLog(){
        return logForProduct.toString();
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductVersion() {
        return ProductVersion;
    }

    public void setProductVersion(String productVersion) {
        ProductVersion = productVersion;
    }

    public String getJarName() {
        return JarName;
    }

    public void setJarName(String jarName) {
        JarName = jarName;
    }

    public String getJarPath() {
        return JarPath;
    }

    public void setJarPath(String jarPath) {
        JarPath = jarPath;
    }

    //用户自定义开发license产品其他方法
//    public abstract void ProductMethod();

}
