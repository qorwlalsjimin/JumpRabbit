import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class JumpRabbit extends JFrame {
    private Image bufferImage;
    private Graphics screenGraphic;
    
    // ��ư
    static JButton btnStart = new JButton(new ImageIcon("src/images/icon_start.png"));
    static JButton btnHow = new JButton(new ImageIcon("src/images/icon_how.png"));
    static JButton btnRank = new JButton(new ImageIcon("src/images/icon_rank.png"));

    // �̹��� �ҽ�
    ImageIcon mainScreen = new ImageIcon("src/images/screen_main.png");
    ImageIcon howScreen = new ImageIcon("src/images/screen_how.png");
    ImageIcon rankScreen = new ImageIcon("src/images/screen_rank.png");
    
    JScrollPane scrollMain, scrollHow;
    private boolean isMainScreen=true, isHowScreen=false, isRankScreen=false;
    
    // ����ȭ�� �г�
    JPanel pStart = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(mainScreen.getImage(), 0, 0, null);
			setOpaque(false);
            super.paintComponent(g);
		}
	};
	
    JPanel pHow = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(howScreen.getImage(), 0, 0, null);
			setOpaque(false);
            super.paintComponent(g);
        }
	};

    //���� ȭ�� ����
    public JumpRabbit(){
    	//* ó�� ȭ�� �г� �߰�
        
    	pStart.setBounds(0,0,1200,850);
		pHow.setBounds(0,0,1200,850);
		
		pStart.setLayout(null);
		pHow.setLayout(null);       
		
        pHow.setVisible(false);
        
        add(pStart);
        add(pHow);
        
	    //	��ư ��ġ ����
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
	    
	    //	��ư ������, ���� ���ֱ�
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

	    //	�гο� ��ư �߰�
        pStart.add(btnStart);
        pStart.add(btnHow);
        pStart.add(btnRank);
        
	    //	��ư Ŭ�� �̺�Ʈ
        setIsScreen("���");
	    btnHow.addMouseListener(btnListener);
        
        //* Frame �⺻ ����
        setTitle("Jump Rabbit!��");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }
    
    MouseAdapter btnListener = new MouseAdapter() {
    	public void mousePressed(java.awt.event.MouseEvent arg0) {
    		if(isMainScreen) {
        		pStart.setVisible(true);
        		pHow.setVisible(false);
    		}else if(isHowScreen) {
    			pStart.setVisible(false);
        		pHow.setVisible(true);
    		}else if(isRankScreen) {
    			pStart.setVisible(false);
        		pHow.setVisible(false);
    		}
    	};
	};
	
	void setIsScreen(String screen) {
		switch(screen){
		case "����" :
			isMainScreen=true;
			isHowScreen=false;
			isRankScreen=false;
			break;
		case "���" :
			isMainScreen=false;
			isHowScreen=true;
			isRankScreen=false;
			break;
		case "��ũ" :
			isMainScreen=false;
			isHowScreen=false;
			isRankScreen=true;
			break;
		}
	}
    
}
