package JumpRabbit;

import panel.ScoreDB;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 850;
    public static final Color defaultColor = Color.decode("#ff42a5");

    public static Font font;

    public static void main(String[] args) throws SQLException {

        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/neodgm.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/neodgm.ttf")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        new JumpRabbit();
    }
}
