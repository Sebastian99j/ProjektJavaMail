package pl.Java.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.Java.App.MailDAO;
import pl.Java.App.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainController{

    @FXML
    private TextField dane1;
    @FXML
    private PasswordField dane2;

    private static String email;
    private static String haslo;
    private List<String> users;
    private String passwordMail;
    MailDAO mailDAO = new MailDAO();

    public static String getEmail(int kod) {
        if (kod == 734658244){
            return email;
        }
        else {
            return null;
        }
    }

    public static String getHaslo(int kod) {
        if (kod == 734658244){
            return haslo;
        }
        else {
            return null;
        }
    }

    void database(){
        users = mailDAO.findMails();
    }

    @FXML
    public void initialize(){
        database();
    }

    @FXML
    public void zatwierdz() throws IOException {
        int access1 = 0 , access2 = 0;
        email = dane1.getText();
        haslo = dane2.getText();

        if (email.equals("") || haslo.equals("")){
            System.out.println("Należy wypełnić oba pola");
        }
        else {
            for (String mail : users) {
                if(mail.equals(email)){
                    access1 = 1;
                    passwordMail = mailDAO.passToEmail(email);
                }
                else {
                    System.out.println("Podanego emailu nie ma w bazie danych!");
                }
            }

            if (passwordMail!=null){
                if(passwordMail.equals(haslo)){
                    access2 = 1;
                }
                else {
                    System.out.println("Podanego hasla nie ma w bazie danych!");
                }
            }

            if (access1 == 1 && access2 == 1){
                System.out.println("Uzyskano dostęp!");
                Main.stageMain.close();
                mailDAO.close();
                FXMLLoader loader = new FXMLLoader(new File("/mail.fxml").toURI().toURL());
                loader.setLocation(this.getClass().getResource("/mail.fxml"));
                AnchorPane anchorPane = loader.load();
                Stage stage = new Stage();

                Scene scene = new Scene(anchorPane);
                stage.setTitle("JavaMail");
                stage.setScene(scene);
                stage.show();
            }
            else {
                System.out.println("Nie uzyskano dostępu!");
            }
        }
    }
}