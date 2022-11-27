package panel;

import JumpRabbit.JumpRabbit;
import JumpRabbit.Main;
import dialog.ConfirmDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class LoginPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	JButton btnJoin = new JButton(new ImageIcon("images/icon_join.png"));
	static JButton btnBack = new JButton(new ImageIcon("images/icon_back.png"));


	public static CustomTextField textID = new CustomTextField();
	public static CustomTextField textPW = new CustomTextField();

    ImageIcon howScreen = new ImageIcon("images/screen_login.png");

    
    public LoginPanel() {
    	setLayout(null);

		// �ڷ� ���ư��� ��ư
		btnBack.setBounds(1070, 700, 102, 64);
		btnBack.setBorderPainted(false); btnBack.setContentAreaFilled(false);
		add(btnBack);
		btnBack.addActionListener(this);

    	//	����
	    labelID.setBounds(322, 341, 182, 56);
		labelPW.setBounds(323, 455, 182, 56);
		btnJoin.setBounds(720,480,200,100);
		textID.setBounds(430, 323, 404, 68);
		textPW.setBounds(430, 433, 404, 68);

		labelID.setFont(Main.font.deriveFont(60f));
		labelPW.setFont(Main.font.deriveFont(60f));

		labelID.setForeground(Main.defaultColor);
		labelPW.setForeground(Main.defaultColor);

		textID.setHint("���̵� �Է��ϼ���.");
		textPW.setHint("��й�ȣ�� �Է��ϼ���.");

		textID.setBackgroundImage("images/textfield.png");
		textPW.setBackgroundImage("images/textfield.png");

		//JButton �⺻ ���, ������ ���ֱ�
		btnJoin.setBorderPainted(false); btnJoin.setContentAreaFilled(false);

	    add(labelID);
	    add(labelPW);
		add(btnJoin);
		add(textID);
		add(textPW);

		btnJoin.addMouseListener(this);
		btnBack.addMouseListener(this);
		textID.addKeyListener(this);
		textPW.addKeyListener(this);

		addKeyListener(this);
		requestFocus();
    }

	// ��� �̹��� ����
	public void paintComponent(Graphics g) {

		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	public static void setBlank(){
		textID.setText("");
		textPW.setText("");
		textID.setHint("���̵� �Է��ϼ���.");
		textPW.setHint("��й�ȣ�� �Է��ϼ���.");
	}

	//��ư Ŭ�� ������
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnBack){
			JumpRabbit.setCurrentPanel("intro");
			setBlank();
		}
	}

	//Ű���� ������
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			String inputID = textID.getText();
			String inputPW = textPW.getText();

			try{

				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				Connection conn = DriverManager.getConnection(url, userName, password);

				String sql = "select * from user_information where id='"+inputID+"';";
				Statement st = conn.createStatement();

				ResultSet rs = null;
				if(!textID.getText().equals("���̵� �Է��ϼ���.")){
					rs = st.executeQuery(sql);
					rs.next();
					JoinNamePanel.Nickname = rs.getString("name");
					System.out.println(JoinNamePanel.Nickname);
				}else
					new ConfirmDialog("��ĭ�� �Է����ּ���");

				if(textID.getText().equals("���̵� �Է��ϼ���.") || textPW.getText().equals("��й�ȣ�� �Է��ϼ���.")){
					new ConfirmDialog("��ĭ�� �Է����ּ���");
				}
				else if(inputPW.equals(rs.getString("pw"))){
					System.out.println("�α��� ����");
					IntroPanel.isLogin = true;

					//ȭ�� �̵�
					JumpRabbit.setCurrentPanel("intro");
				}
				else{
					new ConfirmDialog("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					textPW.setText("");
				}

				st.close();
				if(rs!=null) rs.close();
				conn.close();

			}catch (Exception exception){
				if(exception.toString().contains("Illegal operation on empty result set.")){
					new ConfirmDialog("�ش��ϴ� ���̵� �����ϴ�");
					setBlank();
				}
				else exception.printStackTrace();
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	//���콺 ������
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("icon_join"))
			JumpRabbit.setCurrentPanel("join");
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().toString().contains("icon_join")){
			btnJoin.setIcon(new ImageIcon("images/icon_join_entered.png"));
		}else
		if(e.getSource().toString().contains("icon_back"))
			btnBack.setIcon(new ImageIcon("images/icon_back_entered.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().toString().contains("icon_join"))
			btnJoin.setIcon(new ImageIcon("images/icon_join.png"));
		else if(e.getSource().toString().contains("icon_back_entered"))
			btnBack.setIcon(new ImageIcon("images/icon_back.png"));
	}
}
