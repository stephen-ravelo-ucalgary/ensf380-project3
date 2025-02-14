package edu.ucalgary.oop;

import java.util.regex.*;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    public MedicalRecord(
            Location location,
            String treatmentDetails,
            String dateOfTreatment) {
        this.location = location;
        this.treatmentDetails = treatmentDetails;
        if (isValidDateFormat(dateOfTreatment)) {
            this.dateOfTreatment = dateOfTreatment;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    public String getDateOfTreatment() {
        return dateOfTreatment;
    }

    public void setDateOfTreatment(String dateOfTreatment) {
        if (isValidDateFormat(dateOfTreatment)) {
            this.dateOfTreatment = dateOfTreatment;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidDateFormat(String date) {
        String regex = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
