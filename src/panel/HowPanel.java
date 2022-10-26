package panel;

import JumpRabbit.JumpRabbit;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HowPanel extends JPanel implements ActionListener{

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
	    
    }


	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnHowStart)
			JumpRabbit.setCureentPanel("login");
	}
}
