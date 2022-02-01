import java.util.*;
public class Queue<T> implements Iterable<T> {
    protected ArrayList<T> list=new ArrayList<T>();
    String name;
    Queue()
    {
        name="Queue";
    }
    //Methods that is done via list attribute.
    public void enqueue(T obj)
    {
        list.add(obj);
    }
    public void dequeue()
    {
        list.remove(list.size()-1);
    }
    public void dequeue(int index)
    {
        list.remove(index);
    }
    public T get(int index)
    {
        return list.get(index);
    }
    public Iterator<T> iterator()
    {
        return list.iterator();
    }
    public int size()
    {
        return list.size();
    }
    public void reverse()
    {
        Collections.reverse(list);
    }
}
