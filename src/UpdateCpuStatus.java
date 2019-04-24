import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateCpuStatus extends SwingWorker<Void, Integer> {

    private CpuInfo processorInfo = new CpuInfo();
    private JTextPane txtCpuStatus;
    private JProgressBar cpuLoadBar;

    public UpdateCpuStatus(JTextPane txtCpuStatus, JProgressBar cpuLoadBar){
        this.txtCpuStatus = txtCpuStatus;
        this.cpuLoadBar = cpuLoadBar;
        publish(1);
    }

    @Override
    protected Void doInBackground() throws Exception {

        while(true){
            publish(1);
            Thread.sleep(1000);
        }
    }

    @Override
    protected void process(List<Integer> chunk) {
            updateStatus();
    }

    public void updateStatus(){
        processorInfo.updateValue();

        ArrayList<String> temps = processorInfo.getTemp();

        //Cpu Freq and temperature
        String cpuStatus = "<html>" +
                "<p><strong>Cpu frequency:</strong></p>" +
                "<table>"+
                "<tr>"+
                "<td>"+ processorInfo.getActFreq() +"</td>"+
                "<td>"+ processorInfo.getMinFreq() +"</td>"+
                "<td>"+ processorInfo.getMaxFreq() +"</td>"+
                "</tr>"+
                "</table>"+
                "<p><strong>Cpu temperature:</strong></p>" +
                "<table>"+
                "<tr>";

        for (String temp : temps) {
            cpuStatus = cpuStatus + "<td>"+temp +"</td>";
        }

        cpuStatus = cpuStatus +
                "</tr>"+
                "</table>"+
                "</html>";

        txtCpuStatus.setText(cpuStatus);

        //Cpu load
        cpuLoadBar.setValue(Integer.parseInt(processorInfo.getload()));
    }
}
