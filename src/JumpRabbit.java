import javax.swing.*;
import java.awt.*;

public class JumpRabbit extends JFrame {
    private Image bufferImage;
    private Graphics screenGraphic;

    ImageIcon mainScreen = new ImageIcon("src/images/screen_main.png");
    private Image howScreen = new ImageIcon("src/images/screen_how.png").getImage();
    private Image rankScreen = new ImageIcon("src/images/screen_rank.png").getImage();

    private boolean isMainScreen, isHowScree, isRankScreen;
    
    JScrollPane scrollPane;

    //게임 화면 설정
    public JumpRabbit(){
    	//패널 추가
    	JPanel p = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainScreen.getImage(), 0, 0, null);
				setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
			}
		};
		p.setLayout(null); //절대 위치 사용
		
		//버튼 추가
		JButton btnStart = new JButton(new ImageIcon("src/images/icon_start.png"));
	    JButton btnHow = new JButton(new ImageIcon("src/images/icon_how.png"));
	    JButton btnRank = new JButton(new ImageIcon("src/images/icon_rank.png"));
	    
	    //	버튼 위치 지정
	    btnStart.setBounds(183, 614, 198, 61);
	    btnHow.setBounds(554, 614, 124, 61);
	    btnRank.setBounds(867, 614, 162, 61);
	    
	    //	버튼 윤곽선, 배경색 없애기
	    btnStart.setBorderPainted(false); btnStart.setContentAreaFilled(false);
	    btnHow.setBorderPainted(false); btnHow.setContentAreaFilled(false);
	    btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);
	    
	    //	패널에 버튼 추가
        p.add(btnStart);
        p.add(btnHow);
        p.add(btnRank);
        
        scrollPane = new JScrollPane(p);
        setContentPane(scrollPane);
    	
        setTitle("Jump Rabbit!♥");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }
    
}
