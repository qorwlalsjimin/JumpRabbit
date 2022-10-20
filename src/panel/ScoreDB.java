package panel;

import java.sql.*;

public class ScoreDB {
    static int score = 0;

    String url = "jdbc:mysql://localhost:3306/jumprabbit";
    String userName = "jumprabbit";
    String password = "jumprabbit";

    Connection conn = DriverManager.getConnection(url, userName, password);

    public ScoreDB() throws SQLException {}

    public void selectScore(){
        try{
            String sql = "select * from user_score";

            // 테이블 select해서 값 보기
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String name = rs.getString("name");
                int score = rs.getInt("score");
            }

            st.close();
            rs.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertScore(){
        try{
            String sql = "INSERT INTO user_score VALUES (?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "은영");
            pstmt.setInt(2, 100);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
