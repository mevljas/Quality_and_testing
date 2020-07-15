
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seba
 */
public class Bolnik {
    public static void main(String[] args) {
        
        String vnos;
        SeznamiUV seznamiUV= new SeznamiUV();
        seznamiUV.addImpl("bk", new BinomskaKopica<BolnikObj>(new BolnikPrimerjajPoImenu()), new BinomskaKopica<BolnikObj>(new BolnikPrimerjajPoEMSO()));
        seznamiUV.processInput("use bk");
        String result = "";
        try (Scanner in = new Scanner(System.in)){
            while (true) {
                System.out.print("command>");
                vnos = in.nextLine();
                String[] argumenti = vnos.split(" ");
                if(argumenti.length > 0){
                    switch(argumenti[0]){
                        case "exit":
                            System.out.println("Bye");
                            seznamiUV.processInput("exit");
                            System.exit(0);
                            break;
                        case "add":
                            System.out.print("add> EMSO:");
                            String EMSO = in.nextLine();
                            System.out.print("add> NAME:");
                            String NAME = in.nextLine().replace(" ", "_");
                            System.out.print("add> SURNAME:");
                            String SURNAME = in.nextLine().replace(" ", "_");
                            System.out.print("add> AGE:");
                            String AGE = in.nextLine();
                            result = ">> "+seznamiUV.processInput("add " + NAME + " " + SURNAME + " " + AGE + " " + EMSO);
                            break;
                            
                            
                        case "search":
                            if (argumenti.length == 2) {
                                EMSO = argumenti[1];
                                result = ">> "+seznamiUV.processInput("search " + EMSO);
                            }
                            else if (argumenti.length == 1) {
                                System.out.print("search> NAME:");
                                NAME = in.nextLine().replace(" ", "_");
                                System.out.print("search> SURNAME:");
                                SURNAME = in.nextLine().replace(" ", "_");
                                result = ">> "+seznamiUV.processInput("search " + NAME + " " + SURNAME);
                            }
                            else{
                                System.out.println("Invalid input data");
                            }
                            break;
                        
                        case "remove":
                            if (argumenti.length == 2) {
                                EMSO = argumenti[1];
                                result = ">> "+seznamiUV.processInput("remove " + EMSO);
                            }
                            else if (argumenti.length == 1) {
                                System.out.print("remove> NAME:");
                                NAME = in.nextLine().replace(" ", "_");
                                System.out.print("remove> SURNAME:");
                                SURNAME = in.nextLine().replace(" ", "_");
                                result = ">> "+seznamiUV.processInput("remove " + NAME + " " + SURNAME);
                            }
                            else{
                                System.out.println(">> Invalid input data");
                            }
                            break;
                            
                            
                        case "save":
                            
                            result = ">> "+seznamiUV.processInput("save " + argumenti[1]);
                            break;
                            
                        case "restore":
                            result = ">> "+seznamiUV.processInput("restore " + argumenti[1]);
                            break;
                            
                        case "print":
                            System.out.println(">>Number of patients: " + seznamiUV.processInput("count"));
                            seznamiUV.processInput("print");
                            result = "";
                            break;
                            
                        case "count":
                            result = ">> No. of patients: " + seznamiUV.processInput("count");
                            break;
                            
                        case "reset":
                            System.out.print("reset> Are you sure (y|n):");
                            char answer = in.nextLine().charAt(0);
                            if (answer == 'y') {
                                result = ">> "+seznamiUV.processInput("reset");
                            }
                            break;

                            
                            
                        default:
                            result = ">> Invalid command";
                            break;
                            
                    }
                    System.out.println(result);
                }
                
                
            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
