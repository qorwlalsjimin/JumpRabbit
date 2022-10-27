package panel;

import JumpRabbit.JumpRabbit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

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
        labelScore.setFont(new Font("Neo둥근모",Font.PLAIN, 110));
        labelScore.setForeground(Color.decode("#ff42a5"));

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
            JumpRabbit.setCureentPanel("game");
        else if(ob == btnRank)
            JumpRabbit.setCureentPanel("rank");
    }

    // TODO: 같은 닉네임이 DB에 있으면 update문으로 처리
    // TODO: table의 name(닉네임) 컬럼 FK로 사용하기
    public void insertScore(){
        try{
            String url = "jdbc:mysql://localhost:3306/jumprabbit";
            String userName = "jumprabbit";
            String password = "jumprabbit";
            String sql = "INSERT INTO user_score VALUES (?,?)";

            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, JoinNamePanel.textNickname.getText());
            pstmt.setInt(2, 450);

            pstmt.executeUpdate();
            System.out.println("insert됨");

            pstmt.close();
            conn.close();
            conn.close();
        //}catch (SQLIntegrityConstraintViolationException e) {
        //    System.out.println("이미 존재하는 아이디입니다.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
