import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CpuInfo {

    private Config configs = new Config();
    private String path;
    //Info
    private ArrayList<String> cpuInfo;

    //Freq
    private String maxFreq;
    private String minFreq;
    private String actFreq;

    //Temp
    private String maxTemp;
    private String minTemp;
    private String actTemp;

    public String getMaxFreq() {
        return maxFreq;
    }

    public String getMinFreq() {
        return minFreq;
    }

    public String getActFreq() {
        return actFreq;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getActTemp() {
        return actTemp;
    }

    public CpuInfo(){
        path = configs.getScript_path();
        cpuInfo = readCpuInfo(path);
        readCpufreq(path);
    }

    public ArrayList<String> getProcessorInfo(){
        return cpuInfo;
    }

    public void updateValue(){ readCpufreq(path); }


    //Cpu info
    private ArrayList<String> readCpuInfo(String path) {
        ArrayList<String> helper = new ArrayList<>();

        try {
            // Create process
            Process p = Runtime.getRuntime().exec(path+"getCpuInfo.sh");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            //Read process return
            String ret = in.readLine();

            while (ret != null){

                //Console log
                System.out.println("Script say : "+ret);
                //Create for return
                helper.add(ret);

                //Read all line
                ret = in.readLine();
            }


        } catch (IOException e) {
            //No script
            e.printStackTrace();
        }

        return helper;
    }


    //Cpu frequency
    private void readCpufreq(String path){

        try {
            // Create process
            Process p = Runtime.getRuntime().exec(path+"actualCpuFreq.sh");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            //Read process
            actFreq  = in.readLine();
            maxFreq  = in.readLine();
            minFreq  = in.readLine();


        } catch (IOException e) {
            //No script
            e.printStackTrace();
        }
    }
}
