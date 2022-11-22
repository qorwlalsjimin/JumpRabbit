package JumpRabbit;

import panel.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JumpRabbit extends JFrame {

	private JPanel contentPane;
	static JPanel panel;
	static CardLayout card = new CardLayout();

	private static String currentPanel;

	// 패널
	IntroPanel pIntro = new IntroPanel();
	HowPanel pHow = new HowPanel();
	static RankPanel pRank = new RankPanel();
	GamePanel pGame = new GamePanel();
	GOverPanel pOver = new GOverPanel();
	GClearPanel pClear = new GClearPanel();
	LoginPanel pLogin = new LoginPanel();
	JoinPanel pJoin = new JoinPanel();
	JoinNamePanel pJoinName = new JoinNamePanel();

	// Music 객체
	static Music introMusic;
	static Music gameMusic;

	//폰트
	Font font;

    //게임 화면 설정
    public JumpRabbit(){
    	//* 처음 화면 패널 추가
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setLayout(card);
		panel.add("intro", pIntro);
		panel.add("how", pHow);
		panel.add("rank", pRank);
		panel.add("game", pGame);
		panel.add("over", pOver);
		panel.add("clear", pClear);
		panel.add("login",pLogin);
		panel.add("join",pJoin);
		panel.add("nickname", pJoinName);

		card.show(panel, "intro"); // 실행하자 마자 보이는

		introMusic = new Music("bgm_basic", true);
		introMusic.start();

		panel.setBounds(0,0,1200,800);


		// 커스텀 마우스 커서
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("images/HH.png");
		Point point = new Point(10,10);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "");
		panel.setCursor(cursor);

		contentPane.add(panel);

        //* Frame 기본 설정
        setTitle("Jump Rabbit!♥");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }

	public static void setCurrentPanel(String s) {
		currentPanel = s;
		changeCard();
	}


	public static void changeCard() {

		switch (currentPanel) {
			case "intro":
				card.show(panel, "intro");
				break;

			case "how":
				card.show(panel, "how");
				break;

			case "rank":
				card.show(panel, "rank");
				pRank.printRanking();
				break;

			case "game":
				card.show(panel, "game");
				//gameMusic = new Music("bgm_game", true);
				//gameMusic.start();
				//introMusic.close();

				break;

			case "over":
				card.show(panel, "over");
				break;

			case "clear":
				card.show(panel, "clear");
				(new GClearPanel()).insertScore(false);
				break;

			case "login":
				card.show(panel, "login");
				break;

			case "join":
				card.show(panel, "join");
				break;

			case "nickname":
				card.show(panel, "nickname");
				break;
		}
	}



}
