package JumpRabbit;

import java.sql.*;

public class SQLiteManager {
    //Database 변수
    public static final String SQLITE_JDBC_DRIVER = "org.sqlite.JDBC";

    private static final String dbFile = "src/JumpRabbitDB.db";
    private static final String SQLITE_FILE_DB_URL = "jdbc:sqlite:" + dbFile;

    //접속 정보 변수
    private Connection conn = null;
    private String driver = null;
    private String url = null;

    //생성자
    public SQLiteManager(){
        //JDBC Driver 설정
        this.driver = SQLITE_JDBC_DRIVER;
        this.url = SQLITE_FILE_DB_URL;
    }

    // DB 연결
    public Connection createConnection(){
        try{
            // SQLite JDBC 체크
            Class.forName(this.driver);

            // SQLite 데이터베이스 파일에 연결
            conn = DriverManager.getConnection(this.url);
//
//            ResultSet rs = stat.executeQuery("SELECT id, name FROM user_information");
//            while(rs.next()){
//                String id = rs.getString("id");
//                String name = rs.getString("name");
//            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("DB CONNECT");
        return this.conn;
    }

    // DB 연결 종료
    public void closeConnection(){
        try{
            if(this.conn != null)
                this.conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            this.conn = null;
        }

        System.out.println("DB CLOSE");
    }

    // DB 재연결
    public Connection ensureConnection(){
        try{
            if(this.conn == null){
                closeConnection();
                createConnection();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("DB RE-CONNECT");
        return this.conn;
    }

    // DB 연결 객체 가져오기
    public Connection getConnection(){
        return this.conn;
    }
}
