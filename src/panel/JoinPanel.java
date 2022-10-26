package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JoinPanel extends JPanel implements ActionListener, KeyListener {

	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	JTextField textID = new JTextField();
	JTextField textPW = new JTextField();
    ImageIcon howScreen = new ImageIcon("images/screen_join.png");

	JButton btn = new JButton("�̵� ��ư");

    public JoinPanel() {
    	setLayout(null);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

    	//	����
		labelID.setBounds(322, 325, 182, 56);
		labelPW.setBounds(322, 443, 182, 56);
		textID.setBounds(467, 325, 300, 40);
		textPW.setBounds(467, 443, 300, 40);

		labelID.setFont(new Font("Neo�ձٸ�", 0, 40));
		labelPW.setFont(new Font("Neo�ձٸ�", 0, 40));

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
				String sql = "select pw from user_information where id='"+inputID+"';";

				Connection conn = DriverManager.getConnection(url, userName, password);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				System.out.println(rs);
				rs.next();

				// TODO: db�� ���� ���̵����� Ȯ��
//				if(inputPW.equals(rs.getString("pw"))){
//
//					JumpRabbit.setCureentPanel("game");
//				}
//				else{
//					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
//					textPW.setText("");
//				}

				st.close();
				rs.close();
				conn.close();
			}catch (Exception exception){
				//�ߺ� id üũ
				exception.printStackTrace();
			}

			JumpRabbit.setCureentPanel("nickname");
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
