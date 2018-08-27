package mobi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

//Singleton
public class EventBus {
  private static EventBus eventBus = new EventBus();
  private Observer observer;

  private BlockingQueue<Event> blockingQueue = new LinkedBlockingDeque<Event>();

  private EventBus() {

  }

  public Observer getObserver() {
    return observer;
  }

  public void setObserver(Observer observer) {
    this.observer = observer;
  }

  public static EventBus getEventBus() {
    return eventBus;
  }

  //多线程竞争
  public void putEvent(Event event) throws InterruptedException {
    this.blockingQueue.put(event);
  }

  public Event takeEvent() throws InterruptedException {
    return this.blockingQueue.take();
  }

  public int getQueueCurrentSize() {
    return this.blockingQueue.size();
  }

  public void task() throws InterruptedException {
    while (true) {
      Event event = takeEvent();
      observer.notify(event);
    }
  }
}
