package pm.dao;





import pm.model.Student;

import java.util.List;

public interface StudentDao {

    //指定程序中可能出现的所有抽象方法！
    //设计添加方法
    boolean addStudent(Student student);
    //问题：参数设置的太多比较麻烦！能不能想办法将参数压缩一下！压缩成一个参数！
    //自定义一个类型用于容纳name、age、score这几个数据就可以！
    //新建实体类对应数据库表结构用于容纳数据，当做参数进行传递！
    //实体类：就是一个自定义的Java类用于对应数据库的表结构，规则如下
    //(1). 数据库中有几张表，就建立几个实体类
    //(2). 表中有几个字段，类中就声明几个成员变量
    //实体类：model包/entity包(工作中！)

    //设计删除规范
    boolean deleteStudent(int id);

    //设计修改规范
    boolean updateStudent(Student student);

    //设计查询规范
    //设计全查询规范：建议写方法的时候将ResultSet的结果重新装进List返回！
    //为什么要这么做？不要问为什么？先干就完了！
    List<Student> selectStudent();

    //设计根据年龄区间查询
    List<Student> selectStudentByAge(int minAge, int maxAge);

    //设计根据姓名模糊查询
    List<Student> selectStudentByName(String name);

    //设计根据id查询学生
    Student selectStudentById(int id);
    //任务：不要问为什么这么写！只要能实现我的要求即可！
}








