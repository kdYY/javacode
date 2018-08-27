package mobi.entity;

//数据库对应的实体
public class Orders {
  private String orderId;
  private String payment;
  private int state;
  private String state_discribe;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getPayment() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getState_discribe() {
    return state_discribe;
  }

  public void setState_discribe(String state_discribe) {
    this.state_discribe = state_discribe;
  }

  @Override
  public String toString() {
    return "Orders{" + "orderId='" + orderId + '\'' + ", payment='" + payment + '\'' + ", state=" +
           state + ", state_discribe='" + state_discribe + '\'' + '}';
  }
}
