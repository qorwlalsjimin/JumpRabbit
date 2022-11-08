package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import JumpRabbit.Main;
public class GClearPanel extends JPanel implements ActionListener {
    ImageIcon clearScreen = new ImageIcon("images/screen_gameclear.png");

    JButton btnRe = new JButton(new ImageIcon("images/icon_game_return.png"));
    JButton btnRank = new JButton(new ImageIcon("images/icon_game_rank.png"));

    JLabel labelScore = new JLabel(Integer.toString(ScoreDB.score));

    public GClearPanel(){
        setLayout(null);

        btnRe.setBounds(549, 488, 226, 57);
        btnRank.setBounds(814, 488, 149, 57);

        labelScore.setBounds(465, 373, 200, 45);
        labelScore.setFont(Main.font.deriveFont(64f));
        labelScore.setForeground(Main.defaultColor);

        //	버튼 윤곽선, 배경색 없애기
        btnRe.setBorderPainted(false); btnRe.setContentAreaFilled(false);
        btnRank.setBorderPainted(false); btnRank.setContentAreaFilled(false);

        add(btnRe);
        add(btnRank);
        add(labelScore);

        btnRe.addActionListener(this);
        btnRank.addActionListener(this);
    }


    public void paintComponent(Graphics g) {
        g.drawImage(clearScreen.getImage(), 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnRe)
            JumpRabbit.setCurrentPanel("game");
        else if(ob == btnRank)
            JumpRabbit.setCurrentPanel("rank");
    }

    // TODO: table의 name(닉네임) 컬럼 FK로 사용하기
    public void insertScore(boolean isExist){
        try{
            String url = "jdbc:mysql://localhost:3306/jumprabbit";
            String userName = "jumprabbit";
            String password = "jumprabbit";
            String sql = null;

            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement pstmt = null;

            if(!isExist){ //닉네임 없음 insert
                sql = "INSERT INTO user_score VALUES (?,?)";
                pstmt = conn.prepareStatement(sql);

                System.out.println("닉네임: "+JoinNamePanel.Nickname);
                pstmt.setString(1, JoinNamePanel.Nickname);
                pstmt.setInt(2, 450);

                System.out.println("insert됨");
            }else{ //닉네임 있음 update
                sql = "update user_score set score=? where name=?;";
                pstmt = conn.prepareStatement(sql);

                System.out.println("닉네임: "+JoinNamePanel.Nickname);
                pstmt.setInt(1, 656);
                pstmt.setString(2, JoinNamePanel.Nickname);

                System.out.println("update됨");
            }
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }catch (Exception e){
            if(e.toString().contains("PRIMARY")) {
                System.out.println("업데이트해야해");
                insertScore(true);
            }
            else{
                System.out.println("오류가 발생했습니다.");
                e.printStackTrace();
            }
        }
    }
}
