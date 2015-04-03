package edu.gvsu.cis.lafeldtb.snapjudgement;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Josh Techentin on 4/1/2015.
 */

// This class is used to save games

public class Serializer {

    public void serializeGame(Game g){
        try{

            FileOutputStream fout = new FileOutputStream("c:\\current games.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(g);
            oos.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
