package com.mycompany.praktika_12;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class Doctor implements Serializable {
    String name;
    int age;
    String specialization;
    String hospital;
    String contactNumber;

    public Doctor(String name, int age, String specialization, String hospital, String contactNumber) {
        this.name = name;
        this.age = age;
        this.specialization = specialization;
        this.hospital = hospital;
        this.contactNumber = contactNumber;
    }
}

public class DoctorSerialization {
    public static void main(String[] args) {
        Doctor doctor = createDoctorObject();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Serialized Objects (*.ser)", "ser"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getPath().toLowerCase().endsWith(".ser")) {
                fileToSave = new File(fileToSave.getPath() + ".ser");
            }

            try {
                try (FileOutputStream fileOut = new FileOutputStream(fileToSave); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeObject(doctor);
                }
                System.out.println("Doctor object has been serialized and saved to: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
            }
        }
    }

    public static Doctor createDoctorObject() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter Doctor's name:");
            String name = reader.readLine();
            System.out.println("Enter Doctor's age:");
            int age = Integer.parseInt(reader.readLine());
            System.out.println("Enter Doctor's specialization:");
            String specialization = reader.readLine();
            System.out.println("Enter Doctor's hospital:");
            String hospital = reader.readLine();
            System.out.println("Enter Doctor's contact number:");
            String contactNumber = reader.readLine();

            return new Doctor(name, age, specialization, hospital, contactNumber);
        } catch (IOException e) {
        }
        return null;
    }
}