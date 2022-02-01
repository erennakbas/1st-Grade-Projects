import java.util.*;
public class PriorityQueue<T> extends Queue<T> {
    PriorityQueue()
    {
        name="PriorityQueue";
    }
    public void sort(Comparator<T> c)
    {
        list.sort(c);
    }
}
