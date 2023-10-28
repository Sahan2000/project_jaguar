package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewAllanymalsDetails implements Initializable{


    @FXML
    //All Components in ViewAllanymalForm
    private TableView<Animal> tableAnimal;

    public ObservableList<Animal> data;

    @FXML
    public TableColumn<Animal, String> clmId ;
    public TableColumn<Animal, String> clmName;
    public TableColumn<Animal, String> clmAge;
    public TableColumn<Animal, String> clmStatus;
    public TableColumn<Animal, String> clmGender;
    public TableColumn<Animal, String> clmFoodType;
    public TableColumn<Animal, String> clmKingdomType;
    public TableColumn<Animal, String> clmOrigin;
    public TableColumn<Animal, String> clmHealth;
    public TableColumn<Animal, String> clmEndangered;

    //Get MySql database URL and login data
    final String DB_URL = "jdbc:mysql://localhost:3306/solutech?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "1234";

    Connection conn = null;

    //Initialize Table
    @Override
    public void initialize(URL location, ResourceBundle resources) {

       data = FXCollections.observableArrayList();

        try {

            //Connect to the database
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //Select columns of what we want to get data - create statement
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT id,name,age,status,gender,food_type,kingdom_type,Origin ,health,endangered FROM addanimal");


            // Extract data from result set
            while (result.next()) {

                //Get values from database-table
                String id = result.getString(1);
                String name = result.getString(2);
                String age = result.getString(3);
                String status = result.getString(4);
                String gender = result.getString(5);
                String foodType = result.getString(6);
                String kingdomType = result.getString(7);
                String origin = result.getString(8);
                String health = result.getString(9);
                String endangered = result.getString(10);

                //Set values Animal class
                data.add(new Animal(id,name,age,status,gender,foodType,kingdomType,origin,health,endangered));

                //sets the cell value factory for a table columns
                clmId.setCellValueFactory(new PropertyValueFactory<>("clmId"));
                clmName.setCellValueFactory(new PropertyValueFactory<>("clmName"));
                clmAge.setCellValueFactory(new PropertyValueFactory<>("clmAge"));
                clmStatus.setCellValueFactory(new PropertyValueFactory<>("clmStatus"));
                clmGender.setCellValueFactory(new PropertyValueFactory<>("clmGender"));
                clmFoodType.setCellValueFactory(new PropertyValueFactory<>("clmFoodType"));
                clmKingdomType.setCellValueFactory(new PropertyValueFactory<>("clmKingdomType"));
                clmOrigin.setCellValueFactory(new PropertyValueFactory<>("clmOrigin"));
                clmHealth.setCellValueFactory(new PropertyValueFactory<>("clmHealth"));
                clmEndangered.setCellValueFactory(new PropertyValueFactory<>("clmEndangered"));

                clmId.setCellFactory(TextFieldTableCell.forTableColumn());
                clmName.setCellFactory(TextFieldTableCell.forTableColumn());
                clmAge.setCellFactory(TextFieldTableCell.forTableColumn());
                clmStatus.setCellFactory(TextFieldTableCell.forTableColumn());
                clmGender.setCellFactory(TextFieldTableCell.forTableColumn());
                clmFoodType.setCellFactory(TextFieldTableCell.forTableColumn());
                clmKingdomType.setCellFactory(TextFieldTableCell.forTableColumn());
                clmOrigin.setCellFactory(TextFieldTableCell.forTableColumn());
                clmHealth.setCellFactory(TextFieldTableCell.forTableColumn());
                clmEndangered.setCellFactory(TextFieldTableCell.forTableColumn());

                //Add data to TableView
                tableAnimal.setItems(data);

            }


            clmId.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmId(event.getNewValue());

            });

            clmName.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmName(event.getNewValue());

            });

            clmAge.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmAge(event.getNewValue());

            });

            clmStatus.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmStatus(event.getNewValue());

            });

            clmGender.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmGender(event.getNewValue());

            });

            clmFoodType.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmFoodType(event.getNewValue());

            });

            clmKingdomType.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmKingdomType(event.getNewValue());

            });

            clmOrigin.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmOrigin(event.getNewValue());

            });

            clmHealth.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmHealth(event.getNewValue());

            });

            clmEndangered.setOnEditCommit(event -> {

                Animal animal = event.getRowValue();
                animal.setClmEndangered(event.getNewValue());

            });

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

    }


    //Open AddAnimalForm
    public void btnAddAnimalsDetails(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/AddAnimalForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void btnUpdateAnimalDetails(ActionEvent actionEvent) {

        int selectedID=tableAnimal.getSelectionModel().getSelectedIndex();
        Animal animal=tableAnimal.getSelectionModel().getSelectedItem();

        String animalId=animal.getClmId();
        String name=animal.getClmName();
        String Age=animal.getClmAge();
        String status=animal.getClmStatus();
        String gender=animal.getClmGender();
        String foodType=animal.getClmFoodType();
        String kingdomType=animal.getClmKingdomType();
        String endangered=animal.getClmEndangered();
        String origin=animal.getClmOrigin();
        String health=animal.getClmHealth();



        int age = Integer.parseInt(Age);
        LocalDate today = LocalDate.now();
        LocalDate bornDt = today.minusYears(age);

        try {

            //Connect to the database
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //Select columns what we want to update - create statement
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement("UPDATE addanimal SET id = ?,name = ?,age = ?,bornDate = ?,status = ?,gender = ?,food_type = ?,kingdom_type = ?,endangered = ?,Origin = ?,health = ? WHERE id = ? ");

            //Update values to database
            pstmt.setString(1, animalId);
            pstmt.setString(2, name);
            pstmt.setString(3, Age);
            pstmt.setDate(4, Date.valueOf(bornDt));
            pstmt.setString(5, status);
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
    }


    public void btnDeleteAnimalDetail(ActionEvent actionEvent) {

        int selectedID=tableAnimal.getSelectionModel().getSelectedIndex();
        Animal animal=tableAnimal.getSelectionModel().getSelectedItem();
        String animalId=animal.getClmId();
        tableAnimal.getItems().remove(selectedID);


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
    }

    public String mouseClicked(MouseEvent mouseEvent) {

        Animal animal=tableAnimal.getSelectionModel().getSelectedItem();
        String animalId=animal.getClmId();
        return animalId;
    }
}
