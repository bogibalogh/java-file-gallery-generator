
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
class HtmlGenerator {

    static void generateHtml(File konyvtar, List<File> fajlok) {
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
                generateDirectoryLinks(fajlok) +
                "</div>\n" +
                "<div>\n" +
                "<h2>Images: </h2><br>\n" +
                "<div class=\"underline\"></div>\n" +
                generateImageLinks(fajlok) +
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
    
    public static void generateImageHtml(File fajl, String kepNev, List<File> osszesKepFajl)
    {
         int jelenlegiIndex = Arrays.asList(osszesKepFajl).indexOf(fajl);

        String elozoLink = (jelenlegiIndex > 0) ? ("<a href=\"" + osszesKepFajl.get(jelenlegiIndex - 1).getName() + ".html\">&lt;&lt; </a>") : "";
        String kovetkezoLink = (jelenlegiIndex < osszesKepFajl.size()- 1) ? ("<a href=\"" + osszesKepFajl.get(jelenlegiIndex + 1).getName() + ".html\"> &gt;&gt;</a>") : "";

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
            "<br><a href=\"" + ((jelenlegiIndex > 0) ? osszesKepFajl.get(jelenlegiIndex - 1).getName() + ".html" : "../../index.html") + "\" style=\"color: black;\"> ^^ </a></br>"+
            "<div>\n" +
            "</head>\n" +
            "<body>\n" +
            "<a href=\"" + ((jelenlegiIndex < osszesKepFajl.size() - 1) ? osszesKepFajl.get(jelenlegiIndex + 1).getName() + ".html" : "../../index.html") + "\">" +
            "<h1>" + elozoLink + kepNev + kovetkezoLink + "</h1>\n" +
            "<a href=\"" + ((jelenlegiIndex < osszesKepFajl.size() - 1) ? osszesKepFajl.get(jelenlegiIndex + 1).getName() + ".html" : "../index.html") + "\">" +
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
    
}
