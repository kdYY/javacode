package SimpleMR2.test;

import SimpleMR2.Master;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class test {

    public static void main(String[] args) {

        List<Task> taskList = new LinkedList<Task>();
        Master<Task> master = new Master<Task>(new Customworker(),10,2000000);
        for (int i = 0; i < 1990000; i++) {
            Task task = new Task();
            task.setId(i);
            task.setPrice(10);
            taskList.add(task);
            master.submitTask(task);
        }

        master.executeThread();
        long startTime = System.currentTimeMillis();
        System.out.print("总和为"+master.getFinishedSum());
        long end = System.currentTimeMillis();
        System.out.println(",此MW组件所花费时间为"+(end-startTime));
        startTime = System.currentTimeMillis();
        int total=0;
        for (Task task3:
             taskList) {
            if(task3 != null) { //给出相对较为公平的环境语句
                total +=task3.getPrice()/2;
                total++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("总和为："+total+"别人的组件花费时间为"+(end-startTime));


    }
}
