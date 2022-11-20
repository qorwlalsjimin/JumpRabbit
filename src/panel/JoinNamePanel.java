package panel;

import JumpRabbit.JumpRabbit;
import JumpRabbit.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JoinNamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	JLabel labelNotice = new JLabel("NAME");
	public static CustomTextField textNickname = new CustomTextField();
	public static String Nickname = "";
	ImageIcon howScreen = new ImageIcon("images/screen_nickname.png");
	static JButton btnBack = new JButton(new ImageIcon("images/icon_back.png"));

	static public String inputID;

	public JoinNamePanel() {
		setLayout(null);
		//TODO: ȸ������ �� ���ư��� ���, �г��� ���� ������ �ڵ� ����

		// �ڷ� ���ư��� ��ư
		btnBack.setBounds(1070, 700, 102, 64);
		btnBack.setBorderPainted(false); btnBack.setContentAreaFilled(false);
		add(btnBack);
		btnBack.addActionListener(this);
		btnBack.addMouseListener(this);

		// ����
		labelNotice.setBounds(335, 377, 182, 56);
		textNickname.setBounds(473, 368, 404, 68);

		labelNotice.setFont(Main.font.deriveFont(60f));
		labelNotice.setForeground(Main.defaultColor);

		textNickname.setHint("�г����� �Է��ϼ���");
		textNickname.setBackgroundImage("images/textfield.png");

		add(labelNotice);
		add(textNickname);

		textNickname.addKeyListener(this);
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
		if(ob == btnBack){
			JumpRabbit.setCurrentPanel("login");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//���� �Է½� ȭ�� �̵�
		System.out.println("������ ����");
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try{

				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				String sql = "update user_information set name = ? where id=?;";

				Connection conn = DriverManager.getConnection(url, userName, password);
				PreparedStatement pt = conn.prepareStatement(sql);

				Nickname = textNickname.getText();
				pt.setString(1, textNickname.getText());
				pt.setString(2, inputID);

				int r = pt.executeUpdate();

				pt.close();
				conn.close();
			}catch (Exception exception){
				exception.printStackTrace();
			}

			System.out.println("ȸ������ ����");
			IntroPanel.isLogin = true;
			JumpRabbit.setCurrentPanel("intro");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	//���콺 ������ �߻�޼��� ����
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().toString().contains("icon_back"))
			btnBack.setIcon(new ImageIcon("images/icon_back_entered.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().toString().contains("icon_back_entered"))
			btnBack.setIcon(new ImageIcon("images/icon_back.png"));
	}
}
