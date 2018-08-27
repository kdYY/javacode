package mobi.State;

import mobi.Event;
import mobi.EventBus;
import mobi.EventType;
import mobi.StateEngine;

import java.util.Timer;
import java.util.TimerTask;

public class finishState implements State {
  {
    System.out.println("缓存完成状态");
  }

  @Override
  public void handle(StateEngine stateEngine, Event event) {
    // 状态机收到缓存事件
    if (event.getEventType() == EventType.cache) {
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          try {
            EventBus.getEventBus().putEvent(event);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }, 8000);
      stateEngine.setState(new InitState());
    } else {
      try {
        EventBus.getEventBus().putEvent(event);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
