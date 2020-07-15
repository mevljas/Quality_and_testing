
import java.util.Scanner;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class SeznamiUV {

    HashMap<String, Seznam<String>> seznami;
    Seznam<String> seznam;

    public SeznamiUV() {
        seznami = new HashMap<>();
        seznami.put("pv", new PrioritetnaVrsta<>());
        seznami.put("sk", new Sklad<>());
        seznami.put("bst", new Bst<>());

    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token = sc.next();
        String result = "OK", tmp;
        try {
            switch (token) {
                case "use":
                    if (sc.hasNext()) {
                        seznam = seznami.get(sc.next());
                        if (null == seznam) {
                            result = "Error: please specify a correct data structure type (pv, sk, bst)";
                        }
                    } else {
                        result = "Error: please specify a data structure type (pv, sk, bst)";
                    }
                    break;
                case "add":
                    if (sc.hasNext()) {
                        tmp = sc.next();
                        if (tmp.startsWith("\"")) {
                            tmp = tmp.substring(1);
                            while (sc.hasNext()) {
                                //tmp1=sc.next();

                                tmp = tmp + " " + sc.next();
                                if (tmp.endsWith("\"")) {
                                    break;
                                }
                            }
                            if (tmp.endsWith("\"")) {
                                tmp = tmp.substring(0, tmp.length() - 1);
                            } else {
                                result = "Error: please specify a correct string";
                                break;
                            }
                        }
                        seznam.add(tmp);
                    } else {
                        result = "Error: please specify a string";
                    }
                    break;

                case "remove_first":
                    if (seznam.isEmpty()) {
                        result = "Error: data structure is empty";
                    } else {
                        result = seznam.removeFirst();
                    }

                    break;

                case "size":
                    result = String.format("%d", seznam.size());
                    break;

                case "get_first":
                    if (!seznam.isEmpty()) {
                        result = seznam.getFirst();
                    } else {
                        result = "Error: data structure is empty";
                    }
                    break;

                case "depth":
                    return String.format("%d", seznam.depth());

                case "is_empty":
                    return String.format("Data structure is%s empty",
                            seznam.isEmpty() ? "" : " not");

                case "exists":

                    if (sc.hasNext()) {
                        if (seznam.isEmpty()) {
                            result = "Data structure is empty";
                        } else {
                            result = "Element " + (seznam.exists(sc.next()) ? "exists " : "doesn't exist ") + "in data structure";
                        }

                    } else {
                        result = "Please specify a string";
                    }
                    break;
                case "remove":
                    if (sc.hasNext()) {
                        if (seznam.isEmpty()) {
                            result = "Data structure is empty";
                        } else {
                            result = seznam.remove(sc.next());
                        }

                    } else {
                        result = "Error: please specify a string";
                    }
                    break;

                case "reset":
                    if (seznam instanceof Sklad) {
                        seznam = new Sklad<>();
                    } else if (seznam instanceof Bst) {
                        seznam = new Bst<>();
                    } else if (seznam instanceof PrioritetnaVrsta) {
                        seznam = new PrioritetnaVrsta<>();
                    }
                    break;

                default:
                    result = "Error: invalid command";
            }
        } catch (UnsupportedOperationException e) {
            result = "Error: Operation not supported";
        } catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: element does not exist in data structure";
        }

        return result;
    }
}
