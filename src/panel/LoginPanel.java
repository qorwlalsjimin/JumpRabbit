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
	JLabel labelJoin = new JLabel("ȸ������");
	static JButton btnRankBack = new JButton(new ImageIcon("images/icon_rank_back.png"));


	public static CustomTextField textID = new CustomTextField();
	public static CustomTextField textPW = new CustomTextField();

    ImageIcon howScreen = new ImageIcon("images/screen_login.png");

    
    public LoginPanel() {
    	setLayout(null);

		// �ڷ� ���ư��� ��ư
		btnRankBack.setBounds(1070, 700, 102, 64);
		btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
		add(btnRankBack);
		btnRankBack.addActionListener(this);

    	//	����
	    labelID.setBounds(322, 341, 182, 56);
		labelPW.setBounds(323, 455, 182, 56);
		labelJoin.setBounds(720,480,200,100);
		textID.setBounds(430, 323, 404, 68);
		textPW.setBounds(430, 433, 404, 68);

		labelID.setFont(Main.font.deriveFont(60f));
		labelPW.setFont(Main.font.deriveFont(60f));
		labelJoin.setFont(Main.font.deriveFont(30f));

		labelID.setForeground(Main.defaultColor);
		labelPW.setForeground(Main.defaultColor);
		labelJoin.setForeground(Main.defaultColor);

		// ȸ������ ����
		Font underlinefont = labelJoin.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(underlinefont.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		labelJoin.setFont(underlinefont.deriveFont(attributes));

		textID.setHint("���̵� �Է��ϼ���.");
		textPW.setHint("��й�ȣ�� �Է��ϼ���.");

		textID.setBackgroundImage("images/textfield.png");
		textPW.setBackgroundImage("images/textfield.png");

	    add(labelID);
	    add(labelPW);
		add(labelJoin);
		add(textID);
		add(textPW);

		labelJoin.addMouseListener(this);
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

		if(ob == btnRankBack){
			JumpRabbit.setCurrentPanel("intro");
			setBlank();
		}
	}

	//Ű���� ������
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//���� �Է½� ȭ�� �̵�
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			String inputID = textID.getText();
			String inputPW = textPW.getText();

			try{
				//SQLite
//				Class.forName("org.sqlite.JDBC");
//				String url = "jdbc:sqlite:D:\\JumpRabbit\\JumpRabbitDB.db";
//				Connection conn = DriverManager.getConnection(url);

				//TODO: �α��� ���� ����
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
		if(e.getSource().toString().contains("ȸ������")){
			labelJoin.setForeground(Color.decode("#FFB7E0"));
			System.out.println("�������ڳ���");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().toString().contains("ȸ������"))
			labelJoin.setForeground(Main.defaultColor);
	}
}
