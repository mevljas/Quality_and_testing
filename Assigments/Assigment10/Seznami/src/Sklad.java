
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Comparator;


class Element<Tip> {

    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        vrednost = e;
    }
}

public class Sklad<Tip> implements Seznam<Tip>{

    private Element<Tip> vrh;
    private Comparator<Tip> comparator;

    public Sklad(Comparator<Tip> comparator) {
        this.comparator = comparator;
    }

    public void push(Tip e) {
        Element<Tip> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }

    public Tip pop() {
        if (null == vrh) {
            throw new java.util.NoSuchElementException();
        }

        Tip e = vrh.vrednost;
        vrh = vrh.vezava;
        return e;
    }

    public Tip peek() {
        if (null == vrh) {
            throw new java.util.NoSuchElementException();
        }
        Tip e = vrh.vrednost;
        return e;
    }

    public boolean isEmpty() {
        return (null == vrh);
    }

    public int search(Tip e) {
        Element<Tip> temp = vrh;
        int i = 0;
        while (null != temp) {
            if (0 == comparator.compare(temp.vrednost, e)) {
                return i;
            }
            i++;
            temp = temp.vezava;
        }
        return -1;
    }

    public int count() {
         if (isEmpty()) {
            return 0;
        }
        int stElementov = 0;
        Element<Tip> tmp = vrh;
        while (tmp != null) {
            stElementov++;
            tmp = tmp.vezava;
        }
        return stElementov;
    }

     @Override
    public void add(Tip e) {
        push(e);
    }

    @Override
    public Tip removeFirst() {
        return pop();
    }

     @Override
    public Tip remove(Tip e) {
        Element<Tip> tmp = vrh;
        Element<Tip> prejsni = null;
        while (null != tmp) {
            if (tmp.vrednost.equals(e)) {
                if (prejsni == null) {
                    vrh = vrh.vezava;
                }
                else{
                    prejsni.vezava = tmp;
                }
                return (Tip) tmp.vrednost;
            }
            prejsni = tmp;
            tmp = tmp.vezava;
        }
        throw new java.util.NoSuchElementException();
    }

    @Override
    public Tip getFirst() {
        return peek();
    }

    @Override
    public int size() {
        return count();
    }

    @Override
    public int depth() {
        return count();
    }

    @Override
    public boolean exists(Tip e) {
        if (search(e) == -1){
            return false;
        }
        return true;
    }

    @Override
    public void print() {
        if(vrh != null){
            Element tmp = vrh;
            while (tmp != null) {  
                if (tmp != vrh) {
                    System.out.print(", ");
                }
                System.out.print(tmp.vrednost);
                tmp = tmp.vezava;
            }
            System.out.println("");
        }
        
    }
    
    
    
  
    
    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(size());
        Element tmp = vrh;
        while (tmp != null) {            
            out.writeObject(tmp.vrednost);
            tmp = tmp.vezava;
        }
    }

    
    
    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        int size = in.readInt();
        Object[] array = new Object[size];
       
        for (int i = 0; i < size; i++) {
            array[i] = in.readObject();
        }
        
        
        for (int i = size - 1; i >= 0; i--) {
            push((Tip) array[i]);
            
        }
        
       
    }
}
