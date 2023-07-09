package util;

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
}
