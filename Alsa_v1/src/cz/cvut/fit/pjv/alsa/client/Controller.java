package cz.cvut.fit.pjv.alsa.client;

import cz.cvut.fit.pjv.alsa.common.entity.Product;
import cz.cvut.fit.pjv.alsa.common.entity.Television;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static cz.cvut.fit.pjv.alsa.common.SampleData.*;

public class Controller implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> idCol;

    @FXML
    private TableColumn<Product, String> nameCol;

    @FXML
    private TableColumn<Product, String> priceCol;

    @FXML
    private TableColumn<Product, String> countCol;

    private Model model;

    private int nextId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = new Model();
        model.initiliaze();
        model.createProduct(lenovoE500);
        model.createProduct(hpBusinnesPlus);
        model.createProduct(samsungMediaPlus);

        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                priceField.setText(oldValue);
            }
        });

        idCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().id()));
        nameCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().name()));
        priceCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().price()));
        countCol.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().count()));

        reloadTableView();
    }

    public void onAdd() {
        if (nameField.getText().equals("") || priceField.getText().equals("")) {
            return;
        }

        model.createProduct(new Television(nextId, nameField.getText(), Double.parseDouble(priceField.getText()), 0));
        nameField.clear();
        priceField.clear();
        reloadTableView();
    }

    public void onSell() {
        model.sellProduct(tableView.getSelectionModel().getSelectedItem().id());
        reloadTableView();
    }

    public void onReturn() {
        model.returnProduct(tableView.getSelectionModel().getSelectedItem().id());
        reloadTableView();
    }

    private void reloadTableView() {
        List<Product> products = model.listProducts();
        nextId = products.stream().map(Product::id).max(Integer::compareTo).orElse(0) + 1;
        tableView.getItems().setAll(products);
    }

}
