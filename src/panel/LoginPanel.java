package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import JumpRabbit.Main;

public class LoginPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	JLabel labelJoin = new JLabel("ȸ������");

	CustomTextField textID = new CustomTextField();
	CustomTextField textPW = new CustomTextField();

    ImageIcon howScreen = new ImageIcon("images/screen_login.png");

	JButton btn = new JButton("�̵� ��ư");
    
    public LoginPanel() {
    	setLayout(null);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

    	//	����
	    labelID.setBounds(322, 341, 182, 56);
		labelPW.setBounds(323, 455, 182, 56);
		labelJoin.setBounds(720,480,200,100);
		textID.setBounds(430, 323, 404, 68);
		textPW.setBounds(430, 433, 404, 68);

		labelID.setFont(Main.font.deriveFont(60f));
		labelPW.setFont(Main.font.deriveFont(60f));
		labelJoin.setFont(Main.font.deriveFont(30f));

		labelID.setForeground(Color.decode("#ff42a5"));
		labelPW.setForeground(Color.decode("#ff42a5"));
		labelJoin.setForeground(Color.decode("#ff42a5"));

		Font underlinefont = labelJoin.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(underlinefont.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		labelJoin.setFont(underlinefont.deriveFont(attributes));

		textID.setBackgroundImage("images/textfield.png");
		textPW.setBackgroundImage("images/textfield.png");

		textID.setHint("���̵� �Է��ϼ���.");
		textPW.setHint("��й�ȣ�� �Է��ϼ���.");

	    add(labelID);
	    add(labelPW);
		add(labelJoin);
		add(textID);
		add(textPW);
		add(btn);

		labelJoin.addMouseListener(this);

		addKeyListener(this);
		requestFocus();
    }

	// ��� �̹��� ����
	public void paintComponent(Graphics g) {
		textID.setText("");
		textPW.setText("");

		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	//��ư Ŭ�� ������
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		String inputID = textID.getText();
		String inputPW = textPW.getText();

		if(ob == btn) {
			try{
				//TODO: �α��� ���� ����
				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				String sql = "select * from user_information where id='"+inputID+"';";

				Connection conn = DriverManager.getConnection(url, userName, password);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);

				rs.next();
				JoinNamePanel.Nickname = rs.getString("name");
				System.out.println(JoinNamePanel.Nickname);

				if(inputID.isEmpty() && inputPW.isEmpty()){
					System.out.println("�α��� ���");
					JumpRabbit.setCurrentPanel("intro");
				}
				else if(inputPW.equals(rs.getString("pw"))){
					System.out.println("�α��� ����");
					IntroPanel.isLogin = true;
					JumpRabbit.setCurrentPanel("intro");
				}
				else{
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					textPW.setText("");
				}

				st.close();
				rs.close();
				conn.close();
			}catch (Exception exception){
				if (textID.getText().isEmpty()){
					System.out.println("���̵� �Է����ּ���.");
				}
				else if (textPW.getText().isEmpty()){
					System.out.println("��й�ȣ�� �Է����ּ���.");
				}
				else if(exception.toString().contains("Illegal operation on empty result set.")){
					System.out.println("�ش��ϴ� ���̵� �����ϴ�.");
					textID.setText("");
					textPW.setText("");
				}
				else exception.printStackTrace();
			}
		}
	}

	//Ű���� ������
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//���� �Է½� ȭ�� �̵�
		System.out.println("������ ����");
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//DB�� ���̵� ���� ���� �ʿ���
			System.out.println("����");
			JumpRabbit.setCurrentPanel("game");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	//���콺 ������
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("ȸ������"))
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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
