import java.io.*;
import java.util.HashMap;

//TODO
// 1 - Controllo se viene applicato correttamente o se da errore

public class UndervoltValue {

    private HashMap<String, Double> returnValue  = new HashMap<String, Double>();

    public UndervoltValue(){ }

    public HashMap<String, Double> getValue(){

        runScript("--read", true);

        return returnValue;
    }

    public boolean setValue(int core, int gpu, int cache, int uncore, int analogio){

        String undervolt = "--gpu "+gpu+" --core "+core+" --cache "+cache+" --uncore "+uncore+" --analogio "+analogio+"";

        System.out.println("Applying: " + undervolt);

        runScript(undervolt, false);

        return false;
    }

    private void runScript(String param, boolean read){

        //Path and script name
        String path = "sudo externaltools/";
        String script = "undervolt.py ";

        try {
            // Create process
            System.out.println("Eseguo: " + path+script+param);
            Process p = Runtime.getRuntime().exec(path+script+param);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            //Read process return
            String ret = in.readLine();

            if(ret == null && read){
                //No permission or no script
                System.out.println("Errore nell'eseczuione dello script");
                returnValue.put("errors", 1.0);
            }else{
                //Read all return data form the second line
                ret = in.readLine();

                while (ret != null){

                    //Console log
                    System.out.println("Script say : "+ret);

                    //Create hasmap for return
                    addToHasmap(ret);

                    //Read all line
                    ret = in.readLine();
                }
            }

        } catch (IOException e) {
            //No script
            e.printStackTrace();
        }
    }

    private void addToHasmap(String scrptString){

        //String preparation
        scrptString = scrptString.replace("mV", "");
        scrptString = scrptString.replace(" ", "");
        String[] parts = scrptString.split(":");
        String name  = parts[0]; // Core
        String value = parts[1]; // 0.0

        //Add to hashmap
        System.out.println("Adding: " + name + " " + value );
        returnValue.put(name, Double.parseDouble(value));

    }
}
