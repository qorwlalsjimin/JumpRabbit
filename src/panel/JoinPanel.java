package panel;

import dialog.ConfirmDialog;
import JumpRabbit.JumpRabbit;
import JumpRabbit.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JoinPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	public static CustomTextField textID = new CustomTextField();
	public static CustomTextField textPW = new CustomTextField();
    ImageIcon howScreen = new ImageIcon("images/screen_join.png");
	static JButton btnBack = new JButton(new ImageIcon("images/icon_back.png"));

    public JoinPanel() {
    	setLayout(null);

		// �ڷ� ���ư��� ��ư
		btnBack.setBounds(1070, 700, 102, 64);
		btnBack.setBorderPainted(false); btnBack.setContentAreaFilled(false);
		add(btnBack);
		btnBack.addActionListener(this);

    	//	����
		labelID.setBounds(322, 341, 182, 56);
		labelPW.setBounds(323, 455, 182, 56);
		textID.setBounds(430, 323, 404, 68);
		textPW.setBounds(430, 433, 404, 68);

		labelID.setFont(Main.font.deriveFont(60f));
		labelPW.setFont(Main.font.deriveFont(60f));

		labelPW.setForeground(Main.defaultColor);
		labelID.setForeground(Main.defaultColor);

		textID.setHint("���̵� �Է��ϼ���.");
		textPW.setHint("��й�ȣ�� �Է��ϼ���.");

		textID.setBackgroundImage("images/textfield.png");
		textPW.setBackgroundImage("images/textfield.png");

		add(labelID);
		add(labelPW);
		add(textID);
		add(textPW);

		textID.addKeyListener(this);
		textPW.addKeyListener(this);

		btnBack.addMouseListener(this);
		
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
			setBlank();
		}
	}

	public static void setBlank(){
		textID.setText("");
		textPW.setText("");
		JoinNamePanel.textNickname.setText("");
		textID.setHint("���̵� �Է��ϼ���.");
		textPW.setHint("��й�ȣ�� �Է��ϼ���.");
		JoinNamePanel.textNickname.setHint("�г����� �Է��ϼ���.");
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		String inputID = textID.getText();
		String inputPW = textPW.getText();

		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try{
				//SQLite
//				Class.forName("org.sqlite.JDBC");
//				String dbFile = "src/JumpRabbitDB.db";
//				Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

				//TODO: ��й�ȣ �Է� �� �ص� �Ѿ
				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				String sql = "insert into user_information(id, pw) values(?,?);";

				Connection conn = DriverManager.getConnection(url, userName, password);
				PreparedStatement pt = conn.prepareStatement(sql);

				pt.setString(1, inputID);
				pt.setString(2, inputPW);

				pt.executeUpdate();

				pt.close();
				conn.close();

				//TODO: ȸ������ ����ó���ؾߵ�
				if(inputID.isEmpty() && inputPW.isEmpty())
					new ConfirmDialog("��ĭ�� �Է����ּ���.");
				else{
					JoinNamePanel.inputID = inputID.toString();
					JumpRabbit.setCurrentPanel("nickname");
				}

				JumpRabbit.setCurrentPanel("nickname");
			}catch (Exception exception){
				//�ߺ� id üũ
				if(exception.toString().contains("PRIMARY")){
					new ConfirmDialog("�̹� �����ϴ� ���̵��Դϴ�.");
					textID.setText("");
				}
				else{
					new ConfirmDialog("�� �� ���� ������ �߻��߽��ϴ�.");
					exception.printStackTrace();
				}
			}
		}
	}

	// ���콺 ������ �߻� �޼��� ����
	@Override
	public void keyReleased(KeyEvent e) {}

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
