import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

/*
 *  Login: Admin / Admin123
 */
public class MISCollege extends Application {

    @Override
    public void start(Stage primaryStage) {
        new LoginStage(primaryStage).show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class Student {
        private final StringProperty studentId;
        private final StringProperty fullName;
        private final StringProperty gender;
        private final ObjectProperty<LocalDate> dateOfBirth;
        private final IntegerProperty gradeLevel;
        private final StringProperty track;
        private final StringProperty major;
        private final DoubleProperty gpa;
        private final StringProperty email;
        private final StringProperty phoneNumber;
        private final StringProperty guardianPhone;
        private final StringProperty residence;
        private final ObjectProperty<LocalDate> enrollmentDate;
        private final StringProperty transportMethod;
        private final BooleanProperty enrolled;

        Student(String studentId, String fullName, String gender, LocalDate dateOfBirth, int gradeLevel,
                String track, String major, double gpa, String email, String phoneNumber,
                String guardianPhone, String residence, LocalDate enrollmentDate,
                String transportMethod, boolean enrolled) {
            this.studentId = new SimpleStringProperty(studentId);
            this.fullName = new SimpleStringProperty(fullName);
            this.gender = new SimpleStringProperty(gender);
            this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
            this.gradeLevel = new SimpleIntegerProperty(gradeLevel);
            this.track = new SimpleStringProperty(track);
            this.major = new SimpleStringProperty(major);
            this.gpa = new SimpleDoubleProperty(gpa);
            this.email = new SimpleStringProperty(email);
            this.phoneNumber = new SimpleStringProperty(phoneNumber);
            this.guardianPhone = new SimpleStringProperty(guardianPhone);
            this.residence = new SimpleStringProperty(residence);
            this.enrollmentDate = new SimpleObjectProperty<>(enrollmentDate);
            this.transportMethod = new SimpleStringProperty(transportMethod);
            this.enrolled = new SimpleBooleanProperty(enrolled);
        }

        public String getStudentId() { return studentId.get(); }
        public StringProperty studentIdProperty() { return studentId; }

        public String getFullName() { return fullName.get(); }
        public StringProperty fullNameProperty() { return fullName; }

        public String getGender() { return gender.get(); }
        public StringProperty genderProperty() { return gender; }

        public LocalDate getDateOfBirth() { return dateOfBirth.get(); }
        public ObjectProperty<LocalDate> dateOfBirthProperty() { return dateOfBirth; }

        public int getGradeLevel() { return gradeLevel.get(); }
        public IntegerProperty gradeLevelProperty() { return gradeLevel; }

        public String getTrack() { return track.get(); }
        public StringProperty trackProperty() { return track; }

        public String getMajor() { return major.get(); }
        public StringProperty majorProperty() { return major; }

        public double getGpa() { return gpa.get(); }
        public DoubleProperty gpaProperty() { return gpa; }

        public String getEmail() { return email.get(); }
        public StringProperty emailProperty() { return email; }

        public String getPhoneNumber() { return phoneNumber.get(); }
        public StringProperty phoneNumberProperty() { return phoneNumber; }

        public String getGuardianPhone() { return guardianPhone.get(); }
        public StringProperty guardianPhoneProperty() { return guardianPhone; }

        public String getResidence() { return residence.get(); }
        public StringProperty residenceProperty() { return residence; }

        public LocalDate getEnrollmentDate() { return enrollmentDate.get(); }
        public ObjectProperty<LocalDate> enrollmentDateProperty() { return enrollmentDate; }

        public String getTransportMethod() { return transportMethod.get(); }
        public StringProperty transportMethodProperty() { return transportMethod; }

        public boolean isEnrolled() { return enrolled.get(); }
        public BooleanProperty enrolledProperty() { return enrolled; }


        public String getFaculty() {
            return mapMajorToFaculty(major.get());
        }

        private static String mapMajorToFaculty(String major) {
            if (major == null) return "Unassigned";
            switch (major) {
                case "Computer Science":
                    return "Faculty of Engineering & Technology";
                case "Business Administration":
                    return "Faculty of Business & Economics";
                case "Psychology":
                    return "Faculty of Arts & Humanities";
                case "Biology":
                    return "Faculty of Natural Sciences";
                case "Undecided":
                    return "General Studies Faculty";
                default:
                    return "Unassigned";
            }
        }
    }

    static class DataStore {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private int nextId = 1;

        private void addSeed(String name, String gender, LocalDate dob, int grade, String track, String major, double gpa,
                             String email, String phoneNumber, String guardianPhone, String residence,
                             LocalDate enrollDate, String transport, boolean enrolled) {
            students.add(new Student(generateNextId(), name, gender, dob, grade, track, major, gpa, email,
                    phoneNumber, guardianPhone, residence, enrollDate, transport, enrolled));
        }

        ObservableList<Student> getStudents() { return students; }

        String generateNextId() { return "NB-" + String.format("%04d", nextId++); }
    }
    // STAGE #1: Sign-in window

    static class LoginStage {
        private static final String VALID_USERNAME = "Admin";
        private static final String VALID_PASSWORD = "Admin123";

        private final Stage stage;

        LoginStage(Stage stage) {
            this.stage = stage;
            this.stage.setTitle("MISCollege - Staff Sign In");
            this.stage.setScene(buildScene());
            this.stage.setResizable(false);
        }

        void show() { stage.show(); }

        private Scene buildScene() {
            VBox root = new VBox(16);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(40));
            root.setStyle("-fx-background-color: linear-gradient(to bottom, #0b2447, #19376d);");

            Label title = new Label("MISCollege");
            title.setFont(Font.font("Georgia", FontWeight.BOLD, 23));
            title.setTextFill(Color.web("#f5c518"));
            DropShadow glow = new DropShadow();
            glow.setColor(Color.web("#f5c518"));
            glow.setRadius(14);
            title.setEffect(glow);

            Label subtitle = new Label("Student Information System");
            subtitle.setFont(Font.font("Georgia", 13));
            subtitle.setTextFill(Color.web("#c9d6e8"));

            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");
            usernameField.setMaxWidth(230);

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            passwordField.setMaxWidth(230);

            Label errorLabel = new Label();
            errorLabel.setTextFill(Color.web("#ff6b6b"));
            errorLabel.setFont(Font.font(12));

            Button loginButton = new Button("Sign In");
            loginButton.setStyle("-fx-background-color:#f5c518; -fx-text-fill:#0b2447; -fx-font-weight:bold; -fx-padding:8 20;");
            loginButton.setDefaultButton(true);
            loginButton.setOnAction(e -> {
                if (VALID_USERNAME.equals(usernameField.getText()) && VALID_PASSWORD.equals(passwordField.getText())) {
                    openDashboard();
                } else {
                    errorLabel.setText("Access denied - invalid credentials.");
                }
            });
            root.getChildren().addAll(title, subtitle, usernameField, passwordField, loginButton, errorLabel);
            return new Scene(root, 380, 400);
        }

        private void openDashboard() {
            Stage dashboardStage = new Stage();
            new MainStage(dashboardStage);
            dashboardStage.show();
            stage.close();
        }
    }


    // STAGE #2: Main dashboard window - switch between 3 scenes

    static class MainStage {
        private final Stage stage;
        private final BorderPane root;

        private final DataStore dataStore = new DataStore();

        private final DashboardScene dashboardScene;
        private final EnrollmentScene enrollmentScene;
        private final StudentTableScene tableScene;

        MainStage(Stage stage) {
            this.stage = stage;
            stage.setTitle("MISCollege - Student Information System");

            root = new BorderPane();
            root.setTop(buildNavigationBar());

            dashboardScene = new DashboardScene(dataStore);
            // Jump straight to the table
            enrollmentScene = new EnrollmentScene(dataStore, this::showTable);
            tableScene = new StudentTableScene(dataStore);

            showDashboard();

            stage.setScene(new Scene(root, 1050, 650));
        }

        private HBox buildNavigationBar() {
            HBox nav = new HBox(14);
            nav.setPadding(new Insets(14));
            nav.setAlignment(Pos.CENTER_LEFT);
            nav.setStyle("-fx-background-color:#19376d;");

            Label logo = new Label("MISCollege");
            logo.setFont(Font.font("Georgia", FontWeight.BOLD, 17));
            logo.setTextFill(Color.web("#f5c518"));

            Button dashboardButton = navButton("Dashboard");
            dashboardButton.setOnAction(e -> showDashboard());

            Button enrollButton = navButton("Enroll Student");
            enrollButton.setOnAction(e -> showEnrollment());

            Button tableButton = navButton("All Students");
            tableButton.setOnAction(e -> showTable());

            Button logoutButton = navButton("Logout");
            logoutButton.setOnAction(e -> logout());

            nav.getChildren().addAll(logo, dashboardButton, enrollButton, tableButton, logoutButton);
            return nav;
        }

        private Button navButton(String text) {
            Button button = new Button(text);
            button.setStyle("-fx-background-color:#28518c; -fx-text-fill:white; -fx-font-weight:bold; -fx-padding:6 14;");
            return button;
        }

        void showDashboard() {
            dashboardScene.refresh();
            root.setCenter(dashboardScene.getView());
        }

        void showEnrollment() {
            root.setCenter(enrollmentScene.getView());
        }

        void showTable() {
            tableScene.refresh();
            root.setCenter(tableScene.getView());
        }

        private void logout() {
            Stage loginStage = new Stage();
            new LoginStage(loginStage).show();
            stage.close();
        }
    }


    static class DashboardScene {
        private final DataStore store;
        private final VBox view;
        private final Label totalLabel;
        private final Label enrolledLabel;
        private final Label avgGpaLabel;

        DashboardScene(DataStore store) {
            this.store = store;
            view = new VBox(18);
            view.setAlignment(Pos.CENTER);
            view.setPadding(new Insets(50));
            view.setStyle("-fx-background-color:#f2f5fa;");

            Label welcome = new Label("Welcome back, Admin");
            welcome.setFont(Font.font("Georgia", FontWeight.BOLD, 26));
            welcome.setTextFill(Color.web("#0b2447"));

            totalLabel = new Label();
            totalLabel.setFont(Font.font(16));
            totalLabel.setTextFill(Color.web("#19376d"));

            enrolledLabel = new Label();
            enrolledLabel.setFont(Font.font(16));
            enrolledLabel.setTextFill(Color.web("#19376d"));

            avgGpaLabel = new Label();
            avgGpaLabel.setFont(Font.font(16));
            avgGpaLabel.setTextFill(Color.web("#19376d"));

            Label info = new Label("Use the navigation bar above to enroll a new student\nor browse the full student roster.");
            info.setFont(Font.font(13));
            info.setTextFill(Color.web("#5a6b85"));
            info.setTextAlignment(TextAlignment.CENTER);

            view.getChildren().addAll(welcome, totalLabel, enrolledLabel, avgGpaLabel, info);
        }

        void refresh() {
            ObservableList<Student> students = store.getStudents();
            long enrolledCount = students.stream().filter(Student::isEnrolled).count();
            double avgGpa = students.stream().mapToDouble(Student::getGpa).average().orElse(0.0);

            totalLabel.setText("Total students on record: " + students.size());
            enrolledLabel.setText("Currently enrolled: " + enrolledCount);
            avgGpaLabel.setText(String.format("Average GPA: %.2f", avgGpa));
        }

        VBox getView() { return view; }
    }

    static class EnrollmentScene {
        private final DataStore store;
        private final Runnable onEnrolled;
        private final GridPane view;

        private TextField nameField;
        private ToggleGroup genderGroup;
        private RadioButton maleRadio;
        private RadioButton femaleRadio;
        private DatePicker dobPicker;
        private ComboBox<Integer> gradeBox;
        private ComboBox<String> trackBox;
        private ComboBox<String> majorBox;
        private Slider gpaSlider;
        private TextField emailField;
        private TextField phoneNumberField;
        private TextField guardianPhoneField;
        private TextField residenceField;
        private DatePicker enrollmentDatePicker;
        private ComboBox<String> transportBox;
        private CheckBox enrolledCheck;
        private Label statusLabel;

        EnrollmentScene(DataStore store, Runnable onEnrolled) {
            this.store = store;
            this.onEnrolled = onEnrolled;
            view = new GridPane();
            view.setPadding(new Insets(30));
            view.setHgap(14);
            view.setVgap(12);
            view.setStyle("-fx-background-color:#f2f5fa;");
            build();
        }

        private void build() {
            Label title = new Label("New Student Enrollment");
            title.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
            title.setTextFill(Color.web("#0b2447"));
            view.add(title, 0, 0, 2, 1);

            nameField = new TextField();
            addRow(1, "Full Name:", nameField);

            // Gender - a real JavaFX RadioButton pair sharing one ToggleGroup
            // so only one of the two can be selected at a time.
            maleRadio = new RadioButton("Male");
            femaleRadio = new RadioButton("Female");
            genderGroup = new ToggleGroup();
            maleRadio.setToggleGroup(genderGroup);
            femaleRadio.setToggleGroup(genderGroup);
            HBox genderBox = new HBox(16, maleRadio, femaleRadio);
            genderBox.setAlignment(Pos.CENTER_LEFT);
            addRow(2, "Gender:", genderBox);

            dobPicker = new DatePicker();
            dobPicker.setPromptText("Select date of birth");
            addRow(3, "Date of Birth:", dobPicker);

            gradeBox = new ComboBox<>();
            gradeBox.getItems().addAll(9, 10, 11, 12);
            gradeBox.setPromptText("Select Grade");
            addRow(4, "Grade Level:", gradeBox);

            trackBox = new ComboBox<>();
            trackBox.getItems().addAll("Science & Technology", "Business & Economics",
                    "Humanities & Arts", "General Studies");
            trackBox.setPromptText("Select Academic Track");
            addRow(5, "Academic Track:", trackBox);

            majorBox = new ComboBox<>();
            majorBox.getItems().addAll("Computer Science", "Business Administration",
                    "Psychology", "Biology", "Undecided");
            majorBox.setPromptText("Select Intended Major");
            addRow(6, "Major:", majorBox);

            gpaSlider = new Slider(0.0, 4.0, 3.0);
            gpaSlider.setShowTickLabels(true);
            gpaSlider.setShowTickMarks(true);
            gpaSlider.setMajorTickUnit(1.0);
            gpaSlider.setBlockIncrement(0.1);
            addRow(7, "GPA:", gpaSlider);

            emailField = new TextField();
            addRow(8, "Email:", emailField);

            phoneNumberField = new TextField();
            addRow(9, "Student Phone:", phoneNumberField);

            guardianPhoneField = new TextField();
            addRow(10, "Guardian Phone:", guardianPhoneField);

            residenceField = new TextField();
            addRow(11, "Current Residence:", residenceField);

            enrollmentDatePicker = new DatePicker(LocalDate.now());
            addRow(12, "Enrollment Date:", enrollmentDatePicker);

            transportBox = new ComboBox<>();
            transportBox.getItems().addAll("School Bus", "Private Car", "Walking", "Bicycle");
            transportBox.setPromptText("Select Transportation");
            addRow(13, "Transportation:", transportBox);

            enrolledCheck = new CheckBox("Currently enrolled and attending");
            enrolledCheck.setSelected(true);
            addRow(14, "Status:", enrolledCheck);

            Button submitButton = new Button("Submit Enrollment");
            submitButton.setStyle("-fx-background-color:#f5c518; -fx-text-fill:#0b2447; -fx-font-weight:bold; -fx-padding:8 16;");
            submitButton.setOnAction(e -> submit());
            view.add(submitButton, 1, 15);

            statusLabel = new Label();
            statusLabel.setFont(Font.font(12));
            view.add(statusLabel, 1, 16);
        }

        private void addRow(int row, String labelText, Node control) {
            Label label = new Label(labelText);
            label.setFont(Font.font(13));
            label.setTextFill(Color.web("#0b2447"));
            view.add(label, 0, row);
            view.add(control, 1, row);
        }

        private void submit() {
            if (nameField.getText().isBlank() || genderGroup.getSelectedToggle() == null
                    || dobPicker.getValue() == null
                    || gradeBox.getValue() == null || trackBox.getValue() == null || majorBox.getValue() == null
                    || emailField.getText().isBlank() || phoneNumberField.getText().isBlank()
                    || guardianPhoneField.getText().isBlank() || residenceField.getText().isBlank()
                    || enrollmentDatePicker.getValue() == null || transportBox.getValue() == null) {
                statusLabel.setTextFill(Color.web("#c0392b"));
                statusLabel.setText("Please fill in every field before submitting.");
                return;
            }

            String selectedGender = ((RadioButton) genderGroup.getSelectedToggle()).getText();

            String newId = store.generateNextId();
            Student student = new Student(
                    newId,
                    nameField.getText().trim(),
                    selectedGender,
                    dobPicker.getValue(),
                    gradeBox.getValue(),
                    trackBox.getValue(),
                    majorBox.getValue(),
                    Math.round(gpaSlider.getValue() * 100.0) / 100.0,
                    emailField.getText().trim(),
                    phoneNumberField.getText().trim(),
                    guardianPhoneField.getText().trim(),
                    residenceField.getText().trim(),
                    enrollmentDatePicker.getValue(),
                    transportBox.getValue(),
                    enrolledCheck.isSelected()
            );

            store.getStudents().add(student);

            new ConfirmationPopup(student).show();

            clearForm();
            statusLabel.setTextFill(Color.web("#1e824c"));
            statusLabel.setText("Student enrolled successfully! (ID: " + newId + ")");

            // Jump to the "All Students" table now that the popup is closed,
            // so the newly enrolled student is immediately visible.
            onEnrolled.run();
        }

        private void clearForm() {
            nameField.clear();
            genderGroup.selectToggle(null);
            dobPicker.setValue(null);
            gradeBox.setValue(null);
            trackBox.setValue(null);
            majorBox.setValue(null);
            gpaSlider.setValue(2.5);
            emailField.clear();
            phoneNumberField.clear();
            guardianPhoneField.clear();
            residenceField.clear();
            enrollmentDatePicker.setValue(LocalDate.now());
            transportBox.setValue(null);
            enrolledCheck.setSelected(true);
        }

        GridPane getView() { return view; }
    }

    static class ConfirmationPopup {
        private final Stage stage;

        ConfirmationPopup(Student student) {
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Enrollment Confirmed");
            stage.setResizable(false);

            VBox box = new VBox(14);
            box.setAlignment(Pos.CENTER);
            box.setPadding(new Insets(30));
            box.setStyle("-fx-background-color:#0b2447;");

            Label icon = new Label("\u2713");
            icon.setFont(Font.font(40));
            icon.setTextFill(Color.web("#f5c518"));

            Label message = new Label(student.getFullName() + " (" + student.getGender() + ")\nhas been enrolled as a\n"
                    + student.getMajor() + " major\n(" + student.getFaculty() + ")");
            message.setFont(Font.font("Georgia", FontWeight.BOLD, 15));
            message.setTextFill(Color.web("#f5c518"));
            message.setTextAlignment(TextAlignment.CENTER);

            Button closeButton = new Button("Great!");
            closeButton.setStyle("-fx-background-color:#f5c518; -fx-text-fill:#0b2447; -fx-font-weight:bold;");
            closeButton.setOnAction(e -> stage.close());

            box.getChildren().addAll(icon, message, closeButton);
            stage.setScene(new Scene(box, 300, 260));
        }

        void show() { stage.showAndWait(); }
    }


    static class StudentTableScene {
        private final DataStore store;
        private final VBox view;
        private final TableView<Student> table;

        StudentTableScene(DataStore store) {
            this.store = store;
            view = new VBox(14);
            view.setPadding(new Insets(20));
            view.setStyle("-fx-background-color:#ffffff;");

            Label title = new Label("All Students on Record");
            title.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
            title.setTextFill(Color.web("#0b2447"));

            Button deleteAllButton = new Button("Delete All Students");
            deleteAllButton.setStyle("-fx-background-color:#c0392b; -fx-text-fill:white; -fx-font-weight:bold; -fx-padding:6 14;");
            deleteAllButton.setOnAction(e -> confirmAndDeleteAll());

            BorderPane header = new BorderPane();
            header.setLeft(title);
            header.setRight(deleteAllButton);
            BorderPane.setAlignment(title, Pos.CENTER_LEFT);
            BorderPane.setAlignment(deleteAllButton, Pos.CENTER_RIGHT);

            table = new TableView<>();
            buildColumns();
            table.setItems(store.getStudents());
            table.setPlaceholder(new Label("No students enrolled yet."));

            view.getChildren().addAll(header, table);
        }

        private void confirmAndDeleteAll() {
            if (store.getStudents().isEmpty()) {
                return;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Delete All Students");
            confirm.setHeaderText("Remove all " + store.getStudents().size() + " students from the table?");
            confirm.setContentText("This cannot be undone.");
            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    store.getStudents().clear();
                    table.refresh();
                }
            });
        }

        @SuppressWarnings("unchecked")
        private void buildColumns() {
            TableColumn<Student, String> idCol = new TableColumn<>("ID");
            idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studentId"));

            TableColumn<Student, String> nameCol = new TableColumn<>("Full Name");
            nameCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("fullName"));
            nameCol.setPrefWidth(140);

            TableColumn<Student, String> genderCol = new TableColumn<>("Gender");
            genderCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("gender"));
            genderCol.setPrefWidth(80);

            TableColumn<Student, LocalDate> dobCol = new TableColumn<>("Date of Birth");
            dobCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("dateOfBirth"));
            dobCol.setPrefWidth(100);

            TableColumn<Student, Integer> gradeCol = new TableColumn<>("Grade");
            gradeCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("gradeLevel"));

            TableColumn<Student, String> trackCol = new TableColumn<>("Track");
            trackCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("track"));
            trackCol.setPrefWidth(160);

            TableColumn<Student, String> majorCol = new TableColumn<>("Major");
            majorCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("major"));
            majorCol.setPrefWidth(150);

            TableColumn<Student, String> facultyCol = new TableColumn<>("Faculty");
            facultyCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getFaculty()));
            facultyCol.setPrefWidth(210);

            TableColumn<Student, Double> gpaCol = new TableColumn<>("GPA");
            gpaCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("gpa"));

            TableColumn<Student, String> emailCol = new TableColumn<>("Email");
            emailCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));
            emailCol.setPrefWidth(180);

            TableColumn<Student, String> phoneNumberCol = new TableColumn<>("Student Phone");
            phoneNumberCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("phoneNumber"));
            phoneNumberCol.setPrefWidth(110);

            TableColumn<Student, String> guardianPhoneCol = new TableColumn<>("Guardian Phone");
            guardianPhoneCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("guardianPhone"));
            guardianPhoneCol.setPrefWidth(120);

            TableColumn<Student, String> residenceCol = new TableColumn<>("Residence");
            residenceCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("residence"));
            residenceCol.setPrefWidth(180);

            TableColumn<Student, LocalDate> dateCol = new TableColumn<>("Enrolled On");
            dateCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("enrollmentDate"));
            dateCol.setPrefWidth(100);

            TableColumn<Student, String> transportCol = new TableColumn<>("Transport");
            transportCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("transportMethod"));
            transportCol.setPrefWidth(100);

            TableColumn<Student, Boolean> enrolledCol = new TableColumn<>("Enrolled");
            enrolledCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("enrolled"));

            table.getColumns().addAll(idCol, nameCol, genderCol, dobCol, gradeCol, trackCol, majorCol, facultyCol,
                    gpaCol, emailCol, phoneNumberCol, guardianPhoneCol, residenceCol, dateCol, transportCol, enrolledCol);
        }

        void refresh() { table.refresh(); }

        VBox getView() { return view; }
    }
}