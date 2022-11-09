package dialog;

import JumpRabbit.JumpRabbit;
import JumpRabbit.Main;
import panel.JoinNamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestDialog {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    JButton btnYes = new JButton("확인");
    JButton btnLogin = new JButton("로그인");

    static int cnt = 1000; //게스트 번호

    public GuestDialog() {
        JLabel displayText = new JLabel("! 게스트로 플레이하시겠습니까?");
        displayText.setFont(Main.font.deriveFont(22f));
        displayText.setForeground(Color.white);
        displayText.setHorizontalAlignment(JLabel.CENTER);
        displayText.setVerticalTextPosition(SwingConstants.CENTER);
        displayText.setBounds(0,0,380,110);

        btnYes.setFont(Main.font.deriveFont(20f));
        btnYes.setForeground(Main.defaultColor);
        btnYes.setBackground(Color.white);
        btnYes.setBounds(100,95,80,40);

        btnLogin.setFont(Main.font.deriveFont(20f));
        btnLogin.setForeground(Main.defaultColor);
        btnLogin.setBackground(Color.white);
        btnLogin.setBounds(190,95,100,40);

        btnYes.addActionListener(btnListener);
        btnLogin.addActionListener(btnListener);

        panel.setLayout(null);
        panel.setBackground(Color.decode("#FFB7E0"));
        panel.add(displayText);
        panel.add(btnYes);
        panel.add(btnLogin);
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
                JumpRabbit.setCurrentPanel("game");
                JoinNamePanel.Nickname = "guest" + ++cnt;
            }
            else if(ob == btnLogin)
                JumpRabbit.setCurrentPanel("login");

        }
    };
}
