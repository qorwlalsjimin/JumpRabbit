package panel;

import JumpRabbit.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomTextField extends JTextField {
    private static final long serialVersionUID = 1L;
    private Image img;
    private CustomTextField hintTextField;

    public CustomTextField() {
        hintTextField = this;
        setBorder(new EmptyBorder(20, 20, 10, 3));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img, 0, 0, this);
    }


    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0,0,0,0));
        //g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15); //모서리 둥글게
    }

    public void setBackgroundImage(String imPath){
        img = new ImageIcon(imPath).getImage();
        setOpaque(false);
    }

    public void setHint(String hint){
        hintTextField.setText(hint);
        hintTextField.setFont(Main.font.deriveFont(20f));
        hintTextField.setForeground(Color.GRAY);
        hintTextField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (hintTextField.getText().equals(hint)) {
                    hintTextField.setText("");

                } else {
                    hintTextField.setText(getText());
                }
                hintTextField.setFont(Main.font.deriveFont(20f));
                hintTextField.setForeground(Color.blue);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ( hintTextField.getText().equals(hint) || getText().length() == 0) {
                    hintTextField.setText(hint);
                    hintTextField.setFont(Main.font.deriveFont(20f));
                    hintTextField.setForeground(Color.GRAY);
                } else {
                    hintTextField.setText(getText());
                    hintTextField.setFont(Main.font.deriveFont(20f));
                    hintTextField.setForeground(Color.BLACK);
                }
            }
        });
    }
}
