import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class konyvtarOldalGenerator {
    

   private String konyvtar;

    konyvtarOldalGenerator(String konyvtar) {
        this.konyvtar = konyvtar;
    }

    void konyvtarHtmlGenerálás(File[] fajlok) {
        String htmlTartalom = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title> index.html </title>\n" +
                "<style>\n" +
                "  .underline { border-bottom: 2px solid black; margin-bottom: 10px; }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1><a href=\"../../index.html\" >Start Page</a></h1>\n" +
                "<div class=\"underline\"></div>\n" +
                "<div>\n" +
                "<h2>Directories: </h2><br>\n" +
                "<a href=\"../index.html\" \"> ^^ </a>"+
                "<div class=\"underline\"></div>\n" +
                konyvtarLinkGeneralas(fajlok) +
                "</div>\n" +
                "<div>\n" +
                "<h2>Images: </h2><br>\n" +
                "<div class=\"underline\"></div>\n" +
                kepLinkGenearalas(fajlok) +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        String indexPath = konyvtar + File.separator + "index.html";
        try (FileWriter writer = new FileWriter(indexPath)) {
            writer.write(htmlTartalom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //létrehozza azokat a linkeket, amelyek az adott könyvtárak index.html fájljaira mutatnak.
    private String konyvtarLinkGeneralas(File[] fajlok) {
        StringBuilder linkek = new StringBuilder();
        for (File fajl : fajlok) {
            if (fajl.isDirectory()) {
                String konyvtarNev = fajl.getName();
                linkek.append("<a href=\"").append(konyvtarNev).append("/index.html\">").append(konyvtarNev).append("</a><br>\n");
            }
        }
        return linkek.toString();
    }

    private String kepLinkGenearalas(File[] fajlok) {
        StringBuilder linkek = new StringBuilder();
        Arrays.sort(fajlok);
        for (int i = 0; i < fajlok.length; i++) {
            File fajl = fajlok[i];
            if (fajl.isFile() && isImageFile(fajl)) {
                String kepNev = fajl.getName();
                linkek.append("<a href=\"").append(kepNev + ".html\">").append(kepNev).append("</a>");

                linkek.append("<br>\n");

                // HTML fájl generálása minden képhez
                kepHtmlgenearalas(fajl, kepNev,fajlok);
            }
        }
        return linkek.toString();
    }


    

    private boolean isImageFile(File fajl) {
        String nev = fajl.getName().toLowerCase();
        return nev.endsWith(".jpg") || nev.endsWith(".jpeg") || nev.endsWith(".png") || nev.endsWith(".gif");
    }
    

    
    

    private void kepHtmlgenearalas(File fajl, String kepNev, File[] osszesKepFajl) {
        int jelenlegiIndex = Arrays.asList(osszesKepFajl).indexOf(fajl);

        String elozoLink = (jelenlegiIndex > 0) ? ("<a href=\"" + osszesKepFajl[jelenlegiIndex - 1].getName() + ".html\">&lt;&lt; </a>") : "";
        String kovetkezoLink = (jelenlegiIndex < osszesKepFajl.length- 1) ? ("<a href=\"" + osszesKepFajl[jelenlegiIndex + 1].getName() + ".html\"> &gt;&gt;</a>") : "";

        String kepHtmlTartalom = "<!DOCTYPE html>\n" +
                "<html>\n" +
            "<head>\n" +
            "<title>" + kepNev + "</title>\n" +
            "<style>\n" +
            "  .underline { border-bottom: 2px solid black; margin-bottom: 10px; }\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h1><a href=\"../../index.html\" >Start Page</a></h1>\n" +
            "<div class=\"underline\"></div>\n" +
            "<a href=\"../index.html\" \"> ^^ </a>"+
            "<div>\n" +
            "</head>\n" +
            "<body>\n" +
            "<a href=\"" + ((jelenlegiIndex < osszesKepFajl.length - 1) ? osszesKepFajl[jelenlegiIndex + 1].getName() + ".html" : "../../index.html") + "\">" +
            "<h1>" + elozoLink + kepNev + kovetkezoLink + "</h1>\n" +
            "<a href=\"" + ((jelenlegiIndex < osszesKepFajl.length - 1) ? osszesKepFajl[jelenlegiIndex + 1].getName() + ".html" : "../index.html") + "\">" +
            "<img src=\"" + kepNev + "\" alt=\"" + kepNev + "\">\n" +
            "</a>\n" +
            "<div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";


        String kepHtmlUtvonal = konyvtar + File.separator + kepNev + ".html";
        try (FileWriter writer = new FileWriter(kepHtmlUtvonal)) {
            writer.write(kepHtmlTartalom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

           public void deleteHtmlFiles(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Rekurzív hívás az alkönyvtárakra
                        deleteHtmlFiles(file);
                    } else if (file.getName().toLowerCase().endsWith(".html")) {
                        // Törlés, ha a fájl kiterjesztése ".html"
                        file.delete();
                        System.out.println("Deleted: " + file.getAbsolutePath());
                    }
                }
            }
        }
  }

 
}

