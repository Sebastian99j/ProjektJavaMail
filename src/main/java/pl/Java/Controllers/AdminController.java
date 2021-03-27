package pl.Java.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import pl.Java.App.MailDAO;
import pl.Java.App.Users;
import javafx.scene.control.TextField;

import java.util.List;

public class AdminController {

    MailDAO mailDAO = new MailDAO();
    @FXML
    private TextField newMail;
    @FXML
    private TextField newPassword;
    @FXML
    private ListView<String> lists;

    private List<String> listOfUsers;

    @FXML
    public void initialize(){
        listOfUsers = mailDAO.findMails();
        for (String email : listOfUsers) {
            lists.getItems().add(email);
        }
    }

    @FXML
    private void addUser(){
        String newUserMail = newMail.getText();
        String newUserPassword = newPassword.getText();

        if (newUserMail.equals("") || newUserPassword.equals("")){
            System.out.println("Należy wypełnić oba pola!");
        }
        else {
            try {
                if (wellFormat(newUserMail)){
                    int id = mailDAO.getIdUsers();
                    Users user = new Users(id, newUserMail, newUserPassword);
                    mailDAO.addUser(user);
                    newMail.clear();
                    newPassword.clear();
                    System.out.println("Dodano użytkownika!");
                }
            }
            catch (Exception e){
                    System.out.println("Błędny format maila. Wymagane zakończenie \"@gmail.com\"");
            }
        }
    }

    @FXML
    private void refresh() {
        lists.getItems().removeAll(listOfUsers);
        listOfUsers = mailDAO.findMails();
        lists.getItems().addAll(listOfUsers);
        }

    @FXML
    private void deleteUser(){
        ObservableList<String> deleting = lists.getSelectionModel().getSelectedItems();
        mailDAO.deleteUser(deleting.get(0));
        System.out.println("Usunięto!");
    }

    private static boolean wellFormat(String mail){
        final String acceptFormat = "moc.liamg@";
        String currentMailFormat = "";
        for (int i=mail.length()-1; i>=(mail.length())-10; i--){
            currentMailFormat+=mail.charAt(i);
        }
        if (currentMailFormat.equals(acceptFormat)){
            return true;
        }
        else {
            return false;
        }
    }
}