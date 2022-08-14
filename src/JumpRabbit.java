import javax.swing.*;
import java.awt.*;

public class JumpRabbit extends JFrame {
    private Image bufferImage;
    private Graphics screenGraphic;

    private Image mainScreen = new ImageIcon("src/images/screen_main.png").getImage();
    private Image howScreen = new ImageIcon("src/images/screen_how.png").getImage();
    private Image rankScreen = new ImageIcon("src/images/screen_rank.png").getImage();

    private boolean isMainScreen, isHowScree, isRankScreen;

    //게임 화면 설정
    public JumpRabbit(){
        setTitle("Jump Rabbit!♥");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
    }
}
