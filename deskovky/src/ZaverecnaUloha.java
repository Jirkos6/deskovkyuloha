import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZaverecnaUloha {
    private static final String ODDELOVAC = ";";
    private static final String SOUBOR = "deskovky.txt";


    public List<Deskovka> ziskejSeznamDeskovekZeSouboru(){
        try{
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(SOUBOR)));
            List<Deskovka> seznamDeskovek = new ArrayList<>();
            while (scanner.hasNext()){
                String radek = scanner.nextLine();
                String[] castiRadku = radek.split(ODDELOVAC);
                for (int i = 0; i< castiRadku.length; i++){
                    castiRadku[i] = castiRadku[i].trim();
                }
                String nazev = castiRadku[0];
                boolean zakoupeno;
                if(castiRadku[1].equals("true")){
                    zakoupeno = true;
                }else{
                    zakoupeno = false;
                }
                int oblibenost = Integer.parseInt(castiRadku[2]);
                seznamDeskovek.add(new Deskovka(nazev, zakoupeno, oblibenost));
            }
            scanner.close();
            return seznamDeskovek;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void zapisDoSouboru(List<Deskovka> seznam){
        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(SOUBOR)));
            for (Deskovka deskovka : seznam){
                String nazev = deskovka.getNazev();
                int oblibenost = deskovka.getOblibenost();
                boolean zakoupeno = deskovka.isZakoupeno();
                writer.println(nazev + ODDELOVAC + zakoupeno + ODDELOVAC + oblibenost);
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
