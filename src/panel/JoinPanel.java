package panel;

import JumpRabbit.JumpRabbit;
import JumpRabbit.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JoinPanel extends JPanel implements ActionListener, KeyListener {

	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	CustomTextField textID = new CustomTextField();
	CustomTextField textPW = new CustomTextField();
    ImageIcon howScreen = new ImageIcon("images/screen_login.png");
	static JButton btnRankBack = new JButton(new ImageIcon("images/icon_rank_back.png"));
	JButton btn = new JButton("�̵� ��ư");

    public JoinPanel() {
    	setLayout(null);

		//TODO: �α׾ƿ� �Ŀ� �����ִ� textfield �� �����
		//TODO: ������ ũ���� label, textfield ��ġ �����ϱ�

		// �ڷ� ���ư��� ��ư
		btnRankBack.setBounds(1070, 700, 102, 64);
		btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
		add(btnRankBack);
		btnRankBack.addActionListener(this);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

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
		String inputID = textID.getText();
		String inputPW = textPW.getText();

		if(ob == btn){
			try{

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
					System.out.println("�Է��ϼ���!!!");
				else{
					JoinNamePanel.inputID = inputID.toString();
					JumpRabbit.setCurrentPanel("nickname");
				}

			}catch (Exception exception){
				//�ߺ� id üũ
				if(exception.toString().contains("PRIMARY"))
					System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
				else
					System.out.println("������ �߻��߽��ϴ�.");
			}
		}else if(ob == btnRankBack){
			JumpRabbit.setCurrentPanel("intro");
			setBlank();
		}
	}

	public void setBlank(){
		textID.setText("");
		textPW.setText("");
		textID.setHint("���̵� �Է��ϼ���.");
		textPW.setHint("��й�ȣ�� �Է��ϼ���.");
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
			JumpRabbit.setCurrentPanel("game");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
