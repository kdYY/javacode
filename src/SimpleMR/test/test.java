package SimpleMR.test;

import SimpleMR.Master;

public class test {

    public static void main(String[] args) {
        Master<Task> master = new Master<Task>(new Customworker(),10);
        for (int i = 0; i < 1000; i++) {
            Task task = new Task();
            task.setId(i);
            task.setPrice(1000);
            master.submitTask(task);
        }
        master.executeThread();

       System.out.println("总和为"+master.getSum()+"taskNum "+Customworker.getTaskID());
    }
}
