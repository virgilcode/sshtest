package org.virgil.jdk.thread;

import java.util.concurrent.*;

/**
 * Created by Virgil on 2017/9/8.
 */
public class MultiCallableAndFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 5; i++) {
            final int taskID = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println("run " + taskID);
                    Thread.sleep(2000);
                    return taskID;
                }
            });
        }
        for (int i = 1; i < 5; i++) {
            try {

                System.out.println("get: " + completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
