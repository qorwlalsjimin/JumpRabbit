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

	// �г�
	IntroPanel pIntro = new IntroPanel();
	HowPanel pHow = new HowPanel();
	static RankPanel pRank = new RankPanel();
	GamePanel pGame = new GamePanel();
	GOverPanel pOver = new GOverPanel();
	GClearPanel pClear = new GClearPanel();
	LoginPanel pLogin = new LoginPanel();
	JoinPanel pJoin = new JoinPanel();
	JoinNamePanel pJoinName = new JoinNamePanel();

	// Music ��ü
	static Music introMusic, gameMusic, gameclearMusic, gameoverMusic;

	//��Ʈ
	Font font;

    //���� ȭ�� ����
    public JumpRabbit(){
    	//* ó�� ȭ�� �г� �߰�
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

		card.show(panel, "intro"); // �������� ���� ���̴�
		System.out.println("�����ڿ��� ��Ʈ�� ���� ����");
		introMusic = new Music("bgm_basic", true);
		introMusic.start();

		panel.setBounds(0,0,1200,800);


		// Ŀ���� ���콺 Ŀ��
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("images/HH.png");
		Point point = new Point(10,10);
		Cursor cursor = tk.createCustomCursor(cursorimage, point, "");
		panel.setCursor(cursor);

		contentPane.add(panel);

        //* Frame �⺻ ����
        setTitle("Jump Rabbit!��");
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
				//TODO: ��Ʈ�� ȭ�� ���ö����� ���� �����ż� ���ļ� �鸲
				card.show(panel, "intro");
				if(gameMusic != null && gameMusic.getState() == Thread.State.TERMINATED){
					gameMusic.close();
				}
				if(introMusic != null && introMusic.getState() == Thread.State.TERMINATED){
					System.out.println("��Ʈ�ο� " + introMusic.getState());
					introMusic = new Music("bgm_basic", true);
					introMusic.start();
					System.out.println("��Ʈ�ο� " + introMusic.getState());
				}
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
				introMusic.close();
				System.out.println("��Ʈ�� ����");
				gameMusic = new Music("bgm_game", true);
				gameMusic.start();

				break;

			case "over":
				card.show(panel, "over");
				if(gameMusic.getState() == Thread.State.TERMINATED && gameMusic!=null){
					gameMusic.close();
				}
				gameoverMusic = new Music("sound_gameover", true);
				gameoverMusic.start();
				break;

			case "clear":
				card.show(panel, "clear");
				(new GClearPanel()).insertScore(false);
				if(gameMusic.getState() == Thread.State.TERMINATED && gameMusic!=null){
					gameMusic.close();
				}
				gameclearMusic = new Music("sound_gameclear", true);
				gameclearMusic.start();
				break;

			case "login":
				card.show(panel, "login");
				System.out.println("��Ʈ�ο� " + introMusic.getState());
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
