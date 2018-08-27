package mobi;

public class Event {
  private EventType eventType;
  private Object result;

  public Event(EventType eventType) {
    this.eventType = eventType;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }
}
