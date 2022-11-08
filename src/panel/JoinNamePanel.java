package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import JumpRabbit.Main;

public class JoinNamePanel extends JPanel implements ActionListener, KeyListener {

	JLabel labelNotice = new JLabel("NAME");
	CustomTextField textNickname = new CustomTextField();
	public static String Nickname = "";
	ImageIcon howScreen = new ImageIcon("images/screen_join.png");
	static JButton btnRankBack = new JButton(new ImageIcon("images/icon_rank_back.png"));
	JButton btn = new JButton("이동 버튼");

	static public String inputID;

	public JoinNamePanel() {
		setLayout(null);
		//TODO: 회원가입 중 돌아가기 기능, 닉네임 없는 계정은 자동 삭제

		// 뒤로 돌아가기 버튼
		btnRankBack.setBounds(1070, 700, 102, 64);
		btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
		add(btnRankBack);
		btnRankBack.addActionListener(this);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

		// 정의
		labelNotice.setBounds(335, 370, 182, 56);
		textNickname.setBounds(473, 368, 404, 68);

		labelNotice.setFont(Main.font.deriveFont(60f));
		labelNotice.setForeground(Main.defaultColor);

		textNickname.setHint("Write your name");
		textNickname.setBackgroundImage("images/textfield.png");

		add(labelNotice);
		add(textNickname);
		add(btn);

		addKeyListener(this);
		requestFocus();
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btn){
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

			System.out.println("회원가입 성공");
			IntroPanel.isLogin = true;
			JumpRabbit.setCurrentPanel("intro");
		}else if(ob == btnRankBack){
			JumpRabbit.setCurrentPanel("login");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//엔터 입력시 화면 이동
		System.out.println("리스너 실행");
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//DB에 아이디 유무 판정 필요함
			System.out.println("엔터");
			JumpRabbit.setCurrentPanel("game");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
