package panel;

import JumpRabbit.JumpRabbit;
import JumpRabbit.Main;
import dialog.LogoutDialog;
import dialog.GuestDialog;
import sun.font.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;


public class IntroPanel extends JPanel implements ActionListener, MouseListener {

    // 버튼
    static JButton btnStart;
    static JButton btnHow;
    static JButton btnRank;
	JLabel labelLogin;
	JLabel labelName;

	public static Boolean isLogin = false;
    ImageIcon mainScreen = new ImageIcon("images/screen_main.png");


    public IntroPanel() {
    	setLayout(null);
    	
    	//	정의
    	btnStart = new JButton(new ImageIcon("images/icon_start.png"));
    	btnHow = new JButton(new ImageIcon("images/icon_how.png"));
    	btnRank = new JButton(new ImageIcon("images/icon_rank.png"));
		labelLogin = new JLabel("LOGIN");
		labelName = new JLabel("배요미");
    			
	    //	버튼 위치 지정
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
		labelLogin.setBounds(1080, 10, 150,50);
		labelName.setBounds(1000, 10, 150, 50);

	    //	버튼 윤곽선, 배경색 없애기
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

		// 라벨 글씨 설정
		labelLogin.setFont(Main.font.deriveFont(30f));
		labelName.setFont(Main.font.deriveFont(30f));

//		labelLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		labelLogin.setForeground(Main.defaultColor);
		labelName.setForeground(Main.defaultColor);

		// 닉네임 라벨 밑줄
		Font underlinefont = labelName.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(underlinefont.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		labelName.setFont(underlinefont.deriveFont(attributes));

	    // 온클릭
	    btnStart.addActionListener(this);
	    btnHow.addActionListener(this);
	    btnRank.addActionListener(this);

	    btnStart.addMouseListener(this);
	    btnHow.addMouseListener(this);
	    btnRank.addMouseListener(this);
		labelLogin.addMouseListener(this);

	    //	패널에 버튼 추가
        add(btnStart);
        add(btnHow);
        add(btnRank);
		add(labelLogin);
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		if(isLogin) {
			labelLogin.setText("LOGOUT");
//			labelLogin.setText("배요미" + "님 LOGOUT");
			labelLogin.setBounds(1070, 10, 550,50);
			//add(labelName);
		}
		else {
			labelLogin.setText("LOGIN");
			labelLogin.setBounds(1080, 10, 150,50);
		}

		g.drawImage(mainScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnStart){
			if(!isLogin){
				new GuestDialog();
			}
			else{
				JumpRabbit.setCurrentPanel("game");
			}
		}
		else if(ob == btnHow)
			JumpRabbit.setCurrentPanel("how");
		else if(ob == btnRank){
			JumpRabbit.setCurrentPanel("rank");
		}

	}


	// MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().toString().contains("LOGIN"))
			JumpRabbit.setCurrentPanel("login");
		else if(e.getSource().toString().contains("LOGOUT")){
			new LogoutDialog();
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
		if(e.getSource().toString().contains("LOGIN")){
			labelLogin.setForeground(Color.decode("#FFB7E0"));
		} else if(e.getSource().toString().contains("LOGOUT")){
			labelLogin.setForeground(Color.decode("#FFB7E0"));
		}

		else if(e.getSource().toString().contains("icon_start")){
			btnStart.setIcon(new ImageIcon("images/icon_start_entered.png"));

		}else if(e.getSource().toString().contains("icon_how")){
			btnHow.setIcon(new ImageIcon("images/icon_how_entered.png"));

		}else if(e.getSource().toString().contains("icon_rank")){
			btnRank.setIcon(new ImageIcon("images/icon_rank_entered.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().toString().contains("LOGIN")){
			labelLogin.setForeground(Main.defaultColor);
		} else if(e.getSource().toString().contains("LOGOUT")){
			labelLogin.setForeground(Main.defaultColor);
		}

		else if(e.getSource().toString().contains("icon_start")){
			btnStart.setIcon(new ImageIcon("images/icon_start.png"));

		}else if(e.getSource().toString().contains("icon_how")){
			btnHow.setIcon(new ImageIcon("images/icon_how.png"));

		}else if(e.getSource().toString().contains("icon_rank")){
			btnRank.setIcon(new ImageIcon("images/icon_rank.png"));
		}
	}
}
