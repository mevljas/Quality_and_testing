
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Comparator;

public class PrioritetnaVrsta<Tip> implements Seznam<Tip> {

    private Object[] heap;
    private int end = 0;
    private Comparator<Tip> comparator;

    public PrioritetnaVrsta(Comparator<Tip> comparator) {
        this(100, comparator);
    }
    
    public PrioritetnaVrsta(int maxSize, Comparator<Tip> comparator) {
        heap = new Object[maxSize];
        this.comparator = comparator;
    }

    private void bubbleUp() {
        int eltIdx = end - 1;
        while (eltIdx >= 0) {
            Tip elt = (Tip) heap[eltIdx];
            int childIdx = eltIdx * 2 + 1;
            if (childIdx < end) {
                Tip child = (Tip) heap[childIdx];
                if (childIdx + 1 < end && comparator.compare(child, (Tip)heap[childIdx + 1]) < 0) {
                    child = (Tip) heap[++childIdx];
                }
                if (comparator.compare (elt, child) >= 0) {
                    return;
                }
                swap(eltIdx, childIdx);
            }
            eltIdx = eltIdx % 2 == 1 ? (eltIdx - 1) / 2 : (eltIdx - 2) / 2;
        }
    }

    @Override
    public void add(Tip e) {
        if (end == heap.length) {
            extend();
        }

        heap[end++] = e;
        bubbleUp();
    }


    private void bubbleDown(int start) {
        int eltIdx = start;
        int childIdx = eltIdx * 2 + 1;
        while (childIdx < end) {
            Tip elt = (Tip) heap[eltIdx];
            Tip child = (Tip) heap[childIdx];
            if (childIdx + 1 < end && comparator.compare(child, (Tip)heap[childIdx + 1]) < 0) {
                child = (Tip) heap[++childIdx];
            }
            if (comparator.compare (elt, child) >= 0) {
                return;
            }
            swap(eltIdx, childIdx);
            eltIdx = childIdx;
            childIdx = eltIdx * 2 + 1;
        }
    }

    @Override
    public Tip removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Tip elt = (Tip) heap[0];
        swap(0, --end);
        bubbleDown(0);
        return elt;
    }

    private void swap(int a, int b) {
        Object tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    @Override
    public Tip getFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return (Tip) heap[0];
    }

    @Override
    public int depth() {
        if (end == 0) {
            return 0;
        }
        return (int) (Math.log(end) / Math.log(2)) + 1;
    }

    @Override
    public boolean isEmpty() {
        return end == 0;
    }

    @Override
    public int size() {
        return end;
    }

    @Override
    public Tip remove(Tip e) {
        Tip temp;
        for (int i = 0; i < end; i++) {
            if (heap[i].equals(e)) {
                temp = (Tip) heap[i];
                swap(i, end - 1);
                heap[end - 1] = 0;
                end--;
                bubbleDown(i);
                return temp;

            }
        }
        throw new java.util.NoSuchElementException();
    }

    @Override
    public boolean exists(Tip e) {
        for (int i = 0; i < end; i++) {
                    if (heap[i].equals(e)) {
                        return true;
                    }
                }

        return false;
    }

     @Override
    public void print() {
        print(0, 0);
    }
    
    private void print(int i, int numTabs) {
        if (i >= end)
            return;
        print(2 * i + 2, numTabs + 1);
        for (int j = 0; j < numTabs; j++)
            System.out.print('\t');
        System.out.println(heap[i]);
        print(2 * i + 1, numTabs+1);
        
    }
    
    private void extend() {
        Object[] heapExtended = new Object[heap.length * 2];
        System.arraycopy(heap, 0, heapExtended, 0, heap.length);
        heap = heapExtended;
    }
    
    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(end);
        for (int i = 0; i < end; i++) {
            out.writeObject(heap[i]);
            
        }
    }

    
    
    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        end = in.readInt();
        for (int i = 0; i < end; i++) {
            heap[i] = in.readObject();
            
        }
    }
}
