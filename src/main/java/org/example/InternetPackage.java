package org.example;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class InternetPackage {

    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty address = new SimpleStringProperty(this, "address", "");
    private final ObjectProperty<Speed> speed = new SimpleObjectProperty<>(this, "speed", Speed.TWO );
    private final ObjectProperty<Bandwidth> bandwidth = new SimpleObjectProperty<>(this, "bandwidth", Bandwidth.ONE);
    private final ObjectProperty<Duration> duration = new SimpleObjectProperty<>(this, "duration", Duration.ONE_YEAR);

    public InternetPackage() {}

    public InternetPackage(String firstName, String lastName, String address, Speed speed, Bandwidth bandwidth, Duration duration) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.address.set(address);
        this.speed.set(speed);
        this.bandwidth.set(bandwidth);
        this.duration.set(duration);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public Speed getSpeed() {
        return speed.get();
    }

    public ObjectProperty<Speed> speedProperty() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed.set(speed);
    }

    public Bandwidth getBandwidth() {
        return bandwidth.get();
    }

    public ObjectProperty<Bandwidth> bandwidthProperty() {
        return bandwidth;
    }

    public void setBandwidth(Bandwidth bandwidth) {
        this.bandwidth.set(bandwidth);
    }

    public Duration getDuration() {
        return duration.get();
    }

    public ObjectProperty<Duration> durationProperty() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration.set(duration);
    }


    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());
    public ObjectProperty<ArrayList<String>> errorListProperty() {
        return errorList;
    }

    public boolean isValid() {

        boolean isValid = true;

        if(firstName.get() != null && firstName.get().equals("")) {
            errorList.getValue().add("First name can't be empty!");
            isValid = false;
        }
        if(lastName.get().equals("")) {
            errorList.getValue().add("Last name can't be empty!");
            isValid = false;
        }
        if(address.get().equals("")) {
            errorList.getValue().add("Address name can't be empty!");
            isValid = false;
        }
        return isValid;
    }

}

