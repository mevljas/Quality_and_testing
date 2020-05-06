
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BinomialHeap<Tip extends Comparable> implements Seznam<Tip> {
    
     public static class BinomialHeapNode<Tip extends Comparable>{
        public Tip key;
        public int degree;
        public BinomialHeapNode<Tip> parent;
        public BinomialHeapNode<Tip> child;
        public BinomialHeapNode<Tip> sibling;

        public BinomialHeapNode() {
            key = null;
        }

        public BinomialHeapNode(Tip key) {
            this.key = key;
        }

        public int compareTo(BinomialHeapNode<Tip> other) {
            return this.key.compareTo(other.key);
        }

        public void print(int level) {
            BinomialHeapNode<Tip> curr = this;
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

    private BinomialHeapNode<Tip> topNode;

    public BinomialHeap() {
        topNode = null;
    }

    public BinomialHeap(BinomialHeapNode<Tip> topNode) {
        this.topNode = topNode;
    }

   

    public void clear() {
        topNode = null;
    }


    public Tip findMinimum() {
        if (topNode == null) {
            return null;
        } else {
            BinomialHeapNode<Tip> min = topNode;
            BinomialHeapNode<Tip> next = min.sibling;

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
        if (topNode == null) {
            return null;
        } else {
            BinomialHeapNode<Tip> max = topNode;
            BinomialHeapNode<Tip> next = max.sibling;

            while (next != null) {
                if (next.compareTo(max) > 0) {
                    max = next;
                }
                next = next.sibling;
            }

            return max.key;
        }
    }

    // Implemented to test delete/decrease key, runs in O(n) time
    public BinomialHeapNode<Tip> search(Tip key) {
        List<BinomialHeapNode<Tip>> nodes = new ArrayList<>();
        nodes.add(topNode);
        while (!nodes.isEmpty()) {
            BinomialHeapNode<Tip> curr = nodes.get(0);
            nodes.remove(0);
            if (curr.key == key) {
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

    public void decreaseKey(BinomialHeapNode<Tip> node, Tip newKey) {
        node.key = newKey;
        bubbleUp(node, false);
    }

    public void delete(BinomialHeapNode<Tip> node) {
        node = bubbleUp(node, true);
        if (topNode == node) {
            removeTreeRoot(node, null);
        } else {
            BinomialHeapNode<Tip> prev = topNode;
            while (prev.sibling.compareTo(node) != 0) {
                prev = prev.sibling;
            }
            removeTreeRoot(node, prev);
        }
    }

    private BinomialHeapNode<Tip> bubbleUp(BinomialHeapNode<Tip> node, boolean toRoot) {
        BinomialHeapNode<Tip> parent = node.parent;
        while (parent != null && (toRoot || node.compareTo(parent) < 0)) {
            Tip temp = node.key;
            node.key = parent.key;
            parent.key = temp;
            node = parent;
            parent = parent.parent;
        }
        return node;
    }

    public Tip extractMin() {
        if (topNode == null) {
            return null;
        }

        BinomialHeapNode<Tip> min = topNode;
        BinomialHeapNode<Tip> minPrev = null;
        BinomialHeapNode<Tip> next = min.sibling;
        BinomialHeapNode<Tip> nextPrev = min;

        while (next != null) {
            if (next.compareTo(min) < 0) {
                min = next;
                minPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }

        removeTreeRoot(min, minPrev);
        return min.key;
    }
    
    public Tip extractMax() {
        if (topNode == null) {
            return null;
        }

        BinomialHeapNode<Tip> max = topNode;
        BinomialHeapNode<Tip> maxPrev = null;
        BinomialHeapNode<Tip> next = max.sibling;
        BinomialHeapNode<Tip> nextPrev = max;

        while (next != null) {
            if (next.compareTo(max) < 0) {
                max = next;
                maxPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }

        removeTreeRoot(max, maxPrev);
        return max.key;
    }

    private void removeTreeRoot(BinomialHeapNode<Tip> root, BinomialHeapNode<Tip> prev) {
        // Remove root from the heap
        if (root == topNode) {
            topNode = root.sibling;
        } else {
            prev.sibling = root.sibling;
        }

        // Reverse the order of root's children and make a new heap
        BinomialHeapNode<Tip> newHead = null;
        BinomialHeapNode<Tip> child = root.child;
        while (child != null) {
            BinomialHeapNode<Tip> next = child.sibling;
            child.sibling = newHead;
            child.parent = null;
            newHead = child;
            child = next;
        }
        BinomialHeap<Tip> newHeap = new BinomialHeap<Tip>(newHead);

        // Union the heaps and set its head as this.head
        topNode = union(newHeap);
    }

    // Merge two binomial trees of the same order
    private void linkTree(BinomialHeapNode<Tip> minNodeTree, BinomialHeapNode<Tip> other) {
        other.parent = minNodeTree;
        other.sibling = minNodeTree.child;
        minNodeTree.child = other;
        minNodeTree.degree++;
    }

    // Union two binomial heaps into one and return the head
    public BinomialHeapNode<Tip> union(BinomialHeap<Tip> heap) {
        BinomialHeapNode<Tip> newHead = merge(this, heap);

        topNode = null;
        heap.topNode = null;

        if (newHead == null) {
            return null;
        }

        BinomialHeapNode<Tip> prev = null;
        BinomialHeapNode<Tip> curr = newHead;
        BinomialHeapNode<Tip> next = newHead.sibling;

        while (next != null) {
            if (curr.degree != next.degree || (next.sibling != null &&
                    next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.compareTo(next) < 0) {
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

    private static <Tip extends Comparable<Tip>> BinomialHeapNode<Tip> merge(
            BinomialHeap<Tip> heap1, BinomialHeap<Tip> heap2) {
        if (heap1.topNode == null) {
            return heap2.topNode;
        } else if (heap2.topNode == null) {
            return heap1.topNode;
        } else {
            BinomialHeapNode<Tip> head;
            BinomialHeapNode<Tip> heap1Next = heap1.topNode;
            BinomialHeapNode<Tip> heap2Next = heap2.topNode;

            if (heap1.topNode.degree <= heap2.topNode.degree) {
                head = heap1.topNode;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.topNode;
                heap2Next = heap2Next.sibling;
            }

            BinomialHeapNode<Tip> tail = head;

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
        BinomialHeapNode<Tip> node = new BinomialHeapNode<>(e);
        BinomialHeap<Tip> tempHeap = new BinomialHeap<>(node);
        topNode = union(tempHeap);
    }

    @Override
    public Tip removeFirst() {
        return extractMax();
    }

    @Override
    public Tip getFirst() {
         return findMaximum();
    }

    @Override
    public int size() {
        if (topNode == null) {
            return 0;
        } else {
            BinomialHeapNode<Tip> node = topNode;
            BinomialHeapNode<Tip> next = node.sibling;
            int count = (int) Math.pow(2, next.degree );
            while (next != null) {
                count += Math.pow(2, next.degree );
                next = next.sibling;
                
            }

            return count;
        }
    }

    @Override
    public int depth() {
       if (topNode == null) {
            return 0;
        } else {
            BinomialHeapNode<Tip> max = topNode;
            BinomialHeapNode<Tip> next = max.sibling;

            while (next != null) {
                if (next.compareTo(max) > 0) {
                    max = next;
                }
                next = next.sibling;
            }

            return max.degree;
        }
    }

    @Override
    public Tip remove(Tip e) {
        delete(new BinomialHeapNode<>(e));
        return e;
    }

    @Override
    public boolean exists(Tip e) {
        return search(e) != null;
    }
    
    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public List<Tip> asList() {
        List<BinomialHeapNode<Tip>> nodes = new ArrayList<>();
        nodes.add(topNode);
        for (int i = 0; i < nodes.size(); i++) {
             BinomialHeapNode<Tip> curr = nodes.get(i);
             
            if (curr.sibling != null) {
                nodes.add(curr.sibling);
            }
            if (curr.child != null) {
                nodes.add(curr.child);
            }
        }
           
        
        return nodes.stream().map(s -> s.key).collect(Collectors.toList());
    }
    


    

   

}