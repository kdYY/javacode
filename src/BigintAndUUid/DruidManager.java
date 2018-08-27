package BigintAndUUid;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DruidManager{
    private DruidManager() {}
    private static volatile DruidManager single=null;
    private volatile DruidDataSource dataSource;

    public synchronized  static DruidManager getInstance() {
        if (single == null) {
            single = new DruidManager();
            single.initPool();
        }
        return single;
    }
    private void initPool() {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/kdtest?characterEncoding=utf-8&useSSL=false");
        dataSource.setInitialSize(4);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        // 启用监控统计功能
       /* try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }*/// for mysql
        dataSource.setPoolPreparedStatements(false);
    }
    //要考虑多线程的情况
    public Connection getConnection(){
        Connection connection = null;
        try {
            synchronized(dataSource){
                if (connection==null){
                    connection = dataSource.getConnection();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}