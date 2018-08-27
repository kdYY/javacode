package mobi;

public enum EventType {
  cache("缓存数据"), databack("返回数据"), finish("缓存完成");
  private String eventTypeName;

  private EventType(String eventTypeName) {
    this.eventTypeName = eventTypeName;
  }

  public String getEventTypeName() {
    return eventTypeName;
  }
}
