package be.intec.les111.models.binders;

import javafx.beans.property.*;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.text.*;

public class ChatBinder implements Serializable {

  private SimpleIntegerProperty id;
  private SimpleIntegerProperty fromUserId;
  private SimpleIntegerProperty toUserId;
  private SimpleStringProperty subject;
  private SimpleStringProperty content;


  public ChatBinder ( Integer _id, Integer _fromUserId, Integer _toUserId, String _subject, String _content) { 
    this.id = new SimpleIntegerProperty(_id);
    this.fromUserId = new SimpleIntegerProperty(_fromUserId);
    this.toUserId = new SimpleIntegerProperty(_toUserId);
    this.subject = new SimpleStringProperty(_subject);
    this.content = new SimpleStringProperty(_content);
  }

  public ChatBinder(){ }

  public Integer getId() {
    return this.id.get();
  }

  public void setId(Integer _id) {
    this.id.set(_id);
  }

  public void parseAndSetId(String _id) {
    this.setId(Integer.parseInt(_id));
  }

  public ChatBinder withId(Integer _id) {
    this.setId(_id);
    return this;
  }

  public ChatBinder withoutId(Integer _id) {
    this.setId(null);
    return this;
  }

  public ChatBinder withParsedId(String _id) {
    this.parseAndSetId(_id);
    return this;
  }


  public Integer getFromUserId() {
    return this.fromUserId.get();
  }

  public void setFromUserId(Integer _fromUserId) {
    this.fromUserId.set(_fromUserId);
  }

  public void parseAndSetFromUserId(String _fromUserId) {
    this.setFromUserId(Integer.parseInt(_fromUserId));
  }

  public ChatBinder withFromUserId(Integer _fromUserId) {
    this.setFromUserId(_fromUserId);
    return this;
  }

  public ChatBinder withoutFromUserId(Integer _fromUserId) {
    this.setFromUserId(null);
    return this;
  }

  public ChatBinder withParsedFromUserId(String _fromUserId) {
    this.parseAndSetFromUserId(_fromUserId);
    return this;
  }


  public Integer getToUserId() {
    return this.toUserId.get();
  }

  public void setToUserId(Integer _toUserId) {
    this.toUserId.set(_toUserId);
  }

  public void parseAndSetToUserId(String _toUserId) {
    this.setToUserId(Integer.parseInt(_toUserId));
  }

  public ChatBinder withToUserId(Integer _toUserId) {
    this.setToUserId(_toUserId);
    return this;
  }

  public ChatBinder withoutToUserId(Integer _toUserId) {
    this.setToUserId(null);
    return this;
  }

  public ChatBinder withParsedToUserId(String _toUserId) {
    this.parseAndSetToUserId(_toUserId);
    return this;
  }


  public String getSubject() {
    return this.subject.get();
  }

  public void setSubject(String _subject) {
    this.subject.set(_subject);
  }

  public void parseAndSetSubject(String _subject) {
    this.subject = new SimpleStringProperty(_subject);
  }

  public ChatBinder withSubject(String _subject) {
    this.setSubject(_subject);
    return this;
  }

  public ChatBinder withoutSubject(String _subject) {
    this.setSubject(null);
    return this;
  }

  public ChatBinder withParsedSubject(String _subject) {
    this.parseAndSetSubject(_subject);
    return this;
  }


  public String getContent() {
    return this.content.get();
  }

  public void setContent(String _content) {
    this.content.set(_content);
  }

  public void parseAndSetContent(String _content) {
    this.content = new SimpleStringProperty(_content);
  }

  public ChatBinder withContent(String _content) {
    this.setContent(_content);
    return this;
  }

  public ChatBinder withoutContent(String _content) {
    this.setContent(null);
    return this;
  }

  public ChatBinder withParsedContent(String _content) {
    this.parseAndSetContent(_content);
    return this;
  }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ChatBinder)) return false;
        ChatBinder otherChat = (ChatBinder) obj;
        return 
        this.getId().equals(otherChat.getId()) 
 &&         this.getFromUserId().equals(otherChat.getFromUserId()) 
 &&         this.getToUserId().equals(otherChat.getToUserId()) 
 &&         this.getSubject().equals(otherChat.getSubject()) 
 &&         this.getContent().equals(otherChat.getContent()) 
;    }

    @Override
    public int hashCode() {
        return Objects.hash( 
        this.getId()
,         this.getFromUserId()
,         this.getToUserId()
,         this.getSubject()
,         this.getContent()
 );    }

    @Override
    public String toString() {

        return "{ " + 
        this.getId() + ", " + 
        this.getFromUserId() + ", " + 
        this.getToUserId() + ", " + 
        this.getSubject() + ", " + 
        this.getContent() + ", " + 
    " } ";
    }

    public boolean isNew(){
        return this.getId() == null;
    }

    public boolean isEmpty(){
        return ( this.getId() == null && 
this.getFromUserId() == null && 
this.getToUserId() == null && 
this.getSubject() == null && 
this.getContent() == null );    }
}
