package utils;

import business.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutput {
    public void writeUser(User user) throws IOException {
        try(FileWriter fileWrite = new FileWriter("src/main/java/data/UserData", true);
            BufferedWriter bufferedWrite = new BufferedWriter(fileWrite)){

            bufferedWrite.newLine();
            bufferedWrite.write("Username: (" + user.getUsername() + ")");
            bufferedWrite.newLine();
            bufferedWrite.write("Password: (" + user.getPassword() + ")");
        }
    }
}
