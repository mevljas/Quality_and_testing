class Element<Tip> {
    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        vrednost = e;
    }
}

public class Sklad <Tip> {
    private Element<Tip> vrh;
    
    public Sklad() {
        
    }
    
    public void push(Tip e) {
        Element<Tip> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }
    
    public Tip pop() {
        
        if(null == vrh){
            throw new java.util.NoSuchElementException();
        }
        
        Tip e = vrh.vrednost;
        vrh = vrh.vezava;
        return e;
    }
    
    public boolean isEmpty() {
        return (null == vrh);
    }
    
    public Tip top(){
        if(null == vrh){
            throw new java.util.NoSuchElementException();
        }
        
        return vrh.vrednost;
    }
    
    public int Size(){
        if(null == vrh){
            return 0;
        }
        
        return count(vrh);
    }
    
    public int count( Element<Tip> vrh ){
        if(vrh.vezava == null){
            return 1;
        }
        return count(vrh.vezava) + 1;
    }
}