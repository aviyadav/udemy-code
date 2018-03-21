package simple.thread.demo.blockingqueuedemo;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockingQueueSample {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException ex) {
                Logger.getLogger(BlockingQueueSample.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Thread t2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException ex) {
                Logger.getLogger(BlockingQueueSample.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }
    
    private static void producer() throws InterruptedException {
        Random random = new Random();
        while(true) {
            queue.put(random.nextInt(100));
        }
    }
    
    private static void consumer() throws InterruptedException {
        Random random = new Random();
        
        while(true) {
            Thread.sleep(100);
            
            if(random.nextInt(10) == 0) {
                Integer value = queue.take();
                System.out.println("Taken value : " + value + "; Queue size is : " + queue.size());
            }
        }
    }
}
