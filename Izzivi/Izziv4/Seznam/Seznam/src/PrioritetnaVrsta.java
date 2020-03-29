/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seba
 */
public class PrioritetnaVrsta<Tip extends Comparable> implements Seznam<Tip> {

    private Object[] heap;
    private int end = 0;

    public PrioritetnaVrsta() {
        this(100);
    }

    public PrioritetnaVrsta(int maxSize) {
        heap = new Object[maxSize];
    }

    private void bubbleUp() {
        int eltIdx = end - 1;
        while (eltIdx >= 0) {
            Tip elt = (Tip) heap[eltIdx];
            int childIdx = eltIdx * 2 + 1;
            if (childIdx < end) {
                Tip child = (Tip) heap[childIdx];
                if (childIdx + 1 < end && child.compareTo(heap[childIdx + 1]) < 0) {
                    child = (Tip) heap[++childIdx];
                }
                if (elt.compareTo(child) >= 0) {
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
            if (childIdx + 1 < end && child.compareTo(heap[childIdx + 1]) < 0) {
                child = (Tip) heap[++childIdx];
            }
            if (elt.compareTo(child) >= 0) {
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
    public int size() {
        return end;
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

    private void extend() {
        Object[] heapExtended = new Object[heap.length * 2];
        System.arraycopy(heap, 0, heapExtended, 0, heap.length);
        heap = heapExtended;
    }
}
