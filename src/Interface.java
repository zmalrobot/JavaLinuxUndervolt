import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Interface {
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

    private int coreSliderValue;
    private int gpuSliderValue;
    private int cacheSliderValue;
    private int uncoreSliderValue;
    private int analogioSliderValue;

    private UndervoltValue value = new UndervoltValue();


    public Interface(){

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
                //First i disable the ui
                applybutton.setText("Applying change, please wait...");
                setEnableUi(false);

                //Set value
                value.setValue(coreSliderValue, gpuSliderValue, cacheSliderValue, uncoreSliderValue, analogioSliderValue);

                //Refresh ui value
                setUiValue();

                //Re enable all ui
                applybutton.setText("Apply");
                setEnableUi(true);

            }
        });

        //Set actual value when application start
        setUiValue();
    }

    //ONLY FOR FIRST INSTANCE
    public void createUi(){
        UIManager.getDefaults().put("Button.disabledText",Color.decode("#EAB23B"));
        JFrame frame = new JFrame("Undervolt");
        frame.setContentPane(new Interface().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    //END INSTANZE


    private void setEnableUi(boolean enable){
        coreSlider.setEnabled(enable);
        gpuSlider.setEnabled(enable);
        cacheSlider.setEnabled(enable);
        uncoreSlider.setEnabled(enable);
        analogioSlider.setEnabled(enable);
        applybutton.setEnabled(enable);
    }

    private void setUiValue(){
        //Get the data from undervolt.py
        HashMap<String, Double> valueHashmap = value.getValue();

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

    private void setLabel(int value, JLabel label){

        String labeltext = Integer.toString(value) + " mV";

        if(value>0){
            labeltext = '+'+labeltext;
        }

        label.setText(labeltext);
    }

    public void generateError(int errorCode){

        //Error reference:

        // Code: 1.0 --> Class: Undervolt.java --> Method: runScript
        // Solution: Application start with no sudo permission or something is wrong with undervolt.py file

        setEnableUi(false);
        applybutton.setText("Error code: " + errorCode);
    }

}
