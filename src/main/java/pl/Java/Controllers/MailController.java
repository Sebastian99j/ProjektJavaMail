package pl.Java.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.Java.App.MailJava;

public class MailController {
    @FXML
    private TextArea text;
    @FXML
    private TextField title;
    @FXML
    private TextField recipientMess;

    int success = 0;

    @FXML
    public void sent() throws Exception {
        String email = MainController.getEmail(734658244);
        String password = MainController.getHaslo(734658244);
        String titleMessage = title.getText();
        String textMessage = text.getText();
        String recipient = recipientMess.getText();
        if (textMessage.equals("") || titleMessage.equals("") || recipient.equals("")){
            System.out.println("Wszystkie pola powinny być wypełnione");
        }
        else {
            success = MailJava.sendMail(recipient, email, password, titleMessage, textMessage);
            if (success == 1){
                title.clear();
                text.clear();
            }
        }
    }
}