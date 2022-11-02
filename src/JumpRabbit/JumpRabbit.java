package JumpRabbit;

import panel.*;

import javax.swing.*;
import java.awt.*;

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
	GuestPanel pGuest = new GuestPanel();
	LogoutPanel pLogout = new LogoutPanel();

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
		panel.add("guest",pGuest);
		panel.add("logout",pLogout);

		card.show(panel, "intro"); // �������� ���� ���̴�

		panel.setBounds(0,0,1200,800);

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

			case "guest":
				card.show(panel, "guest");
				break;

			case "logout":
				card.show(panel, "logout");
				break;


		}
	}
}
