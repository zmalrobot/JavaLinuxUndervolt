import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

public class Interface {
    private JFrame frame;
    private JPanel MainPanel;
    private JSlider analogioSlider;
    private JSlider uncoreSlider;
    private JSlider cacheSlider;
    private JSlider gpuSlider;
    private JSlider coreSlider;
    private JButton applybutton;
    private JLabel valueCore;
    private JLabel valueGpu;
    private JLabel valueCache;
    private JLabel valueUncore;
    private JLabel valueAnalogio;
    private JButton faqbutton;
    private JButton resetbutton;
    private JButton buttonLoad;
    private JButton buttonSave;

    private int coreSliderValue;
    private int gpuSliderValue;
    private int cacheSliderValue;
    private int uncoreSliderValue;
    private int analogioSliderValue;

    private Config configs = new Config();
    private Profile profile = new Profile(frame);
    private UndervoltValue value = new UndervoltValue();
    private Stresstest stresstest = new Stresstest();



    // Set the listener and the actual params
    public Interface(){

        //LISTENER

        //Cpu voltage
        coreSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //Set the value from the slider
                coreSliderValue = coreSlider.getValue();

                //Set te label for the slider
                setLabel(coreSliderValue, valueCore);

            }
        });

        //Gpu voltage
        gpuSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //Set the value from the slider
                gpuSliderValue = gpuSlider.getValue();

                //Set te label for the slider
                setLabel(gpuSliderValue, valueGpu);
            }
        });

        //Cache voltage
        cacheSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //Set the value from the slider
                cacheSliderValue = cacheSlider.getValue();

                //Set te label for the slider
                setLabel(cacheSliderValue, valueCache);
            }
        });

        //Cache uncore
        uncoreSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //Set the value from the slider
                uncoreSliderValue = uncoreSlider.getValue();

                //Set te label for the slider
                setLabel(uncoreSliderValue, valueUncore);
            }
        });

        //Cache analogio
        analogioSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                //Set the value from the slider
                analogioSliderValue = analogioSlider.getValue();

                //Set te label for the slider
                setLabel(analogioSliderValue, valueAnalogio);
            }
        });

        //Button listener
        applybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                set();
            }
        });

        resetbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               reset();
            }
        });

        faqbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Faq faq = new Faq();
                faq.createUi();
            }
        });

        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                read();
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                save();
            }
        });

        //Set actual value when application start
        setUiValue();

        //END LISTENER
    }

    // Ask to read the profile
    private void read() {
        HashMap<String, Double> prof = profile.read();
        if(!prof.containsKey("errors")){
            setSliderValue(prof);
        }
    }

    // Save the profile
    private void save() {
        profile.save(coreSliderValue, gpuSliderValue, cacheSliderValue, uncoreSliderValue, analogioSliderValue);
    }


    //Set actual slider value as undervolt
    private void set() {
        //First i disable the ui
        applybutton.setText("Applying change, please wait...");
        setEnableUi(false);

        //Set value
        boolean correct = value.setValue(coreSliderValue, gpuSliderValue, cacheSliderValue, uncoreSliderValue, analogioSliderValue);

        if(correct){
            //Refresh ui value
            setUiValue();

            try {
                //Stress to see if it stable
                stresstest.setStressMaxCore(10000);
                stresstest.startStress();

                //Wait for the stress test ending
                Thread.sleep(11000);

            } catch (InterruptedException e) {
                System.out.println("Stress test skipped");
            }

            //Re enable all ui
            applybutton.setText("Applied!");
            setEnableUi(true);

        }else{
            generateError(2);
        }
    }

    // Set 0 to all value
    private void reset() {
        //First i disable the ui
        applybutton.setText("Applying change, please wait...");
        setEnableUi(false);

        //Set value
        boolean correct = value.setValue(0, 0, 0, 0, 0);

        if(correct){
            //Refresh ui value
            setUiValue();

            //Re enable all ui
            applybutton.setText("Restored!");
            setEnableUi(true);
        }else{
            generateError(2);
        }
    }

    //ONLY FOR FIRST INSTANCE
    public void createUi(){

        UIManager.getDefaults().put("Button.disabledText",Color.decode("#EAB23B"));
        frame = new JFrame("Undervolt");
        frame.setContentPane(new Interface().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    //Enable and disable de ui minus faq button
    private void setEnableUi(boolean enable){
        coreSlider.setEnabled(enable);
        gpuSlider.setEnabled(enable);
        cacheSlider.setEnabled(enable);
        uncoreSlider.setEnabled(enable);
        analogioSlider.setEnabled(enable);
        applybutton.setEnabled(enable);
        resetbutton.setEnabled(enable);
    }


    //Called after set value
    private void setUiValue() {
        //Get the data from undervolt.py
        HashMap<String, Double> valueHashmap = value.getValue();

        setSliderValue(valueHashmap);
    }

    //Set the value from the hashmap to the ui
    private void setSliderValue( HashMap<String, Double> valueHashmap){

        if(valueHashmap.containsKey("errors")){
            //If there is something wrong with the readings
            generateError(valueHashmap.get("errors").intValue());
        }else{
            //Set actual core value
            coreSlider.setValue(valueHashmap.get("core").intValue());
            setLabel(valueHashmap.get("core").intValue(), valueCore);

            //Set actual gpu value
            gpuSlider.setValue(valueHashmap.get("gpu").intValue());
            setLabel(valueHashmap.get("gpu").intValue(), valueGpu);

            //Set actual cache value
            cacheSlider.setValue(valueHashmap.get("cache").intValue());
            setLabel(valueHashmap.get("cache").intValue(), valueCache);

            //Set actual uncore value
            uncoreSlider.setValue(valueHashmap.get("uncore").intValue());
            setLabel(valueHashmap.get("uncore").intValue(), valueUncore);

            //Set actual analogio value
            analogioSlider.setValue(valueHashmap.get("analogio").intValue());
            setLabel(valueHashmap.get("analogio").intValue(), valueAnalogio);
        }

    }

    //Create the label from the value
    private void setLabel(int value, JLabel label){

        String labeltext = Integer.toString(value) + " mV";

        if(value>0){
            labeltext = '+'+labeltext;
        }

        label.setText(labeltext);
    }

    //Show error
    public void generateError(int errorCode){

        //Error reference:

        // Code: 1.0 --> Class: Undervolt.java --> Method: runScript
        // Solution: Application start with no sudo permission or something is wrong with undervolt.py file

        // Code: 2.0 --> Class: Undervolt.java --> Method: setValue
        // Solution: Error during setting voltage, pc not compatible or something is wrong with undervolt.py file

        setEnableUi(false);
        applybutton.setText("Error code: " + errorCode);
    }

}
