
import java.util.ArrayList;
import java.util.List;

public class BinomialHeap<Tip extends Comparable> implements Seznam<Tip> {

    private Node<Tip> head;

    public BinomialHeap() {
        head = null;
    }

    public BinomialHeap(Node<Tip> head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }

    public void insert(Tip key) {
        Node<Tip> node = new Node<Tip>(key);
        BinomialHeap<Tip> tempHeap = new BinomialHeap<Tip>(node);
        head = union(tempHeap);
    }

    public Tip findMinimum() {
        if (head == null) {
            return null;
        } else {
            Node<Tip> min = head;
            Node<Tip> next = min.sibling;

            while (next != null) {
                if (next.compareTo(min) < 0) {
                    min = next;
                }
                next = next.sibling;
            }

            return min.key;
        }
    }

    // Implemented to test delete/decrease key, runs in O(n) time
    public Node<Tip> search(Tip key) {
        List<Node<Tip>> nodes = new ArrayList<Node<Tip>>();
        nodes.add(head);
        while (!nodes.isEmpty()) {
            Node<Tip> curr = nodes.get(0);
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

    public void decreaseKey(Node<Tip> node, Tip newKey) {
        node.key = newKey;
        bubbleUp(node, false);
    }

    public void delete(Node<Tip> node) {
        node = bubbleUp(node, true);
        if (head == node) {
            removeTreeRoot(node, null);
        } else {
            Node<Tip> prev = head;
            while (prev.sibling.compareTo(node) != 0) {
                prev = prev.sibling;
            }
            removeTreeRoot(node, prev);
        }
    }

    private Node<Tip> bubbleUp(Node<Tip> node, boolean toRoot) {
        Node<Tip> parent = node.parent;
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
        if (head == null) {
            return null;
        }

        Node<Tip> min = head;
        Node<Tip> minPrev = null;
        Node<Tip> next = min.sibling;
        Node<Tip> nextPrev = min;

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

    private void removeTreeRoot(Node<Tip> root, Node<Tip> prev) {
        // Remove root from the heap
        if (root == head) {
            head = root.sibling;
        } else {
            prev.sibling = root.sibling;
        }

        // Reverse the order of root's children and make a new heap
        Node<Tip> newHead = null;
        Node<Tip> child = root.child;
        while (child != null) {
            Node<Tip> next = child.sibling;
            child.sibling = newHead;
            child.parent = null;
            newHead = child;
            child = next;
        }
        BinomialHeap<Tip> newHeap = new BinomialHeap<Tip>(newHead);

        // Union the heaps and set its head as this.head
        head = union(newHeap);
    }

    // Merge two binomial trees of the same order
    private void linkTree(Node<Tip> minNodeTree, Node<Tip> other) {
        other.parent = minNodeTree;
        other.sibling = minNodeTree.child;
        minNodeTree.child = other;
        minNodeTree.degree++;
    }

    // Union two binomial heaps into one and return the head
    public Node<Tip> union(BinomialHeap<Tip> heap) {
        Node<Tip> newHead = merge(this, heap);

        head = null;
        heap.head = null;

        if (newHead == null) {
            return null;
        }

        Node<Tip> prev = null;
        Node<Tip> curr = newHead;
        Node<Tip> next = newHead.sibling;

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

    private static <Tip extends Comparable<Tip>> Node<Tip> merge(
            BinomialHeap<Tip> heap1, BinomialHeap<Tip> heap2) {
        if (heap1.head == null) {
            return heap2.head;
        } else if (heap2.head == null) {
            return heap1.head;
        } else {
            Node<Tip> head;
            Node<Tip> heap1Next = heap1.head;
            Node<Tip> heap2Next = heap2.head;

            if (heap1.head.degree <= heap2.head.degree) {
                head = heap1.head;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.head;
                heap2Next = heap2Next.sibling;
            }

            Node<Tip> tail = head;

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
        if (head != null) {
            head.print(0);
        }
    }

    @Override
    public void add(Tip e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tip removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tip getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int depth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tip remove(Tip e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Tip e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tip> asList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class Node<Tip extends Comparable> 
            implements Comparable<Node<Tip>> {
        public Tip key;
        public int degree;
        public Node<Tip> parent;
        public Node<Tip> child;
        public Node<Tip> sibling;

        public Node() {
            key = null;
        }

        public Node(Tip key) {
            this.key = key;
        }

        public int compareTo(Node<Tip> other) {
            return this.key.compareTo(other.key);
        }

        public void print(int level) {
            Node<Tip> curr = this;
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

}