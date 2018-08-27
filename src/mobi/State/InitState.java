package mobi.State;

import mobi.*;
import mobi.entity.Orders;

import java.sql.SQLException;
import java.util.List;

public class InitState implements State {
  {
    System.out.println("初始状态");
  }

  @Override
  public void handle(StateEngine stateEngine, Event event) {
    // 状态机收到缓存事件
    if (event.getEventType() == EventType.cache) {
      //异步查询数据
      new Thread() {
        @Override
        public void run() {
          try {
            List<Orders> cache = stateEngine.loadData();
            Event event = new Event(EventType.databack);
            event.setResult(cache);
            EventBus.getEventBus().putEvent(event);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }.start();
      // 改为等待加载状态
      stateEngine.setState(new waitLoadingState());
    } else {
      try {
        EventBus.getEventBus().putEvent(event);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
