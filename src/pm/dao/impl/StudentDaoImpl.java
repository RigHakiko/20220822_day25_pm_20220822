package pm.dao.impl;



import pm.dao.StudentDao;
import pm.model.Student;
import pm.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    //JDBC驱动信息(类的全类名)
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    //连接数据库的地址信息(如果添加数据中文在数据库中出现乱码时这么写！)
    public static final String URL = "jdbc:mysql://localhost:3306/fckj_6";
    //连接数数据的账号信息
    public static final String USER = "root";
    //连接数数据的密码信息
    public static final String PASSWORD = "root";

    @Override
    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
            String sql = "insert into student values(default, ?, ?, ?);";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setDouble(3, student.getScore());
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            result = ps.executeUpdate();
            //result：表示执行成功的条数！
            //应该将result当做结果进行返回！
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
        }
        return result > 0;
    }

    @Override
    public boolean deleteStudent(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            //conn：指代连接数据库的通道！
            //(3). 准备SQL语句(通过定义字符串变量即可！)
            String sql = "delete from student where id = ?";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            result = ps.executeUpdate();
            //result：表示执行成功的条数！
            //应该将result当做结果进行返回！
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null,ps,conn);
        }
        return result > 0;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update student set name = ?, age = ? ,score = ? where id = ?";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setDouble(3, student.getScore());
            ps.setInt(4, student.getId());
            //ps：指代预编译对象(车)
            //(5). 通过预编译对象将SQL语句发送到MySQL并执行该SQL语句！
            //1>. 执行DML语句：ps.executeUpdate();   (方法返回整数结果)
            //2>. 执行DQL语句：ps.executeQuery();    (方法返回结果集结果)
            result = ps.executeUpdate();
            //result：表示执行成功的条数！
            //应该将result当做结果进行返回！
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //(6). 关闭资源(后创建的资源先关闭！！！)
            JDBCUtil.close(null,ps,conn);
        }
        return result > 0;
    }

    @Override
    public List<Student> selectStudent() {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id, name, age, score from student";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double score = rs.getDouble("score");
                students.add(new Student(id, name, age, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return students;
    }

    @Override
    public List<Student> selectStudentByAge(int minAge, int maxAge) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id, name, age, score from student where age between ? and ?;";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            ps.setInt(1, minAge);
            ps.setInt(2, maxAge);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double score = rs.getDouble("score");
                students.add(new Student(id, name, age, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return students;
    }

    @Override
    public List<Student> selectStudentByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs= null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id, name, age, score from student where name like concat('%',?,'%')";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name2 = rs.getString("name");
                int age = rs.getInt("age");
                double score = rs.getDouble("score");
                students.add(new Student(id, name2, age, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return students;
    }

    @Override
    public Student selectStudentById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        ArrayList<Student> students = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select id, name, age, score from student where id = ?";
            //(4). 获取预编译对象装载SQL语句(找一台车装货！)
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double score = rs.getDouble("score");
                students.add(new Student(id, name, age, score));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return students.size()>0? students.get(0):null;
    }
}
