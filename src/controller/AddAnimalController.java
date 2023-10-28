package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class AddAnimalController {

    //All Components in AddAnimalForm
    public TextField txtId;
    public TextField txtName;
    public TextField txtAge;
    public DatePicker dateBorn;
    public DatePicker dateDeath;
    public ComboBox foodTypeBox;
    public ComboBox kingdomTypeBox;
    public TextField txtHealth;
    public RadioButton rbtnEndangerd;
    public RadioButton rbtnLocal;
    public RadioButton rbtnExotic;
    public RadioButton rbtnMale;
    public RadioButton rbtnFemale;
    public RadioButton rbtnPregnant;
    public TextField txtSearch;
    public Button btnAdd;

    //Add items to foodTypeBox and kingdomTypeBox and call methods
    @FXML
    public void initialize() {

        foodTypeBox.getItems().addAll("Herbivores", "Carnivorous", "Omnivores");
        kingdomTypeBox.getItems().addAll("Pisces", "Amphibian", "Reptilian", "Aves", "Mammal");

        addRbtnsToGroup();
        addOriginToGroup();

    }

    //Add male,female radio buttons to a Toggle group
    private void addRbtnsToGroup(){

        ToggleGroup genderGroup = new ToggleGroup();
        rbtnMale.setToggleGroup(genderGroup);
        rbtnFemale.setToggleGroup(genderGroup);

    }

    //Add local,exotic radio buttons to a Toggle group
    private void addOriginToGroup(){

        ToggleGroup originGroup = new ToggleGroup();
        rbtnLocal.setToggleGroup(originGroup);
        rbtnExotic.setToggleGroup(originGroup);

    }

    //Get MySql database URL and login data
    final String DB_URL = "jdbc:mysql://localhost:3306/solutech?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "1234";

    Connection conn = null;

    public void addAnimal() throws IOException {

        //Assign AddAnimalForm Components values for variables
        String id = txtId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String health = getHealth();
        Date bornDt = Date.valueOf(dateBorn.getValue());
        String deathDt = findStatus();
        String gender = chooseGender();
        String foodType = String.valueOf(foodTypeBox.getValue());
        String kingdomType = String.valueOf(kingdomTypeBox.getValue());
        String origin = findOrigin();
        String endangered = isEndangered();





        try {

            //Connect to the database
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //Select table and columns of what we want to add data - create statement
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("INSERT INTO addanimal (id, name, age ,bornDate,status,gender,food_type,kingdom_type,endangered,Origin ,health) VALUE (?,?,?,?,?,?,?,?,?,?,?)");

            //Assign values to database
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, age);
            pstmt.setDate(4, bornDt);
            pstmt.setString(5, deathDt);
            pstmt.setString(6, gender);
            pstmt.setString(7, foodType);
            pstmt.setString(8, kingdomType);
            pstmt.setString(9, endangered);
            pstmt.setString(10, origin);
            pstmt.setString(11, health);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Call clear form method
        clerForm();

    }

    //Find animals health and return it
    private String getHealth() {

        if (rbtnPregnant.isSelected() && txtHealth.getText().equals("")) {
            return "Pregnant";
        } else if (!txtHealth.getText().equals("") && rbtnPregnant.isSelected()) {
            return "Pregnant, " + txtHealth.getText();
        } else {
            return txtHealth.getText();
        }

    }

    //Find animals gender and return it
    private String chooseGender() {

        if (rbtnMale.isSelected()) {
            return "Male";
        } else if (rbtnFemale.isSelected()) {
            return "Female";
        }
        return null;

    }

    //Find animals is endangered or not and return it
    private String isEndangered() {

        if (rbtnEndangerd.isSelected()) {
            return "Yes";
        } else {
            return "No";
        }

    }

    //Find animals origin and return it
    private String findOrigin() {

        if (rbtnLocal.isSelected()) {
            return "Local";
        } else if (rbtnExotic.isSelected()) {
            return "Foreign";
        }
        return null;

    }

    //Find animals life status and return it
    private String findStatus() {

        if (dateDeath.getValue() == null) {
            return "Alives";
        } else {
            return "Dead-" + dateDeath.getValue();
        }

    }

    //Clear components values after add data to the table
    private void clerForm() {

        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtHealth.setText("");
        txtSearch.setText("");
        dateBorn.setValue(null);
        dateDeath.setValue(null);
        foodTypeBox.setValue(null);
        kingdomTypeBox.setValue(null);
        rbtnMale.setSelected(false);
        rbtnFemale.setSelected(false);
        rbtnPregnant.setSelected(false);
        rbtnEndangerd.setSelected(false);
        rbtnLocal.setSelected(false);
        rbtnExotic.setSelected(false);

    }

    //Search animal data in database
    public void searchData() throws IOException {

        //Disable animal add button
       // btnAdd.setDisable(true);

        //Assign search textfield value to animalId
        String animalId = txtSearch.getText();


        try {

            //Connect to the database
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //Select table and columns of what we want to add data - create statement
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("SELECT * FROM addanimal WHERE id = ?");

            //Set data to WHERE clause
            pstmt.setString(1, animalId);

            //Create a resultset variable
            ResultSet resultSet = pstmt.executeQuery();

            // Extract data from result set
            while (resultSet.next()) {

                //Get values from database-table
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String age = resultSet.getString(3);
                Date bornDt = resultSet.getDate(4);
                String status = resultSet.getString(5);
                String gender = resultSet.getString(6);
                String foodType = resultSet.getString(7);
                String kingdomType = resultSet.getString(8);
                String endangered = resultSet.getString(9);
                String origin = resultSet.getString(10);
                String health = resultSet.getString(11);

                //Set values to components
                txtId.setText(id);
                txtName.setText(name);
                txtAge.setText(age);
                setHealth(health);
                setBornDate(bornDt);
                setStatus(status);
                setGender(gender);
                foodTypeBox.setValue(foodType);
                kingdomTypeBox.setValue(kingdomType);
                setOrigin(origin);
                setEndangerdOrNOt(endangered);

            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        if(txtId.getText().equals(animalId)){
            btnAdd.setDisable(true);
        }else {
            clerForm();
            btnAdd.setDisable(false);
        }

    }

    //Set gender to Radio Button - from database
    private void setGender(String gender) {

        if (gender.equals("Male")) {
            rbtnMale.setSelected(true);
        } else {
            rbtnFemale.setSelected(true);
        }

    }

    //Set health records
    private void setHealth(String health){

        char[] charArray = health.toCharArray();

        if(charArray.length>=9) {

            String stringValue = new String(charArray, 0, 7 - 0 + 1);

            String stringvalue1 = new String(charArray, 9, charArray.length-1 - 9 + 1);

            if (stringValue.equalsIgnoreCase("Pregnant")) {
                rbtnPregnant.setSelected(true);
                txtHealth.setText(stringvalue1);
            } else {
                rbtnPregnant.setSelected(false);
                txtHealth.setText(health);
            }
        }else{
            rbtnPregnant.setSelected(false);
            txtHealth.setText(health);
        }

    }

    //Set origin to Radio Button - from database
    private void setOrigin(String origin) {

        if (origin.equalsIgnoreCase("local")) {
            rbtnLocal.setSelected(true);
        } else {
            rbtnExotic.setSelected(true);
        }

    }

    //Set enderged or not to Radio Button - from database
    private void setEndangerdOrNOt(String endangered) {

        if (endangered.equalsIgnoreCase("yes")) {
            rbtnEndangerd.setSelected(true);
        } else {
            rbtnEndangerd.setSelected(false);
        }

    }

    //Set lifeStatus to Radio Button - from database
    private void setStatus(String status) {

        if (status.equals("Alives")) {
            dateDeath.setValue(null);
        } else {
            char[] charArray = status.toCharArray();
            String stringValue = new String(charArray, 5, 14 - 5 + 1);
            dateDeath.setValue(LocalDate.parse(stringValue));
        }

    }

    //Set born date to Date Picker - from database
    private void setBornDate(Date bornDt) {

        String date = String.valueOf(bornDt);
        dateBorn.setValue(LocalDate.parse(date));

    }

    //Update selected data in database
    public void updateAnimal() throws IOException {

        //Enable animal add button
        btnAdd.setDisable(false);

        //Assign AddAnimalForm Components values for variables
        String id = txtId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String health = getHealth();
        Date bornDt = Date.valueOf(dateBorn.getValue());
        String deathDt = findStatus();
        String gender = chooseGender();
        String foodType = String.valueOf(foodTypeBox.getValue());
        String kingdomType = String.valueOf(kingdomTypeBox.getValue());
        String origin = findOrigin();
        String endangered = isEndangered();
        String animalId = txtSearch.getText();


        try {

            //Connect to the database
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //Select columns what we want to update - create statement
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("UPDATE addanimal SET id = ?,name = ?,age = ?,bornDate = ?,status = ?,gender = ?,food_type = ?,kingdom_type = ?,endangered = ?,Origin = ?,health = ? WHERE id = ? ");

            //Update values to database
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, age);
            pstmt.setDate(4, bornDt);
            pstmt.setString(5, deathDt);
            pstmt.setString(6, gender);
            pstmt.setString(7, foodType);
            pstmt.setString(8, kingdomType);
            pstmt.setString(9, endangered);
            pstmt.setString(10, origin);
            pstmt.setString(11, health);

            //Set data to WHERE clause
            pstmt.setString(12, animalId);

           pstmt.executeUpdate();


         } catch (Exception e) {
            e.printStackTrace();
        }

        //Call clearForm method
        clerForm();

    }

    //Delete selected data in database
    public void deleteAnimal()throws IOException{

        //Enable animal add button
        btnAdd.setDisable(false);

        //Assign searched id to variable
        String animalId = txtSearch.getText();


        try {

            //Connect to the database
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //Select column what we want to delete - create statement
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("DELETE FROM addanimal WHERE id=?");

            //Set data to WHERE clause
            pstmt.setString(1,animalId);

            pstmt.executeUpdate();


        }catch (Exception e) {
            e.printStackTrace();
        }

        //Call clear form method
        clerForm();

    }
}
