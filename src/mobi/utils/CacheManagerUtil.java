package mobi.utils;

import mobi.entity.Orders;
import mobi.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CacheManagerUtil {
  public static List<Orders> cache() throws SQLException {
    List<Orders> orderList = new ArrayList<>();
    Connection conn = JdbcUtils.getConnection();
    String sql = "select * from orders;";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet res = pstmt.executeQuery();
    while (res.next()) {
      Orders orders = new Orders();
      orders.setOrderId(res.getString(1));
      orders.setPayment(res.getString(2));
      orders.setState(res.getInt(3));
      orders.setState_discribe(res.getString(4));
      orderList.add(orders);
    }
    JdbcUtils.closeResource(conn, pstmt, res);
    return orderList;
  }
}
