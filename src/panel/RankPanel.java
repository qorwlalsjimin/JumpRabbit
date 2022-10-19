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

    // ��ư
    static JButton btnRankBack = new JButton(new ImageIcon("images/icon_rank_back.png"));

    ImageIcon rankScreen = new ImageIcon("images/screen_rank.png");

    JLabel labelRank, labelName, labelScore;

    private ArrayList listScore = new ArrayList();

    public RankPanel() {
        setLayout(null);

        // �ڷ� ���ư��� ��ư
	    btnRankBack.setBounds(1070, 700, 102, 64);
	    btnRankBack.setBorderPainted(false); btnRankBack.setContentAreaFilled(false);
	    add(btnRankBack);
        btnRankBack.addActionListener(this);

        // ���� �����ִ� ��
        printRanking();
    }

    private void printRanking() {
        System.out.println("�޼��� ����");

        //���� ����½� ArrayList�� ������ �߰�
        try (
                FileReader fr = new FileReader("src/test.txt");
                BufferedReader br = new BufferedReader(fr);
        ) {
            String readLine = null;
            while ((readLine = br.readLine()) != null) { //�����̷�������?
                this.listScore.add(readLine);
                System.out.println(readLine);
            }
        } catch (IOException e) {
            System.out.println("���� �ҷ����� ����");
        }

        // score�� ���� ����Ʈ(int)
        ArrayList<Integer> listScore = new ArrayList<Integer>();
        for (int i = 1; i <= this.listScore.size() / 3; i++) {
            listScore.add(Integer.valueOf((String) this.listScore.get(3 * i - 2)));
        }
        Collections.sort(listScore); //����

        // score�� ���� ����Ʈ(String)
        ArrayList<String> listScore2 = new ArrayList<String>();
        for (int i = 0; i < listScore.size(); i++) { //intŸ���� isScore�� �ٽ� String����
            listScore2.add(String.valueOf(listScore.get(i)));
        }

        int rank = 0;
        for (int i = listScore2.size(); i >= 1; i--) { //
            int x = this.listScore.indexOf(listScore2.get(i - 1)); //listScore�� �ε����� 1, 4,7..ã��
            rank++;
            callAllGen(x, rank);
        }

    }

    private void callAllGen(int x, int rank) { // x�� ���ھ ���� ������� �־������
        // y�� x�� ������������� 1~n���� for������ �־��� -> callAllGen�޼ҵ���°�����ŭ �Ȱ��ڴ�

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
