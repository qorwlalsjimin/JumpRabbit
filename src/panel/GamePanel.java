package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    // 버튼
    JButton btnGameOver = new JButton("게임오버");
    JButton btnGameClear = new JButton("게임클리어");

    ImageIcon gameScreen = new ImageIcon("images/screen_game.jpg");
    
    public GamePanel() {
    	setLayout(null);

		btnGameOver.setBounds(40,20, 100,300);
		btnGameClear.setBounds(180,20, 100,300);

		add(btnGameOver);
	    add(btnGameClear);

		btnGameOver.addActionListener(this);
		btnGameClear.addActionListener(this);

		ScoreDB.score = 500;

		//DB에 insert


	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		g.drawImage(gameScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	//버튼 클릭 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnGameOver)
			JumpRabbit.setCurrentPanel("over");
		else if(ob == btnGameClear)
			JumpRabbit.setCurrentPanel("clear");
	}
}
