import java.io.File;




public class Main {
    
    
public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Hiba: Adjon meg egy könyvtárat!");
            System.exit(1);
        }

        
        String gyokerKonyvtar = args[0];

        if (args.length > 1 && args[1].equalsIgnoreCase("delete")) {
            htmlFajlokTorlese(gyokerKonyvtar);
        } else {
            // Képgaléria generálása
            kepgaleriaGeneralas(gyokerKonyvtar);
        }
    }

    private static void htmlFajlokTorlese(String konyvtar) {
        konyvtarOldalGenerator konyvtarOldal = new konyvtarOldalGenerator(konyvtar);
        konyvtarOldal.deleteHtmlFiles(new File(konyvtar));
    }

    private static void kepgaleriaGeneralas(String konyvtar) {
        File konyvtarFajl = new File(konyvtar);
        File[] fajlok = konyvtarFajl.listFiles();

        if (fajlok == null) {
            System.out.println("Érvénytelen könyvtár: " + konyvtar);
            return;
        }

        // Generálja az HTML-t az aktuális könyvtárhoz
        konyvtarOldalGenerator konyvtarOldal = new konyvtarOldalGenerator(konyvtar);
        konyvtarOldal.konyvtarHtmlGenerálás(fajlok);

        // Feldolgozza az almappákat
        for (File fajl : fajlok) {
            if (fajl.isDirectory()) {
                kepgaleriaGeneralas(fajl.getAbsolutePath());
                System.out.println(fajl.getAbsolutePath());
            }
        }
    }
   
}

