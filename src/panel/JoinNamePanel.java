package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JoinNamePanel extends JPanel implements ActionListener, KeyListener {

	JLabel labelNotice = new JLabel("�г����� �Է��ϼ���");
	JTextField textNickname = new JTextField();
	ImageIcon howScreen = new ImageIcon("images/screen_join.png");

	JButton btn = new JButton("�̵� ��ư");

	static public String inputID, inputPW;

	public JoinNamePanel() {
		setLayout(null);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

		//	����
		labelNotice.setBounds(335, 368, 182, 56);
		textNickname.setBounds(473, 368, 300, 40);

		labelNotice.setFont(new Font("Neo�ձٸ�", 0, 40));

		add(labelNotice);
		add(textNickname);
		add(btn);

		addKeyListener(this);
		requestFocus();
	}

	// ��� �̹��� ����
	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btn){
			try{

				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				String sql = "insert into user_information values(?,?,?);";

				Connection conn = DriverManager.getConnection(url, userName, password);
				PreparedStatement pt = conn.prepareStatement(sql);

				pt.setString(1, inputID);
				pt.setString(2, inputPW);
				pt.setString(3, textNickname.getText());

				pt.close();
				conn.close();
			}catch (Exception exception){
				exception.printStackTrace();
			}

			JumpRabbit.setCureentPanel("game");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//���� �Է½� ȭ�� �̵�
		System.out.println("������ ����");
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//DB�� ���̵� ���� ���� �ʿ���
			System.out.println("����");
			JumpRabbit.setCureentPanel("game");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
