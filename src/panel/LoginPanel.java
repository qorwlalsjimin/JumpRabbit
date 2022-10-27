package panel;

import JumpRabbit.JumpRabbit;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class LoginPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("PW");
	JLabel labelJoin = new JLabel("ȸ������");
	JLabel labelGuest = new JLabel("�Խ�Ʈ");
	JTextField textID = new JTextField();
	JTextField textPW = new JTextField();

    ImageIcon howScreen = new ImageIcon("images/screen_login.png");

	JButton btn = new JButton("�̵� ��ư");
    
    public LoginPanel() {
    	setLayout(null);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

    	//	����
	    labelID.setBounds(322, 325, 182, 56);
		labelPW.setBounds(322, 443, 182, 56);
		labelJoin.setBounds(720,460,200,100);
		labelGuest.setBounds(620,460,100,100);
		textID.setBounds(467, 325, 300, 40);
		textPW.setBounds(467, 443, 300, 40);

		labelID.setFont(new Font("Neo�ձٸ�", 0, 40));
		labelPW.setFont(new Font("Neo�ձٸ�", 0, 40));
		labelJoin.setFont(new Font("Neo�ձٸ�", 0, 30));
		labelGuest.setFont(new Font("Neo�ձٸ�", 0, 30));

		Font font = labelJoin.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		labelJoin.setFont(font.deriveFont(attributes));
		labelGuest.setFont(font.deriveFont(attributes));

	    add(labelID);
	    add(labelPW);
		add(labelJoin);
		add(labelGuest);
		add(textID);
		add(textPW);
		add(btn);

		labelJoin.addMouseListener(this);
		labelGuest.addMouseListener(this);

		addKeyListener(this);
		requestFocus();
    }

	// ��� �̹��� ����
	public void paintComponent(Graphics g) {
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
				String url = "jdbc:mysql://localhost:3306/jumprabbit";
				String userName = "jumprabbit";
				String password = "jumprabbit";
				String sql = "select pw from user_information where id='"+inputID+"';";

				Connection conn = DriverManager.getConnection(url, userName, password);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				System.out.println(rs);
				rs.next();

				if(inputPW.equals(rs.getString("pw"))){
					System.out.println("�α��� ����");
					JumpRabbit.setCureentPanel("game");
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
			JumpRabbit.setCureentPanel("game");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	//���콺 ������
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("ȸ������"))
			JumpRabbit.setCureentPanel("join");
		if(e.getSource().toString().contains("�Խ�Ʈ"))
			JumpRabbit.setCureentPanel("game");
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
