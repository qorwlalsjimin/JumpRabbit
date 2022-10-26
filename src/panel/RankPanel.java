package panel;

import JumpRabbit.JumpRabbit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class RankPanel extends JPanel implements ActionListener {
    final int X_RANK = 380;
    final int X_NAME = 450;
    final int X_SCORE = 660;
    final int Y_LABEL = 290;

    final int W_RANK = 300;
    final int W_NAME = 300;
    final int W_SCORE = 200;
    final int H_LABEL = 50;

    // 버튼
    static JButton btnRankBack = new JButton(new ImageIcon("images/icon_rank_back.png"));

    ImageIcon rankScreen = new ImageIcon("images/screen_rank.png");

    JLabel labelRank, labelName, labelScore;
    ArrayList<JLabel> listRank = new ArrayList<>();
    ArrayList<JLabel> listName = new ArrayList<>();
    ArrayList<JLabel> listGrade = new ArrayList<>();

    private ArrayList listScore = new ArrayList();

    public RankPanel() {
        setLayout(null);

        // 뒤로 돌아가기 버튼
	    btnRankBack.setBounds(1070, 700, 102, 64);
	    btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
	    add(btnRankBack);
        btnRankBack.addActionListener(this);
    }

    public void printRanking() {
        System.out.println("랭킹 메서드 진입");
        this.listScore.clear();
        clearAllGen();


        try{

            String url = "jdbc:mysql://localhost:3306/jumprabbit";
            String userName = "jumprabbit";
            String password = "jumprabbit";
            String sql = "select * from user_score";

            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("name");
                int score = rs.getInt("score");

                this.listScore.add(name);
                this.listScore.add(Integer.toString(score));
            }

            System.out.println("select됨");

            st.close();
            rs.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        // score을 넣은 리스트(int)
        ArrayList<Integer> listScore = new ArrayList<Integer>();
        for (int i = 1; i <= this.listScore.size() / 2; i++) {
            listScore.add(Integer.valueOf((String) this.listScore.get(2 * i - 1)));
        }
        Collections.sort(listScore); //정렬

        System.out.println(listScore.toString());

        // score을 넣은 리스트(String)
        ArrayList<String> listScore2 = new ArrayList<String>();
        for (int i = 0; i < listScore.size(); i++) { //int타입인 isScore를 다시 String으로
            listScore2.add(String.valueOf(listScore.get(i)));
        }

        int rank = 0;
        for (int i = listScore2.size(); i >= 1; i--) { //
            int x = this.listScore.indexOf(listScore2.get(i - 1)); //listScore의 인덱스값 1, 4,7..찾기
            rank++;
            callAllGen(x, rank, i);
        }

        listScore.clear();
        listScore2.clear();
    }

    private void callAllGen(int x, int rank, int i) { // x는 스코어가 높은 점수대로 넣어줘야함
        // y는 x가 몇개들어가는지에따라서 1~n까지 for문으로 넣어줌 -> callAllGen메소드출력갯수만큼 똑같겠다
        if(rank<8){
            genRank(Integer.toString(rank), rank, i);
            genName(x - 1, rank, i);
            genScore(x, rank, i);
        }
    }

    private void genRank(String number, int rank, int i) {
        listRank.add(new JLabel((number)));
        listRank.get(rank-1).setFont(new Font("Neo둥근모", Font.PLAIN, 43));
        if(i==0)
            listRank.get(rank-1).setBounds(X_RANK, Y_LABEL, W_RANK, H_LABEL);
        else
            listRank.get(rank-1).setBounds(X_RANK, Y_LABEL + 50 * rank, W_RANK, H_LABEL);

        listRank.get(rank-1).setFont(new Font("Airal",0, 29));
        listRank.get(rank-1).setForeground(Color.decode("#ff42a5"));
        add(listRank.get(rank-1));
    }


    private void genName(int index, int rank, int i) {
        listName.add(new JLabel((String) listScore.get(index)));
        listName.get(rank-1).setFont(new Font("Neo둥근모", Font.PLAIN, 28));
        listName.get(rank-1).setForeground(Color.decode("#ff42a5"));

        if(i==0)
            listName.get(rank-1).setBounds(X_RANK, Y_LABEL, W_RANK, H_LABEL);
        else
            listName.get(rank-1).setBounds(X_NAME, Y_LABEL + 50 * rank, W_NAME, H_LABEL);
        add(listName.get(rank-1));
    }

    private void genScore(int index, int rank, int i) {
        listGrade.add(new JLabel((String) listScore.get(index)));
        listGrade.get(rank-1).setFont(new Font("Neo둥근모", Font.PLAIN, 41));
        if(i==0)
            listGrade.get(rank-1).setBounds(X_RANK, Y_LABEL, W_RANK, H_LABEL);
        else
            listGrade.get(rank-1).setBounds(X_SCORE, Y_LABEL + 50 * rank, W_SCORE, H_LABEL);
        listGrade.get(rank-1).setFont(new Font("Airal",0, 29));
        listGrade.get(rank-1).setForeground(Color.decode("#ff42a5"));
        add(listGrade.get(rank-1));
    }

    private void clearAllGen(){
        for(int i = 0; i<listRank.size(); i++){
            this.remove(listRank.get(i));
            this.remove(listName.get(i));
            this.remove(listGrade.get(i));
        }

        listRank.clear();
        listName.clear();
        listGrade.clear();

    }

    // 배경 이미지 설정
	public void paintComponent(Graphics g) {
		g.drawImage(rankScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnRankBack)
            JumpRabbit.setCureentPanel("intro");
    }
}
