package pm.view;



import pm.dao.impl.StudentDaoImpl;
import pm.model.Student;

import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDaoImpl studentDao = new StudentDaoImpl();
        circleLevel1:
        while (true) {
            System.out.println("请选择：1.添加学生  2.修改学生  3.删除学生  4.查询学生  5.退出系统");
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    System.out.println("请输入年龄");
                    int age = scanner.nextInt();
                    System.out.println("请输入成绩");
                    double score = scanner.nextDouble();
                    Student student = new Student();
                    student.setName(name);
                    student.setAge(age);
                    student.setScore(score);
                    boolean result = studentDao.addStudent(student);
                    System.out.println(result ? "添加成功" : "添加失败");
                }
                break;
                case 2: {
                    System.out.println("请输入id: ");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    System.out.println("请输入年龄");
                    int age = scanner.nextInt();
                    System.out.println("请输入成绩");
                    double score = scanner.nextDouble();
                    studentDao.updateStudent(new Student(id, name, age, score));
                }
                break;
                case 3: {
                    System.out.println("请输入id: ");
                    int id = scanner.nextInt();
                    boolean result = studentDao.deleteStudent(id);
                    System.out.println(result ? "成功" : "失败");
                }
                break;
                case 4: {
                    circleLevel2:
                    while (true) {
                        System.out.println("请选择：1. 全查询  2.根据年龄区间查询  3.根据编号查询  4.根据姓名模糊查询  5.返回上级菜单");
                        switch (scanner.nextInt()) {
                            case 1: {
                                List<Student> students = studentDao.selectStudent();
                                for (Student student :
                                        students) {
                                    System.out.println(student.toString());
                                }
                            }
                            break;
                            case 2: {
                                System.out.println("请输入最小年龄: ");
                                int minAge = scanner.nextInt();
                                System.out.println("请输入最大年龄: ");
                                int maxAge = scanner.nextInt();
                                List<Student> students = studentDao.selectStudentByAge(minAge, maxAge);
                                for (Student student :
                                        students) {
                                    System.out.println(student.toString());
                                }
                            }
                            break;
                            case 3: {
                                System.out.println("请输入id");
                                int id = scanner.nextInt();
                                Student student = studentDao.selectStudentById(id);
                                System.out.println(student);
                            }
                            break;
                            case 4: {
                                System.out.println("请输入模糊姓名:");
                                String likeName = scanner.next();
                                List<Student> students = studentDao.selectStudentByName(likeName);
                                for (Student student :
                                        students) {
                                    System.out.println(student.toString());
                                }
                            }
                            break;
                            case 5: {
                                break circleLevel2;
                            }
                            default:
                                System.out.println("错误");
                        }
                    }
                }
                break;
                case 5: {
                }
                break circleLevel1;
                default:
                    System.out.println("错误");
            }
        }

    }
}

