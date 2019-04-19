import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;

public class Profile {

    private final JFrame frame;
    private Config configs = new Config();

    private HashMap<String, Double> returnValue;

    public Profile(JFrame frame){
        this.frame = frame;
    }

    public void save(int coreSliderValue, int gpuSliderValue, int cacheSliderValue, int uncoreSliderValue, int analogioSliderValue){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save profile");
        fileChooser.setCurrentDirectory(new File(configs.getSave_path()));

        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            try {
                Date today = new Date();
                FileWriter fw = new FileWriter(fileChooser.getSelectedFile()+".txt");
                fw.write("#Setting profile data: " + today);
                fw.write("\n");
                fw.write("core: " + coreSliderValue+".0 mV");
                fw.write("\n");
                fw.write("gpu: " + gpuSliderValue+".0 mV");
                fw.write("\n");
                fw.write("cache: " + cacheSliderValue+".0 mV");
                fw.write("\n");
                fw.write("uncore: " + uncoreSliderValue+".0 mV");
                fw.write("\n");
                fw.write("analogio: " + analogioSliderValue+".0 mV");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, Double> read(){

        returnValue = new  HashMap<String, Double>();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load profile");
        fileChooser.setCurrentDirectory(new File(configs.getSave_path()));

        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            readfile(file);
        }

        System.out.println(returnValue);
        return returnValue;

    }

    private void readfile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));

            //Ignore the first row (date) or it make KABOOOM!!!
            String line = reader.readLine();
            System.out.println(line);

            //Now let's learn how to read ;-)
            line = reader.readLine();
            while (line != null) {
                 System.out.println(line);
                 addToHasmap(line);
                 line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
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
        System.out.println("Adding: " + name + " Value: " + value );
        returnValue.put(name, Double.parseDouble(value));

    }
}
