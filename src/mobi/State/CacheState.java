package mobi.State;

import mobi.Event;
import mobi.EventBus;
import mobi.EventType;
import mobi.StateEngine;

public class CacheState implements State {
  {
    System.out.println("正在缓存状态");
  }

  @Override
  public void handle(StateEngine stateEngine, Event event) {
    // 状态机收到缓存完成事件
    if (event.getEventType() == EventType.finish) {
      // 状态改为完成状态
      stateEngine.setState(new finishState());
      stateEngine.notify(new Event(EventType.cache));
    } else {
      try {
        EventBus.getEventBus().putEvent(event);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
