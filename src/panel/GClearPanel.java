package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GClearPanel extends JPanel implements ActionListener {
    ImageIcon clearScreen = new ImageIcon("images/screen_gameclear.png");

    JButton btnRe = new JButton(new ImageIcon("images/icon_game_return.png"));
    JButton btnRank = new JButton(new ImageIcon("images/icon_game_rank.png"));

    JLabel labelScore = new JLabel(Integer.toString(ScoreDB.score));

    public GClearPanel(){
        setLayout(null);

        btnRe.setBounds(549, 488, 226, 57);
        btnRank.setBounds(814, 488, 149, 57);

        labelScore.setBounds(465, 373, 200, 45);
        labelScore.setFont(new Font("Airal",0, 41));
        labelScore.setForeground(Color.decode("#ff42a5"));


        //	버튼 윤곽선, 배경색 없애기
        btnRe.setBorderPainted(false); btnRe.setContentAreaFilled(false);
        btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

        add(btnRe);
        add(btnRank);
        add(labelScore);

        btnRe.addActionListener(this);
        btnRank.addActionListener(this);
    }


    public void paintComponent(Graphics g) {
        g.drawImage(clearScreen.getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnRe)
            JumpRabbit.setCureentPanel("game");
        else if(ob == btnRank)
            JumpRabbit.setCureentPanel("rank");
    }
}
