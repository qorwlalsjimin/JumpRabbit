package JumpRabbit;

import java.sql.*;

public class SQLiteManager {
    //Database ����
    public static final String SQLITE_JDBC_DRIVER = "org.sqlite.JDBC";

    private static final String dbFile = "src/JumpRabbitDB.db";
    private static final String SQLITE_FILE_DB_URL = "jdbc:sqlite:" + dbFile;

    //���� ���� ����
    private Connection conn = null;
    private String driver = null;
    private String url = null;

    //������
    public SQLiteManager(){
        //JDBC Driver ����
        this.driver = SQLITE_JDBC_DRIVER;
        this.url = SQLITE_FILE_DB_URL;
    }

    // DB ����
    public Connection createConnection(){
        try{
            // SQLite JDBC üũ
            Class.forName(this.driver);

            // SQLite �����ͺ��̽� ���Ͽ� ����
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

    // DB ���� ����
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

    // DB �翬��
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

    // DB ���� ��ü ��������
    public Connection getConnection(){
        return this.conn;
    }
}
