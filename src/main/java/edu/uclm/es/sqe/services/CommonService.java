package edu.uclm.es.sqe.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

@Service
public class CommonService {

    public boolean validarToken(String token) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url("http://localhost:9000/validarToken?token=" + token)
            .build();
        try (Response response = client.newCall(request).execute()) {
            return (response.code() == 200);
        }
    }
    
}
