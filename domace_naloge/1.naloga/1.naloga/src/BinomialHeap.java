
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BinomialHeap<Tip extends Comparable> implements Seznam<Tip> {
    
     public static class BinomskaKopicaNode<Tip extends Comparable>{
        public Tip key;
        public int degree;
        public BinomskaKopicaNode<Tip> parent;
        public BinomskaKopicaNode<Tip> child;
        public BinomskaKopicaNode<Tip> sibling;

        public BinomskaKopicaNode() {
            key = null;
        }

        public BinomskaKopicaNode(Tip key) {
            this.key = key;
        }

        public int compareTo(BinomskaKopicaNode<Tip> other) {
            return this.key.compareTo(other.key);
        }

        public void print(int level) {
            BinomskaKopicaNode<Tip> curr = this;
            while (curr != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < level; i++) {
                    sb.append(" ");
                }
                sb.append(curr.key.toString());
                System.out.println(sb.toString());
                if (curr.child != null) {
                    curr.child.print(level + 1);
                }
                curr = curr.sibling;
            }
        }
    }

    private BinomskaKopicaNode<Tip> topNode;

    public BinomialHeap() {
        topNode = null;
    }

    public BinomialHeap(BinomskaKopicaNode<Tip> topNode) {
        this.topNode = topNode;
    }

   

    public void clear() {
        topNode = null;
    }


    public Tip findMinimum() {
        if (topNode == null) {
            return null;
        } else {
            BinomskaKopicaNode<Tip> min = topNode;
            BinomskaKopicaNode<Tip> next = min.sibling;

            while (next != null) {
                if (next.compareTo(min) < 0) {
                    min = next;
                }
                next = next.sibling;
            }

            return min.key;
        }
    }
    
    public Tip findMaximum() {
        BinomskaKopicaNode<Tip> max = topNode;
            BinomskaKopicaNode<Tip> next = max.sibling;

            while (next != null) {
                if (next.compareTo(max) > 0) {
                    max = next;
                }
                next = next.sibling;
            }

            return max.key;
    }

    // Implemented to test delete/decrease key, runs in O(n) time
    public BinomskaKopicaNode<Tip> search(Tip key) {
        List<BinomskaKopicaNode<Tip>> nodes = new ArrayList<>();
        if (topNode == null) {
            throw new NoSuchElementException();
        }
        nodes.add(topNode);
        while (!nodes.isEmpty()) {
            BinomskaKopicaNode<Tip> curr = nodes.get(0);
            nodes.remove(0);
            if (curr.key.compareTo(key) == 0) {
                return curr;
            }
            if (curr.sibling != null) {
                nodes.add(curr.sibling);
            }
            if (curr.child != null) {
                nodes.add(curr.child);
            }
        }
        return null;
    }

//    public void decreaseKey(BinomialHeapNode<Tip> node, Tip newKey) {
//        node.key = newKey;
//        bubbleUp(node, false);
//    }

    public void delete(BinomskaKopicaNode<Tip> node) {
        node = bubbleUp(node, true);
        if (topNode == node) {
            removeTreeRoot(node, null);
        } else {
            BinomskaKopicaNode<Tip> prev = topNode;
            while (prev.sibling.compareTo(node) != 0) {
                prev = prev.sibling;
            }
            removeTreeRoot(node, prev);
        }
    }

        private BinomskaKopicaNode<Tip> bubbleUp(BinomskaKopicaNode<Tip> node, boolean toRoot) {
        BinomskaKopicaNode<Tip> parent = node.parent;
        while (parent != null && (toRoot || node.compareTo(parent) > 0)) {
            Tip temp = node.key;
            node.key = parent.key;
            parent.key = temp;
            node = parent;
            parent = parent.parent;
        }
        return node;
    }

    
    public Tip extractMax() {
        if (topNode == null) {
            throw new NoSuchElementException();
        }

        BinomskaKopicaNode<Tip> max = topNode;
        BinomskaKopicaNode<Tip> maxPrev = null;
        BinomskaKopicaNode<Tip> next = max.sibling;
        BinomskaKopicaNode<Tip> nextPrev = max;

        while (next != null) {
            if (next.compareTo(max) > 0) {
                max = next;
                maxPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }

        removeTreeRoot(max, maxPrev);
        return max.key;
    }

    private void removeTreeRoot(BinomskaKopicaNode<Tip> root, BinomskaKopicaNode<Tip> prev) {
        // Remove root from the heap
        if (root == topNode) {
            topNode = root.sibling;
        } else {
            prev.sibling = root.sibling;
        }

        // Reverse the order of root's children and make a new heap
        BinomskaKopicaNode<Tip> newHead = null;
        BinomskaKopicaNode<Tip> child = root.child;
        while (child != null) {
            BinomskaKopicaNode<Tip> next = child.sibling;
            child.sibling = newHead;
            child.parent = null;
            newHead = child;
            child = next;
        }
        BinomialHeap<Tip> newHeap = new BinomialHeap<>(newHead);

        // Union the heaps and set its head as this.head
        topNode = union(newHeap);
    }

    // Merge two binomial trees of the same order
    private void linkTree(BinomskaKopicaNode<Tip> minNodeTree, BinomskaKopicaNode<Tip> other) {
        other.parent = minNodeTree;
        other.sibling = minNodeTree.child;
        minNodeTree.child = other;
        minNodeTree.degree++;
    }

    // Union two binomial heaps into one and return the head
    public BinomskaKopicaNode<Tip> union(BinomialHeap<Tip> heap) {
        BinomskaKopicaNode<Tip> newHead = merge(this, heap);

        topNode = null;
        heap.topNode = null;

        if (newHead == null) {
            return null;
        }

        BinomskaKopicaNode<Tip> prev = null;
        BinomskaKopicaNode<Tip> curr = newHead;
        BinomskaKopicaNode<Tip> next = newHead.sibling;

        while (next != null) {
            if (curr.degree != next.degree || (next.sibling != null &&
                    next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.compareTo(next) > 0) {
                    curr.sibling = next.sibling;
                    linkTree(curr, next);
                } else {
                    if (prev == null) {
                        newHead = next;
                    } else {
                        prev.sibling = next;
                    }

                    linkTree(next, curr);
                    curr = next;
                }
            }

            next = curr.sibling;
        }

        return newHead;
    }

    private static <Tip extends Comparable<Tip>> BinomskaKopicaNode<Tip> merge(
            BinomialHeap<Tip> heap1, BinomialHeap<Tip> heap2) {
        if (heap1.topNode == null) {
            return heap2.topNode;
        } else if (heap2.topNode == null) {
            return heap1.topNode;
        } else {
            BinomskaKopicaNode<Tip> head;
            BinomskaKopicaNode<Tip> heap1Next = heap1.topNode;
            BinomskaKopicaNode<Tip> heap2Next = heap2.topNode;

            if (heap1.topNode.degree <= heap2.topNode.degree) {
                head = heap1.topNode;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.topNode;
                heap2Next = heap2Next.sibling;
            }

            BinomskaKopicaNode<Tip> tail = head;

            while (heap1Next != null && heap2Next != null) {
                if (heap1Next.degree <= heap2Next.degree) {
                    tail.sibling = heap1Next;
                    heap1Next = heap1Next.sibling;
                } else {
                    tail.sibling = heap2Next;
                    heap2Next = heap2Next.sibling;
                }

                tail = tail.sibling;
            }

            if (heap1Next != null) {
                tail.sibling = heap1Next;
            } else {
                tail.sibling = heap2Next;
            }

            return head;
        }
    }

    public void print() {
        System.out.println("Binomial heap:");
        if (topNode != null) {
            topNode.print(0);
        }
    }

    @Override
    public void add(Tip e) {
        BinomskaKopicaNode<Tip> node = new BinomskaKopicaNode<>(e);
        BinomialHeap<Tip> tempHeap = new BinomialHeap<>(node);
        topNode = union(tempHeap);
    }

    @Override
    public Tip removeFirst() {
        return extractMax();
    }

    @Override
    public Tip getFirst() {
        if (topNode == null) {
            throw new NoSuchElementException();
        }
         return findMaximum();
    }

    @Override
    public int size() {
        if (topNode == null) {
            return 0;
        } else {
            BinomskaKopicaNode<Tip> node = topNode;
            int count = 0;
            do {
                count += Math.pow(2, node.degree );
                node = node.sibling;
                
            }while (node != null);

            return count;
        }
    }

    @Override
    public int depth() {
       if (topNode == null) {
            return 0;
        } else {
            BinomskaKopicaNode<Tip> max = topNode;
            BinomskaKopicaNode<Tip> next = max.sibling;

            while (next != null) {
                if (next.degree > max.degree) {
                    max = next;
                }
                next = next.sibling;
            }

            return max.degree;
        }
    }

    @Override
    public Tip remove(Tip e) {
        BinomskaKopicaNode node = search(e);
        if (node != null) {
            delete(search(e));
            return e;
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean exists(Tip e) {
        return topNode != null && search(e) != null;
    }
    
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public List<Tip> asList() {
        List<BinomskaKopicaNode<Tip>> currentRow = new ArrayList<>();
        List<BinomskaKopicaNode<Tip>> nextRow = new ArrayList<>();
        LinkedHashMap hashMap = new LinkedHashMap();
        if (topNode == null) {
            return new ArrayList<>(hashMap.values());
        }
        currentRow.add(topNode);
        while (!currentRow.isEmpty()) {
            BinomskaKopicaNode<Tip> curr = currentRow.get(0);
            hashMap.put(curr.key, curr.key);
            currentRow.remove(0);

            if (curr.sibling != null) {
                currentRow.add(curr.sibling);
            }
            if (curr.child != null) {
                BinomskaKopicaNode<Tip> child = curr.child;
                nextRow.add(child);
                while (child.sibling != null) {
                    nextRow.add(child.sibling);
                    child = child.sibling;
                    
                }
            }
            if (currentRow.isEmpty()) {
                currentRow.addAll(nextRow);
                nextRow.clear();
            }
        }
        return new ArrayList<>(hashMap.values());
    }
    


    

   

}