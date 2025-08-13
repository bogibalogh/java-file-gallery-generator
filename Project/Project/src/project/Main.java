
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;
import java.io.File;
import java.util.List;
/**
 *
 * @author Admin
 */
public class Main {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Hiba: Adjon meg egy könyvtárat!");
            System.exit(1);
        }

        String rootDirectory = args[0];

        if (args.length > 1 && args[1].equalsIgnoreCase("delete")) {
            FileUtils.deleteHtmlFiles(new File(rootDirectory));
        } else {
            generateImageGallery(new File(rootDirectory));
        }
    }

    private static void generateImageGallery(File directory) {
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("Érvénytelen könyvtár: " + directory.getAbsolutePath());
            return;
        }

        List<File> imageFiles = ImageFileProcessor.filterImageFiles(files);

        // Generálja az HTML-t az aktuális könyvtárhoz
        HtmlGenerator.generateHtml(directory, imageFiles);

        // Feldolgozza az almappákat
        for (File file : files) {
            if (file.isDirectory()) {
                generateImageGallery(file);
                System.out.println(file.getAbsolutePath());
            }
        }
    }
    
}
