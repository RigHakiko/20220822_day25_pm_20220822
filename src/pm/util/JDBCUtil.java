package pm.util;

import java.sql.*;

public class JDBCUtil {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    //连接数据库的地址信息(如果添加数据中文在数据库中出现乱码时这么写！)
    public static final String URL = "jdbc:mysql://localhost:3306/fckj_6";
    //连接数数据的账号信息
    public static final String USER = "root";
    //连接数数据的密码信息
    public static final String PASSWORD = "root";

    //共通类：提取JDBC共通的代码！
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //(1). 加载JDBC驱动(保证JDBC可以运行！)(获取Driver类的字节码信息！)
            Class.forName(DRIVER);
            //(2). 获取连接对象(建立和MySQL的连接通道)
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /*public static void close(PreparedStatement ps, Connection conn) {
        try {
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    //提取关闭资源的共通代码
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
