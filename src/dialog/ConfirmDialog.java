package dialog;

import JumpRabbit.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmDialog {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public ConfirmDialog(String message) {

        JLabel displayText = new JLabel("! "+message);
        displayText.setFont(Main.font.deriveFont(20f));
        displayText.setForeground(Color.white);
        displayText.setHorizontalAlignment(JLabel.CENTER);
        displayText.setVerticalTextPosition(SwingConstants.CENTER);
        displayText.setBounds(0,0,380,110);

        JButton btn = new JButton("확인");
        btn.setFont(Main.font.deriveFont(20f));
        btn.setForeground(Main.defaultColor);
        btn.setBackground(Color.white);
        btn.setBounds(150,95,80,40);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFB7E0"));
        panel.add(displayText);
        panel.add(btn);
        frame.add(panel);

        //* Frame 기본 설정
        frame.setTitle("Try to Login");
        frame.setSize(400, 220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
