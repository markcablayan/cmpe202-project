
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Alien>{
    private int n;        // number of elements on list
    private Node pre;     // sentinel before first item
    private Node post;    // sentinel after last item

    public DoublyLinkedList() {
        pre  = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }

    // linked list node helper data type
    public class Node {
        private Alien item;
        private Node next;
        private Node prev;
        
        public Alien getItem()
        {
            return item;
        }
        
        public Node getNext()
        {
            return next;
        }
        
        public Node getPrev()
        {
            return prev;
        }
    }

    public boolean isEmpty()    { return n == 0; }
    public int size()           { return n;      }

    // add the item to the list
    public void add(Alien item) {
        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        n++;
    }
    
    public Node getHead(){
        return pre;
    }
}