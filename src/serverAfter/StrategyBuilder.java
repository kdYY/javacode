package serverAfter;

public abstract class StrategyBuilder {

    protected JarProduct product;

    public void setProduct(JarProduct product) {
        this.product = product;
    }
    public JarProduct getProduct(){
        return  product;
    }

    //具体的
    public abstract JarProduct buildPartProduct()throws Exception;



}
