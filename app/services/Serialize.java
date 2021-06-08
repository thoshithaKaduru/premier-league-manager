package services;
//TODO: match and club wrappers need to be turned into FootballClubs and

import java.io.*;
import java.util.Map;

public class Serialize {
    // No need to close Output and Input streams since Java Try with Resources is used. It handles those operations automatically
    public boolean saveMapObject(
            Map<Integer, ?> mapObject,
            String fileName
    ) {
        int count = 0; // count is for trying again if I/O exception is encountered
        while (count < 3) {
            count ++;
            try ( FileOutputStream fileOut = new FileOutputStream(fileName);
                  ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {

                // writing the number of items to Text file
                objOut.writeInt(mapObject.size());
                objOut.writeObject(mapObject);
                // Save success
                return true;

            }  catch (FileNotFoundException e) {
                // File not found... can occur when file was deleted ryt after creation before saving
                return false;
            } catch (IOException e) {
                System.out.println("Ran into an I/O exception when saving file" + fileName);
                System.out.println("trying one more time in another 2 seconds");
                try {
                    // awaiting before trying again for one more time
                    Thread.sleep(2000);
                } catch (InterruptedException interruptedException) {
                    System.out.println("couldn't await read controller");
                    System.out.println("trying immediately");
                }
            }
        }
        return false;
    }
    // No need to close Output and Input streams since Java Try with Resources is used. It handles those operations automatically
    public Map<Integer, ?> readMapObject(String fileName) {
        Map<Integer, ?> mapObj = null;
        int count = 0;

        while (count < 3 || mapObj == null) {
            count++;
            try( FileInputStream fileIn = new FileInputStream(fileName);
                 ObjectInputStream objIn = new ObjectInputStream(fileIn)) {

                // reads no of objects written in save operation.
                // can be useful in future requirements
                int noOfStartingObjects = objIn.readInt();
                mapObj = (Map<Integer, ?>) objIn.readObject();
                return mapObj;

            } catch (FileNotFoundException e) {
                // This occurs when there is no saved data yet
                return null;
            } catch (IOException e) {
                System.out.println("I/O exception... trying again in another 3 seconds");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interruptedException) {
                    System.out.println("couldn't await read controller");
                }
            } catch (ClassNotFoundException e) {
                System.out.println(fileName + "class not found in save file");
                return null;
            }
        }
        return null;
    }
}