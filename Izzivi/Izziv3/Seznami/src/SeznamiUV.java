
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
public class SeznamiUV {

    Sklad<String> sklad;

    public SeznamiUV() {
        sklad = new Sklad<String>();
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token = sc.next();
        String result = "OK";
        switch (token) {
            case "push":
                if (sc.hasNext()) {
                    sklad.push(sc.nextLine()
                            .substring(1).replaceAll("\"", ""));
                } else {
                    result = "Error: please specify a string";
                }
                break;
            case "pop":
                if (!sklad.isEmpty()) {
                    result = sklad.pop();
                } else {
                    result = "Error: stack is empty";
                }
                break;
            case "reset":
                while (!sklad.isEmpty()) {
                    sklad.pop();
                }
                break;
            case "count":
                result = String.format("%d", sklad.size());
                break;
            case "isTop":
                if (sc.hasNext()) {
                    if (sklad.isEmpty()) {
                        result = "Error: stack is empty";
                    } else if (sc.next().equals(sklad.top())) {
                        result = "OK";
                    } else {
                        result = "Error: wrong element";
                    }
                    {

                    }
                } else {
                    result = "Error: please specify a string";
                }
                break;
            case "search":
                if (sc.hasNext()) {
                    result = String.format("%d", sklad.search(sc.nextLine()
                            .substring(1).replaceAll("\"", "")));

                } else {
                    result = "Error: please specify a string";
                }
                break;

        }
        return result;
    }

}
