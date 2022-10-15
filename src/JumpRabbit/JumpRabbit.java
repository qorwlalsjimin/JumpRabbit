package JumpRabbit;

import panel.*;

import javax.swing.*;
import java.awt.*;

public class JumpRabbit extends JFrame {

	private JPanel contentPane;
	static JPanel panel;
	static CardLayout card = new CardLayout();

	private static String currentPanel;

	// 패널
	IntroPanel pIntro = new IntroPanel();
	HowPanel pHow = new HowPanel();
	RankPanel pRank = new RankPanel();
	GamePanel pGame = new GamePanel();
	GOverPanel pOver = new GOverPanel();
	GClearPanel pClear = new GClearPanel();

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

		card.show(panel, "intro"); // 실행하자 마자 보이는

		panel.setBounds(0,0,1200,800);

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

	public static void setCureentPanel(String s) {
		currentPanel = s;
		System.out.println("세터 실행!");
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
				break;

			case "game":
				card.show(panel, "game");
				break;

			case "over":
				card.show(panel, "over");
				break;

			case "clear":
				card.show(panel, "clear");
				break;
		}
	}
}
