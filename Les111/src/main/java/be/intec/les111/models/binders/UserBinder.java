package be.intec.les111.models.binders;

import javafx.beans.property.*;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.text.*;

public class UserBinder implements Serializable {

  private SimpleIntegerProperty id;
  private SimpleStringProperty email;
  private SimpleStringProperty passcode;


  public UserBinder ( Integer _id, String _email, String _passcode) { 
    this.id = new SimpleIntegerProperty(_id);
    this.email = new SimpleStringProperty(_email);
    this.passcode = new SimpleStringProperty(_passcode);
  }

  public UserBinder(){ }

  public Integer getId() {
    return this.id.get();
  }

  public void setId(Integer _id) {
    this.id.set(_id);
  }

  public void parseAndSetId(String _id) {
    this.setId(Integer.parseInt(_id));
  }

  public UserBinder withId(Integer _id) {
    this.setId(_id);
    return this;
  }

  public UserBinder withoutId(Integer _id) {
    this.setId(null);
    return this;
  }

  public UserBinder withParsedId(String _id) {
    this.parseAndSetId(_id);
    return this;
  }


  public String getEmail() {
    return this.email.get();
  }

  public void setEmail(String _email) {
    this.email.set(_email);
  }

  public void parseAndSetEmail(String _email) {
    this.email = new SimpleStringProperty(_email);
  }

  public UserBinder withEmail(String _email) {
    this.setEmail(_email);
    return this;
  }

  public UserBinder withoutEmail(String _email) {
    this.setEmail(null);
    return this;
  }

  public UserBinder withParsedEmail(String _email) {
    this.parseAndSetEmail(_email);
    return this;
  }


  public String getPasscode() {
    return this.passcode.get();
  }

  public void setPasscode(String _passcode) {
    this.passcode.set(_passcode);
  }

  public void parseAndSetPasscode(String _passcode) {
    this.passcode = new SimpleStringProperty(_passcode);
  }

  public UserBinder withPasscode(String _passcode) {
    this.setPasscode(_passcode);
    return this;
  }

  public UserBinder withoutPasscode(String _passcode) {
    this.setPasscode(null);
    return this;
  }

  public UserBinder withParsedPasscode(String _passcode) {
    this.parseAndSetPasscode(_passcode);
    return this;
  }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserBinder)) return false;
        UserBinder otherUser = (UserBinder) obj;
        return 
        this.getId().equals(otherUser.getId()) 
 &&         this.getEmail().equals(otherUser.getEmail()) 
 &&         this.getPasscode().equals(otherUser.getPasscode()) 
;    }

    @Override
    public int hashCode() {
        return Objects.hash( 
        this.getId()
,         this.getEmail()
,         this.getPasscode()
 );    }

    @Override
    public String toString() {

        return "{ " + 
        this.getId() + ", " + 
        this.getEmail() + ", " + 
        this.getPasscode() + ", " + 
    " } ";
    }

    public boolean isNew(){
        return this.getId() == null;
    }

    public boolean isEmpty(){
        return ( this.getId() == null && 
this.getEmail() == null && 
this.getPasscode() == null );    }
}
