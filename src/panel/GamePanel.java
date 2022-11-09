package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    // ��ư
    JButton btnGameOver = new JButton("���ӿ���");
    JButton btnGameClear = new JButton("����Ŭ����");

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

		//DB�� insert


	}

	// ��� �̹��� ����
	public void paintComponent(Graphics g) {
		g.drawImage(gameScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	//��ư Ŭ�� ������
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnGameOver)
			JumpRabbit.setCurrentPanel("over");
		else if(ob == btnGameClear)
			JumpRabbit.setCurrentPanel("clear");
	}
}
