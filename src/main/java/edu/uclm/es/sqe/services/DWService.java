package edu.uclm.es.sqe.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DWService {
    
    public String generarCodigo(int [][] matriz) throws Exception {
        String template = this.read("dwaveTemplate.txt");

        StringBuilder sb = new StringBuilder("x = [");
        for (int i = 0; i < matriz.length; i++) {
            sb.append("\"x" + i + "\",");

        }
        sb.append("]");
        String lineax = sb.toString();

        int poRules = template.indexOf("#RULES#");
        String inicio = template.substring(0, poRules);

        String fin = template.substring(poRules);

        String inicializacionMatriz = "...";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = i; j < matriz.length; j++) {
                inicializacionMatriz += matriz[i][j] + " ";
            }
            inicializacionMatriz += "\n";
        }
        
        String code  = inicio + lineax + inicializacionMatriz + fin;
        return code;
    }

    private String read(String fileName) throws FileNotFoundException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream fis = classLoader.getResourceAsStream(fileName)) {
            byte[] b = new byte[fis.available()];
            fis.read(b);
            String s = new String(b);
            return s;
        }
    }
}
