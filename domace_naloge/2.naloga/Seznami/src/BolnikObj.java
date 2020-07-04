
class BolnikPrimerjajPoImenu implements java.util.Comparator<BolnikObj>
{
    @Override
    public int compare(BolnikObj o1, BolnikObj o2)
    {
        String ime1 = o1.getPriimek() + ", " + o1.getIme();
        String ime2 = o2.getPriimek() + ", " + o2.getIme();
        return ime1.compareToIgnoreCase(ime2);
    }
}

class BolnikPrimerjajPoEMSO implements java.util.Comparator<BolnikObj>
{
    @Override
    public int compare(BolnikObj o1, BolnikObj o2)
    {
        String s1 = o1.getEMSO();
        String s2 = o2.getEMSO();
        
        return -s1.compareTo(s2);
    }
}

class BolnikPrimerjajPoStarost implements java.util.Comparator<BolnikObj>
{
    @Override
    public int compare(BolnikObj o1, BolnikObj o2)
    {
        String s1 = o1.getStarost();
        String s2 = o2.getStarost();

        return -s1.compareTo(s2);
    }
}

public class BolnikObj implements java.io.Serializable
{
    protected String ime;
    protected String priimek;
    protected String starost;
    protected String EMSO;
    
    public BolnikObj()  {}

    public BolnikObj(String ime, String priimek, String starost, String EMSO)
    {
        this.ime = ime;
        this.priimek = priimek;
        if (Integer.parseInt(starost) < 0 || Integer.parseInt(starost) > 130){
            throw new UnsupportedOperationException();
        }
        this.starost = starost;
        if (EMSO.length() != 13){
            throw new UnsupportedOperationException();
        }
        this.EMSO = EMSO;
    }
    
    public String getIme()
    {
        return ime;
    }

    public void setIme(String ime)
    {
        this.ime = ime;
    }

    public String getPriimek()
    {
        return priimek;
    }

    public void setPriimek(String priimek)
    {
        this.priimek = priimek;
    }
    
    public String getStarost()
    {
        return starost;
    }

    public void setStarost(String starost)
    {
        this.starost = starost;
    }
    
    public String getEMSO()
    {
        return EMSO;
    }

    public void setEMSO(String EMSO)
    {
        this.EMSO = EMSO;
    }

   
    
    @Override
    public String toString()
    {
        return EMSO + " | " + priimek.replace("_", " ") + ", " + ime.replace("_", " ") + " | " + starost;
    }
}
