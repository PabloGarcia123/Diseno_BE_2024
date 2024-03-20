package edu.uclm.es.sqe.src.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PruebaSerial {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        FileInputStream fis1 = new FileInputStream("/Users/Desktop/pepe.obj");
        ObjectInputStream ois1 = new ObjectInputStream(fis1);
        Persona pepe = (Persona) ois1.readObject();
        ois1.close();
        /* 
        Persona pepe = new Persona("Pepe", "Perez");
        Persona ana = new Persona("Ana", "Lopez");

        List<Persona> personas = new ArrayList<>();
        personas.add(pepe);
        personas.add(ana);

        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("/Users/Desktop/pepe.obj"));
        oos1.writeObject(pepe);
        oos1.close();

        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("/Users/Desktop/ana.obj"));
        oos2.writeObject(ana);
        oos2.close();

        ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("/Users/Desktop/personas.obj"));
        oos3.writeObject(personas);
        oos3.close();
        */
    }

    
}
class Persona {
    private String n;
    private String a;

    public Persona(String n, String a) {
        this.n = n;
        this.a = a;
    }
}
