
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

// Source: https://www.growingwiththeweb.com/data-structures/binomial-heap/overview/
public class BinomskaKopica<Tip extends Comparable> implements Seznam<Tip> {

    public static class BinomskaKopicaNode<Tip extends Comparable> {

        public Tip key;
        public int degree;
        public BinomskaKopicaNode<Tip> parent;
        public BinomskaKopicaNode<Tip> child;
        public BinomskaKopicaNode<Tip> sibling;

        public BinomskaKopicaNode(Tip key) {
            this.key = key;
        }

        public int compareTo(BinomskaKopicaNode<Tip> other) {
            return this.key.compareTo(other.key);
        }

    }

    private BinomskaKopicaNode<Tip> topNode;

    public BinomskaKopica() {
        topNode = null;
    }

    public BinomskaKopica(BinomskaKopicaNode<Tip> topNode) {
        this.topNode = topNode;
    }

    private Tip getMax() {
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
            if (curr.key.compareTo(key) >= 0 && curr.child != null) {
                nodes.add(curr.child);
            }
        }
        return null;
    }

    private BinomskaKopicaNode<Tip> bubbleUp(BinomskaKopicaNode<Tip> node) {
        BinomskaKopicaNode<Tip> parent = node.parent;
        while (parent != null) {
            Tip temp = node.key;
            node.key = parent.key;
            parent.key = temp;
            node = parent;
            parent = parent.parent;
        }
        return node;
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
        BinomskaKopica<Tip> newHeap = new BinomskaKopica<>(newHead);

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
    public BinomskaKopicaNode<Tip> union(BinomskaKopica<Tip> heap) {
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
            if (curr.degree != next.degree) {
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
            BinomskaKopica<Tip> heap1, BinomskaKopica<Tip> heap2) {
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

    @Override
    public void add(Tip e) {
        BinomskaKopicaNode<Tip> node = new BinomskaKopicaNode<>(e);
        BinomskaKopica<Tip> tempHeap = new BinomskaKopica<>(node);
        topNode = union(tempHeap);
    }

    @Override
    public Tip removeFirst() {
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
            BinomskaKopicaNode<Tip> node = topNode;
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
            BinomskaKopicaNode<Tip> max = topNode;

            while (max.sibling != null) {
                max = max.sibling;
            }

            return max.degree;
        }
    }

    @Override
    public Tip remove(Tip e) {
        BinomskaKopicaNode node = search(e);
        if (node != null) {
            node = bubbleUp(node);
            if (topNode == node) {
                removeTreeRoot(node, null);
            } else {
                BinomskaKopicaNode<Tip> prev = topNode;
                removeTreeRoot(node, prev);
            }
            return e;
        } else {
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
        return inorderTraversal(topNode);

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

}
