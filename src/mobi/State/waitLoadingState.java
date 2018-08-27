package mobi.State;

import mobi.*;
import mobi.entity.Orders;

import java.util.List;

public class waitLoadingState implements State {
  {
    System.out.println("等待加载状态");
  }

  @Override
  public void handle(StateEngine stateEngine, Event event) {
    // 状态机收到数据返回事件
    if (event.getEventType() == EventType.databack) {
      // 状态改为正在缓存状态
      stateEngine.setState(new CacheState());

      // 从事件中获取返回的数据
      Object result = event.getResult();
      List<Orders> ordersList = (List<Orders>) result;

      try {
        stateEngine.setCache(ordersList);
        event = new Event(EventType.finish);
        EventBus.getEventBus().putEvent(event);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    } else {
      try {
        EventBus.getEventBus().putEvent(event);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
