package panel;

import JumpRabbit.JumpRabbit;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel implements ActionListener, KeyListener {

	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	JTextField textID = new JTextField();
	JTextField textPW = new JTextField();

    ImageIcon howScreen = new ImageIcon("images/screen_login.png");

	JButton btn = new JButton("이동 버튼");
    
    public LoginPanel() {
    	setLayout(null);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

    	//	정의
	    labelID.setBounds(322, 325, 182, 56);
		labelPW.setBounds(322, 443, 182, 56);
		textID.setBounds(467, 325, 300, 40);
		textPW.setBounds(467, 443, 300, 40);

		labelID.setFont(new Font("Neo둥근모", 0, 40));
		labelPW.setFont(new Font("Neo둥근모", 0, 40));

	    add(labelID);
	    add(labelPW);
		add(textID);
		add(textPW);
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

		if(ob == btn) JumpRabbit.setCureentPanel("join");
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
