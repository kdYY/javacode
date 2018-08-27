package mobi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JdbcUtils {
  private static final String DRIVERCLASS;
  private static final String URL;
  private static final String USER;
  private static final String PASSWORD;

  static {
    ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    DRIVERCLASS = bundle.getString("driverClass");
    URL = bundle.getString("url");
    USER = bundle.getString("user");
    PASSWORD = bundle.getString("password");
  }

  static {
    // 注册驱动
    try {
      Class.forName(DRIVERCLASS);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  // 获取连接
  public static Connection getConnection() throws SQLException {
    // 获取连接
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  /**
   * 释放资源
   */
  public static void closeResource(Connection conn, Statement st, ResultSet rs) {
    closeResultSet(rs);
    closeStatement(st);
    closeConn(conn);
  }

  /**
   * 释放连接
   */
  public static void closeConn(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      conn = null;
    }

  }

  /**
   * 释放语句执行者
   */
  public static void closeStatement(Statement st) {
    if (st != null) {
      try {
        st.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      st = null;
    }

  }

  /**
   * 释放结果集
   */
  public static void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      rs = null;
    }

  }
}
