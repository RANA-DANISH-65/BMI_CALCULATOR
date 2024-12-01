package MODELS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.io.*;

public class UserManager {
    private BufferedReader reader;
    private BufferedWriter writer;
    private ObservableList<User> usersList;

    public UserManager() {
        usersList = FXCollections.observableArrayList();
        try {
            File userFile = new File("USERS.txt");
            if (!userFile.exists()) {
                userFile.createNewFile();
            }

            reader = new BufferedReader(new FileReader(userFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 5) {
                    User user = new User(data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]),data[5]);
                    usersList.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users from file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing reader: " + e.getMessage());
            }
        }
    }

    public String calculateBMI(User user) {
        double weight = user.getWeight();
        double height = user.getHeight();
        height = height / 100;
        double bmi = weight / (height * height);
        String category;

        if (bmi < 18.5) {
            category = "underweight";
            user.setCategory("UNDERWEIGHT");
        } else if (bmi >= 18.5 && bmi < 24.9) {
            category = "normal weight";
            user.setCategory("NORMAL");
        } else if (bmi >= 25 && bmi < 29.9) {
            category = "overweight";
            user.setCategory("OVERWEIGHT");
        } else {
            category = "obese";
            user.setCategory("OBESITY");
        }

        addUserToList(user);
        writeToFile();
        return String.format("Your BMI is %.2f. You are classified as %s.", bmi, category);
    }

    public void addUserToList(User user) {
        usersList.add(user);
    }

    public void writeToFile() {
        try {
            // Open the file in overwrite mode (false means do not append)
            writer = new BufferedWriter(new FileWriter("USERS.txt", false));  // This will overwrite the file

            for (User user : usersList) {
                String formattedData = String.format("%s|%s|%s|%.2f|%.2f|%s",
                        user.getID(),
                        user.getName(),
                        user.getDob(),
                        user.getWeight(),
                        user.getHeight(),
                        user.getCategory()
                );
                writer.write(formattedData);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing users to file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.out.println("Error closing writer: " + e.getMessage());
            }
        }
    }

    public void showResponse(String response) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BMI Result");
        alert.setHeaderText(null);
        alert.setContentText(response);
        alert.showAndWait();
    }

    public ObservableList<User> getUsersList() {
        return usersList;
    }
}
