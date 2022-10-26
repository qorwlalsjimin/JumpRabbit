package panel;

import JumpRabbit.JumpRabbit;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class IntroPanel extends JPanel implements ActionListener{

    // ��ư
    static JButton btnStart;
    static JButton btnHow;
    static JButton btnRank;
    
    ImageIcon mainScreen = new ImageIcon("images/screen_main.png");
    
    public IntroPanel() {
    	setLayout(null);
    	
    	//	����
    	btnStart = new JButton(new ImageIcon("images/icon_start.png"));
    	btnHow = new JButton(new ImageIcon("images/icon_how.png"));
    	btnRank = new JButton(new ImageIcon("images/icon_rank.png"));
    			
	    //	��ư ��ġ ����
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
	    
	    //	��ư ������, ���� ���ֱ�
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);
	    
	    // ��Ŭ��
	    btnStart.addActionListener(this);
	    btnHow.addActionListener(this);
	    btnRank.addActionListener(this);

	    //	�гο� ��ư �߰�
        add(btnStart);
        add(btnHow);
        add(btnRank);
    }

	// ��� �̹��� ����
	public void paintComponent(Graphics g) {
		g.drawImage(mainScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnStart)
			JumpRabbit.setCureentPanel("login");
		else if(ob == btnHow)
			JumpRabbit.setCureentPanel("how");
		else if(ob == btnRank)
			JumpRabbit.setCureentPanel("rank");

	}


}
