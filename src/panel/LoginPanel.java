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

		// 뒤로 돌아가기 버튼
		btnBack.setBounds(1070, 700, 102, 64);
		btnBack.setBorderPainted(false); btnBack.setContentAreaFilled(false);
		add(btnBack);
		btnBack.addActionListener(this);

    	//	정의
	    labelID.setBounds(322, 341, 182, 56);
		labelPW.setBounds(323, 455, 182, 56);
		btnJoin.setBounds(720,480,200,100);
		textID.setBounds(430, 323, 404, 68);
		textPW.setBounds(430, 433, 404, 68);

		labelID.setFont(Main.font.deriveFont(60f));
		labelPW.setFont(Main.font.deriveFont(60f));

		labelID.setForeground(Main.defaultColor);
		labelPW.setForeground(Main.defaultColor);

		textID.setHint("아이디를 입력하세요.");
		textPW.setHint("비밀번호를 입력하세요.");

		textID.setBackgroundImage("images/textfield.png");
		textPW.setBackgroundImage("images/textfield.png");

		//JButton 기본 배경, 윤곽선 없애기
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

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {

		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	public static void setBlank(){
		textID.setText("");
		textPW.setText("");
		textID.setHint("아이디를 입력하세요.");
		textPW.setHint("비밀번호를 입력하세요.");
	}

	//버튼 클릭 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnBack){
			JumpRabbit.setCurrentPanel("intro");
			setBlank();
		}
	}

	//키보드 리스너
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
				if(!textID.getText().equals("아이디를 입력하세요.")){
					rs = st.executeQuery(sql);
					rs.next();
					JoinNamePanel.Nickname = rs.getString("name");
					System.out.println(JoinNamePanel.Nickname);
				}else
					new ConfirmDialog("빈칸을 입력해주세요");

				if(textID.getText().equals("아이디를 입력하세요.") || textPW.getText().equals("비밀번호를 입력하세요.")){
					new ConfirmDialog("빈칸을 입력해주세요");
				}
				else if(inputPW.equals(rs.getString("pw"))){
					System.out.println("로그인 성공");
					IntroPanel.isLogin = true;

					//화면 이동
					JumpRabbit.setCurrentPanel("intro");
				}
				else{
					new ConfirmDialog("비밀번호가 틀렸습니다.");
					textPW.setText("");
				}

				st.close();
				if(rs!=null) rs.close();
				conn.close();

			}catch (Exception exception){
				if(exception.toString().contains("Illegal operation on empty result set.")){
					new ConfirmDialog("해당하는 아이디가 없습니다");
					setBlank();
				}
				else exception.printStackTrace();
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	//마우스 리스너
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
