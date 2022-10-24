package panel;

import JumpRabbit.JumpRabbit;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener{

	JButton btn = new JButton("이동하자");
	JLabel labelID = new JLabel("ID");
	JLabel labelPW = new JLabel("ID");
    ImageIcon howScreen = new ImageIcon("images/screen_how.png");
    
    public LoginPanel() {
    	setLayout(null);

		btn.setBounds(200,200,0,0);
    	//	정의
	    labelID.setBounds(790, 560, 182, 56);
	    add(labelID);
		add(btn);
    }


	public void paintComponent(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btn)
			JumpRabbit.setCureentPanel("game");
	}
}
