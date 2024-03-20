package edu.uclm.es.sqe.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.es.sqe.model.Hamiltoniano;
import edu.uclm.es.sqe.services.CommonService;
import jakarta.servlet.http.HttpServletRequest;

public class CommonController {
    
    private CommonService commonService;

    public boolean validarToken(String token) throws IOException {
        boolean valido  = this.commonService.validarToken(token);
        if (valido){
            this.crearCarpeta(token);
        }
        return valido;
    }
    public String validarPeticion(HttpServletRequest req) {
        String token = req.getHeader("token");
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No tienes permiso para acceder a este recurso");
        }
        try{
            if(!validarToken(token)){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token invalido");
            }
        }
        catch(IOException e){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"El sistema de control de credenciales no esta disponible en este momento");
        }
        return token;
    }

    private void crearCarpeta(String token) {
        new File(this.getName(token)).mkdirs();
    }
    
    public String save(String token, Hamiltoniano h) throws FileNotFoundException, IOException{
        String FileName = this.getName(token) + "hamiltoniano.txt";
        try (FileOutputStream fos = new FileOutputStream(FileName)){
            fos.write(h.toString().getBytes());
        }
        return FileName;
    }
    public void saveCodigo(String token, String codigo) throws FileNotFoundException, IOException{
        String FileName = this.getName(token) + "codigo.py";
        try (FileOutputStream fos = new FileOutputStream(FileName)){
            fos.write(codigo.toString().getBytes());
        }
    }

    private String getName(String token) {
        String userPath = System.getProperty("user.home");
        if (!userPath.endsWith(File.separator)){
            userPath += File.separator;
        }
        userPath += "practicaDiseño" + File.separator;
        return userPath + token + File.separator;
    }
}
