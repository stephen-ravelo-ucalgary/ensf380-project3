package edu.ucalgary.oop;
import java.util.regex.*;

public class DisasterVictim {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private final int ASSIGNED_SOCIAL_ID;
    private FamilyRelation[] familyConnections;
    private MedicalRecord[] medicalRecords;
    private Supply[] personalBelongings;
    private final String ENTRY_DATE;
    private String gender;
    private String comments;
    private static int counter = 0;

    public DisasterVictim(String firstName, String ENTRY_DATE){
        this.firstName = firstName;
        if (isValidDateFormat(ENTRY_DATE)){
            this.ENTRY_DATE = ENTRY_DATE;
        } else{
            throw new IllegalArgumentException("Invalid date format.");
        }        
        this.familyConnections = new FamilyRelation[0];
        this.medicalRecords = new MedicalRecord[0];
        this.personalBelongings = new Supply[0];
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    public DisasterVictim(String firstName, String ENTRY_DATE, String dateOfBirth){
        this.firstName = firstName;
        if (!isValidDateFormat(dateOfBirth) || !isValidDateFormat(ENTRY_DATE)){
            throw new IllegalArgumentException("Invalid date format.");
        } 
        this.ENTRY_DATE = ENTRY_DATE;
        this.dateOfBirth = dateOfBirth;

        if(convertDateStringToInt(dateOfBirth) > convertDateStringToInt(ENTRY_DATE)){
            throw new IllegalArgumentException("Birthdate cannot be after entry date.");
        }
        this.familyConnections = new FamilyRelation[0];
        this.medicalRecords = new MedicalRecord[0];
        this.personalBelongings = new Supply[0];
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        if (isValidDateFormat(dateOfBirth)){
            this.dateOfBirth = dateOfBirth;
        } else{
            throw new IllegalArgumentException("Invalid date format.");
        }
    }

    public int getAssignedSocialID(){
        return ASSIGNED_SOCIAL_ID;
    }

    public FamilyRelation[] getFamilyConnections(){
        return familyConnections;
    }

    public MedicalRecord[] getMedicalRecords(){
        return medicalRecords;
    }

    public Supply[] getPersonalBelongings(){
        return personalBelongings;
    }

    public void setFamilyConnections(FamilyRelation[] connections){
        this.familyConnections = connections;
    }

    public void setMedicalRecords(MedicalRecord[] records){
        this.medicalRecords = records;
    }

    public void setPersonalBelongings(Supply[] belongings){
        this.personalBelongings = belongings;
    }

    public void addPersonalBelonging(Supply supply){
        Supply[] newBelongings = new Supply[personalBelongings.length + 1];
        newBelongings[personalBelongings.length] = supply;
        personalBelongings = newBelongings; 
    }

    public void removePersonalBelonging(Supply unwantedSupply){
        if (personalBelongings.length == 0){
            return;
        }

        int numRemove = 0;;
        for (int i = 0; i < personalBelongings.length; i++){
            if (personalBelongings[i] == unwantedSupply){
                numRemove++;
            }
        }

        if (numRemove == 0){
            return;
        }

        Supply[] newBelongings = new Supply[personalBelongings.length - 1];
        int newIndex = 0;
        for (int i = 0; i < personalBelongings.length; i++){
            if (personalBelongings[i] != unwantedSupply){
                newBelongings[newIndex] = personalBelongings[i];
                newIndex++;
            }
        }

        personalBelongings = newBelongings;
    }

    public void removeFamilyConnection(FamilyRelation exRelation){
        if (familyConnections.length == 0){
            return;
        }

        int numRemove = 0;;
        for (int i = 0; i < familyConnections.length; i++){
            if (familyConnections[i] == exRelation){
                numRemove++;
            }
        }

        if (numRemove == 0){
            return;
        }

        FamilyRelation[] newConnections = new FamilyRelation[familyConnections.length - 1];
        int newIndex = 0;
        for (int i = 0; i < familyConnections.length; i++){
            if (familyConnections[i] != exRelation){
                newConnections[newIndex] = familyConnections[i];
                newIndex++;
            }
        }

        familyConnections = newConnections;
    }

    public void addFamilyConnection(FamilyRelation record){
        FamilyRelation[] newConnections = new FamilyRelation[familyConnections.length + 1];
        newConnections[familyConnections.length] = record;
        familyConnections = newConnections; 
    }

    public void addMedicalRecord(MedicalRecord record){
        MedicalRecord[] newRecords = new MedicalRecord[medicalRecords.length + 1];
        medicalRecords[medicalRecords.length] = record;
        medicalRecords = newRecords; 
    }

    public String getEntryDate(){
        return ENTRY_DATE;
    }

    public String getComments(){
        return comments;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")){
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Invalid gender.");
        }
    }

    public static int generateSocialID(){
        return counter++;
    }

    public static boolean isValidDateFormat(String date){
        String regex = "^([0-9]{4})-([0-9]{2})-([0-9]{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static int convertDateStringToInt(String dateStr){
        if (dateStr == null || dateStr.charAt(4) != '-' || dateStr.charAt(7) != '-' || dateStr.length() != 10){
            return -1;
        }
        String year = dateStr.substring(0,4);
        String month = dateStr.substring(5,7);
        String day = dateStr.substring(8,10);
        return Integer.valueOf(year) * 10000 + Integer.valueOf(month) * 100 + Integer.valueOf(day);
    }
}
