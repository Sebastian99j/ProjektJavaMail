package pl.Java.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.Java.Controllers.MainController;
import pl.Java.App.MailJava;

public class MailController {
    @FXML
    private TextArea text;
    @FXML
    private TextField title;
    @FXML
    private TextField recipientMess;

    @FXML
    public void sent() throws Exception {
        String email = MainController.getEmail(734658244);
        String password = MainController.getHaslo(734658244);
        String titleMessage = title.getText();
        String textMessage = text.getText();
        String recipient = recipientMess.getText();
        MailJava.sendMail(recipient, email, password, titleMessage, textMessage);
    }
}