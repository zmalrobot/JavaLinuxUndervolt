import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Faq {

    private Config configs = new Config();

    private JPanel FaqMainPanel;
    private JTextPane faq;
    private  JFrame frame;

    public Faq(){
        faq.setText("<html>"+
                "<p><strong>Faq: </strong></p>" +
                "<ol>" +
                "<li>Why does the cpu go to maximum when I apply the settings?<br />" +
                "<ol>" +
                "<li>Because a 10 second test is performed&nbsp; to ensure the stability of the system</li>" +
                "</ol>" +
                "</li>" +
                "<li>Why my cpu is not supported?<br />" +
                "<ol>" +
                "<li> Depends on the architecture and implementation of the manufacturer, for more information visit the developer page diundervolt.py (see below )</li>" +
                "</ol>" +
                "</li>" +
                "<li>I have and AMD processor why does not work?<br />" +
                "<ol>" +
                "<li>Because the script that i'm using is for INTEL cpu only ( <a href='https://github.com/mihic/linux-intel-undervolt'>Script faq</a>)</li>" +
                "</ol>" +
                "</li>" +
                "</ol>" +
                "<p><strong>Error code: </strong></p>" +
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
                "<p><strong>Bug: </strong></p>" +
                "</ul>"   +
                "<li>Override save will create a double extension (like Profile1.txt.txt)</li>" +
                "<li>I'm sure i've miss some... if you find one.. or two... or more just tell me ;-) </li>" +
                "</ul>"   +
                "<p><strong>Thanks to: </strong></p>" +
                "</ul>"   +
                "<li>Georgewhewell and all his contributors for undervolt.py</li>" +
                "<li><a href='https://github.com/georgewhewell/undervolt'>https://github.com/georgewhewell/undervolt</a></li>" +
                "<li>Caffinc for the cpu stress class</li>" +
                "<li><a href='https://caffinc.github.io/2016/03/cpu-load-generator'>https://caffinc.github.io/2016/03/cpu-load-generator</a></li>" +
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
