import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class JumpRabbit extends JFrame {
    private Image bufferImage;
    private Graphics screenGraphic;
    
    // 버튼
    static JButton btnStart = new JButton(new ImageIcon("src/images/icon_start.png"));
    static JButton btnHow = new JButton(new ImageIcon("src/images/icon_how.png"));
    static JButton btnRank = new JButton(new ImageIcon("src/images/icon_rank.png"));

    // 이미지 소스
    ImageIcon mainScreen = new ImageIcon("src/images/screen_main.png");
    ImageIcon howScreen = new ImageIcon("src/images/screen_how.png");
    ImageIcon rankScreen = new ImageIcon("src/images/screen_rank.png");
    
    JScrollPane scrollMain, scrollHow;
    private boolean isMainScreen=true, isHowScreen=false, isRankScreen=false;
    
    // 시작화면 패널
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

    //게임 화면 설정
    public JumpRabbit(){
    	//* 처음 화면 패널 추가
        
    	pStart.setBounds(0,0,1200,850);
		pHow.setBounds(0,0,1200,850);
		
		pStart.setLayout(null);
		pHow.setLayout(null);       
		
        pHow.setVisible(false);
        
        add(pStart);
        add(pHow);
        
	    //	버튼 위치 지정
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
	    
	    //	버튼 윤곽선, 배경색 없애기
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

	    //	패널에 버튼 추가
        pStart.add(btnStart);
        pStart.add(btnHow);
        pStart.add(btnRank);
        
	    //	버튼 클릭 이벤트
        setIsScreen("방법");
	    btnHow.addMouseListener(btnListener);
        
        //* Frame 기본 설정
        setTitle("Jump Rabbit!♥");
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
		case "시작" :
			isMainScreen=true;
			isHowScreen=false;
			isRankScreen=false;
			break;
		case "방법" :
			isMainScreen=false;
			isHowScreen=true;
			isRankScreen=false;
			break;
		case "랭크" :
			isMainScreen=false;
			isHowScreen=false;
			isRankScreen=true;
			break;
		}
	}
    
}
