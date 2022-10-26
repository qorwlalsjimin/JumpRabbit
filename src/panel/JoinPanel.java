package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JoinPanel extends JPanel implements ActionListener, KeyListener {

	JLabel labelNotice = new JLabel("닉네임을 입력하세요");
	JTextField textNickname = new JTextField();
    ImageIcon howScreen = new ImageIcon("images/screen_join.png");

	JButton btn = new JButton("이동 버튼");

    public JoinPanel() {
    	setLayout(null);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

    	//	정의
		labelNotice.setBounds(335, 368, 182, 56);
		textNickname.setBounds(473, 368, 300, 40);

		labelNotice.setFont(new Font("Neo둥근모", 0, 40));

		add(labelNotice);
	    add(textNickname);
		add(btn);

		addKeyListener(this);
		requestFocus();
    }


	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btn) JumpRabbit.setCureentPanel("game");
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//엔터 입력시 화면 이동
		System.out.println("리스너 실행");
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//DB에 아이디 유무 판정 필요함
			System.out.println("엔터");
			JumpRabbit.setCureentPanel("game");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
