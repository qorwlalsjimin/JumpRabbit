package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutPanel extends JPanel implements ActionListener {
    ImageIcon overScreen = new ImageIcon("images/screen_logout.png");

    JButton btnYes = new JButton("예");
    JButton btnNo = new JButton("아니요");


    public LogoutPanel(){
        setLayout(null);

        btnYes.setBounds(249, 488, 226, 57);
        btnNo.setBounds(614, 488, 149, 57);

        //	버튼 윤곽선, 배경색 없애기
        btnYes.setBorderPainted(false); btnYes.setContentAreaFilled(false);
        btnNo.setBorderPainted(false); btnNo.setContentAreaFilled(false);

        add(btnYes);
        add(btnNo);

        btnYes.addActionListener(this);
        btnNo.addActionListener(this);

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
            IntroPanel.isLogin = false;
        }
        JumpRabbit.setCurrentPanel("intro");
    }
}
