
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;


public class SeznamiUV {

    HashMap<String, Seznam<Prijatelj>> seznamiPoImenu;
    HashMap<String, Seznam<Prijatelj>> seznamiPoTelSt;
    Seznam<Prijatelj> seznamPoImenu;
    Seznam<Prijatelj> seznamPoTelSt;
    
    static String memoryError = "Error: not enough memory, operation failed";
   
    public SeznamiUV() {
        seznamiPoImenu = new HashMap<>();
        seznamiPoTelSt = new HashMap<>();
    }
    
    public void addImpl(String key, Seznam<Prijatelj> seznamPoImenu, Seznam<Prijatelj> seznamPoTelSt) {
        seznamiPoImenu.put(key, seznamPoImenu);
        seznamiPoTelSt.put(key, seznamPoTelSt);
    }

    public String processInput(String input) {
        String token;
        String result = "OK";
        String[] params = input.split(" ");


        //moramo preverjati za primer praznega Niza
        if (params.length == 0) {
            return "Error: enter command";
        }
        else{
            token = params[0];
        }

        if (!token.equals("use") && (null == seznamPoImenu) && !token.equals("exit")) {
            return "Error: please specify a data structure (use {pv|sk|bst})";
        }
        try {
            if (token.equals("use")){
                if (params.length > 1) {
                    String structType = params[1];
                    seznamPoImenu = seznamiPoImenu.get(structType);
                    seznamPoTelSt = seznamiPoTelSt.get(structType);
                    if (null == seznamPoImenu) {
                        result = "Error: please specify a correct data structure type {pv|sk|bst}";
                    }

                } else {
                    result = "Error: please specify a data structure type {pv|sk|bst}";
                }
            }
            else if (token.equals("add")){
                if (params.length == 4)
                {
                    Prijatelj p = new Prijatelj(params[1], params[2], params[3]);
                    seznamPoImenu.add(p);
                    seznamPoTelSt.add(p);
                }
                else
                    result = "Error: please specify three strings";
            }
            else if (token.equals("removefirst")){
                Prijatelj p = seznamPoImenu.removeFirst();
                result = seznamPoTelSt.remove(p).toString();
            }
            else if (token.equals("remove")){
                if (params.length == 3)  {
                    Prijatelj p = seznamPoImenu.remove(new Prijatelj(params[1], params[2], ""));
                    seznamPoTelSt.remove(p);
                }
                else
                    result = "Error: please specify two strings";
            }
            else if (token.equals("getfirst")){
                result = seznamPoImenu.getFirst().toString();
            }
            else if (token.equals("count")){
                result = seznamPoImenu.size() + "";
            }
            else if (token.equals("depth")){
                result = seznamPoImenu.depth() + " " + seznamPoTelSt.depth();
            }
            else if (token.equals("reset")){
                while (!seznamPoImenu.isEmpty()) {
                    seznamPoImenu.removeFirst();
                }
                while (!seznamPoTelSt.isEmpty()) {
                    seznamPoTelSt.removeFirst();
                }
            }
            else if (token.equals("exists")){
                result = "No";
                if (params.length == 3)  {
                    if (seznamPoImenu.exists(new Prijatelj(params[1], params[2], "")))
                        result = "Yes";
                } else if (params.length == 2)  {
                    if (seznamPoTelSt.exists(new Prijatelj("", "", params[1])))
                        result = "Yes";
                }
                else {
                    result = "Error: please specify two strings";
                }
            }
            else if (token.equals("print")){
                seznamPoImenu.print();
                seznamPoTelSt.print();
            }
            else if (token.equals("save")){
                if (params.length == 2) {
                    seznamPoImenu.save(new FileOutputStream( "p_" + params[1] ));
                    seznamPoTelSt.save(new FileOutputStream( "t_" + params[1] ));
                } else {
                    result = "Error: please specify a file name";
                }
            }
            else if (token.equals("restore")){
                if (params.length == 2) {
                    seznamPoImenu.restore(new FileInputStream( "p_" + params[1]  ));
                    seznamPoTelSt.restore(new FileInputStream( "t_" +params[1] ));
                } else {
                    result = "Error: please specify a file name";
                }
            }
            else if (token.equals("exit")){
                    result = "Have a nice day.";
            }
            else {
                result = "Error: invalid command";
            }
                

        } catch (UnsupportedOperationException e) {
            result = "Error: Operation not supported";
        } catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: structure is empty";
        } catch (IOException e) {
            result = "Error: IO error " + e.getMessage();
        } catch (ClassNotFoundException e) {
            result = "Error: Unknown format";
        } catch (OutOfMemoryError e) {
            return memoryError;
        }

        return result;
    }
}
