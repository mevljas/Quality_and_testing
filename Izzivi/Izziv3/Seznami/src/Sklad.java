
class Element<Tip> {

    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        vrednost = e;
    }
}

public class Sklad<Tip> {

    private Element<Tip> vrh;

    public Sklad() {
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

    public boolean isEmpty() {
        return (null == vrh);
    }

    public int size() {
        int result = 0;
        Element<Tip> temp = vrh;
        while (null != temp) {
            ++result;
            temp = temp.vezava;
        }
        return result;
    }

    public Tip top() {
        if (null == vrh) {
            throw new java.util.NoSuchElementException();
        }

        return vrh.vrednost;
    }

    public int search(String wantedString) {
        int result = 0;
        Element<Tip> temp = vrh;
        while (null != temp) {
            if (temp.vrednost.equals(wantedString)) {
                return result;
            }
            temp = temp.vezava;
            result++;
        }
        return -1;
    }

}
