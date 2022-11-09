package dialog;

import JumpRabbit.Main;
import panel.IntroPanel;
import panel.JoinNamePanel;
import panel.JoinPanel;
import panel.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutDialog {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton btnYes = new JButton("예");
    JButton btnNo = new JButton("아니요");

    public LogoutDialog() {

        JLabel displayText = new JLabel("! 로그아웃하시겠습니까?");
        displayText.setFont(Main.font.deriveFont(20f));
        displayText.setForeground(Color.white);
        displayText.setHorizontalAlignment(JLabel.CENTER);
        displayText.setVerticalTextPosition(SwingConstants.CENTER);
        displayText.setBounds(0,0,380,110);

        btnYes.setFont(Main.font.deriveFont(20f));
        btnYes.setForeground(Main.defaultColor);
        btnYes.setBackground(Color.white);
        btnYes.setBounds(100,95,80,40);

        btnNo.setFont(Main.font.deriveFont(20f));
        btnNo.setForeground(Main.defaultColor);
        btnNo.setBackground(Color.white);
        btnNo.setBounds(190,95,100,40);

        btnYes.addActionListener(btnListener);
        btnNo.addActionListener(btnListener);

        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFB7E0"));
        panel.add(displayText);
        panel.add(btnYes);
        panel.add(btnNo);
        frame.add(panel);

        //* Frame 기본 설정
        frame.setTitle("Try to Login");
        frame.setSize(400, 220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob = e.getSource();
            frame.setVisible(false);

            if(ob == btnYes){
                IntroPanel.isLogin = false;
                LoginPanel.setBlank();
                JoinPanel.setBlank();
            }
            else if(ob == btnNo){}
        }
    };
}
