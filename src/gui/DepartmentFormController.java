package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entidades.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private Department entidade;
	private DepartmentService service;

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Label labelErrorName;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;

	public void setDepartment(Department entidade) {
		this.entidade = entidade;
	}

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	@FXML
	private void onBtSaveAction(ActionEvent event) {
		if (entidade == null) {
			throw new IllegalStateException("Entidade null");
		}
		if (service == null) {
			throw new IllegalStateException("Service null");
		}
		try {
			entidade = getFormData();
			service.saveOrUpdate(entidade);
			Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Erro ao salvar", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		obj.setId(Utils.tryParserToInt(txtId.getText()));
		obj.setName(txtName.getText());
		return obj;
	}

	@FXML
	private void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializableNodes();

	}

	private void initializableNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}

	public void updateFormData() {
		if (entidade == null) {
			throw new IllegalStateException("Entidade null");
		}
		txtId.setText(String.valueOf(entidade.getId()));
		txtName.setText(entidade.getName());
	}
}
