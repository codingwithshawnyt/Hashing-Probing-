public interface Hash<E>
{
    public int length();
    public int size();
    public boolean isEmpty();
    public boolean add(E value);
    public boolean contains(E value);
    public boolean remove(E value);
    public String toString();   
}