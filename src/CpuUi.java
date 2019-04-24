import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CpuUi {

    private Config configs = new Config();
    private CpuInfo processorInfo = new CpuInfo();

    private JFrame frame;
    private JPanel jframe;
    private JTextPane txtCpuInfo;
    private JButton stressCPU;
    private JTextPane txtCpuStatus;

    public CpuUi(){

        //Processor info
        ArrayList<String> processor = processorInfo.getProcessorInfo();
        /*String helper = "<html><p><strong>Processo info:</strong></p>";

        for (String info : processor) {
            String[] tmp = info.split(":");

            helper = helper + "<p><strong>"+tmp[0]+": </strong>"+tmp[1]+"</p>";
        }
        helper = helper + "</html>";

        txtCpuInfo.setText(helper);*/

        txtCpuInfo.setText(processorInfoTable(processor));

        updateStatusLoop();
    }

    public String processorInfoTable(ArrayList<String> processor){
        String ret ="<html>";
        ret = ret + "<p>Cpu info: </p>";
        ret = ret +
                "<table>"+
                "<tr>"+
                    "<td>"+ processor.get(0)+"</td>"+
                    "<td>"+ processor.get(1) +"</td>"+
                    "<td>"+ processor.get(2) +"</td>"+
                "</tr>"+
                "<tr>"+
                    "<td>"+ processor.get(3)+"</td>"+
                    "<td>"+ processor.get(4) +"</td>"+
                    "<td>"+ processor.get(5) +"</td>"+
                "</tr>"+
                "<tr>"+
                    "<td>"+ processor.get(6)+"</td>"+
                    "<td>"+ processor.get(7) +"</td>"+
                    "<td>"+ processor.get(8) +"</td>"+
                "</tr>"+
                "<tr>"+
                    "<td>"+ processor.get(9)+"</td>"+
                    "<td>"+ processor.get(10) +"</td>"+
                    "<td>"+ processor.get(11) +"</td>"+
                "</tr>"+
                "<tr>"+
                    "<td>"+ processor.get(12)+"</td>"+
                    "<td>"+ processor.get(13) +"</td>"+
                    "<td>"+ processor.get(14) +"</td>"+
                "</tr>"+
                "<tr>"+
                    "<td>"+ processor.get(15)+"</td>"+
                    "<td>"+ processor.get(16) +"</td>"+
                    "<td>"+ processor.get(17) +"</td>"+
                "</tr>"+
                "<tr>"+
                    "<td>"+ processor.get(18)+"</td>"+
                    "<td>"+ processor.get(19) +"</td>"+
                    "<td>"+ processor.get(20) +"</td>"+
                "</tr>"+
                "<tr>"+
                    "<td>"+ processor.get(21)+"</td>"+
                    "<td>"+ processor.get(22) +"</td>"+
                    "<td>"+ processor.get(23) +"</td>"+
                "</tr>"+
                "</table>";
        ret = ret + "<p>"+processor.get(24)+"</p>";
        ret = ret + "</html>";
        return ret;
    }

    public void updateStatusLoop(){
        processorInfo.updateValue();

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
                            "<tr>"+
                                "<td>"+ processorInfo.getActTemp() +"</td>"+
                                "<td>"+ processorInfo.getMinTemp() +"</td>"+
                                "<td>"+ processorInfo.getMaxTemp() +"</td>"+
                            "</tr>"+
                        "</table>"+
                         "</html>";
        txtCpuStatus.setText(cpuStatus);
    }

    //ONLY FOR FIRST INSTANCE
    public void createUi(){

        UIManager.getDefaults().put("Button.disabledText", Color.decode("#EAB23B"));
        frame = new JFrame("CpuInfo");
        frame.setContentPane(new CpuUi().jframe);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            frame.setIconImage(ImageIO.read(new File(configs.getImage_path()+"icoover.png")));
        } catch (IOException e) {
            System.out.println("No icon found");
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    //END INSTANZE
}

