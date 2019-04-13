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
                applybutton.setBackground(Color.decode("#A33C48"));
                setEnableUi(false);


                //setUiValue();
            }
        });

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

    private void setLabel(int value, JLabel label){

        String labeltext = Integer.toString(value);

        if(value>0){
            labeltext = '+'+labeltext;
        }

        label.setText(labeltext);
    }

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
        UndervoltValue value = new UndervoltValue();
        HashMap<String, Integer> valueHashmap = value.getValue();


        //Set actual core value
        coreSlider.setValue(valueHashmap.get("cpu"));
        valueCore.setText(valueHashmap.get("cpu").toString());

        //Set actual gpu value
        gpuSlider.setValue(valueHashmap.get("gpu"));
        valueGpu.setText(valueHashmap.get("gpu").toString());

        //Set actual cache value
        cacheSlider.setValue(valueHashmap.get("cache"));
        valueCache.setText(valueHashmap.get("cache").toString());

        //Set actual uncore value
        uncoreSlider.setValue(valueHashmap.get("uncore"));
        valueUncore.setText(valueHashmap.get("uncore").toString());

        //Set actual analogio value
        analogioSlider.setValue(valueHashmap.get("analogio"));
        valueAnalogio.setText(valueHashmap.get("analogio").toString());

    }

}
