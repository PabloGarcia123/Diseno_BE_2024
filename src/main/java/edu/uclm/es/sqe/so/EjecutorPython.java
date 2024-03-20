package edu.uclm.es.sqe.so;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EjecutorPython {

    public String ejecutar(String fileName) {
        System.out.println("Ejecutando " + fileName);
        //ruta del archivo python a ejecutar
        fileName = "";
        ProcessBuilder pb = new ProcessBuilder("python", fileName);

        String carpeta = fileName.substring(0, fileName.length() - 9);
        String salida = carpeta + "salida.txt";
        String errores = carpeta + "errores.txt";

        pb.directory(new File(carpeta));
        pb.redirectOutput(new File(salida));
        pb.redirectError(new File(errores));

        try {
            Process process = pb.start();
            process.waitFor();
            if(new File(errores).length() > 0)
                System.out.println("Errores en la ejecución: " + new File(errores).length());
            else
                System.out.println("Ejecución correcta. Salida en " + new File(salida).length() + " bytes");
                String respuesta;
                try (FileInputStream fis = new FileInputStream(salida)) {
                    byte[] bytes = new byte[fis.available()];
                    fis.read(bytes);
                    respuesta = new String(bytes);
                    System.out.println(respuesta);
                    return respuesta;
                }

            } catch (IOException | InterruptedException e) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "El sistema de ejecución de código no está disponible en este momento");
            }
        }
    
}
