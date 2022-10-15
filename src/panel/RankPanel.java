package panel;

import JumpRabbit.JumpRabbit;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RankPanel extends JPanel implements ActionListener {

    // ¹öÆ°
    static JButton btnRankBack = new JButton(new ImageIcon("images/icon_rank_back.png"));

    ImageIcon rankScreen = new ImageIcon("images/screen_rank.png");
    
    public RankPanel() {
        setLayout(null);

	    btnRankBack.setBounds(1070, 700, 102, 64);
	    btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
	    add(btnRankBack);
        btnRankBack.addActionListener(this);
    }


	public void paintComponent(Graphics g) {
		g.drawImage(rankScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnRankBack)
            JumpRabbit.setCureentPanel("intro");
    }
}
