package be.intec.les110;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {


    @DisplayName("Testing welcome message success scenario.")
    @Test
    void welcomeSuccess() {
        String expected = "Welcome to JUnit5 Testing app.";
        String actual = MessageService.welcome();

        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Testing welcome message with all lower cased failure scenario")
    @Test
    void welcomeFailureWithLowerCaseLetters() {
        String expected = "welcome to junit5 testing app.";
        String actual = MessageService.welcome();

        Assertions.assertNotEquals(expected, actual);


    }


    @DisplayName("Testing goodbye message success scenario.")
    @Test
    void goodbyeSuccess() {
        String expected = "Thank you for choosing our app.";

        String actual = MessageService.goodbye();
        Assertions.assertEquals(expected, actual);
    }


    @DisplayName("Testing goodbye message with all lower cased failure scenario")
    @Test
    void goodbyeFailureWithLowerCaseLetters() {
        String expected = "thank you for choosing our app.";
        String actual = MessageService.goodbye();

        Assertions.assertNotEquals(expected, actual);

    }

    @DisplayName("Testing send method success scenario")
    @Test
    void sendSuccess() {
        String expected = "Message from " + "yilmaz@mail.be" + " to " + "justin@rich.com" + ": " + "Hello Richie Rich"
                + "\n" + "Sing no more!";
        // System.out.println("send method is test with the following example: " + "\n"
        // + expected);
        String actual = MessageService.send("yilmaz@mail.be", "justin@rich.com", "Hello Richie Rich", "Sing no more!");
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Testing send method with incorrect input failure scenario")
    @Test
    void sendFailWhenInputIsIncorrect(){

        String expected= "Message from" + "yilmaz@mail.be" + " to " + "justin@rich.be" + " : " +
                "Hello Richie rich"+ "\n" + "Sing no more";


        String actual1=MessageService.send(
                "abc@...","justin@rich.be",
                "Hello Richie rich","Sing no more");


        String actual2=MessageService.send(
                "abc@...","123/5",
                "Hello Richie rich","Sing no more");

        String actual3=MessageService.send(
                "abc@...","justin@rich.be",
                "Konivica!","Sing no more");

        String actual4=MessageService.send(
                "abc@...","justin@rich.be",
                "Hello Richie rich","Please sing everyone fancies you!");


        Assertions.assertNotEquals(expected,actual1);
        Assertions.assertNotEquals(expected,actual2);
        Assertions.assertNotEquals(expected,actual3);
        Assertions.assertNotEquals(expected,actual4);


    }

    @DisplayName("Testing sendToEveryone method success scenario")
    @Test
    void sendToEveryoneSuccess(){

        Integer expected=5;

        MessageService.clear();

        //logical issue|mISTAKES

        MessageService.add("yilmaz@mail.be");
        MessageService.add("justin@mail.be");
        MessageService.add("nikola@mail.be");
        MessageService.add("robert@mail.be");
        MessageService.add("marie@mail.be");

        Integer actual=MessageService.sendToEveryone("yilmaz@mail.be","Hello to everyone",
                "Please do not follow Justin on Instagram");
        Assertions.assertEquals(expected,actual);


    }


}