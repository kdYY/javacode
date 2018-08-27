package mobi;

public class StateEngineMain {
  public static void main(String[] args) throws InterruptedException {
    EventBus eventBus = EventBus.getEventBus();
    eventBus.putEvent(new Event(EventType.cache));
    eventBus.setObserver(new StateEngine());
    eventBus.task();
  }
}
