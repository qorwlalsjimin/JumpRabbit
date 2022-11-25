package panel;

import JumpRabbit.JumpRabbit;
import JumpRabbit.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class RankPanel extends JPanel implements ActionListener, MouseListener {
    final int X_RANK = 380;
    final int X_NAME = 450;
    final int X_SCORE = 660;
    final int Y_LABEL = 290;

    final int W_RANK = 300;
    final int W_NAME = 300;
    final int W_SCORE = 200;
    final int H_LABEL = 50;

    ImageIcon rankScreen = new ImageIcon("images/screen_rank.png");

    // ��ư
    static JButton btnBack = new JButton(new ImageIcon("images/icon_back.png"));

    ArrayList<JLabel> listRank = new ArrayList<>();
    ArrayList<JLabel> listName = new ArrayList<>();
    ArrayList<JLabel> listGrade = new ArrayList<>();

    private ArrayList listScore = new ArrayList();

    public RankPanel() {
        setLayout(null);

        // �ڷ� ���ư��� ��ư
	    btnBack.setBounds(1070, 700, 102, 64);
	    btnBack.setBorderPainted(false); btnBack.setContentAreaFilled(false);
	    add(btnBack);

        btnBack.addActionListener(this);
        btnBack.addMouseListener(this);
    }

    public void printRanking() {
        this.listScore.clear();
        clearAllGen();
        System.out.println("��ŷ �޼��� ����2");

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

            System.out.println("select��");

            st.close();
            rs.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //(new IntroPanel()).hideLoading();
        System.out.println("��ŷ �޼��� ����3");

        // TODO: ������ ������ �ϳ��� �г������θ� ���̴� ����
        // score�� ���� ����Ʈ(int)
        ArrayList<Integer> listScore = new ArrayList<Integer>();
        for (int i = 1; i <= this.listScore.size() / 2; i++) {
            listScore.add(Integer.valueOf((String) this.listScore.get(2 * i - 1)));
        }
        Collections.sort(listScore); //����

        System.out.println(listScore.toString());

        // score�� ���� ����Ʈ(String)
        ArrayList<String> listScore2 = new ArrayList<String>();
        for (int i = 0; i < listScore.size(); i++) { //intŸ���� isScore�� �ٽ� String����
            listScore2.add(String.valueOf(listScore.get(i)));
        }

        int rank = 0;
        for (int i = listScore2.size(); i >= 1; i--) { //
            int x = this.listScore.indexOf(listScore2.get(i - 1)); //listScore�� �ε����� 1, 4,7..ã��
            rank++;
            callAllGen(x, rank, i);
        }

        listScore.clear();
        listScore2.clear();

        System.out.println("��ŷ �޼��� ����4");
    }

    private void callAllGen(int x, int rank, int i) { // x�� ���ھ ���� ������� �־������
        // y�� x�� ������������� 1~n���� for������ �־��� -> callAllGen�޼ҵ���°�����ŭ �Ȱ��ڴ�
        if(rank<8){
            genRank(Integer.toString(rank), rank, i);
            genName(x - 1, rank, i);
            genScore(x, rank, i);
        }
    }

    private void genRank(String number, int rank, int i) {
        listRank.add(new JLabel((number)));
        if(i==0)
            listRank.get(rank-1).setBounds(X_RANK, Y_LABEL, W_RANK, H_LABEL);
        else
            listRank.get(rank-1).setBounds(X_RANK, Y_LABEL + 50 * rank, W_RANK, H_LABEL);

        listRank.get(rank-1).setFont(Main.font.deriveFont(29f));
        listRank.get(rank-1).setForeground(Main.defaultColor);
        add(listRank.get(rank-1));
    }


    private void genName(int index, int rank, int i) {
        listName.add(new JLabel((String) listScore.get(index)));
        listName.get(rank-1).setFont(Main.font.deriveFont(29f));
        listName.get(rank-1).setForeground(Main.defaultColor);

        if(i==0)
            listName.get(rank-1).setBounds(X_RANK, Y_LABEL, W_RANK, H_LABEL);
        else
            listName.get(rank-1).setBounds(X_NAME, Y_LABEL + 50 * rank, W_NAME, H_LABEL);
        add(listName.get(rank-1));
    }

    private void genScore(int index, int rank, int i) {
        listGrade.add(new JLabel((String) listScore.get(index)));
        listGrade.get(rank-1).setFont(Main.font.deriveFont(41f));
        if(i==0)
            listGrade.get(rank-1).setBounds(X_RANK, Y_LABEL, W_RANK, H_LABEL);
        else
            listGrade.get(rank-1).setBounds(X_SCORE, Y_LABEL + 50 * rank, W_SCORE, H_LABEL);
        listGrade.get(rank-1).setFont(Main.font.deriveFont(29f));
        listGrade.get(rank-1).setForeground(Main.defaultColor);
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

    // ��� �̹��� ����
	public void paintComponent(Graphics g) {
		g.drawImage(rankScreen.getImage(), 0, 0, null);
		setOpaque(false);
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if(ob == btnBack)
            JumpRabbit.setCurrentPanel("intro");
    }

    // ���콺 ������ �߻� �޼��� ����
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().toString().contains("icon_back"))
            btnBack.setIcon(new ImageIcon("images/icon_back_entered.png"));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().toString().contains("icon_back_entered"))
            btnBack.setIcon(new ImageIcon("images/icon_back.png"));
    }
}
