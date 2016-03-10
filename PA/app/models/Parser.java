package models;

import com.avaje.ebean.Ebean;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, String[][]> xlsxParse(String sourceAddress) {
        try {
            File source = new File(sourceAddress);
            XSSFWorkbook wb = readFile(source);

            Map<String, String[][]> sheets = new HashMap<String, String[][]>();

            for (int i = 0; i < wb.getNumberOfSheets(); ++i) {
                XSSFSheet sheet = wb.getSheetAt(i);
                int n = sheet.getPhysicalNumberOfRows();
                int m = sheet.getRow(0).getPhysicalNumberOfCells();

                String[][] data = new String[n][m];
                for (int j = 1; j < n + 1; j++) {
                    XSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        for (int k = 0; k < m; k++) {
                            data[j - 1][k] = "empty";
                        }
                    } else {
                        for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); ++k) {
                            data[j - 1][k] = row.getCell(k).toString();
                        }

                    }
                }
                sheets.put(sheet.getSheetName(), data);
            }

            return sheets;
        } catch (IOException e) {
            System.err.println("Could not connect to EDU.");
            return null;
        }
    }


    private static XSSFWorkbook readFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        return new XSSFWorkbook(fis);
    }


    public static void objectsCreation(Map<String, String[][]> sheets) {

        String[][] Students = sheets.get("Students");
        String[][] Teachers = sheets.get("Lecturers");
        String[][] CourseTeacher = sheets.get("Course-Lecturer Mapping");
        String[][] StudentCourse = sheets.get("Student-Course Mapping");

        //Create Students
        //TODO:correct the date format
        for (int i = 0; i < Students.length; i++) {
            if (!(Students[i][0].equals("empty"))) {
                int id = Double.valueOf(Students[i][0]).intValue();
                System.err.println("before find");
                Student student = (Student) Person.find.byId(id);
                System.err.println("after find");
                String name = Students[i][1];
                String password = Students[i][2];


                if (student == null) {
                    student = new Student(id,  password, name, new Date());
                    Ebean.save(student);
                } else {
                    student.setName(name);
                    student.setPassword(password);
                    Ebean.update(student);
                }

            }
        }

        //Create Teachers
        for (int i = 0; i < Teachers.length; i++) {
            if (!(Teachers[i][0].equals("empty"))) {
                int id = Double.valueOf(Teachers[i][0]).intValue();
                Teacher teacher = (Teacher) Person.find.byId(id);
                String name = Teachers[i][1];
                String password = Students[i][2];


                if (teacher == null) {
                    teacher = new Teacher(id,  password , name, new Date());
                    Ebean.save(teacher);
                } else {
                    System.err.println("We are updating the Teacher with ID : " + id);
                    teacher.setName(name);
                    teacher.setPassword(password);
                    Ebean.update(teacher);
                }
            }
        }

        //Create Course-Teacher relation
        //TODO : we suppose that all the teachers that appear here are already in db
        for (int i = 0; i < CourseTeacher.length; i++) {
            if (!(CourseTeacher[i][0].equals("empty"))) {

                int id = Double.valueOf(CourseTeacher[i][0]).intValue();
                ProvidedCourse providedCourse = ProvidedCourse.find.where().eq("id", id).findUnique();
                int teacherID = Double.valueOf(CourseTeacher[i][3]).intValue();
                Teacher teacher = (Teacher) Person.find.byId(teacherID);

                if (teacher == null) {
                    System.err.println("The teacher with ID " + teacherID + " Doesn't exist! We are omitting this row");
                } else {
                    if (providedCourse == null) {
                        try {
                            int courseNo = Double.valueOf(CourseTeacher[i][1]).intValue();
                            String title = CourseTeacher[i][2];
                            String time = CourseTeacher[i][4];
                            Date finalExamTime = new Date();
                            String semester = CourseTeacher[i][6];
                            int groupID = Double.valueOf(CourseTeacher[i][7]).intValue();
                            String room = CourseTeacher[i][8];

                            providedCourse = new ProvidedCourse(id, courseNo, title, time, finalExamTime, teacherID, semester,
                                    groupID, room, 3);
                            Ebean.save(providedCourse);

                            teacher.addCurrentCourse(providedCourse);
                            Ebean.update(teacher);

                        } catch (Exception e) {
                            System.err.println("in course-teacher");
                            System.err.println(e);
                        }
                    }

                }

            }
        }

        //Create Course-Student relation
        for (int i = 0; i < StudentCourse.length; i++) {
            if (!(StudentCourse[i][0].equals("empty"))) {
                int studentID = Double.valueOf(StudentCourse[i][1]).intValue();
                int providedCourseId = Double.valueOf(StudentCourse[i][0]).intValue();
                Student student = (Student) Person.find.byId(studentID);
                ProvidedCourse providedCourse=ProvidedCourse.find.byId(providedCourseId);

                if (student==null || providedCourse==null){
                    System.err.println("One of student " + studentID + "or provided course ID "+providedCourseId+" is null");
                }
                else{
                    try {
                        student.addCurrentCourse(providedCourse);
                        Ebean.update(student);
                    } catch (Exception e) {
                        System.err.println("in course-student");
                        System.err.println(e);
                    }
                }
            }
        }

    }

}
