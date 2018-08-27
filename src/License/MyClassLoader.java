package License;

public class MyClassLoader extends ClassLoader {


    /**
     * 类的名字，以及一个表示JVM是否要求解析类名字的标记（即是否同时装入有依赖关系的类）
     * 如果这个标记是true，我们只需在返回JVM之前调用resolveClass。
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //要创建的class对象
        Class clasi = null;
        // 必需的步骤1：如果类已经在系统缓冲之中，
        // 我们不必再次装入它
        clasi = findLoadedClass(name);
        if(clasi != null){
            return clasi;
        }
        //定制
        //获取字节码数据
        byte classData[]  = {};
        if(classData != null){
            clasi = defineClass(name,classData,0,classData.length);
        }
        //如果上述不成功，交给系统加载器去加载
        if(clasi == null){
            clasi = findSystemClass(name);
        }
        //如果有必要，则装入相关类
        if(resolve && clasi != null){
            resolveClass(clasi);
        }
        //把类返回给调用者
        return clasi;
    }
}
