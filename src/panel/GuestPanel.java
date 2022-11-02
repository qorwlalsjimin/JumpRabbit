package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestPanel extends JPanel implements ActionListener {
    ImageIcon overScreen = new ImageIcon("images/screen_guest.jpg");

    JButton btnYes = new JButton("예");
    JButton btnLogin = new JButton("로그인");

    int cnt = 1000; //게스트 번호

    public GuestPanel(){
        setLayout(null);

        btnYes.setBounds(249, 488, 226, 57);
        btnLogin.setBounds(614, 488, 149, 57);

        //	버튼 윤곽선, 배경색 없애기
        btnYes.setBorderPainted(false); btnYes.setContentAreaFilled(false);
        btnLogin.setBorderPainted(false); btnLogin.setContentAreaFilled(false);

        add(btnYes);
        add(btnLogin);

        btnYes.addActionListener(this);
        btnLogin.addActionListener(this);

    }


    public void paintComponent(Graphics g) {
        g.drawImage(overScreen.getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnYes){
            JumpRabbit.setCurrentPanel("game");
            JoinNamePanel.Nickname = "guest" + ++cnt;
            System.out.println(cnt);
        }
        else if(ob == btnLogin)
            JumpRabbit.setCurrentPanel("login");
    }
}
