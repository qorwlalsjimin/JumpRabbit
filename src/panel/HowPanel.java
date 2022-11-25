package panel;

import JumpRabbit.JumpRabbit;
import dialog.GuestDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HowPanel extends JPanel implements ActionListener, MouseListener {

    // 버튼
    static JButton btnHowStart = new JButton(new ImageIcon("images/icon_how_start.png"));

    
    ImageIcon howScreen = new ImageIcon("images/screen_how.png");
    
    public HowPanel() {
    	setLayout(null);

    	//	정의
	    btnHowStart.setBounds(790, 560, 182, 56);
	    btnHowStart.setBorderPainted(false); 
	    btnHowStart.setContentAreaFilled(false);
	    add(btnHowStart);
		btnHowStart.addActionListener(this);

		btnHowStart.addMouseListener(this);
	    
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

		if(ob == btnHowStart){
			if(!IntroPanel.isLogin){
				new GuestDialog();
			}
			else{
				JumpRabbit.setCurrentPanel("game");
			}
		}
	}

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
		if(e.getSource().toString().contains("icon_how_start")){
			btnHowStart.setIcon(new ImageIcon("images/icon_how_start_entered.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().toString().contains("icon_how_start_entered"))
			btnHowStart.setIcon(new ImageIcon("images/icon_how_start.png"));
	}
}
