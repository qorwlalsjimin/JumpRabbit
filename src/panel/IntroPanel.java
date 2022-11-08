package panel;

import JumpRabbit.JumpRabbit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import JumpRabbit.Main;


public class IntroPanel extends JPanel implements ActionListener, MouseListener {

    // ��ư
    static JButton btnStart;
    static JButton btnHow;
    static JButton btnRank;
	JLabel labelLogin;

	public static Boolean isLogin = false;
    ImageIcon mainScreen = new ImageIcon("images/screen_main.png");

    public IntroPanel() {
    	setLayout(null);
    	
    	//	����
    	btnStart = new JButton(new ImageIcon("images/icon_start.png"));
    	btnHow = new JButton(new ImageIcon("images/icon_how.png"));
    	btnRank = new JButton(new ImageIcon("images/icon_rank.png"));
		labelLogin = new JLabel("�α���");
    			
	    //	��ư ��ġ ����
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
		labelLogin.setBounds(1070, 10, 150,50);
	    
	    //	��ư ������, ���� ���ֱ�
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

		// �� �۾� ����
		labelLogin.setFont(Main.font.deriveFont(30f));
		labelLogin.setForeground(Color.decode("#ff42a5"));

	    // ��Ŭ��
	    btnStart.addActionListener(this);
	    btnHow.addActionListener(this);
	    btnRank.addActionListener(this);
		labelLogin.addMouseListener(this);

	    //	�гο� ��ư �߰�
        add(btnStart);
        add(btnHow);
        add(btnRank);
		add(labelLogin);
    }

	// ��� �̹��� ����
	public void paintComponent(Graphics g) {
		if(isLogin) labelLogin.setText("�α׾ƿ�");
		else labelLogin.setText("�α���");

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
		if(e.getSource().toString().contains("�α���"))
			JumpRabbit.setCurrentPanel("login");
		else{
			System.out.println("�α׾ƿ��Ͻ���?");
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
