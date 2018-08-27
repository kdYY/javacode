package SimpleMR.test;

import SimpleMR.Worker;

public class Customworker extends Worker<Task> {
    @Override
    public Object handle(Task task){
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return task.getPrice();
    }

    @Override
    public Class<Task> getTaskClass() {
        return Task.class;
    }
}
