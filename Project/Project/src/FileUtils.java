
import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
class FileUtils {

    static void deleteHtmlFiles(File file) {
        
         public static void deleteHtmlFiles(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteHtmlFiles(file);
                    } else if (file.getName().toLowerCase().endsWith(".html")) {
                        file.delete();
                        System.out.println("Deleted: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }
    }
    

