import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ScreenHow extends JPanel{
	ImageIcon howScreen = new ImageIcon("src/images/screen_how.png");
	
	ScreenHow(){
		JButton che = new JButton("�Űܿ�");
		add(che);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(howScreen.getImage(), 0, 0, null);
		setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
		super.paint(g);
	}
}
