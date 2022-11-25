package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GOverPanel extends JPanel implements ActionListener, MouseListener {
    ImageIcon overScreen = new ImageIcon("images/screen_gameover.png");

    JButton btnRe = new JButton(new ImageIcon("images/icon_game_return.png"));
    JButton btnRank = new JButton(new ImageIcon("images/icon_game_rank.png"));

    JLabel labelScore = new JLabel(Integer.toString(ScoreDB.score));

    public GOverPanel(){
        setLayout(null);

        btnRe.setBounds(549, 488, 226, 57);
        btnRank.setBounds(814, 488, 149, 57);

        //	버튼 윤곽선, 배경색 없애기
        btnRe.setBorderPainted(false); btnRe.setContentAreaFilled(false);
        btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

        add(btnRe);
        add(btnRank);

        btnRe.addActionListener(this);
        btnRank.addActionListener(this);

        btnRe.addMouseListener(this);
        btnRank.addMouseListener(this);

    }


    public void paintComponent(Graphics g) {
        g.drawImage(overScreen.getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnRe)
            JumpRabbit.setCurrentPanel("game");
        else if(ob == btnRank)
            JumpRabbit.setCurrentPanel("rank");
    }

    // 마우스 리스너 추상메서드 구현
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().toString().contains("icon_game_rank")){
            btnRank.setIcon(new ImageIcon("images/icon_game_rank_entered.png"));
        }
        else if(e.getSource().toString().contains("icon_game_return"))
            btnRe.setIcon(new ImageIcon("images/icon_game_return_entered.png"));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().toString().contains("icon_game_rank_entered"))
            btnRank.setIcon(new ImageIcon("images/icon_game_rank.png"));
        else if(e.getSource().toString().contains("icon_game_return_entered"))
            btnRe.setIcon(new ImageIcon("images/icon_game_return.png"));
    }
}
