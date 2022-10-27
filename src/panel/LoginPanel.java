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
	JLabel labelJoin = new JLabel("회원가입");
	JLabel labelGuest = new JLabel("게스트");
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
		labelJoin.setBounds(720,460,200,100);
		labelGuest.setBounds(620,460,100,100);
		textID.setBounds(467, 325, 300, 40);
		textPW.setBounds(467, 443, 300, 40);

		labelID.setFont(new Font("Neo둥근모", 0, 40));
		labelPW.setFont(new Font("Neo둥근모", 0, 40));
		labelJoin.setFont(new Font("Neo둥근모", 0, 30));
		labelGuest.setFont(new Font("Neo둥근모", 0, 30));

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

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	//버튼 클릭 리스너
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
					System.out.println("로그인 성공");
					JumpRabbit.setCureentPanel("game");
				}
				else{
					System.out.println("비밀번호가 틀렸습니다.");
					textPW.setText("");
				}

				st.close();
				rs.close();
				conn.close();
			}catch (Exception exception){
				if (textID.getText().isEmpty()){
					System.out.println("아이디를 입력해주세요.");
				}
				else if (textPW.getText().isEmpty()){
					System.out.println("비밀번호를 입력해주세요.");
				}
				else if(exception.toString().contains("Illegal operation on empty result set.")){
					System.out.println("해당하는 아이디가 없습니다.");
					textID.setText("");
					textPW.setText("");
				}
				else exception.printStackTrace();
			}
		}
	}

	//키보드 리스너
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

	//마우스 리스너
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("회원가입"))
			JumpRabbit.setCureentPanel("join");
		if(e.getSource().toString().contains("게스트"))
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
