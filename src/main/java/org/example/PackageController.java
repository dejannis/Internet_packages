package org.example;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class PackageController {
    @FXML
    private TextField firstName, lastname, address;

    @FXML
    private ToggleGroup speed, bandwidth, duration;

    @FXML
    private TableView<InternetPackage> internetPackageTableView;

    ObservableList<InternetPackage> internetPackages;
    InternetPackage internetPackage;

    public PackageController() {}



    @FXML
    public void initialize() {

        internetPackages = FXCollections.observableArrayList();

        internetPackage = new InternetPackage();

        firstName.textProperty().bindBidirectional(internetPackage.firstNameProperty());
        lastname.textProperty().bindBidirectional(internetPackage.lastNameProperty());
        address.textProperty().bindBidirectional(internetPackage.addressProperty());

        speed.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {

                if(newValue != null) {
                    ToggleButton button = (ToggleButton) newValue;

                    switch (button.getId()) {
                        case "s2":
                            internetPackage.speedProperty().set(Speed.TWO);
                            break;
                        case "s5":
                            internetPackage.speedProperty().set(Speed.FIVE);
                            break;
                        case "s10":
                            internetPackage.speedProperty().set(Speed.TEN);
                            break;
                        case "s20":
                            internetPackage.speedProperty().set(Speed.TWENTY);
                            break;
                        case "s50":
                            internetPackage.speedProperty().set(Speed.FIFTY);
                            break;
                        case "s100":
                            internetPackage.speedProperty().set(Speed.HUNDRED);
                            break;
                    }
                }
            }
        });

        bandwidth.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {

                if(newValue != null) {
                    ToggleButton button = (ToggleButton) newValue;

                    switch (button.getId()) {
                        case "b1":
                            internetPackage.bandwidthProperty().set(Bandwidth.ONE);
                            break;
                        case "b5":
                            internetPackage.bandwidthProperty().set(Bandwidth.FIVE);
                            break;
                        case "b10":
                            internetPackage.bandwidthProperty().set(Bandwidth.TEN);
                            break;
                        case "b100":
                            internetPackage.bandwidthProperty().set(Bandwidth.HUNDRED);
                            break;
                        case "flat":
                            internetPackage.bandwidthProperty().set(Bandwidth.FLAT);
                            break;
                    }
                }
            }
        });

        duration.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {

                if(newValue != null) {
                    ToggleButton button = (ToggleButton) newValue;

                    switch (button.getId()) {
                        case "one_year":
                            internetPackage.durationProperty().set(Duration.ONE_YEAR);
                            break;
                        case "two_years":
                            internetPackage.durationProperty().set(Duration.TWO_YEARS);
                            break;
                    }
                }
            }
        });
    }

    @FXML
    private void savePackage() {
        if(internetPackage.isValid()) {

            internetPackages = internetPackageTableView.getItems();

            ToggleButton speedButton = (ToggleButton) speed.getSelectedToggle();
            ToggleButton bandwidthButton = (ToggleButton) bandwidth.getSelectedToggle();
            ToggleButton durationButton = (ToggleButton) duration.getSelectedToggle();

            Speed selectedSpeed = null;
            switch (speedButton.getId()) {
                case "s2":
                    selectedSpeed = Speed.TWO;
                    break;
                case "s5":
                    selectedSpeed = Speed.FIVE;
                    break;
                case "s10":
                    selectedSpeed = Speed.TEN;
                    break;
                case "s20":
                    selectedSpeed = Speed.TWENTY;
                    break;
                case "s50":
                    selectedSpeed = Speed.FIFTY;
                    break;
                case "s100":
                    selectedSpeed = Speed.HUNDRED;
            }

            Bandwidth selectedBandwidth = null;
            switch (bandwidthButton.getId()) {
                case "b1":
                    selectedBandwidth = Bandwidth.ONE;
                    break;
                case "b5":
                    selectedBandwidth = Bandwidth.FIVE;
                    break;
                case "b10":
                    selectedBandwidth = Bandwidth.TEN;
                    break;
                case "b100":
                    selectedBandwidth = Bandwidth.HUNDRED;
                    break;
                case "flat":
                    selectedBandwidth = Bandwidth.FLAT;
                    break;
            }

            Duration selectedDuration = null;
            switch (durationButton.getId()) {
                case "one_year":
                    selectedDuration = Duration.ONE_YEAR;
                    break;
                case "two_years":
                    selectedDuration = Duration.TWO_YEARS;
                    break;
            }

            internetPackages.add(new InternetPackage(firstName.getText(), lastname.getText(), address.getText(), selectedSpeed, selectedBandwidth, selectedDuration));
            internetPackageTableView.setItems(internetPackages);

        } else {
            StringBuilder errMsg = new StringBuilder();
            ArrayList<String> errList = internetPackage.errorListProperty().get();
            for (int i = 0; i < errList.size(); i++) {
                errMsg.append(errList.get(i));
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Package can't be saved");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }
    }


    @FXML
    private void clearForm() {
        internetPackage.firstNameProperty().set("");
        internetPackage.lastNameProperty().set("");
        internetPackage.addressProperty().set("");
        internetPackage.speedProperty().set(null);
        internetPackage.bandwidthProperty().set(null);
        internetPackage.durationProperty().set(null);

        if(speed.getSelectedToggle() != null) {
            speed.getSelectedToggle().setSelected(false);
        }

        if(bandwidth.getSelectedToggle() != null) {
            bandwidth.getSelectedToggle().setSelected(false);
        }

        if(duration.getSelectedToggle() != null) {
            duration.getSelectedToggle().setSelected(false);
        }
    }

    @FXML
    private void deleteInternetPackage() {

        internetPackages = internetPackageTableView.getItems();
        int i = internetPackageTableView.selectionModelProperty().getValue().getSelectedIndex();
        internetPackages.remove(i);
        internetPackageTableView.setItems(internetPackages);
    }
}
