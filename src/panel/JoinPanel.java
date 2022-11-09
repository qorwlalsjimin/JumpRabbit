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
	JButton btn = new JButton("이동 버튼");

    public JoinPanel() {
    	setLayout(null);

		//TODO: 로그아웃 후에 남아있는 textfield 값 지우기
		//TODO: 디자인 크기대로 label, textfield 위치 조정하기

		// 뒤로 돌아가기 버튼
		btnRankBack.setBounds(1070, 700, 102, 64);
		btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
		add(btnRankBack);
		btnRankBack.addActionListener(this);

		btn.setBounds(0,0,100,100);
		btn.addActionListener(this);

    	//	정의
		labelID.setBounds(322, 341, 182, 56);
		labelPW.setBounds(323, 455, 182, 56);
		textID.setBounds(430, 323, 404, 68);
		textPW.setBounds(430, 433, 404, 68);

		labelID.setFont(Main.font.deriveFont(60f));
		labelPW.setFont(Main.font.deriveFont(60f));

		labelPW.setForeground(Main.defaultColor);
		labelID.setForeground(Main.defaultColor);

		textID.setHint("아이디를 입력하세요.");
		textPW.setHint("비밀번호를 입력하세요.");

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

	// 배경 이미지 설정
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

				//TODO: 회원가입 예외처리해야됨
				if(inputID.isEmpty() && inputPW.isEmpty())
					System.out.println("입력하세요!!!");
				else{
					JoinNamePanel.inputID = inputID.toString();
					JumpRabbit.setCurrentPanel("nickname");
				}

			}catch (Exception exception){
				//중복 id 체크
				if(exception.toString().contains("PRIMARY"))
					System.out.println("이미 존재하는 아이디입니다.");
				else
					System.out.println("오류가 발생했습니다.");
			}
		}else if(ob == btnRankBack){
			JumpRabbit.setCurrentPanel("intro");
			setBlank();
		}
	}

	public void setBlank(){
		textID.setText("");
		textPW.setText("");
		textID.setHint("아이디를 입력하세요.");
		textPW.setHint("비밀번호를 입력하세요.");
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
