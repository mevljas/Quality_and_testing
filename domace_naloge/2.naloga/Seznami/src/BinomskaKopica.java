
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Source: https://www.growingwiththeweb.com/data-structures/binomial-heap/overview/
public class BinomskaKopica<Tip> implements Seznam<Tip> {

    

   

   

    class BinomskaKopicaNode {

        public Tip key;
        public int degree;
        public BinomskaKopicaNode parent;
        public BinomskaKopicaNode child;
        public BinomskaKopicaNode sibling;

        public BinomskaKopicaNode(Tip key) {
            this.key = key;
        }



    }

    private BinomskaKopicaNode topNode;
    private Comparator<Tip> comparator;

    public BinomskaKopica(Comparator<Tip> comparator) {
        topNode = null;
        this.comparator = comparator;
    }

    public BinomskaKopica(BinomskaKopicaNode topNode, Comparator comparator) {
        this.topNode = topNode;
        this.comparator = comparator;
    }

    private Tip getMax() {
        BinomskaKopicaNode max = topNode;
        BinomskaKopicaNode next = max.sibling;

        while (next != null) {
            if (comparator.compare(next.key, max.key) > 0) {
                max = next;
            }
            next = next.sibling;
        }

        return max.key;
    }


    public BinomskaKopicaNode searchNode(Tip key) {
        List<BinomskaKopicaNode> nodes = new ArrayList<>();
        if (topNode == null) {
            throw new NoSuchElementException();
        }
        nodes.add(topNode);
        while (!nodes.isEmpty()) {
            BinomskaKopicaNode curr = nodes.get(0);
            nodes.remove(0);
            if (comparator.compare(curr.key, key) == 0) {
                return curr;
            }
            if (curr.sibling != null) {
                nodes.add(curr.sibling);
            }
            if (comparator.compare(curr.key, key) >= 0 && curr.child != null) {
                nodes.add(curr.child);
            }
        }
        return null;
    }

    private BinomskaKopicaNode bubbleUp(BinomskaKopicaNode node) {
        BinomskaKopicaNode parent = node.parent;
        while (parent != null) {
            Tip temp = node.key;
            node.key = parent.key;
            parent.key = temp;
            node = parent;
            parent = parent.parent;
        }
        return node;
    }

    private void removeTreeRoot(BinomskaKopicaNode root, BinomskaKopicaNode prev) {
        // Remove root from the heap
        if (root == topNode) {
            topNode = root.sibling;
        } else {
            prev.sibling = root.sibling;
        }

        // Reverse the order of root's children and make a new heap
        BinomskaKopicaNode newHead = null;
        BinomskaKopicaNode child = root.child;
        while (child != null) {
            BinomskaKopicaNode next = child.sibling;
            child.sibling = newHead;
            child.parent = null;
            newHead = child;
            child = next;
        }
        BinomskaKopica<Tip> newHeap = new BinomskaKopica<>(newHead, comparator);

        // Union the heaps and set its head as this.head
        topNode = union(newHeap);
    }

    // Merge two binomial trees of the same order
    private void linkTree(BinomskaKopicaNode minNodeTree, BinomskaKopicaNode other) {
        other.parent = minNodeTree;
        other.sibling = minNodeTree.child;
        minNodeTree.child = other;
        minNodeTree.degree++;
    }

    // Union two binomial heaps into one and return the head
    public BinomskaKopicaNode union(BinomskaKopica heap) {
        BinomskaKopicaNode newHead = merge(this, heap);

        topNode = null;
        heap.topNode = null;

        if (newHead == null) {
            return null;
        }

        BinomskaKopicaNode prev = null;
        BinomskaKopicaNode curr = newHead;
        BinomskaKopicaNode next = newHead.sibling;

        while (next != null) {
            if (curr.degree != next.degree) {
                prev = curr;
                curr = next;
            } else {
                if (comparator.compare(curr.key, next.key) > 0) {
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

    private BinomskaKopicaNode merge(
            BinomskaKopica heap1, BinomskaKopica heap2) {
        if (heap1.topNode == null) {
            return heap2.topNode;
        } else if (heap2.topNode == null) {
            return heap1.topNode;
        } else {
            BinomskaKopicaNode head;
            BinomskaKopicaNode heap1Next = heap1.topNode;
            BinomskaKopicaNode heap2Next = heap2.topNode;

            if (heap1.topNode.degree <= heap2.topNode.degree) {
                head = heap1.topNode;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.topNode;
                heap2Next = heap2Next.sibling;
            }

            BinomskaKopicaNode tail = head;

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

    @Override
    public void add(Tip e) {
        BinomskaKopicaNode node = new BinomskaKopicaNode(e);
        BinomskaKopica<Tip> tempHeap = new BinomskaKopica<>(node, comparator);
        topNode = union(tempHeap);
    }

    @Override
    public Tip removeFirst() {
        if (topNode == null) {
            throw new NoSuchElementException();
        }

        BinomskaKopicaNode max = topNode;
        BinomskaKopicaNode maxPrev = null;
        BinomskaKopicaNode next = max.sibling;
        BinomskaKopicaNode nextPrev = max;

        while (next != null) {
            if (comparator.compare(next.key, max.key) > 0) {
                max = next;
                maxPrev = nextPrev;
            }
            nextPrev = next;
            next = next.sibling;
        }

        removeTreeRoot(max, maxPrev);
        return max.key;
    }

    @Override
    public Tip getFirst() {
        if (topNode == null) {
            throw new NoSuchElementException();
        }
        return getMax();
    }

    @Override
    public int size() {
        if (topNode == null) {
            return 0;
        } else {
            BinomskaKopicaNode node = topNode;
            int count = 0;
            do {
                count += Math.pow(2, node.degree);
                node = node.sibling;

            } while (node != null);

            return count;
        }
    }

    @Override
    public int depth() {
        if (topNode == null) {
            return 0;
        } else {
            BinomskaKopicaNode max = topNode;

            while (max.sibling != null) {
                max = max.sibling;
            }

            return max.degree;
        }
    }

    @Override
    public Tip remove(Tip e) {
        BinomskaKopicaNode node = searchNode(e);
        BinomskaKopicaNode node2 = node;
        if (node != null) {
            node = bubbleUp(node);
            if (topNode == node) {
                removeTreeRoot(node, null);
            } else {
                BinomskaKopicaNode prev = topNode;
                removeTreeRoot(node, prev);
            }
            return node.key;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean exists(Tip e) {
        return topNode != null && searchNode(e) != null;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }


    List<Tip> inorderTraversal(BinomskaKopicaNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List list = new ArrayList<>();
        list.add(root.key);

        list.addAll(inorderTraversal(root.child));

        list.addAll(inorderTraversal(root.sibling));

        return list;
    }
    
    @Override
    public void print()
    {
        displayHeap(topNode);
    }
    private void displayHeap(BinomskaKopicaNode r)
    {
        if (r != null)
        {
            displayHeap(r.child);
            displayHeap(r.sibling);
            System.out.println("\t" + r.key);
            
        }
    }    

    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(this.size());
        List<Tip> l = inorderTraversal(topNode);
        for (Tip key : l) {
            out.writeObject(key);
        }
        out.close();
    }
    
   

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        int count = in.readInt();
        ArrayList<Tip> list= new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add((Tip) in.readObject());
        }
        Collections.reverse(list);
        for (Tip tip : list) {
            add(tip);
        }
        in.close();
    }
    
    @Override
    public Tip search(Tip e) {
        return searchNode(e).key;
    }
    
     
   
   


}
