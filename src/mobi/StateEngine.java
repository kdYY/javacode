package mobi;

import mobi.State.InitState;
import mobi.State.State;
import mobi.entity.Orders;
import mobi.utils.CacheManagerUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class StateEngine implements Observer {
  private State state;
  private HashMap<String, Object> cacheMap = new HashMap<>();

  /*{
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        try {
          EventBus.getEventBus().putEvent(new Event(EventType.cache));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }, 0, 10000);
  }*/

  public StateEngine() {
    this.state = new InitState();
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public List<Orders> loadData() throws InterruptedException, SQLException {
    List<Orders> cache = CacheManagerUtil.cache();
    Thread.sleep(3000);
    return cache;
  }

  //多线程竞争
  public void setCache(List<Orders> ordersList) throws InterruptedException {
    for (Orders orders : ordersList) {
      cacheMap.put(orders.getOrderId(), orders);
    }
    Thread.sleep(3000);

  }

  public void clearCache() {
    this.cacheMap.clear();
  }

  @Override
  public void notify(Event event) {
    this.state.handle(this, event);
  }
}
