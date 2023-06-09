package com.iamruda.lildesk;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.iamruda.lildesk.tables.ContractDev;
import com.iamruda.lildesk.tables.Client;
import com.iamruda.lildesk.tables.TechDoc;
import com.iamruda.lildesk.tables.Worker;
import com.iamruda.lildesk.tables.Project;
import com.iamruda.lildesk.tables.ContractWork;
import com.iamruda.lildesk.tables.Task;
import com.iamruda.lildesk.tables.Status;
import com.iamruda.lildesk.tables.ExecutorProject;
import com.iamruda.lildesk.tables.ExecutorTask;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowController {

    @FXML private TableView table;

    public void initialize() {
        ;
    }

    private ArrayList<Client> getSQLClients() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Client";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Client> listClient = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idClient = resultSet.getInt("IdClient");
            String fullName = resultSet.getString("FullName");
            String review = resultSet.getString("Review");
            String contactPhone = resultSet.getString("ContactPhone");
            String email = resultSet.getString("Email");
            Client newClient = new Client(idClient, fullName, review, contactPhone, email);
            listClient.add(newClient);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listClient;
    }

    public void showSQLClient() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<Client, String> idCol = new TableColumn<>("ID");
        TableColumn<Client, String> fullNameCol = new TableColumn<>("Full name");
        TableColumn<Client, String> reviewCol = new TableColumn<>("Review");
        TableColumn<Client, String> contactCol = new TableColumn<>("Contact phone");
        TableColumn<Client, String> emailCol = new TableColumn<>("Email");

        idCol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        reviewCol.setCellValueFactory(new PropertyValueFactory<>("review"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().add(idCol);
        table.getColumns().add(fullNameCol);
        table.getColumns().add(reviewCol);
        table.getColumns().add(contactCol);
        table.getColumns().add(emailCol);

        ObservableList<Client> data = FXCollections.observableArrayList(getSQLClients());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<ContractDev> getSQLContractDev() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM ContractDev";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<ContractDev> listContractDev = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int numberContractDev = resultSet.getInt("NumberContractDev");
            int idClient = resultSet.getInt("IdClient");
            String itemContract = resultSet.getString("ItemContract");
            String priceContract = resultSet.getString("PriceContract");
            String linkDoc = resultSet.getString("LinkDoc");
            ContractDev newContractDev = new ContractDev(numberContractDev, idClient, itemContract, priceContract, linkDoc);
            listContractDev.add(newContractDev);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listContractDev;
    }

    public void showSQLContractDev() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<Client, String> numberContractDevCol = new TableColumn<>("ID");
        TableColumn<Client, String> idClientCol = new TableColumn<>("ID Client");
        TableColumn<Client, String> itemContractCol = new TableColumn<>("Item");
        TableColumn<Client, String> priceContractCol = new TableColumn<>("Price");
        TableColumn<Client, String> linkDocCol = new TableColumn<>("Link");

        numberContractDevCol.setCellValueFactory(new PropertyValueFactory<>("NumberContractDev"));
        idClientCol.setCellValueFactory(new PropertyValueFactory<>("IdClient"));
        itemContractCol.setCellValueFactory(new PropertyValueFactory<>("ItemContract"));
        priceContractCol.setCellValueFactory(new PropertyValueFactory<>("PriceContract"));
        linkDocCol.setCellValueFactory(new PropertyValueFactory<>("LinkDoc"));

        table.getColumns().add(numberContractDevCol);
        table.getColumns().add(idClientCol);
        table.getColumns().add(itemContractCol);
        table.getColumns().add(priceContractCol);
        table.getColumns().add(linkDocCol);

        ObservableList<ContractDev> data = FXCollections.observableArrayList(getSQLContractDev());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<TechDoc> getSQLTechDoc() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM TechDoc";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<TechDoc> listTechDoc = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int numberTechDoc = resultSet.getInt("NumberTechDoc");
            String linkDoc = resultSet.getString("LinkDoc");
            TechDoc newTechDoc = new TechDoc(numberTechDoc, linkDoc);
            listTechDoc.add(newTechDoc);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listTechDoc;
    }

    public void showSQLTechDoc() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<TechDoc, String> numberTechDocCol = new TableColumn<>("ID");
        TableColumn<TechDoc, String> linkDocCol = new TableColumn<>("Link");

        numberTechDocCol.setCellValueFactory(new PropertyValueFactory<>("numberTechDoc"));
        linkDocCol.setCellValueFactory(new PropertyValueFactory<>("linkDoc"));

        table.getColumns().add(numberTechDocCol);
        table.getColumns().add(linkDocCol);

        ObservableList<TechDoc> data = FXCollections.observableArrayList(getSQLTechDoc());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<Worker> getSQLWorker() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Worker";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Worker> listWorker = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idWorker = resultSet.getInt("IdWorker");
            String fullName = resultSet.getString("FullName");
            int numberContractWork = resultSet.getInt("NumberContractWork");
            String post = resultSet.getString("Post");
            String skills = resultSet.getString("Skills");
            int salary = resultSet.getInt("Salary");
            Worker newWorker = new Worker(idWorker, fullName, numberContractWork, post, skills, salary);
            listWorker.add(newWorker);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listWorker;
    }

    public void showSQLWorker() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<Worker, String> idWorkerCol = new TableColumn<>("ID");
        TableColumn<Worker, String> fullNameCol = new TableColumn<>("FullName");
        TableColumn<Worker, String> numberContractWorkCol = new TableColumn<>("Contract Work");
        TableColumn<Worker, String> postCol = new TableColumn<>("Post");
        TableColumn<Worker, String> skillsCol = new TableColumn<>("Skills");
        TableColumn<Worker, String> salaryCol = new TableColumn<>("Salary");

        idWorkerCol.setCellValueFactory(new PropertyValueFactory<>("idWorker"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        numberContractWorkCol.setCellValueFactory(new PropertyValueFactory<>("numberContractWork"));
        postCol.setCellValueFactory(new PropertyValueFactory<>("post"));
        skillsCol.setCellValueFactory(new PropertyValueFactory<>("skills"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));

        table.getColumns().add(idWorkerCol);
        table.getColumns().add(fullNameCol);
        table.getColumns().add(numberContractWorkCol);
        table.getColumns().add(postCol);
        table.getColumns().add(skillsCol);
        table.getColumns().add(salaryCol);


        ObservableList<Worker> data = FXCollections.observableArrayList(getSQLWorker());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<Project> getSQLProject() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Project";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Project> listProject = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idProject = resultSet.getInt("IdProject");
            String name = resultSet.getString("Name");
            int numberContractDev = resultSet.getInt("NumberContractDev");
            int numberTechDoc = resultSet.getInt("NumberTechDoc");
            String deadline = resultSet.getString("Deadline");
            Project newProject = new Project(idProject, name, numberContractDev, numberTechDoc, deadline);
            listProject.add(newProject);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listProject;
    }

    public void showSQLProject() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<Project, String> idProjectCol = new TableColumn<>("ID");
        TableColumn<Project, String> nameCol = new TableColumn<>("Name");
        TableColumn<Project, String> numberContractDevCol = new TableColumn<>("Contract Dev");
        TableColumn<Project, String> numberTechDocCol = new TableColumn<>("Technical Doc");
        TableColumn<Project, String> deadlineCol = new TableColumn<>("Deadline");

        idProjectCol.setCellValueFactory(new PropertyValueFactory<>("idProject"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberContractDevCol.setCellValueFactory(new PropertyValueFactory<>("numberContractDev"));
        numberTechDocCol.setCellValueFactory(new PropertyValueFactory<>("numberTechDoc"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        table.getColumns().add(idProjectCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(numberContractDevCol);
        table.getColumns().add(numberTechDocCol);
        table.getColumns().add(deadlineCol);

        ObservableList<Project> data = FXCollections.observableArrayList(getSQLProject());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<ContractWork> getSQLContractWork() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM ContractWork";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<ContractWork> listContractWork = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int numberContractWork = resultSet.getInt("NumberContractWork");
            String dateConclusion = resultSet.getString("DateConclusion");
            String placeConclusion = resultSet.getString("PlaceConclusion");
            String placeWork = resultSet.getString("PlaceWork");
            int numberWorkBook = resultSet.getInt("NumberWorkBook");
            String linkDoc = resultSet.getString("LinkDoc");
            ContractWork newContractWork = new ContractWork(numberContractWork, dateConclusion, placeConclusion, placeWork, numberWorkBook, linkDoc);
            listContractWork.add(newContractWork);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listContractWork;
    }

    public void showSQLContractWork() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<ContractWork, String> numberContractWorkCol = new TableColumn<>("ID");
        TableColumn<ContractWork, String> dateConclusionCol = new TableColumn<>("Date Conclusion");
        TableColumn<ContractWork, String> placeConclusionDevCol = new TableColumn<>("Place Conclusion");
        TableColumn<ContractWork, String> placeWorkCol = new TableColumn<>("Place Work");
        TableColumn<ContractWork, String> numberWorkBookCol = new TableColumn<>("Workbook");
        TableColumn<ContractWork, String> linkDocCol = new TableColumn<>("Link Doc");

        numberContractWorkCol.setCellValueFactory(new PropertyValueFactory<>("numberContractWork"));
        dateConclusionCol.setCellValueFactory(new PropertyValueFactory<>("dateConclusion"));
        placeConclusionDevCol.setCellValueFactory(new PropertyValueFactory<>("placeConclusion"));
        placeWorkCol.setCellValueFactory(new PropertyValueFactory<>("placeWork"));
        numberWorkBookCol.setCellValueFactory(new PropertyValueFactory<>("numberWorkBook"));
        linkDocCol.setCellValueFactory(new PropertyValueFactory<>("linkDoc"));

        table.getColumns().add(numberContractWorkCol);
        table.getColumns().add(dateConclusionCol);
        table.getColumns().add(placeConclusionDevCol);
        table.getColumns().add(placeWorkCol);
        table.getColumns().add(numberWorkBookCol);
        table.getColumns().add(linkDocCol);

        ObservableList<ContractWork> data = FXCollections.observableArrayList(getSQLContractWork());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<Task> getSQLTask() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Task";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Task> listTask = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idTask = resultSet.getInt("IdTask");
            String name = resultSet.getString("Name");
            int idProject = resultSet.getInt("IdProject");
            String description = resultSet.getString("Description");
            String criterion = resultSet.getString("Сriterion");
            String leadTime = resultSet.getString("LeadTime");
            String opening = resultSet.getString("Opening");
            String deadline = resultSet.getString("Deadline");
            String label = resultSet.getString("Label");
            int idStatus = resultSet.getInt("IdStatus");
            Task newTask = new Task(idTask, name, idProject, description, criterion, leadTime, opening, deadline, label, idStatus);
            listTask.add(newTask);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listTask;
    }

    public void showSQLTask() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<Task, String> idTaskCol = new TableColumn<>("ID");
        TableColumn<Task, String> nameCol = new TableColumn<>("Name");
        TableColumn<Task, String> idProjectCol = new TableColumn<>("ID Project");
        TableColumn<Task, String> descriptionCol = new TableColumn<>("Description");
        TableColumn<Task, String> criterionCol = new TableColumn<>("Сriterion");
        TableColumn<Task, String> leadTimeCol = new TableColumn<>("Leadtime");
        TableColumn<Task, String> openingCol = new TableColumn<>("Opening");
        TableColumn<Task, String> deadlineCol = new TableColumn<>("Deadline");
        TableColumn<Task, String> labelCol = new TableColumn<>("Label");
        TableColumn<Task, String> idStatusCol = new TableColumn<>("ID Status");

        idTaskCol.setCellValueFactory(new PropertyValueFactory<>("idTask"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idProjectCol.setCellValueFactory(new PropertyValueFactory<>("idProject"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        criterionCol.setCellValueFactory(new PropertyValueFactory<>("criterion"));
        leadTimeCol.setCellValueFactory(new PropertyValueFactory<>("leadTime"));
        openingCol.setCellValueFactory(new PropertyValueFactory<>("opening"));
        deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        labelCol.setCellValueFactory(new PropertyValueFactory<>("label"));
        idStatusCol.setCellValueFactory(new PropertyValueFactory<>("idStatus"));

        table.getColumns().add(idTaskCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(idProjectCol);
        table.getColumns().add(descriptionCol);
        table.getColumns().add(criterionCol);
        table.getColumns().add(leadTimeCol);
        table.getColumns().add(openingCol);
        table.getColumns().add(deadlineCol);
        table.getColumns().add(labelCol);
        table.getColumns().add(idStatusCol);

        ObservableList<Task> data = FXCollections.observableArrayList(getSQLTask());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<Status> getSQLStatus() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM Status";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<Status> listStatus = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idStatus = resultSet.getInt("IdStatus");
            String name = resultSet.getString("Name");
            String description = resultSet.getString("Description");
            Status newStatus = new Status(idStatus, name, description);
            listStatus.add(newStatus);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listStatus;
    }

    public void showSQLStatus() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<Status, String> idStatusCol = new TableColumn<>("ID");
        TableColumn<Status, String> nameCol = new TableColumn<>("Name");
        TableColumn<Status, String> descriptionCol = new TableColumn<>("Description");

        idStatusCol.setCellValueFactory(new PropertyValueFactory<>("idStatus"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));

        table.getColumns().add(idStatusCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(descriptionCol);

        ObservableList<Status> data = FXCollections.observableArrayList(getSQLStatus());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<ExecutorProject> getSQLExecutorProject() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM ExecutorProject";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<ExecutorProject> listExecutorProject = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idExecutorProject = resultSet.getInt("IdExecutorProject");
            int idWorker = resultSet.getInt("IdWorker");
            int idProject = resultSet.getInt("IdProject");
            ExecutorProject newExecutorProject = new ExecutorProject(idExecutorProject, idWorker, idProject);
            listExecutorProject.add(newExecutorProject);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listExecutorProject;
    }

    public void showSQLExecutorProject() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<ExecutorProject, String> idExecutorProject = new TableColumn<>("ID");
        TableColumn<ExecutorProject, String> idWorker = new TableColumn<>("ID Worker");
        TableColumn<ExecutorProject, String> idProject = new TableColumn<>("ID Project");

        idExecutorProject.setCellValueFactory(new PropertyValueFactory<>("idExecutorProject"));
        idWorker.setCellValueFactory(new PropertyValueFactory<>("idWorker"));
        idProject.setCellValueFactory(new PropertyValueFactory<>("idProject"));

        table.getColumns().add(idExecutorProject);
        table.getColumns().add(idWorker);
        table.getColumns().add(idProject);

        ObservableList<ExecutorProject> data = FXCollections.observableArrayList(getSQLExecutorProject());

        table.setItems(data);
        table.refresh();
    }

    private ArrayList<ExecutorTask> getSQLExecutorTask() throws ClassNotFoundException,
            SQLException {

        // Получаем соединение к базе данных MySQL.
        Connection conn = MySQLConnUtils.getMySQLConnection();

        // Создаем объект Statement для выполнения запросов.
        Statement statement = conn.createStatement();

        // Выполняем запрос SELECT.
        String sql = "SELECT * FROM ExecutorTask";
        ResultSet resultSet = statement.executeQuery(sql);

        ArrayList<ExecutorTask> listExecutorTask = new ArrayList<>();

        // Обрабатываем результаты запроса.
        while (resultSet.next()) {
            int idExecutorTask = resultSet.getInt("IdExecutorTask");
            int idWorker = resultSet.getInt("IdWorker");
            int idTask = resultSet.getInt("IdTask");
            ExecutorTask newExecutorTask = new ExecutorTask(idExecutorTask, idWorker, idTask);
            listExecutorTask.add(newExecutorTask);
        }

        // Закрываем соединение к базе данных.
        conn.close();
        return listExecutorTask;
    }

    public void showSQLExecutorTask() throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        TableColumn<ExecutorProject, String> idExecutorTask = new TableColumn<>("ID");
        TableColumn<ExecutorProject, String> idWorker = new TableColumn<>("ID Worker");
        TableColumn<ExecutorProject, String> idTask = new TableColumn<>("ID Project");

        idExecutorTask.setCellValueFactory(new PropertyValueFactory<>("idExecutorTask"));
        idWorker.setCellValueFactory(new PropertyValueFactory<>("idWorker"));
        idTask.setCellValueFactory(new PropertyValueFactory<>("idTask"));

        table.getColumns().add(idExecutorTask);
        table.getColumns().add(idWorker);
        table.getColumns().add(idTask);

        ObservableList<ExecutorTask> data = FXCollections.observableArrayList(getSQLExecutorTask());

        table.setItems(data);
        table.refresh();
    }

    public void showSQLQuery(ArrayList<Map<String, Object>> listObject) throws SQLException, ClassNotFoundException {
        table.getColumns().clear(); // удаление всех столбцов из таблицы
        table.getItems().clear(); // удаление всех элементов из ObservableList

        if (!listObject.isEmpty()) {
            Map<String, Object> firstRow = listObject.get(0);

            for (String columnName : firstRow.keySet()) {
                TableColumn<Map<String, Object>, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(columnName).toString()));
                table.getColumns().add(column);
            }

            ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(listObject);

            table.setItems(data);
            table.refresh();
        }
    }

    public void handleAddElementWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add-element.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));

        stage.setTitle("Добавление записи");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleEditElementWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-element.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));

        stage.setTitle("Редактирование записи");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleDeleteElementWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("delete-element.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));

        stage.setTitle("Удаление записи");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleFindElementWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("find-element.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));

        stage.setTitle("Поиск записи");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleCloseButton(ActionEvent event) throws IOException {
        Platform.exit();
    }

    public void handleAboutWindow(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("lildesk.png")));
        stage.setTitle("О разработчике");
        stage.setResizable(false);
        stage.initModality(Modality.NONE);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }
}