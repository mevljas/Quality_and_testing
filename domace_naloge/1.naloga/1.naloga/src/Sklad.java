
import java.util.ArrayList;
import java.util.List;

class Element<Tip> {

    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        this.vrednost = e;
    }
}

public class Sklad<Tip> implements Seznam<Tip> {

    private Element<Tip> vrh;

    public Sklad() {
    }

    public void push(Tip e) {
        Element<Tip> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }

    public Tip pop() {
        if (vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        Tip e = vrh.vrednost;
        vrh = vrh.vezava;
        return e;
    }

    @Override
    public boolean isEmpty() {
        return (vrh == null);
    }

    public Tip top() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost;
    }

    @Override
    public int size() {
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

    public boolean isTop(Tip e) {
        if (vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost.equals(e);
    }

    public int search(Tip e) {
        Element<Tip> tmp = vrh;
        int i = 0;
        while (null != tmp) {
            if (tmp.vrednost.equals(e)) {
                return i;
            }
            i++;
            tmp = tmp.vezava;
        }
        return -1;
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
    public Tip getFirst() {
        return top();
    }

    @Override
    public int depth() {
        return size();
    }

    @Override
    public Tip remove(Tip e) {
        Element<Tip> tmp = vrh;
        Element<Tip> prejsni = null;
        while (null != tmp) {
            if (tmp.vrednost.equals(e)) {
                if (prejsni == null) {
                    vrh = vrh.vezava;
                } else {
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
    public boolean exists(Tip e) {
        return this.search(e) >= 0;
    }

    @Override
    public List<Tip> asList() {
        ArrayList<Tip> list = new ArrayList<>();
        Element<Tip> tmp = vrh;
        Element<Tip> prejsni = null;
        while (null != tmp) {
            list.add(tmp.vrednost);
            prejsni = tmp;
            tmp = tmp.vezava;
        }
        return list;
    }

}
