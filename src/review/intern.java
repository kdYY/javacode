package review;

import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.DelayQueue;

public class intern implements Cloneable{
    public static void main(String[] args) {
        /**
         * 这段代码在JDK1.6中运行，会得到两个false，而在JDK1.7中运行，会得到一个true和两个false。产生差异的原因是：
         * 在JDK1.6中，intern（）方法会把首次遇到的字符串实例复制到永久代中，返回的也是永久代中这个字符串实例的引用，
         * 而由StringBuilder创建的字符串实例在Java堆上，所以必然不是同一个引用，将返回false。而JDK1.7（以及部分其他虚
         * 拟机，例如JRockit）的intern（）实现不会再复制实例，只是在常量池中记录首次出现的实例引用，因此intern（）返
         * 回的引用和由StringBuilder创建的那个字符串实例是同一个。对str2比较返回false是因为“java”这个字符串在执行
         * StringBuilder.toString（）之前已经出现过，字符串常量池中已经有它的引用了，不符合“首次出现”的原则，而
         * “计算机软件”这个字符串则是首次出现的，因此返回true。
         */
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str3 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str3.intern() == str3);
        String str2 = new StringBuilder("jav").append("a").toString();//常量池中已经事先存在"java"这个字符串
        System.out.println(str2.intern() == str2);
        intern in = new intern();
        intern in2 = null;
        try {
            System.out.println(in == in.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
//        System.out.println(in == in2);
       // list.add(null);

    }
/****************************************************************************************************************/

    @Test
    public void test1(){
        //        List<?> list = new ArrayList (); //success
//        List list = new ArrayList<? extends Object> (); //compile error: 通配符类型无法实例化

//        list.get(3);

//      List<(String.class)> tt = new ArrayList<>();//在类文字(class literal)中必须使用原生态类
//      在使用instanceof操作符时候有关时候
        List<?> o = new ArrayList<>();
        if(o instanceof List){
            List m = o;
            //经过泛型擦除，反而能添加了，这是很危险的行为(阻止不了语句的插入)，所以一定要
            // List<?> m = (List<?>)o; //继续保护m
            m.add("test");
        }

        //只可以读取到Number
        List<? extends Number> list = new ArrayList<Integer>();
        Number i= list.get(11);
        // Integer i= list.get(11);
        //不可插入任何对象除了null
        //list.add(12); error

        //只能插入Integer的子类的对象，因为Integer的子类同时也是Integer
        List<? super Integer> list1 = new ArrayList<Object>();
        list1.add(new Integer(20));
        //可以读到Object接收类型的数据
        Object obj = list.get(22);





    }


    //Java代码中的方法特征签名只包括了方法名称、参数顺序及参数类型，而在字节码中的特征签名还包括方法返回值及受查异常表，
    /*public static int fin(List<Integer> list) throws Exception{
        return 1;
    }*/
    //Java代码中的方法特征签名只包括了方法名称、参数顺序及参数类型
    public static String fin(List<Integer> list){
        return "1";
    }

    /**
     * 从上面例子中可以看出，java的泛型是伪泛型
     * 在C++中，标准的叫法是：类模版，在java中叫泛型。二者的作用是相同的，不一样的地方在编译解析的方式不同。
     * C++的模板特性是是编译时对于给定的不同的模板类型参数生成对应版本的目标代码。而Java中的泛型是用类型擦
     * 除实现的语法糖，实际上在编译期类型检查以外，生成目标代码的过程中根本不区分泛型的类型参数，只不过针对
     * object（而不是类型参数）生成代码，同时在必要处插入从object到给定的类型参数的类型转换而已（也因此类型
     * 参数不能是基本类型参数，C++模板就没这个限制），不会比手动实现显式类型转换有更高的运行期效率（不过可以
     * 在源代码中省略类型转换能稍微减少写源代码过程中的出错机会）。所以，某种意义上来说，Java的泛型是伪泛型，
     * 这也是Java中被人（例如Bruce Eckel）批评最多的特性之一。
     */

/****************************************************************************************/
    /**
     * 下面例子说明使用泛型的必要性
     */
    @Test
    public void test(){
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings,new Integer(42));
        String s = strings.get(0);
    }

    public void unsafeAdd(List list, Object o){
        list.add(o);
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }




}
