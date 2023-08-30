package base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallerTask implements Callable<String> {
    public String call() throws Exception {
//        return "Hello,i am running!";
        return "thread created by implements caller way";
    }

    public static void main(String[] args) {
        // 创建异步任务
        FutureTask<String> task = new FutureTask<String>(new CallerTask());
        // 启动线程
        new Thread(task).start();
        try {
            // 等待执⾏完成，并获取返回结果
            String result = task.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
