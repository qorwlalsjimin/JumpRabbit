package panel;

import JumpRabbit.JumpRabbit;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

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

	}


	public void paintComponent(Graphics g) {
		g.drawImage(gameScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnGameOver)
			JumpRabbit.setCureentPanel("over");
		else if(ob == btnGameClear)
			JumpRabbit.setCureentPanel("clear");
	}
}
