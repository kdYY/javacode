package mobi.State;

import mobi.Event;
import mobi.StateEngine;

public interface State {
  void handle(StateEngine stateEngine, Event event);
}
