package panel;

import JumpRabbit.JumpRabbit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import JumpRabbit.Main;


public class IntroPanel extends JPanel implements ActionListener, MouseListener {

    // 버튼
    static JButton btnStart;
    static JButton btnHow;
    static JButton btnRank;
	JLabel labelLogin;

	public static Boolean isLogin = false;
    ImageIcon mainScreen = new ImageIcon("images/screen_main.png");

    public IntroPanel() {
    	setLayout(null);
    	
    	//	정의
    	btnStart = new JButton(new ImageIcon("images/icon_start.png"));
    	btnHow = new JButton(new ImageIcon("images/icon_how.png"));
    	btnRank = new JButton(new ImageIcon("images/icon_rank.png"));
		labelLogin = new JLabel("로그인");
    			
	    //	버튼 위치 지정
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
		labelLogin.setBounds(1070, 10, 150,50);
	    
	    //	버튼 윤곽선, 배경색 없애기
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

		// 라벨 글씨 설정
		labelLogin.setFont(Main.font.deriveFont(30f));
		labelLogin.setForeground(Color.decode("#ff42a5"));

	    // 온클릭
	    btnStart.addActionListener(this);
	    btnHow.addActionListener(this);
	    btnRank.addActionListener(this);
		labelLogin.addMouseListener(this);

	    //	패널에 버튼 추가
        add(btnStart);
        add(btnHow);
        add(btnRank);
		add(labelLogin);
    }

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		if(isLogin) labelLogin.setText("로그아웃");
		else labelLogin.setText("로그인");

		g.drawImage(mainScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnStart){
			if(!isLogin){
				JumpRabbit.setCurrentPanel("guest");
			}
			else{
				JumpRabbit.setCurrentPanel("game");
			}
		}
		else if(ob == btnHow)
			JumpRabbit.setCurrentPanel("how");
		else if(ob == btnRank)
			JumpRabbit.setCurrentPanel("rank");

	}


	// MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("로그인"))
			JumpRabbit.setCurrentPanel("login");
		else{
			System.out.println("로그아웃하실텨?");
			JumpRabbit.setCurrentPanel("logout");
		}
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
