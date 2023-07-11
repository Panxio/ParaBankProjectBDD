package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class abstractMethods {

    public String randomUserNumber(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return dateTime.format(formatter);
    }

    public String getUserNameRegister(String name){
        // Dividir el texto en palabras separadas por espacios
        String[] words = name.split(" ");
        //Obtengo y devuelvo la última palabra
        return words[words.length - 1];
    }

    public static void captureScreenshot(WebDriver driver, String filePath) {
        // Castear el driver a TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Obtener el archivo de la pantalla como OutputType.FILE
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            // Crear un directorio si no existe
            File directory = new File(filePath).getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Obtener la fecha y hora actual para incluirla en el nombre del archivo
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);

            // Definir el nombre completo del archivo con la fecha y hora
            String fileName = "screenshot_" + timestamp + ".png";

            // Definir la ubicación de destino del archivo
            File destinationFile = new File(filePath + "/" + fileName);

            // Copiar el archivo capturado a la ubicación de destino
            sourceFile.renameTo(destinationFile);

            System.out.println("Screenshot capturado: " + destinationFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
