// Connor Hathaway

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

class StudentFileManager {
    ArrayList<Student> student = new ArrayList<Student>();

    StudentFileManager (String filename) {
        try {
            File myObj = new File(filename);
            myObj.createNewFile();
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String studentInfo = myReader.nextLine();
                StringTokenizer token = new StringTokenizer(studentInfo, ",");

                Student studentObj = new Student(Integer.parseInt(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()));

                student.add(studentObj);
            }
            myReader.close();
        }
        catch(Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    boolean AddStudent (int id, String name, String address, String city, String state, String zip, GridPane gridPane) {
        if (GetStudent(id) == null) {
            try {
                FileWriter myWriter = new FileWriter("Student.txt", true);
                BufferedReader myReader = new BufferedReader(new FileReader("Student.txt"));
                if (myReader.readLine() != null) {
                    myWriter.write("\n");
                }
                String infoToFile = String.join(",", Integer.toString(id), name, address, city, state, zip);
                myWriter.write(infoToFile);
                myWriter.close();
                Label studentAdded = new Label("New student has been added.");
                gridPane.add(studentAdded, 1, 8);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return true;
        }
        else {
            Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
            duplicateWarning.setContentText("A student with this ID already exists in our database.");
            duplicateWarning.show();
            return false;
        }
    }

    Student GetStudent (int id) {
        int i;

        for (i = 0; i < student.size(); i++) {
            if (student.get(i).id == id) {
                return student.get(i);
            }
        }
        return null;
    }

    boolean UpdateStudent (int id, String name, String address, String city, String state, String zip, GridPane gridPane) throws IOException {
        if (GetStudent(id) != null) {
            int i;
            FileWriter myWriter = new FileWriter("Student.txt");

            Student studentObj = new Student(id, name, address, city, state, zip);

            for (i = 0; i < student.size(); i++) {
                if (student.get(i).id == id) {
                    student.set(i, studentObj);
                }
            }

            for (i = 0; i < student.size(); i++) {
                try {
                    String infoToFile = String.join(",", Integer.toString(student.get(i).id), student.get(i).name, student.get(i).address, student.get(i).city, student.get(i).state, student.get(i).zip);
                    if (i == student.size() - 1) {
                        myWriter.write(infoToFile);
                    }
                    else {
                        myWriter.write(infoToFile + "\n");
                    }

                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            myWriter.close();

            Label studentUpdated = new Label("The student's information has been updated.");

            gridPane.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 9);

            gridPane.add(studentUpdated, 1, 9);
            return true;
        }
        else {
            Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
            duplicateWarning.setContentText("A student with this ID does not exist in our database.");
            duplicateWarning.show();
            return false;
        }
    }
}

class CourseFileManager {
    ArrayList<Course> course = new ArrayList<Course>();
    CourseFileManager (String filename) {
        try {
            File myObj = new File(filename);
            myObj.createNewFile();
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String studentInfo = myReader.nextLine();
                StringTokenizer token = new StringTokenizer(studentInfo, ",");

                Course courseObj = new Course(Integer.parseInt(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()));

                course.add(courseObj);
            }
            myReader.close();
        }
        catch(Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    boolean AddCourse (int courseID, String courseName, String department, String courseNum, String instructor, GridPane gridPane) {
        if (GetCourse(courseID) == null) {
            try {
                FileWriter myWriter = new FileWriter("Course.txt", true);
                BufferedReader myReader = new BufferedReader(new FileReader("Course.txt"));
                if (myReader.readLine() != null) {
                    myWriter.write("\n");
                }
                String infoToFile = String.join(",", Integer.toString(courseID), courseName, department, courseNum, instructor);
                myWriter.write(infoToFile);
                myWriter.close();
                Label courseAdded = new Label("New course has been added.");
                gridPane.add(courseAdded, 1, 8);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return true;
        }
        else {
            Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
            duplicateWarning.setContentText("A course with this ID already exists in our database.");
            duplicateWarning.show();
            return false;
        }
    }

    Course GetCourse (int id) {
        int i;

        for (i = 0; i < course.size(); i++) {
            if (course.get(i).id == id) {
                return course.get(i);
            }
        }
        return null;
    }

    boolean UpdateCourse (int id, String name, String department, String num, String instructor, GridPane gridPane) throws IOException {
        if (GetCourse(id) != null) {
            int i;
            FileWriter myWriter = new FileWriter("Course.txt");

            Course courseObj = new Course(id, name, department, num, instructor);

            for (i = 0; i < course.size(); i++) {
                if (course.get(i).id == id) {
                    course.set(i, courseObj);
                }
            }

            for (i = 0; i < course.size(); i++) {
                try {
                    String infoToFile = String.join(",", Integer.toString(course.get(i).id), course.get(i).name, course.get(i).department, course.get(i).num, course.get(i).instructor);
                    if (i == course.size() - 1) {
                        myWriter.write(infoToFile);
                    }
                    else {
                        myWriter.write(infoToFile + "\n");
                    }

                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            myWriter.close();

            Label courseUpdated = new Label("The course's information has been updated.");

            gridPane.getChildren().removeIf( node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 7);

            gridPane.add(courseUpdated, 1, 7);
            return true;
        }
        else {
            Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
            duplicateWarning.setContentText("A course with this ID does not exist in our database.");
            duplicateWarning.show();
            return false;
        }
    }
}

class EnrollmentFileManager {
    ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();
    EnrollmentFileManager (String filename) {
        try {
            File myObj = new File(filename);
            myObj.createNewFile();
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String enrollmentInfo = myReader.nextLine();
                StringTokenizer token = new StringTokenizer(enrollmentInfo, ",");

                Enrollment enrollObj = new Enrollment(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), String.format(token.nextToken()), String.format(token.nextToken()), token.nextToken().charAt(0));

                enrollment.add(enrollObj);
            }
            myReader.close();
        }
        catch(Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    boolean AddEnrollment (int eid, int sid, int cid, String year, String semester, char grade, GridPane gridPane) {
        if (GetEnrollment(eid) == null) {
            try {
                FileWriter myWriter = new FileWriter("Enrollment.txt", true);
                BufferedReader myReader = new BufferedReader(new FileReader("Enrollment.txt"));
                if (myReader.readLine() != null) {
                    myWriter.write("\n");
                }
                String infoToFile = String.join(",", Integer.toString(eid), Integer.toString(sid), Integer.toString(cid), year, semester, Character.toString(grade));
                myWriter.write(infoToFile);
                myWriter.close();
                Label enrollmentAdded = new Label ("New enrollment has been added.");
                gridPane.add(enrollmentAdded, 1, 10);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return true;
        }
        else {
            Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
            duplicateWarning.setContentText("An enrollment with this ID already exists in our database.");
            duplicateWarning.show();
            return false;
        }
    }

    Enrollment GetEnrollment (int eid) {
        int i;

        for (i = 0; i < enrollment.size(); i++) {
            if (enrollment.get(i).eid == eid) {
                return enrollment.get(i);
            }
        }
        return null;
    }

    boolean UpdateEnrollment (int eid, int sid, int cid, String year, String semester, char grade, GridPane gridPane) throws IOException {
        if (GetEnrollment(eid) != null) {
            int i;
            FileWriter myWriter = new FileWriter("Enrollment.txt");

            Enrollment enrollmentObj = new Enrollment(eid, sid, cid, year, semester, grade);

            for (i = 0; i < enrollment.size(); i++) {
                if (enrollment.get(i).eid == eid) {
                    enrollment.set(i, enrollmentObj);
                }
            }

            for (i = 0; i < enrollment.size(); i++) {
                try {
                    String infoToFile = String.join(",", Integer.toString(enrollment.get(i).eid), Integer.toString(enrollment.get(i).sid), Integer.toString(enrollment.get(i).cid), enrollment.get(i).year, enrollment.get(i).semester, Character.toString(enrollment.get(i).grade));
                    if (i == enrollment.size() - 1) {
                        myWriter.write(infoToFile);
                    }
                    else {
                        myWriter.write(infoToFile + "\n");
                    }

                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            myWriter.close();
            Label gradeUpdated = new Label("Grade updated.");
            gridPane.add(gradeUpdated, 1, 11);
            return true;
        }
        else {
            Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
            duplicateWarning.setContentText("An enrollment with this ID does not exist in our database.");
            duplicateWarning.show();
            return false;
        }
    }
}

class Student {
    int id;
    String name;;
    String address;
    String city;
    String state;
    String zip;

    Student(int id, String name, String address, String city, String state, String zip) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}

class Course {
    int id;
    String name;
    String num;
    String department;
    String instructor;

    Course(int id, String name, String department, String num, String instructor) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.num = num;
        this.instructor = instructor;
    }
}

class Enrollment {
    int eid;
    int sid;
    int cid;
    String year;
    String semester;
    char grade;

    Enrollment(int eid, int sid, int cid, String year, String semester, char grade) {
        this.eid = eid;
        this.sid = sid;
        this.cid = cid;
        this.year = year;
        this.semester = semester;
        this.grade = grade;
    }
}

public class Homework extends Application {
    public void AddStudentWindow(GridPane gridPane, ComboBox stateComboBox) {
        StudentFileManager studentObj = new StudentFileManager("Student.txt");

        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField idTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField cityTextField = new TextField();
        TextField zipTextField = new TextField();
        Label addStudentLabel = new Label("New Student Information");
        Label idLabel = new Label("Student ID");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label addressLabel = new Label("Address");
        Label cityLabel = new Label("City");
        Label zipLabel = new Label("Zip Code");
        Label stateLabel = new Label("State");
        Button addStudentButton = new Button("Add Student");

        gridPane.getChildren().clear();

        stateComboBox.getSelectionModel().clearSelection();

        idTextField.setDisable(false);
        firstNameTextField.setDisable(false);
        lastNameTextField.setDisable(false);
        addressTextField.setDisable(false);
        cityTextField.setDisable(false);
        stateComboBox.setDisable(false);
        zipTextField.setDisable(false);

        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.add(addStudentLabel, 1, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(idTextField, 1, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameTextField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameTextField, 1, 3);
        gridPane.add(addressLabel, 0, 4);
        gridPane.add(addressTextField, 1, 4);
        gridPane.add(cityLabel, 0, 5);
        gridPane.add(cityTextField, 1, 5);
        gridPane.add(stateLabel, 2, 5);
        gridPane.add(stateComboBox, 3, 5);
        gridPane.add(zipLabel, 0, 6);
        gridPane.add(zipTextField, 1, 6);
        gridPane.add(addStudentButton, 1, 7);

        addStudentButton.setOnAction(event -> {
            if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || idTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || cityTextField.getText().isEmpty() || stateComboBox.getSelectionModel().isEmpty() || zipTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("None of the fields can be left blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                String name = new String(firstNameTextField.getText() + " " + lastNameTextField.getText());
                studentObj.AddStudent(Integer.parseInt(idTextField.getText()), name, addressTextField.getText(), cityTextField.getText(), String.valueOf(stateComboBox.getValue()), zipTextField.getText(), gridPane);

                firstNameTextField.clear();
                lastNameTextField.clear();
                idTextField.clear();
                addressTextField.clear();
                cityTextField.clear();
                stateComboBox.getSelectionModel().clearSelection();
                zipTextField.clear();
            }
        });
    }

    public void EditStudentWindow(GridPane gridPane, ComboBox stateComboBox) {
        StudentFileManager studentObj = new StudentFileManager("Student.txt");

        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField idTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField cityTextField = new TextField();
        TextField zipTextField = new TextField();
        Label editStudentLabel = new Label("Edit Student Information");
        Label idLabel = new Label("Student ID");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label addressLabel = new Label("Address");
        Label cityLabel = new Label("City");
        Label stateLabel = new Label("State");
        Label zipLabel = new Label("Zip Code");
        Button updateButton = new Button("Update");
        Button resetButton = new Button("Reset");
        Button searchButton = new Button("Search");

        gridPane.getChildren().clear();

        stateComboBox.getSelectionModel().clearSelection();

        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.add(editStudentLabel, 1, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(idTextField, 1, 1);
        gridPane.add(searchButton, 2, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameTextField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameTextField, 1, 3);
        gridPane.add(addressLabel, 0, 4);
        gridPane.add(addressTextField, 1, 4);
        gridPane.add(cityLabel, 0, 5);
        gridPane.add(cityTextField, 1, 5);
        gridPane.add(stateLabel, 0, 6);
        gridPane.add(stateComboBox, 1, 6);
        gridPane.add(zipLabel, 0, 7);
        gridPane.add(zipTextField, 1, 7);
        gridPane.add(resetButton, 1, 8);
        gridPane.add(updateButton, 2, 8);

        firstNameTextField.setDisable(true);
        lastNameTextField.setDisable(true);
        addressTextField.setDisable(true);
        cityTextField.setDisable(true);
        stateComboBox.setDisable(true);
        zipTextField.setDisable(true);

        searchButton.setOnAction(event -> {
            if (idTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("Student ID field can not be blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                if (studentObj.GetStudent(Integer.parseInt(idTextField.getText())) != null) {
                    int i;
                    for (i = 0; i < studentObj.student.size(); i++) {
                        if (studentObj.student.get(i).id == Integer.parseInt(idTextField.getText())) {
                            idTextField.setDisable(true);
                            firstNameTextField.setDisable(false);
                            lastNameTextField.setDisable(false);
                            addressTextField.setDisable(false);
                            cityTextField.setDisable(false);
                            stateComboBox.setDisable(false);
                            zipTextField.setDisable(false);

                            String[] fullName = studentObj.student.get(i).name.split(" ");
                            String firstName = fullName[0];
                            String lastName = fullName[1];
                            firstNameTextField.setText(firstName);
                            lastNameTextField.setText(lastName);
                            addressTextField.setText(studentObj.student.get(i).address);
                            cityTextField.setText(studentObj.student.get(i).city);
                            stateComboBox.getSelectionModel().select(studentObj.student.get(i).state);
                            zipTextField.setText(studentObj.student.get(i).zip);

                            updateButton.setOnAction(e -> {
                                if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || cityTextField.getText().isEmpty() || stateComboBox.getSelectionModel().isEmpty() || zipTextField.getText().isEmpty()) {
                                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                                    duplicateWarning.setContentText("None of the fields can be left blank. Please try again.");
                                    duplicateWarning.show();
                                }
                                else {
                                    try {
                                        String name = new String(firstNameTextField.getText() + " " + lastNameTextField.getText());
                                        studentObj.UpdateStudent(Integer.parseInt(idTextField.getText()), name, addressTextField.getText(), cityTextField.getText(), String.valueOf(stateComboBox.getValue()), zipTextField.getText(), gridPane);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });
                            resetButton.setOnAction(e -> {
                                gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 9);

                                idTextField.setText("");
                                firstNameTextField.setText("");
                                lastNameTextField.setText("");
                                addressTextField.setText("");
                                cityTextField.setText("");
                                stateComboBox.getSelectionModel().select("");
                                zipTextField.setText("");

                                idTextField.setDisable(false);
                                firstNameTextField.setDisable(true);
                                lastNameTextField.setDisable(true);
                                addressTextField.setDisable(true);
                                cityTextField.setDisable(true);
                                stateComboBox.setDisable(true);
                                zipTextField.setDisable(true);
                            });
                        }
                    }
                } else {
                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                    duplicateWarning.setContentText("A student with this ID does not exist in our database.");
                    duplicateWarning.show();
                }
            }
        });
    }

    public void ViewStudentWindow(GridPane gridPane) {
        StudentFileManager studentObj = new StudentFileManager("Student.txt");

        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField idTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField cityTextField = new TextField();
        TextField stateTextField = new TextField();
        TextField zipTextField = new TextField();
        Label idLabel = new Label("Student ID");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label addressLabel = new Label("Address");
        Label cityLabel = new Label("City");
        Label stateLabel = new Label("State");
        Label zipLabel = new Label("Zip Code");
        Button searchButton = new Button("Search");

        gridPane.getChildren().clear();

        stateTextField.setMaxWidth(40);

        idTextField.setDisable(false);
        firstNameTextField.setDisable(true);
        lastNameTextField.setDisable(true);
        addressTextField.setDisable(true);
        cityTextField.setDisable(true);
        stateTextField.setDisable(true);
        zipTextField.setDisable(true);

        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(idTextField, 1, 1);
        gridPane.add(searchButton, 2, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameTextField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameTextField, 1, 3);
        gridPane.add(addressLabel, 0, 4);
        gridPane.add(addressTextField, 1, 4);
        gridPane.add(cityLabel, 0, 5);
        gridPane.add(cityTextField, 1, 5);
        gridPane.add(stateLabel, 0, 6);
        gridPane.add(stateTextField, 1, 6);
        gridPane.add(zipLabel, 0, 7);
        gridPane.add(zipTextField, 1, 7);

        searchButton.setOnAction(event -> {
            if (idTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("Student ID field can not be blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                if (studentObj.GetStudent(Integer.parseInt(idTextField.getText())) != null) {
                    int i;
                    for (i = 0; i < studentObj.student.size(); i++) {
                        if (studentObj.student.get(i).id == Integer.parseInt(idTextField.getText())) {
                            String[] fullName = studentObj.student.get(i).name.split(" ");
                            String firstName = fullName[0];
                            String lastName = fullName[1];
                            firstNameTextField.setText(firstName);
                            lastNameTextField.setText(lastName);
                            addressTextField.setText(studentObj.student.get(i).address);
                            cityTextField.setText(studentObj.student.get(i).city);
                            stateTextField.setText(studentObj.student.get(i).state);
                            zipTextField.setText(studentObj.student.get(i).zip);
                        }
                    }
                } else {
                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                    duplicateWarning.setContentText("A student with this ID does not exist in our database.");
                    duplicateWarning.show();
                }
            }
        });
    }

    public void AddCourseWindow(GridPane gridPane, ComboBox departmentComboBox) {
        CourseFileManager courseObj = new CourseFileManager("Course.txt");

        TextField courseIDTextField = new TextField();
        TextField courseNameTextField = new TextField();
        TextField courseNumTextField = new TextField();
        TextField instructorTextField = new TextField();
        Label addCourseLabel = new Label("New Course Information");
        Label courseIDLabel = new Label("Course ID");
        Label courseNameLabel = new Label("Course Name");
        Label courseNumLabel = new Label("Course Number");
        Label departmentLabel = new Label("Department");
        Label instructorLabel = new Label("Instructor Name");
        Button addCourseButton = new Button("Create Course");

        gridPane.getChildren().clear();

        departmentComboBox.getSelectionModel().clearSelection();

        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.add(addCourseLabel, 1, 0);
        gridPane.add(courseIDLabel, 0, 1);
        gridPane.add(courseIDTextField, 1, 1);
        gridPane.add(courseNameLabel, 0, 2);
        gridPane.add(courseNameTextField, 1, 2);
        gridPane.add(departmentLabel, 0, 3);
        gridPane.add(departmentComboBox, 1, 3);
        gridPane.add(courseNumLabel, 0, 4);
        gridPane.add(courseNumTextField, 1, 4);
        gridPane.add(instructorLabel, 0, 5);
        gridPane.add(instructorTextField, 1, 5);
        gridPane.add(addCourseButton, 1, 6);

        courseIDTextField.setDisable(false);
        courseNameTextField.setDisable(false);
        departmentComboBox.setDisable(false);
        courseNumTextField.setDisable(false);
        instructorTextField.setDisable(false);

        addCourseButton.setOnAction(event -> {
            if (courseIDTextField.getText().isEmpty() || courseNameTextField.getText().isEmpty() || departmentComboBox.getSelectionModel().isEmpty() || courseNumTextField.getText().isEmpty() || instructorTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("None of the fields can be left blank. Please try again.");
                duplicateWarning.show();
            } else {
                courseObj.AddCourse(Integer.parseInt(courseIDTextField.getText()), courseNameTextField.getText(), String.valueOf(departmentComboBox.getValue()), courseNumTextField.getText(), instructorTextField.getText(), gridPane);

                courseIDTextField.clear();
                courseNameTextField.clear();
                courseNumTextField.clear();
                instructorTextField.clear();
                departmentComboBox.getSelectionModel().clearSelection();
            }
        });
    }

    public void EditCourseWindow(GridPane gridPane, ComboBox departmentComboBox) {
        CourseFileManager courseObj = new CourseFileManager("Course.txt");

        TextField courseIDTextField = new TextField();
        TextField courseNameTextField = new TextField();
        TextField courseNumTextField = new TextField();
        TextField instructorTextField = new TextField();
        Label editCourseLabel = new Label("Edit Course Information");
        Label courseIDLabel = new Label("Course ID");
        Label courseNameLabel = new Label("Course Name");
        Label courseNumLabel = new Label("Course Number");
        Label departmentLabel = new Label("Department");
        Label instructorLabel = new Label("Instructor Name");
        Button resetButton = new Button("Reset");
        Button updateButton = new Button("Update");
        Button searchButton = new Button("Search");

        gridPane.getChildren().clear();

        departmentComboBox.getSelectionModel().clearSelection();

        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.add(editCourseLabel, 1, 0);
        gridPane.add(courseIDLabel, 0, 1);
        gridPane.add(courseIDTextField, 1, 1);
        gridPane.add(searchButton, 2, 1);
        gridPane.add(courseNameLabel, 0, 2);
        gridPane.add(courseNameTextField, 1, 2);
        gridPane.add(departmentLabel, 0, 3);
        gridPane.add(departmentComboBox, 1, 3);
        gridPane.add(courseNumLabel, 0, 4);
        gridPane.add(courseNumTextField, 1, 4);
        gridPane.add(instructorLabel, 0, 5);
        gridPane.add(instructorTextField, 1, 5);
        gridPane.add(resetButton, 1, 6);
        gridPane.add(updateButton, 2, 6);

        courseIDTextField.setDisable(false);
        courseNameTextField.setDisable(true);
        departmentComboBox.setDisable(true);
        courseNumTextField.setDisable(true);
        instructorTextField.setDisable(true);

        searchButton.setOnAction(event -> {
            if (courseIDTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("Course ID field can not be blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                if (courseObj.GetCourse(Integer.parseInt(courseIDTextField.getText())) != null) {
                    int i;
                    for (i = 0; i < courseObj.course.size(); i++) {
                        if (courseObj.course.get(i).id == Integer.parseInt(courseIDTextField.getText())) {
                            courseIDTextField.setDisable(true);
                            courseNameTextField.setDisable(false);
                            departmentComboBox.setDisable(false);
                            courseNumTextField.setDisable(false);
                            instructorTextField.setDisable(false);

                            courseNameTextField.setText(courseObj.course.get(i).name);
                            departmentComboBox.getSelectionModel().select(courseObj.course.get(i).department);
                            courseNumTextField.setText(courseObj.course.get(i).num);
                            instructorTextField.setText(courseObj.course.get(i).instructor);

                            updateButton.setOnAction(e -> {
                                if (courseNameTextField.getText().isEmpty() || departmentComboBox.getSelectionModel().isEmpty() || courseNumTextField.getText().isEmpty() || instructorTextField.getText().isEmpty()) {
                                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                                    duplicateWarning.setContentText("None of the fields can be left blank. Please try again.");
                                    duplicateWarning.show();
                                }
                                else {
                                    try {
                                        courseObj.UpdateCourse(Integer.parseInt(courseIDTextField.getText()), courseNameTextField.getText(), String.valueOf(departmentComboBox.getValue()), courseNumTextField.getText(), instructorTextField.getText(), gridPane);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            });
                            resetButton.setOnAction(e -> {
                                gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 7);

                                courseIDTextField.setText("");
                                courseNameTextField.setText("");
                                departmentComboBox.getSelectionModel().select("");
                                courseNumTextField.setText("");
                                instructorTextField.setText("");

                                courseIDTextField.setDisable(false);
                                courseNameTextField.setDisable(true);
                                departmentComboBox.setDisable(true);
                                courseNumTextField.setDisable(true);
                                instructorTextField.setDisable(true);
                            });
                        }
                    }
                } else {
                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                    duplicateWarning.setContentText("A course with this ID does not exist in our database.");
                    duplicateWarning.show();
                }
            }
        });
    }

    public void ViewCourseWindow(GridPane gridPane) {
        CourseFileManager courseObj = new CourseFileManager("Course.txt");

        TextField courseIDTextField = new TextField();
        TextField courseNameTextField = new TextField();
        TextField courseNumTextField = new TextField();
        TextField instructorTextField = new TextField();
        TextField departmentTextField = new TextField();
        Label courseIDLabel = new Label("Course ID");
        Label courseNameLabel = new Label("Course Name");
        Label courseNumLabel = new Label("Course Number");
        Label departmentLabel = new Label("Department");
        Label instructorLabel = new Label("Instructor Name");
        Button searchButton = new Button("Search");

        gridPane.getChildren().clear();

        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.add(courseIDLabel, 0, 0);
        gridPane.add(courseIDTextField, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(courseNameLabel, 0, 1);
        gridPane.add(courseNameTextField, 1, 1);
        gridPane.add(courseNumLabel, 0, 2);
        gridPane.add(courseNumTextField, 1, 2);
        gridPane.add(departmentLabel, 0, 3);
        gridPane.add(departmentTextField, 1, 3);
        gridPane.add(instructorLabel, 0, 4);
        gridPane.add(instructorTextField, 1, 4);

        courseIDTextField.setDisable(false);
        courseNameTextField.setDisable(true);
        departmentTextField.setDisable(true);
        courseNumTextField.setDisable(true);
        instructorTextField.setDisable(true);

        searchButton.setOnAction(event -> {
            if (courseIDTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("Course ID field can not be blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                if (courseObj.GetCourse(Integer.parseInt(courseIDTextField.getText())) != null) {
                    int i;
                    for (i = 0; i < courseObj.course.size(); i++) {
                        if (courseObj.course.get(i).id == Integer.parseInt(courseIDTextField.getText())) {
                            courseNameTextField.setText(courseObj.course.get(i).name);
                            departmentTextField.setText(courseObj.course.get(i).department);
                            courseNumTextField.setText(courseObj.course.get(i).num);
                            instructorTextField.setText(courseObj.course.get(i).instructor);
                        }
                    }
                } else {
                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                    duplicateWarning.setContentText("A course with this ID does not exist in our database.");
                    duplicateWarning.show();
                }
            }
        });
    }

    public void AddEnrollmentWindow(GridPane gridPane, ComboBox semesterComboBox, ComboBox yearComboBox, ComboBox gradeComboBox) {
        StudentFileManager studentObj = new StudentFileManager("Student.txt");
        CourseFileManager courseObj = new CourseFileManager("Course.txt");
        EnrollmentFileManager enrollmentObj = new EnrollmentFileManager("Enrollment.txt");

        Label enrollmentLabel = new Label("New Enrollment Information");
        Label semesterLabel = new Label("Semester");
        Label yearLabel = new Label("Year");
        Label gradeLabel = new Label("Grade");
        Label enrollmentIDLabel = new Label("Enrollment ID");
        Label studentIDLabel = new Label("Student ID");
        Label courseIDLabel = new Label("Course ID");
        Label studentNameLabel = new Label("Student Name");
        Label courseNumLabel = new Label("Course Number");
        Label courseNameLabel = new Label("Course Name");
        TextField enrollmentIDTextField = new TextField();
        TextField studentIDTextField = new TextField();
        TextField studentNameTextField = new TextField();
        TextField courseIDTextField = new TextField();
        TextField courseNumTextField = new TextField();
        TextField courseNameTextField = new TextField();
        Button findStudentButton = new Button("Find Student");
        Button findCourseButton = new Button("Find Course");
        Button addEnrollmentButton = new Button("Create Enrollment");

        gridPane.getChildren().clear();

        gridPane.setVgap(20);
        gridPane.setHgap(10);

        semesterComboBox.getSelectionModel().clearSelection();
        yearComboBox.getSelectionModel().clearSelection();
        gradeComboBox.getSelectionModel().clearSelection();

        studentNameTextField.setDisable(true);
        courseNumTextField.setDisable(true);
        courseNameTextField.setDisable(true);
        courseIDTextField.setDisable(true);
        findCourseButton.setDisable(true);
        addEnrollmentButton.setDisable(true);

        gridPane.add(enrollmentLabel, 1, 0);
        gridPane.add(enrollmentIDLabel, 0, 1);
        gridPane.add(enrollmentIDTextField, 1, 1);
        gridPane.add(studentIDLabel, 0, 2);
        gridPane.add(studentIDTextField, 1, 2);
        gridPane.add(findStudentButton, 2, 2);
        gridPane.add(studentNameLabel, 0, 3);
        gridPane.add(studentNameTextField, 1, 3);
        gridPane.add(courseIDLabel, 0, 4);
        gridPane.add(courseIDTextField, 1, 4);
        gridPane.add(findCourseButton, 2, 4);
        gridPane.add(courseNumLabel, 0, 5);
        gridPane.add(courseNumTextField, 1, 5);
        gridPane.add(courseNameLabel, 0, 6);
        gridPane.add(courseNameTextField, 1, 6);
        gridPane.add(semesterLabel, 0, 7);
        gridPane.add(yearLabel, 1, 7);
        gridPane.add(gradeLabel, 2, 7);
        gridPane.add(semesterComboBox, 0, 8);
        gridPane.add(yearComboBox, 1, 8);
        gridPane.add(gradeComboBox, 2, 8);
        gridPane.add(addEnrollmentButton, 1, 9);

        findStudentButton.setOnAction(event -> {
            if (studentIDTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("Student ID field can not be blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                int i;

                if (studentObj.GetStudent(Integer.parseInt(studentIDTextField.getText())) != null) {
                    for (i = 0; i < studentObj.student.size(); i++) {
                        if (studentObj.student.get(i).id == Integer.parseInt(studentIDTextField.getText())) {
                            studentNameTextField.setText(studentObj.student.get(i).name);
                            courseIDTextField.setDisable(false);
                            findCourseButton.setDisable(false);
                        }
                    }
                } else {
                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                    duplicateWarning.setContentText("A student with this ID does not exist in our database.");
                    duplicateWarning.show();

                    studentNameTextField.clear();
                }
            }
        });

        findCourseButton.setOnAction(event -> {
            if (courseIDTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("Course ID field can not be blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                int i;

                if (courseObj.GetCourse(Integer.parseInt(courseIDTextField.getText())) != null) {
                    for (i = 0; i < courseObj.course.size(); i++) {
                        if (courseObj.course.get(i).id == Integer.parseInt(courseIDTextField.getText())) {
                            courseNameTextField.setText(courseObj.course.get(i).name);
                            courseNumTextField.setText(courseObj.course.get(i).num);
                            addEnrollmentButton.setDisable(false);
                        }
                    }
                } else {
                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                    duplicateWarning.setContentText("A course with this ID does not exist in our database.");
                    duplicateWarning.show();

                    courseNameTextField.clear();
                    courseNumTextField.clear();
                }
            }
        });

        addEnrollmentButton.setOnAction(event -> {
            if (enrollmentIDTextField.getText().isEmpty() || yearComboBox.getSelectionModel().isEmpty() || semesterComboBox.getSelectionModel().isEmpty() || gradeComboBox.getSelectionModel().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("None of the fields can be left blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                enrollmentObj.AddEnrollment(Integer.parseInt(enrollmentIDTextField.getText()), Integer.parseInt(studentIDTextField.getText()), Integer.parseInt(courseIDTextField.getText()), String.valueOf(yearComboBox.getValue()), String.valueOf(semesterComboBox.getValue()), Character.valueOf((Character) gradeComboBox.getValue()), gridPane);

                enrollmentIDTextField.clear();
                studentIDTextField.clear();
                studentNameTextField.clear();
                courseIDTextField.clear();
                courseNameTextField.clear();
                courseNumTextField.clear();
                yearComboBox.getSelectionModel().clearSelection();
                semesterComboBox.getSelectionModel().clearSelection();
                gradeComboBox.getSelectionModel().clearSelection();
            }
        });
    }

    public void ViewEditEnrollmentWindow(GridPane gridPane, ComboBox gradeComboBox) {
        StudentFileManager studentObj = new StudentFileManager("Student.txt");
        CourseFileManager courseObj = new CourseFileManager("Course.txt");
        EnrollmentFileManager enrollmentObj = new EnrollmentFileManager("Enrollment.txt");

        Label enrollmentLabel = new Label("View/Edit Enrollment Information");
        Label enrollmentIDLabel = new Label("Enrollment ID");
        Label studentIDLabel = new Label("Student ID");
        Label studentNameLabel = new Label("Student Name");
        Label courseIDLabel = new Label("Course ID");
        Label courseNumLabel = new Label("Course Number");
        Label courseNameLabel = new Label("Course Name");
        Label semesterLabel = new Label("Semester");
        Label yearLabel = new Label("Year");
        Label gradeLabel = new Label("Grade");
        TextField enrollmentIDTextField = new TextField();
        TextField studentIDTextField = new TextField();
        TextField studentNameTextField = new TextField();
        TextField courseIDTextField = new TextField();
        TextField courseNumTextField = new TextField();
        TextField courseNameTextField = new TextField();
        TextField semesterTextField = new TextField();
        TextField yearTextField = new TextField();
        Button findEnrollmentButton = new Button("Find Enrollment");
        Button setNewGradeButton = new Button("Set New Grade");
        Button resetButton = new Button("Reset");

        gridPane.getChildren().clear();

        gradeComboBox.getSelectionModel().clearSelection();

        gridPane.setVgap(20);
        gridPane.setHgap(10);

        studentIDTextField.setDisable(true);
        studentNameTextField.setDisable(true);
        courseIDTextField.setDisable(true);
        courseNumTextField.setDisable(true);
        courseNameTextField.setDisable(true);
        semesterTextField.setDisable(true);
        yearTextField.setDisable(true);
        setNewGradeButton.setDisable(true);

        gridPane.add(enrollmentLabel, 1, 0);
        gridPane.add(enrollmentIDLabel, 0, 1);
        gridPane.add(enrollmentIDTextField, 1, 1);
        gridPane.add(findEnrollmentButton, 2, 1);
        gridPane.add(studentIDLabel, 0, 2);
        gridPane.add(studentIDTextField, 1, 2);
        gridPane.add(studentNameLabel, 0, 3);
        gridPane.add(studentNameTextField, 1, 3);
        gridPane.add(courseIDLabel, 0, 4);
        gridPane.add(courseIDTextField, 1, 4);
        gridPane.add(courseNumLabel, 0, 5);
        gridPane.add(courseNumTextField, 1, 5);
        gridPane.add(courseNameLabel, 0, 6);
        gridPane.add(courseNameTextField, 1, 6);
        gridPane.add(semesterLabel, 0, 7);
        gridPane.add(semesterTextField, 1, 7);
        gridPane.add(yearLabel, 0, 8);
        gridPane.add(yearTextField, 1, 8);
        gridPane.add(gradeLabel, 0, 9);
        gridPane.add(gradeComboBox, 1, 9);
        gridPane.add(setNewGradeButton, 1, 10);
        gridPane.add(resetButton, 2, 10);

        findEnrollmentButton.setOnAction(event -> {
            if (enrollmentIDTextField.getText().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("Enrollment ID field can not be blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                if (enrollmentObj.GetEnrollment(Integer.parseInt(enrollmentIDTextField.getText())) != null) {
                    int i;
                    int j;
                    int k;

                    for (i = 0; i < enrollmentObj.enrollment.size(); i++) {
                        if (enrollmentObj.enrollment.get(i).eid == Integer.parseInt(enrollmentIDTextField.getText())) {
                            studentIDTextField.setText(String.valueOf(enrollmentObj.enrollment.get(i).sid));
                            courseIDTextField.setText(String.valueOf(enrollmentObj.enrollment.get(i).cid));
                            for (j = 0; j < studentObj.student.size(); j++) {
                                if (studentObj.student.get(j).id == Integer.parseInt(studentIDTextField.getText())) {
                                    studentNameTextField.setText(studentObj.student.get(j).name);
                                }
                            }
                            for (k = 0; k < courseObj.course.size(); k++) {
                                if (courseObj.course.get(k).id == Integer.parseInt(courseIDTextField.getText())) {
                                    courseNameTextField.setText(courseObj.course.get(k).name);
                                    courseNumTextField.setText(courseObj.course.get(k).num);
                                }
                            }
                            semesterTextField.setText(enrollmentObj.enrollment.get(i).semester);
                            yearTextField.setText(enrollmentObj.enrollment.get(i).year);
                            gradeComboBox.getSelectionModel().select(Character.valueOf(enrollmentObj.enrollment.get(i).grade));

                            setNewGradeButton.setDisable(false);
                        }
                    }
                } else {
                    Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                    duplicateWarning.setContentText("An enrollment with this ID does not exist in our database.");
                    duplicateWarning.show();
                }
            }
        });

        setNewGradeButton.setOnAction(event -> {
            try {
                enrollmentObj.UpdateEnrollment(Integer.parseInt(enrollmentIDTextField.getText()), Integer.parseInt(studentIDTextField.getText()), Integer.parseInt(courseIDTextField.getText()), yearTextField.getText(), semesterTextField.getText(), Character.valueOf((Character) gradeComboBox.getValue()), gridPane);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        resetButton.setOnAction(event -> {
            gridPane.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1 && GridPane.getRowIndex(node) == 11);

            enrollmentIDTextField.clear();
            studentIDTextField.clear();
            studentNameTextField.clear();
            courseIDTextField.clear();
            courseNameTextField.clear();
            courseNumTextField.clear();
            yearTextField.clear();
            semesterTextField.clear();
            gradeComboBox.getSelectionModel().clearSelection();

            setNewGradeButton.setDisable(true);
        });
    }

    public void GenerateReportWindow(GridPane gridPane, ComboBox semesterComboBox, ComboBox yearComboBox, TextArea reportTextArea) {
        StudentFileManager studentObj = new StudentFileManager("Student.txt");
        CourseFileManager courseObj = new CourseFileManager("Course.txt");
        EnrollmentFileManager enrollmentObj = new EnrollmentFileManager("Enrollment.txt");

        Label courseIDLabel = new Label("Course ID");
        Label semesterLabel = new Label("Semester");
        Label yearLabel = new Label("Year");
        TextField courseIDTextField = new TextField();
        Button generateReportButton = new Button("Generate Report");

        gridPane.getChildren().clear();

        semesterComboBox.getSelectionModel().clearSelection();
        yearComboBox.getSelectionModel().clearSelection();
        reportTextArea.clear();

        gridPane.setVgap(20);
        gridPane.setHgap(10);

        gridPane.add(courseIDLabel, 0, 0);
        gridPane.add(semesterLabel, 1, 0);
        gridPane.add(yearLabel, 2, 0);
        gridPane.add(courseIDTextField, 0, 1);
        gridPane.add(semesterComboBox, 1, 1);
        gridPane.add(yearComboBox, 2, 1);
        gridPane.add(generateReportButton, 3, 1);

        courseIDTextField.setMaxWidth(50);

        generateReportButton.setOnAction(event -> {
            if (courseIDTextField.getText().isEmpty() || yearComboBox.getSelectionModel().isEmpty() || semesterComboBox.getSelectionModel().isEmpty()) {
                Alert duplicateWarning = new Alert(Alert.AlertType.ERROR);
                duplicateWarning.setContentText("None of the fields can be left blank. Please try again.");
                duplicateWarning.show();
            }
            else {
                int i;
                int j;
                int k;

                reportTextArea.clear();

                for (i = 0; i < courseObj.course.size(); i++) {
                    if (Integer.valueOf(courseIDTextField.getText()) == courseObj.course.get(i).id) {
                        reportTextArea.appendText(courseObj.course.get(i).num + " Java Report\n");
                        reportTextArea.appendText("--------------------------------------------------------------------");
                        for (j = 0; j < enrollmentObj.enrollment.size(); j++) {
                            if (Integer.valueOf(courseIDTextField.getText()) == enrollmentObj.enrollment.get(j).cid && Objects.equals(String.valueOf(yearComboBox.getValue()), enrollmentObj.enrollment.get(j).year) && Objects.equals(String.valueOf(semesterComboBox.getValue()), enrollmentObj.enrollment.get(j).semester)) {
                                for (k = 0; k < studentObj.student.size(); k++) {
                                    if (enrollmentObj.enrollment.get(j).sid == studentObj.student.get(k).id) {
                                        reportTextArea.appendText("\n" + studentObj.student.get(k).name + "     " + enrollmentObj.enrollment.get(j).grade);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
        String states[] = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
                "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
                "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
                "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
        String departments[] = {"ART", "BUSINESS", "CO SCI", "ENGLISH", "HISTORY", "MATH", "SCIENCE"};
        String semesters[] = {"SPRING", "SUMMER", "FALL", "WINTER"};
        String years[] = {"2020", "2021", "2022", "2023", "2024"};
        Character grades[] = { 'A', 'B', 'C', 'D', 'F' };
        ComboBox<String> stateComboBox = new ComboBox<>(FXCollections.observableArrayList(states));
        ComboBox<String> departmentComboBox = new ComboBox<>(FXCollections.observableArrayList(departments));
        ComboBox<String> semesterComboBox = new ComboBox<>(FXCollections.observableArrayList(semesters));
        ComboBox<String> yearComboBox = new ComboBox<>(FXCollections.observableArrayList(years));
        ComboBox<Character> gradeComboBox = new ComboBox<>(FXCollections.observableArrayList(grades));
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();

        Menu file = new Menu("File");
        Menu student = new Menu("Students");
        Menu course = new Menu("Courses");
        Menu enrollment = new Menu("Enrollments");
        Menu report = new Menu("Reports");

        MenuItem exit = new MenuItem("Exit");
        MenuItem addStudent = new MenuItem("Add Student");
        MenuItem editStudent = new MenuItem("Edit Student");
        MenuItem displayStudent = new MenuItem("View Student");
        MenuItem addCourse = new MenuItem("Add Course");
        MenuItem editCourse = new MenuItem("Edit Course");
        MenuItem displayCourse = new MenuItem("View Course");
        MenuItem addEnrollment = new MenuItem("Add Enrollment");
        MenuItem viewEditEnrollment = new MenuItem("View/Edit Enrollment");
        MenuItem generateReport = new MenuItem("Generate Report");

        file.getItems().add(exit);
        student.getItems().addAll(addStudent, editStudent, displayStudent);
        course.getItems().addAll(addCourse, editCourse, displayCourse);
        enrollment.getItems().addAll(addEnrollment, viewEditEnrollment);
        report.getItems().add(generateReport);

        MenuBar mb = new MenuBar();

        mb.getMenus().addAll(file, student, course, enrollment, report);

        TextArea reportTextArea = new TextArea();

        reportTextArea.setPrefHeight(300);  //sets height of the TextArea to 400 pixels
        reportTextArea.setMaxWidth(600);

        VBox vb = new VBox(gridPane, reportTextArea);
        vb.setAlignment(Pos.CENTER);

        addStudent.setOnAction(event -> {
            reportTextArea.setVisible(false);
            AddStudentWindow(gridPane, stateComboBox);
        });
        editStudent.setOnAction(event -> {
            reportTextArea.setVisible(false);
            EditStudentWindow(gridPane, stateComboBox);
        });
        displayStudent.setOnAction(event -> {
            reportTextArea.setVisible(false);
            ViewStudentWindow(gridPane);
        });
        addCourse.setOnAction(event -> {
            reportTextArea.setVisible(false);
            AddCourseWindow(gridPane, departmentComboBox);
        });
        editCourse.setOnAction(event -> {
            reportTextArea.setVisible(false);
            EditCourseWindow(gridPane, departmentComboBox);
        });
        displayCourse.setOnAction(event -> {
            reportTextArea.setVisible(false);
            ViewCourseWindow(gridPane);
        });
        addEnrollment.setOnAction(event -> {
            reportTextArea.setVisible(false);
            AddEnrollmentWindow(gridPane, semesterComboBox, yearComboBox, gradeComboBox);
        });
        viewEditEnrollment.setOnAction(event -> {
            reportTextArea.setVisible(false);
            ViewEditEnrollmentWindow(gridPane, gradeComboBox);
        });
        generateReport.setOnAction(event -> {
            reportTextArea.setVisible(true);
            GenerateReportWindow(gridPane, semesterComboBox, yearComboBox, reportTextArea);
        });
        exit.setOnAction(event -> {
            Platform.exit();
        });

        borderPane.setTop(mb);
        borderPane.setCenter(vb);

        gridPane.setPadding(new Insets(50));

        gridPane.setAlignment(Pos.CENTER);

        reportTextArea.setVisible(false);

        Label welcomeLabel = new Label("Welcome to the University Enrollment application!");
        gridPane.add(welcomeLabel, 0, 0);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("University Enrollment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
