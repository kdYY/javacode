package SimpleMR2.test;

import SimpleMR2.Worker;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class Customworker extends Worker<Task> {
    @Override
    public Object handle(Task task){
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }sum = sum*(Integer) v;
//                            sum += 5;
        int sum = 0;
        sum = sum + task.getPrice();
        sum /= 2;
        return ++sum;
    }

    @Override
    public Class<Task> getTaskClass() {
        return Task.class;
    }


}
