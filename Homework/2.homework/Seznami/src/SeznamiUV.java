
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class SeznamiUV {

    HashMap<String, Seznam<BolnikObj>> seznamiPoImenu;
    HashMap<String, Seznam<BolnikObj>> seznamiPoEMSO;
    Seznam<BolnikObj> seznamPoImenu;
    Seznam<BolnikObj> seznamPoEMSO;
    
    static String memoryError = "Error: not enough memory, operation failed";
   
    public SeznamiUV() {
        seznamiPoImenu = new HashMap<>();
        seznamiPoEMSO = new HashMap<>();
    }
    
    public void addImpl(String key, Seznam<BolnikObj> seznamPoImenu, Seznam<BolnikObj> seznamPoTelSt) {
        seznamiPoImenu.put(key, seznamPoImenu);
        seznamiPoEMSO.put(key, seznamPoTelSt);
    }
    
    public void addImpl(String key, Seznam<BolnikObj> seznam) {
        seznamiPoImenu.put(key, seznam);
        seznamiPoEMSO.put(key, seznamPoEMSO);
    }


    public String processInput(String input) {
        String token;
        String result = "OK";
        String[] params = input.split(" ");


        token = params[0];

        if (!token.equals("use") && (null == seznamPoImenu) && !token.equals("exit")) {
            return "Error: please specify a data structure (use {pv|sk|bst|bk})";
        }
        try {
            if (token.equals("use")){
                if (params.length > 1) {
                    String structType = params[1];
                    seznamPoImenu = seznamiPoImenu.get(structType);
                    seznamPoEMSO = seznamiPoEMSO.get(structType);

                } 
            }
            else if (token.equals("add")){
                if (params.length == 5)
                {
                    BolnikObj p = new BolnikObj(params[1], params[2], params[3], params[4]);
                    if (!seznamPoImenu.exists(p)) {
                        seznamPoImenu.add(p);
                        seznamPoEMSO.add(p);
                    }
                    else{
                        throw new IllegalArgumentException();
                    }
                    
                }
                else
                    result = "Error: please specify four strings";
            }
            else if (token.equals("removefirst")){
                BolnikObj p = seznamPoImenu.removeFirst();
                result = seznamPoEMSO.remove(p).toString();
            }
            else if (token.equals("remove")){
                try {
                    if (params.length == 3)  {
                        BolnikObj p = seznamPoImenu.remove(new BolnikObj(params[1], params[2], "1", "3105940500232"));
                        seznamPoEMSO.remove(p);
                    }
                    else if (params.length == 2)  {
                        BolnikObj p = seznamPoEMSO.remove(new BolnikObj("Ime", "Priimek","1", params[1]));
                        seznamPoImenu.remove(p);
                    }
                    else {
                        throw new UnsupportedOperationException();
                    }
                    result = "OK";
                    
                } catch (NoSuchElementException | NullPointerException e) {
                    result = "Patient does not exist";
                }
                
            }
            else if (token.equals("getfirst")){
                result = seznamPoImenu.getFirst().toString();
            }
            else if (token.equals("count")){
                result = seznamPoImenu.size() + "";
            }
            else if (token.equals("depth")){
                result = seznamPoImenu.depth()+"";
            }
            else if (token.equals("reset")){
                while (!seznamPoImenu.isEmpty()) {
                    seznamPoImenu.removeFirst();
                }
                while (!seznamPoEMSO.isEmpty()) {
                    seznamPoEMSO.removeFirst();
                }
            }
//            else if (token.equals("exists")){
//                result = "No";
//                if (params.length == 3)  {
//                    if (seznamPoImenu.exists(new BolnikObj(params[1], params[2], "", "")))
//                        result = "Yes";
//                } else if (params.length == 2)  {
//                    if (seznamPoEMSO.exists(new BolnikObj("", "","", params[1])))
//                        result = "Yes";
//                }
//                else {
//                    result = "Error: please specify two strings";
//                }
//            }
            else if (token.equals("print")){
                seznamPoImenu.print();
            }
            else if (token.equals("save")){
                if (params.length == 2) {
                    seznamPoImenu.save(new FileOutputStream( "p_" + params[1] ));
                    seznamPoEMSO.save(new FileOutputStream( "t_" + params[1] ));
                    result = "OK";
                } else {
                    result = "Error: please specify a file name";
                }
            }
            else if (token.equals("restore")){
                if (params.length == 2) {
                    seznamPoImenu.restore(new FileInputStream( "p_" + params[1]  ));
                    seznamPoEMSO.restore(new FileInputStream( "t_" +params[1] ));
                    result = "OK";
                } else {
                    result = "Error: please specify a file name";
                }
            }
            else if (token.equals("exit")){
                    result = "Have a nice day.";
            }
            else if (token.equals("search")){
                result = "";
                try {
                    BolnikObj bo;
                    if (params.length == 3)  {
                         bo = seznamPoImenu.search(new BolnikObj(params[1], params[2], "1", "3105940500232"));

                    } else if (params.length == 2)  {
                         bo = seznamPoEMSO.search(new BolnikObj("Ime", "Priimek","1", params[1]));
                    }
                    else {
                        throw new UnsupportedOperationException();
                    }
                    result = bo.toString();
                    
                } catch (NoSuchElementException | NullPointerException e) {
                    result = "Patient does not exist";
                }
               
            }
            else {
                result = "Error: invalid command";
            }
                

        } catch (UnsupportedOperationException e) {
            result = "Invalid input data";
        } catch (IllegalArgumentException e) {
            result = "Patient already exists";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: structure is empty";
        } 
        catch (IOException e) {
            result = "Error: IO error " + e.getMessage();
        }  catch (ClassNotFoundException e) {
            result = "Invalid input data";
        } catch (OutOfMemoryError e) {
            return memoryError;
        }

        return result;
    }
}
