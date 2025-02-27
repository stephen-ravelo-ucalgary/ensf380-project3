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
        setLocation(location);
        setTreatmentDetails(treatmentDetails);
        setDateOfTreatment(dateOfTreatment);
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

    public void setDateOfTreatment(String dateOfTreatment) throws IllegalArgumentException{
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
        return matcher.matches();
    }
}
