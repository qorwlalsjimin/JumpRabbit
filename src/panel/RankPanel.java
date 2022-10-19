package panel;

import JumpRabbit.JumpRabbit;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class RankPanel extends JPanel implements ActionListener {
    final int X_RANK = 370;
    final int X_NAME = 450;
    final int X_SCORE = 660;
    final int Y_LABEL = 350;

    final int W_RANK = 30;
    final int W_NAME = 100;
    final int W_SCORE = 200;
    final int H_LABEL = 50;

    // 버튼
    static JButton btnRankBack = new JButton(new ImageIcon("images/icon_rank_back.png"));

    ImageIcon rankScreen = new ImageIcon("images/screen_rank.png");

    JLabel labelRank, labelName, labelScore;

    private ArrayList listScore = new ArrayList();

    public RankPanel() {
        setLayout(null);

        // 뒤로 돌아가기 버튼
	    btnRankBack.setBounds(1070, 700, 102, 64);
	    btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
	    add(btnRankBack);
        btnRankBack.addActionListener(this);

        // 순위 보여주는 라벨
        printRanking();
    }

    private void printRanking() {
        System.out.println("메서드 실행");

        //파일 입출력시 ArrayList에 데이터 추가
        try (
                FileReader fr = new FileReader("src/test.txt");
                BufferedReader br = new BufferedReader(fr);
        ) {
            String readLine = null;
            while ((readLine = br.readLine()) != null) { //　왜이렇게하지?
                this.listScore.add(readLine);
                System.out.println(readLine);
            }
        } catch (IOException e) {
            System.out.println("파일 불러오기 실패");
        }

        // score을 넣은 리스트(int)
        ArrayList<Integer> listScore = new ArrayList<Integer>();
        for (int i = 1; i <= this.listScore.size() / 3; i++) {
            listScore.add(Integer.valueOf((String) this.listScore.get(3 * i - 2)));
        }
        Collections.sort(listScore); //정렬

        // score을 넣은 리스트(String)
        ArrayList<String> listScore2 = new ArrayList<String>();
        for (int i = 0; i < listScore.size(); i++) { //int타입인 isScore를 다시 String으로
            listScore2.add(String.valueOf(listScore.get(i)));
        }

        int rank = 0;
        for (int i = listScore2.size(); i >= 1; i--) { //
            int x = this.listScore.indexOf(listScore2.get(i - 1)); //listScore의 인덱스값 1, 4,7..찾기
            rank++;
            callAllGen(x, rank);
        }

    }

    private void callAllGen(int x, int rank) { // x는 스코어가 높은 점수대로 넣어줘야함
        // y는 x가 몇개들어가는지에따라서 1~n까지 for문으로 넣어줌 -> callAllGen메소드출력갯수만큼 똑같겠다

        genRank(Integer.toString(rank), rank);
        genName(x - 1, rank);
        genScore(x, rank);
    }

    private void genRank(String number, int rank) {
        labelRank = new JLabel(number);
        labelRank.setBounds(X_RANK, Y_LABEL + 25 * rank, W_RANK, H_LABEL);
        add(labelRank);
    }


    private void genName(int index, int rank) {
        labelName = new JLabel((String) listScore.get(index));
        labelName.setBounds(X_NAME, Y_LABEL + 25 * rank, W_NAME, H_LABEL);
        add(labelName);
    }

    private void genScore(int index, int rank) {
        labelScore = new JLabel((String) listScore.get(index));
        labelScore.setBounds(X_SCORE, Y_LABEL + 25 * rank, W_SCORE, H_LABEL);
        add(labelScore);
    }

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
