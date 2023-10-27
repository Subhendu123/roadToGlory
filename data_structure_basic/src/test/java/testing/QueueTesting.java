package testing;

import com.roadtoglory.ds.stackq.Queue;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/public class QueueTesting
{
    public static void main (String[] args)
    {
        Queue queue = new Queue(1);
        queue.add(2);
        queue.add(3);
        queue.add(5);
        queue.remove();
        queue.printQueue();
        queue.add(6);
        queue.add(7);
        queue.remove();
        queue.add(10);
        queue.printQueue();
    }
}
