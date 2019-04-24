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
    private ArrayList<String> tempArray;

    //Load
    private String load;

    public String getMaxFreq() {
        return maxFreq;
    }

    public String getMinFreq() {
        return minFreq;
    }

    public String getActFreq() {
        return actFreq;
    }

    public ArrayList<String> getTemp() {
        return tempArray;
    }

    public String getload() {
        return load;
    }

    public CpuInfo(){
        //Read only once
        path = configs.getScript_path();
        cpuInfo = readCpuInfo(path);

        updateValue();
    }

    public ArrayList<String> getProcessorInfo(){
        return cpuInfo;
    }

    public void updateValue(){
        readCpufreq(path);
        readCputemp(path);
        readCpuload(path);
    }


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
                //System.out.println("Script say : "+ret);
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

    private String convertTemp(String temp, String label){

        //Set real value
        if(Double.parseDouble(temp) != 0){
            Double tempVal = Double.parseDouble(temp)/1000.00;
            temp = label+" " + tempVal.toString() + " °C";
        }else{
            temp = label+" " + temp + " C";
        }

        return temp;
    }

    //Cpu temperature
    private void readCputemp(String path){

        try {
            // Create process
            Process p = Runtime.getRuntime().exec(path+"actualCpuTemp.sh");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            //Read process
            tempArray = new ArrayList<String>();
            int count = 0;

            String ret = in.readLine();
            while (ret != null){

                //Console log
               // System.out.println("Script say : "+ret);
                //Create for return
                tempArray.add(convertTemp(ret, "T"+count));

                //Read all line
                count++;
                ret = in.readLine();
            }

        } catch (IOException e) {
            //No script
            e.printStackTrace();
        }
    }

    //Cpu load
    private void readCpuload(String path){

        try {
            // Create process
            Process p = Runtime.getRuntime().exec(path+"actualCpuLoad.sh");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            //Read process
            load  = in.readLine();


        } catch (IOException e) {
            //No script
            e.printStackTrace();
        }
    }
}
