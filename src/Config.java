import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Config {

    //Read the config file

    private String undervolt_path;
    private String save_path;
    private String image_path;
    private String script_path;

    public Config(){
        readConfigFile();
    }

    private void readConfigFile(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("resource/config.txt"));
            String line = reader.readLine();
            while (line != null) {
               // System.out.println(line);

                if(line.contains("undervolt_path")){
                    String value = getValue(line);
                    undervolt_path = value;
                }

                if(line.contains("save_path")){
                    String value = getValue(line);
                    save_path = value;
                }

                if(line.contains("image_path")){
                    String value = getValue(line);
                    image_path = value;
                }

                if(line.contains("script_path")){
                    String value = getValue(line);
                    script_path = value;
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getValue(String line) {
        line = line.replace(" ","");
        String path[] = line.split("=");

        return path[1];
    }

    public String getUndervolt_path() {
        System.out.println("Undervolt path: " + undervolt_path);
        return undervolt_path;
    }

    public String getSave_path() {
        System.out.println("Save path: " + save_path);
        return save_path;
    }

    public String getImage_path() {
        System.out.println("Image path: " + image_path);
        return image_path;
    }

    public String getScript_path() {
        System.out.println("String path: " + script_path);
        return script_path;
    }
}
