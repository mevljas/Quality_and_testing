
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

// Source: https://www.growingwiththeweb.com/data-structures/binomial-heap/overview/
public class BinomialHeap<Tip extends Comparable> implements Seznam<Tip> {

    public static class BinomskaKopica<Tip extends Comparable> {

        public Tip key;
        public int degree;
        public BinomskaKopica<Tip> parent;
        public BinomskaKopica<Tip> child;
        public BinomskaKopica<Tip> sibling;

        public BinomskaKopica(Tip key) {
            this.key = key;
        }

        public int compareTo(BinomskaKopica<Tip> other) {
            return this.key.compareTo(other.key);
        }

    }

    private BinomskaKopica<Tip> topNode;

    public BinomialHeap() {
        topNode = null;
    }

    public BinomialHeap(BinomskaKopica<Tip> topNode) {
        this.topNode = topNode;
    }

    private Tip getMax() {
        BinomskaKopica<Tip> max = topNode;
        BinomskaKopica<Tip> next = max.sibling;

        while (next != null) {
            if (next.compareTo(max) > 0) {
                max = next;
            }
            next = next.sibling;
        }

        return max.key;
    }

    public BinomskaKopica<Tip> search(Tip key) {
        List<BinomskaKopica<Tip>> nodes = new ArrayList<>();
        if (topNode == null) {
            throw new NoSuchElementException();
        }
        nodes.add(topNode);
        while (!nodes.isEmpty()) {
            BinomskaKopica<Tip> curr = nodes.get(0);
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

    private BinomskaKopica<Tip> bubbleUp(BinomskaKopica<Tip> node) {
        BinomskaKopica<Tip> parent = node.parent;
        while (parent != null) {
            Tip temp = node.key;
            node.key = parent.key;
            parent.key = temp;
            node = parent;
            parent = parent.parent;
        }
        return node;
    }

    private void removeTreeRoot(BinomskaKopica<Tip> root, BinomskaKopica<Tip> prev) {
        // Remove root from the heap
        if (root == topNode) {
            topNode = root.sibling;
        } else {
            prev.sibling = root.sibling;
        }

        // Reverse the order of root's children and make a new heap
        BinomskaKopica<Tip> newHead = null;
        BinomskaKopica<Tip> child = root.child;
        while (child != null) {
            BinomskaKopica<Tip> next = child.sibling;
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
    private void linkTree(BinomskaKopica<Tip> minNodeTree, BinomskaKopica<Tip> other) {
        other.parent = minNodeTree;
        other.sibling = minNodeTree.child;
        minNodeTree.child = other;
        minNodeTree.degree++;
    }

    // Union two binomial heaps into one and return the head
    public BinomskaKopica<Tip> union(BinomialHeap<Tip> heap) {
        BinomskaKopica<Tip> newHead = merge(this, heap);

        topNode = null;
        heap.topNode = null;

        if (newHead == null) {
            return null;
        }

        BinomskaKopica<Tip> prev = null;
        BinomskaKopica<Tip> curr = newHead;
        BinomskaKopica<Tip> next = newHead.sibling;

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

    private static <Tip extends Comparable<Tip>> BinomskaKopica<Tip> merge(
            BinomialHeap<Tip> heap1, BinomialHeap<Tip> heap2) {
        if (heap1.topNode == null) {
            return heap2.topNode;
        } else if (heap2.topNode == null) {
            return heap1.topNode;
        } else {
            BinomskaKopica<Tip> head;
            BinomskaKopica<Tip> heap1Next = heap1.topNode;
            BinomskaKopica<Tip> heap2Next = heap2.topNode;

            if (heap1.topNode.degree <= heap2.topNode.degree) {
                head = heap1.topNode;
                heap1Next = heap1Next.sibling;
            } else {
                head = heap2.topNode;
                heap2Next = heap2Next.sibling;
            }

            BinomskaKopica<Tip> tail = head;

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
        BinomskaKopica<Tip> node = new BinomskaKopica<>(e);
        BinomialHeap<Tip> tempHeap = new BinomialHeap<>(node);
        topNode = union(tempHeap);
    }

    @Override
    public Tip removeFirst() {
        if (topNode == null) {
            throw new NoSuchElementException();
        }

        BinomskaKopica<Tip> max = topNode;
        BinomskaKopica<Tip> maxPrev = null;
        BinomskaKopica<Tip> next = max.sibling;
        BinomskaKopica<Tip> nextPrev = max;

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
            BinomskaKopica<Tip> node = topNode;
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
            BinomskaKopica<Tip> max = topNode;

            while (max.sibling != null) {
                max = max.sibling;
            }

            return max.degree;
        }
    }

    @Override
    public Tip remove(Tip e) {
        BinomskaKopica node = search(e);
        if (node != null) {
            node = bubbleUp(node);
            if (topNode == node) {
                removeTreeRoot(node, null);
            } else {
                BinomskaKopica<Tip> prev = topNode;
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
        List<BinomskaKopica<Tip>> currentRow = new ArrayList<>();
        List<BinomskaKopica<Tip>> nextRow = new ArrayList<>();
        LinkedHashMap hashMap = new LinkedHashMap();
        if (topNode == null) {
            return new ArrayList<>(hashMap.values());
        }
        currentRow.add(topNode);
        while (!currentRow.isEmpty()) {
            BinomskaKopica<Tip> curr = currentRow.get(0);
            hashMap.put(curr.key, curr.key);
            currentRow.remove(0);

            if (curr.sibling != null) {
                currentRow.add(curr.sibling);
            }
            if (curr.child != null) {
                BinomskaKopica<Tip> child = curr.child;
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
