import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Faq {

    private Config configs = new Config();

    private JPanel FaqMainPanel;
    private JTextPane faq;
    private  JFrame frame;

    public Faq(){
        faq.setText("<html>"+
                "<p><strong>Faq</strong></p>" +
                "<ol>" +
                "<li>Why does the cpu go to maximum when I apply the settings?<br />" +
                "<ol>" +
                "<li>Because a 10 second test is performed&nbsp; to ensure the stability of the system</li>" +
                "</ol>" +
                "</li>" +
                "</ol>" +
                "<p><strong>Error code</strong></p>" +
                "<ul>" +
                "<li>&nbsp;Code: 1.0 --&gt; Class: Undervolt.java --&gt; Method: runScript" +
                "<ul>" +
                "<li>Solution: Application start with no sudo permission or something is wrong with undervolt.py file</li>" +
                "</ul>" +
                "</li>" +
                "<li>Code: 2.0 --&gt; Class: Undervolt.java --&gt; Method: setValue" +
                "<ul>" +
                "<li>Solution: Error during setting voltage, pc not compatible or something is wrong with undervolt.py file</li>" +
                "</ul>" +
                "</li>" +
                "</ul>"   +
                "</html>"
        );

    }

    //ONLY FOR FIRST INSTANCE
    public void createUi(){
        UIManager.getDefaults().put("Button.disabledText", Color.decode("#EAB23B"));
        frame = new JFrame("Faq and error code");
        frame.setContentPane(new Faq().FaqMainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            frame.setIconImage(ImageIO.read(new File(configs.getImage_path()+"icofaq.png")));
        } catch (IOException e) {
            System.out.println("No icon found");
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    //END INSTANZE

}
