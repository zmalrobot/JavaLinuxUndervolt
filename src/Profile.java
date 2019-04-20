import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;

public class Profile {

    private final JFrame frame;
    private Config configs = new Config();

    private HashMap<String, Double> returnValue;

    //Constructor
    public Profile(JFrame frame){
        this.frame = frame;
    }

    //Save data to file
    public void save(int coreSliderValue, int gpuSliderValue, int cacheSliderValue, int uncoreSliderValue, int analogioSliderValue){
        JFileChooser fileChooser = new JFileChooser(new File(configs.getSave_path())){
            @Override
            protected JDialog createDialog( Component parent ) throws HeadlessException {
                JDialog dialog = super.createDialog( parent );
                try {
                    dialog.setIconImage(ImageIO.read(new File(configs.getImage_path()+"icosave.png")));
                } catch (IOException e) {
                    System.out.println("No icon found");
                }
                return dialog;
            }
        };

        fileChooser.setDialogTitle("Save profile");

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


    //Read data from file
    public HashMap<String, Double> read(){

        returnValue = new  HashMap<String, Double>();

        JFileChooser fileChooser = new JFileChooser(new File(configs.getSave_path())){
            @Override
            protected JDialog createDialog( Component parent ) throws HeadlessException {
                JDialog dialog = super.createDialog( parent );
                try {
                    dialog.setIconImage(ImageIO.read(new File(configs.getImage_path()+"icoload.png")));
                } catch (IOException e) {
                    System.out.println("No icon found");
                }
                return dialog;
            }
        };

        fileChooser.setApproveButtonText("Load");

        int userSelection = fileChooser.showOpenDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            readfile(file);
            return returnValue;
        } else{
            returnValue.put("errors", 0.0);
            return returnValue;
        }

    }

    //Read the selected file
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

    //Create the hashmap (i's a duplicate forn now form undervolt class, i'll remove soon or later)
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
