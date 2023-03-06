package com.dungnguyen.readmicrosoftexcel;

public enum Degree {
    BACHELOR,
    ENGINEER,
    MASTER,
    DOCTOR;

    public static Degree getDegreeFromString(String value){
        for (Degree degree: Degree.values()){
            if (degree.toString().equals(value)){
                return degree;
            }
        }
        throw new RuntimeException("Value is not valid");
    }
}
