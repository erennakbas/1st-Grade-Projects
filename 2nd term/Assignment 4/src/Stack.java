import java.util.*;
public class Stack<T> implements Iterable<T>{
    private ArrayList<T> list=new ArrayList<T>();
    String name;
    Stack()
    {
        name="Stack";
    }
    //Methods that is done via list attribute.
    public void push(T obj)
    {
        list.add(0,obj);
    }
    public void pop()
    {
        list.remove(0);
    }
    public T get(int index)
    {
        return list.get(index);
    }
    public void sort(Comparator<T> c)
    {
        list.sort(c);
    }
    public Iterator<T> iterator()
    {
        return list.iterator();
    }
    public void reverse()
    {
        Collections.reverse(list);
    }
}

