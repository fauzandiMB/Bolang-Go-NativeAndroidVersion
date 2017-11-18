package model;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by arifb on 19-Nov-17.
 */

public final class DataManager {
    public static boolean saveGameInfo(GameInfo gameInfo, String filename, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(gameInfo);
            os.close();
            fos.close();
            Log.d("DataManager","Save Game Successed");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static GameInfo loadGameInfo(String filename, Context context){
        GameInfo gameInfo = null;
        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream is = new ObjectInputStream(fis);
            gameInfo = (GameInfo) is.readObject();
            is.close();
            fis.close();
            Log.d("DataManager","Success Load Game");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameInfo;
    }

    public static boolean hasGameInfo(String filename, Context context){
        try{
            FileInputStream fis = context.openFileInput(filename);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
