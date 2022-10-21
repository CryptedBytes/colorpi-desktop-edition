import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class mainGUI extends JFrame {
public JPanel mainPane;
    private JButton generateRandomColorButton;
    private JSlider slider_RED;
    private JSlider slider_GREEN;
    private JSlider slider_BLUE;
    private JLabel valueLabel_RED;
    private JLabel valueLabel_GREEN;
    private JLabel valueLabel_BLUE;
    private JLabel hexLabel;
    private JLabel HEXValueInfoLabel;
    private JLabel redLabel;
    private JLabel greenLabel;
    private JLabel blueLabel;
    public static JFrame mainframe;
    public boolean dynReqDARK = false;

    public mainGUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPane);
        this.setPreferredSize(new Dimension(400,500));
        this.pack();


        slider_RED.setValue(generateRandomInt(255));
        slider_GREEN.setValue(generateRandomInt(255));
        slider_BLUE.setValue(generateRandomInt(255));
        updateSliderValueDisplay();
        changeColorNow();
        updateHEXValue();

        slider_RED.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeColorNow();
                updateHEXValue();
                updateSliderValueDisplay();
            }
        });
        slider_GREEN.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeColorNow();
                updateHEXValue();
                updateSliderValueDisplay();
            }
        });
        slider_BLUE.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeColorNow();
                updateHEXValue();
                updateSliderValueDisplay();
            }
        });

        hexLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int r = slider_RED.getValue();
                int g = slider_GREEN.getValue();
                int b = slider_BLUE.getValue();
                JOptionPane.showMessageDialog(mainframe, String.format("RGB COLOR:\nR:%d G:%d B:%d\nHEX COLOR CODE:\n%s",r,g,b, hexLabel.getText()),"Color Information", JOptionPane.PLAIN_MESSAGE);
            }
        });
        generateRandomColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomizeBackgroundColor();
            }
        });
    }

    public static void main(String[] args){
        mainframe = new mainGUI("ColorPi - Java Edition");
        mainframe.setVisible(true);
    }

    public void changeColorNow(){
        Color userPreferredColor = new Color(slider_RED.getValue(), slider_GREEN.getValue(), slider_BLUE.getValue());
        mainPane.setBackground(userPreferredColor);
        dynamicallyAdaptUIColor();
    }


    public void updateHEXValue(){
        String hex_r = Integer.toHexString(slider_RED.getValue());
        String hex_g = Integer.toHexString(slider_GREEN.getValue());
        String hex_b = Integer.toHexString(slider_BLUE.getValue());

        if(hex_r.length() < 2) hex_r = "0" + hex_r;
        if(hex_g.length() < 2) hex_g = "0" + hex_g;
        if(hex_b.length() < 2) hex_b = "0" + hex_b;


        hexLabel.setText(hex_r + hex_g + hex_b);
      //  hexLabel.setText(hexLabel.getText().toUpperCase());
    }

    public void updateSliderValueDisplay(){
        valueLabel_RED.setText(String.valueOf(slider_RED.getValue()) + "  ");
        valueLabel_GREEN.setText(String.valueOf(slider_GREEN.getValue()) + "  ");
        valueLabel_BLUE.setText(String.valueOf(slider_BLUE.getValue()) + "  ");
    }

    public int generateRandomInt(int max){
      Random rand = new Random();
      return rand.nextInt(max);
}

    public void randomizeBackgroundColor(){
          slider_RED.setValue(generateRandomInt(255));
          slider_GREEN.setValue(generateRandomInt(255));
          slider_BLUE.setValue(generateRandomInt(255));
          updateSliderValueDisplay();
          changeColorNow();
          updateHEXValue();
    }

    public void dynamicallyAdaptUIColor(){
        if (slider_RED.getValue() > 187) {

            if (slider_GREEN.getValue() > 200) {
                dynReqDARK = true;
            } else {
                dynReqDARK = false;
            }
        } else {

            if (slider_GREEN.getValue() > 211) {
                if (slider_BLUE.getValue() > 87) {
                    dynReqDARK = true;
                }
                else {
                    if (slider_GREEN.getValue() > 195) dynReqDARK = true;
                    else dynReqDARK = false;
                }
            }
            else {
                dynReqDARK = false;

            }
        }

        if(dynReqDARK){
            hexLabel.setForeground(Color.black);
            HEXValueInfoLabel.setForeground(Color.black);
            redLabel.setForeground(Color.black);
            greenLabel.setForeground(Color.black);
            blueLabel.setForeground(Color.black);
            valueLabel_RED.setForeground(Color.black);
            valueLabel_BLUE.setForeground(Color.black);
            valueLabel_GREEN.setForeground(Color.black);
        }
        else{
            hexLabel.setForeground(Color.white);
            HEXValueInfoLabel.setForeground(Color.white);
            redLabel.setForeground(Color.white);
            greenLabel.setForeground(Color.white);
            blueLabel.setForeground(Color.white);
            valueLabel_RED.setForeground(Color.white);
            valueLabel_BLUE.setForeground(Color.white);
            valueLabel_GREEN.setForeground(Color.white);
        }
    }
    private void createUIComponents() {

    }
}
