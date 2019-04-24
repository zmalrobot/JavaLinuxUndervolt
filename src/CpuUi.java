import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CpuUi {

    private Config configs = new Config();
    private CpuInfo processorInfo = new CpuInfo();

    private JFrame frame;
    private JPanel jframe;
    private JTextPane txtCpuInfo;
    private JTextPane txtCpuStatus;
    private JProgressBar cpuLoadBar;
    private JButton btnStress;

    public CpuUi(){

        //Processor info
        ArrayList<String> processor = processorInfo.getProcessorInfo();

        txtCpuInfo.setText(processorInfoTable(processor));

        btnStress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Stresstest stress = new Stresstest();
                stress.startStress();
            }
        });

        backgrloundLoop();
    }

    private void backgrloundLoop() {
        UpdateCpuStatus loop = new UpdateCpuStatus(txtCpuStatus, cpuLoadBar);
        loop.execute();
    }

    public String processorInfoTable(ArrayList<String> processor){
        String ret ="<html>";
        ret = ret + "<p><strong>Cpu info:</strong></p>";
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
        ret = ret + "<p>"+processor.get(25)+"</p>";
        ret = ret + "</html>";
        return ret;
    }

    //ONLY FOR FIRST INSTANCE
    public void createUi(){

        UIManager.getDefaults().put("Button.disabledText", Color.decode("#EAB23B"));
        frame = new JFrame("CpuInfo");
        frame.setContentPane(new CpuUi().jframe);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            frame.setIconImage(ImageIO.read(new File(configs.getImage_path()+"icocpu.png")));
        } catch (IOException e) {
            System.out.println("No icon found");
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    //END INSTANZE
}

