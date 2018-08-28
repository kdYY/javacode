package serverAfter;

import java.util.ArrayList;
import java.util.List;


public abstract class Strategy {

    protected  List<StrategyBuilder> builderList = new ArrayList<StrategyBuilder>();

    public void setBuilderList(List<StrategyBuilder> builderList) {
        this.builderList = builderList;
    }

    public void addBulilder(StrategyBuilder builder){
        builderList.add(builder);
    }

    /**
     *
     * @return
     */
    public abstract JarProduct buildProduct() throws Exception;


}
