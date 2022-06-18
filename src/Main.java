import java.io.*;
import java.util.*;

public class Main {

    private static final String INCORRECT_LENGTH = "Length is incorrect";
    private static final String INCORRECT_NAME = "Name is incorrect";
    private static final String CORRECT_NAME = "Name is correct";

    public static void main(String[] args) throws IOException {

        List<File> files = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String temp = sc.nextLine();
            if (temp.equals("0")) {
                break;
            } else {
                File file = new File(temp);
                files.add(file);
            }
        }

        Set<String> docs = new HashSet<>();

        try {
            for (File file : files) {
                FileReader contractreader = new FileReader(file);
                BufferedReader reader = new BufferedReader(contractreader);
                while (reader.ready()) {
                    String readLine = reader.readLine();
                    docs.add(readLine);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File name is incorrect");
            throw new RuntimeException(e);
        }

        Map<String, String> comments = new HashMap<>();
        for (String doc : docs) {
            if (doc.length() != 15) {
                comments.put(doc, INCORRECT_LENGTH);
            } else if (!(doc.startsWith("contract") || (doc.startsWith("docnum")))) {
                comments.put(doc, INCORRECT_NAME);
            } else {
                comments.put(doc, CORRECT_NAME);
            }
        }
        BufferedWriter bw = null;

        try {
            FileWriter fw = new FileWriter("otchet1.txt");
            bw = new BufferedWriter(fw);

            for (Map.Entry<String, String> entry : comments.entrySet()) {
                bw.write(entry.getKey());
                bw.write(" : ");
                bw.write(entry.getValue());
                bw.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            bw.close();
        }


//        File contract = new File("C:\\Users\\Julija\\Desktop\\document\\contract.txt");//C:\Users\Julija\Desktop\document\contract.txt
//        BufferedWriter bw = null;
//        BufferedWriter bw1 = null;
//        try {
//            FileReader contractreader = new FileReader(contract);
//            BufferedReader reader = new BufferedReader(contractreader);
//            FileWriter writer = new FileWriter("otchet1.txt");
//            FileWriter writer1 = new FileWriter("otchet2.txt");
//            bw = new BufferedWriter(writer);
//            bw1 = new BufferedWriter(writer1);
//
//            while (reader.ready()) {
//                String readLine = reader.readLine();
//                System.out.println(readLine);
//
//                if ((readLine.startsWith("contract"))|| (readLine.startsWith("docnum")) || (readLine.length() == 15) || (readLine.matches("[a-z], %d"))) {
//                    bw.write(readLine);
//                    bw.write("\n");
//                } else {
//                    bw1.write(readLine);
//                    bw1.write("\n");
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }finally {
//            bw.close();
//            bw1.close();
//        }
    }
}