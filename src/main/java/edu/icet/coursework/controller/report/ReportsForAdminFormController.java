package edu.icet.coursework.controller.report;

import edu.icet.coursework.bo.BoFactory;
import edu.icet.coursework.bo.custom.OrderBo;
import edu.icet.coursework.bo.custom.ProductBo;
import edu.icet.coursework.bo.custom.SupplierBo;
import edu.icet.coursework.bo.custom.UserBo;
import edu.icet.coursework.controller.user.UserSession;
import edu.icet.coursework.dto.Order;
import edu.icet.coursework.dto.Product;
import edu.icet.coursework.dto.Supplier;
import edu.icet.coursework.dto.User;
import edu.icet.coursework.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class ReportsForAdminFormController implements Initializable {
    public AnchorPane adminpane;
    public Label lblUserId;
    public Label lblUserType;
    public Label lblUserName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User currentUser = UserSession.getInstance().getCurrentSession();
        lblUserId.setText(currentUser.getUserId());
        lblUserType.setText(currentUser.getUserType());
        lblUserName.setText(currentUser.getFname());
    }


    ProductBo productBoImpl = BoFactory.getInstance().getBo(BoType.PRODUCT);
    SupplierBo supplierBoImpl = BoFactory.getInstance().getBo(BoType.SUPPLIER);
    OrderBo orderBoImpl = BoFactory.getInstance().getBo(BoType.ORDER);
    UserBo userBoImpl = BoFactory.getInstance().getBo(BoType.USER);


    public boolean exportInventoryReport(String reportFormat) {
        String path = "D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\reports";
        List<Product> products = productBoImpl.searchAllProducts();

        try {
            // Load JRXML file
            File file = new File("D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\src\\main\\resources\\view\\inventoryReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Create data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);

            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Created By", "Clothify Store");

            // Fill report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export report based on format
            if ("html".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\InventoryReport.html");
            } else if ("pdf".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\InventoryReport.pdf");
            } else {
                // Invalid format
                showAlert("Error", "Unsupported Report Format", Alert.AlertType.ERROR);
                return false;
            }

            // Success message
            showAlert("Success", "Inventory Report Generated Successfully", Alert.AlertType.INFORMATION);
            return true;

        } catch (JRException e) {
            // Exception occurred
            showAlert("Error", "Failed To Generate Inventory Report:\n" + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            return false;
        }
    }

    private boolean exportSupplierReport(String reportFormat) {
        String path = "D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\reports";
        List<Supplier> suppliers = supplierBoImpl.searchAllSuppliers();

        try {
            // Load JRXML file
            File file = new File("D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\src\\main\\resources\\view\\supplierReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Create data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(suppliers);

            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Created By", "Clothify Store");

            // Fill report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export report based on format
            if ("html".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\SupplierReport.html");
            } else if ("pdf".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\SupplierReport.pdf");
            } else {
                // Invalid format
                showAlert("Error", "Unsupported Report Format", Alert.AlertType.ERROR);
                return false;
            }

            // Success message
            showAlert("Success", "Supplier Report Generated Successfully", Alert.AlertType.INFORMATION);
            return true;

        } catch (JRException e) {
            // Exception occurred
            showAlert("Error", "Failed To Generate Supplier Report:\n" + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            return false;
        }
    }

    private boolean exportSalesReport(String reportFormat) {
        String path = "D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\reports";
        List<Order> orders = orderBoImpl.searchAllOrders();

        try {
            // Load JRXML file
            File file = new File("D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\src\\main\\resources\\view\\salesReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Create data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Created By", "Clothify Store");

            // Fill report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export report based on format
            if ("html".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\SalesReport.html");
            } else if ("pdf".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\SalesReport.pdf");
            } else {
                // Invalid format
                showAlert("Error", "Unsupported Report Format", Alert.AlertType.ERROR);
                return false;
            }

            // Success message
            showAlert("Success", "Sales Report Generated Successfully", Alert.AlertType.INFORMATION);
            return true;

        } catch (JRException e) {
            // Exception occurred
            showAlert("Error", "Failed To Generate Sales Report:\n" + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            return false;
        }
    }

    private boolean exportEmployeeReport(String reportFormat) {
        String path = "D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\reports";
        List<User> users = userBoImpl.searchUser();

        try {
            // Load JRXML file
            File file = new File("D:\\Documents\\Career\\My Projects\\Clothify Store POS JavaFX\\clothify-store-pos-javafx\\src\\main\\resources\\view\\employeeReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Create data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);

            // Set parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Created By", "Clothify Store");

            // Fill report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export report based on format
            if ("html".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\EmployeeReport.html");
            } else if ("pdf".equalsIgnoreCase(reportFormat)) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\EmployeeReport.pdf");
            } else {
                // Invalid format
                showAlert("Error", "Unsupported Report Format", Alert.AlertType.ERROR);
                return false;
            }

            // Success message
            showAlert("Success", "Employee Report Generated Successfully", Alert.AlertType.INFORMATION);
            return true;

        } catch (JRException e) {
            // Exception occurred
            showAlert("Error", "Failed To Generate Employee Report:\n" + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void btnInventoryReportOnAction(ActionEvent actionEvent) {
        try {
            if (!exportInventoryReport("pdf")) {
                showAlert("Error", "Failed To Generate Inventory Report", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierReportOnAction(ActionEvent actionEvent) {
        try {
            if (!exportSupplierReport("pdf")) {
                showAlert("Error", "Failed To Generate Supplier Report", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSalesReportOnAction(ActionEvent actionEvent) {
        try {
            if (!exportSalesReport("pdf")) {
                showAlert("Error", "Failed To Generate Sales Report", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnEmployeeReportOnAction(ActionEvent actionEvent) {
        try {
            if (!exportEmployeeReport("pdf")) {
                showAlert("Error", "Failed To Generate Employee Report", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        User currentUser = UserSession.getInstance().getCurrentSession();
        if (currentUser.getUserType().equals("Admin")){
            Stage stage=(Stage) adminpane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminDashboardForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
            ((Stage) adminpane.getScene().getWindow()).close();
        }
        Stage stage=(Stage) adminpane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employeeDashboardForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        ((Stage) adminpane.getScene().getWindow()).close();
    }

}
