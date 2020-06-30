
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        long start, end;
        Scanner scanner = new Scanner(System.in);
        String input;

        Trie trie = new Trie();
        try {
            File file = new File("F:\\Project_Test\\50mb\\DS_Project.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            start = System.currentTimeMillis();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                String[] eachLine = inputLine.split(" ");
                if (eachLine[0].equals("function"))
                    trie.insert(eachLine[0], eachLine[1], eachLine[2], eachLine[3]);
                else
                    trie.insert(eachLine[0], eachLine[1], eachLine[2], "");
            }
            end = System.currentTimeMillis();
            System.out.println("Reading file and making tree time : " + (end - start));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        while (true) {
            input = scanner.next();
            switch (input) {
                case "search":
                    start = System.currentTimeMillis();
                    if (!(trie.search(scanner.next())))
                        System.out.println(" ## Nothing found with this prefix!");
                    end = System.currentTimeMillis();
                    System.out.println(" @@ Search time : " + (end - start));
                    break;
                case "remove":
                    start = System.currentTimeMillis();
                    if (trie.remove(scanner.next()))
                        System.out.println(" ## Deleted successfully!");
                    else
                        System.out.println(" ## Nothing deleted!");
                    end = System.currentTimeMillis();
                    System.out.println(" @@ Remove time : " + (end - start));
                    break;
                case "add":
                    start = System.currentTimeMillis();
                    String varOrFunc = scanner.next();
                    if (varOrFunc.equals("function")) {
                        trie.insert(varOrFunc, scanner.next(), scanner.next(), scanner.next());
                        System.out.println(" ## Added successfully!");
                    } else if (varOrFunc.equals("variable")) {
                        trie.insert(varOrFunc, scanner.next(), scanner.next(), "");
                        System.out.println(" ## Added successfully!");
                    }
                    end = System.currentTimeMillis();
                    System.out.println(" @@ Add time : " + (end - start));
                    break;
                default:
                    return;
            }
        }
    }
}
