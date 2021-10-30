package be.intec.les111.models.entities;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class ChatEntity implements Serializable, Comparable<ChatEntity> {

    private Integer id;
    private Integer fromUserId;
    private Integer toUserId;
    private String subject;
    private String content;

    public ChatEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void parseAndSetId(String id) {
        this.id = Integer.parseInt(id);
    }

    public ChatEntity withId(Integer id) {
        this.setId(id);
        return this;
    }

    public ChatEntity withParsedId(String id) {
        this.parseAndSetId(id);
        return this;
    }

    public ChatEntity withoutId(Integer id) {
        this.setId(null);
        return this;
    }

    public ChatEntity withoutParsedId(String id) {
        this.setId(null);
        return this;
    }


    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void parseAndSetFromUserId(String fromUserId) {
        this.fromUserId = Integer.parseInt(fromUserId);
    }

    public ChatEntity withFromUserId(Integer fromUserId) {
        this.setFromUserId(fromUserId);
        return this;
    }

    public ChatEntity withParsedFromUserId(String fromUserId) {
        this.parseAndSetFromUserId(fromUserId);
        return this;
    }

    public ChatEntity withoutFromUserId(Integer fromUserId) {
        this.setFromUserId(null);
        return this;
    }

    public ChatEntity withoutParsedFromUserId(String fromUserId) {
        this.setFromUserId(null);
        return this;
    }


    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public void parseAndSetToUserId(String toUserId) {
        this.toUserId = Integer.parseInt(toUserId);
    }

    public ChatEntity withToUserId(Integer toUserId) {
        this.setToUserId(toUserId);
        return this;
    }

    public ChatEntity withParsedToUserId(String toUserId) {
        this.parseAndSetToUserId(toUserId);
        return this;
    }

    public ChatEntity withoutToUserId(Integer toUserId) {
        this.setToUserId(null);
        return this;
    }

    public ChatEntity withoutParsedToUserId(String toUserId) {
        this.setToUserId(null);
        return this;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void parseAndSetSubject(String subject) {
        this.subject = subject;
    }

    public ChatEntity withSubject(String subject) {
        this.setSubject(subject);
        return this;
    }

    public ChatEntity withParsedSubject(String subject) {
        this.parseAndSetSubject(subject);
        return this;
    }

    public ChatEntity withoutSubject(String subject) {
        this.setSubject(null);
        return this;
    }

    public ChatEntity withoutParsedSubject(String subject) {
        this.setSubject(null);
        return this;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void parseAndSetContent(String content) {
        this.content = content;
    }

    public ChatEntity withContent(String content) {
        this.setContent(content);
        return this;
    }

    public ChatEntity withParsedContent(String content) {
        this.parseAndSetContent(content);
        return this;
    }

    public ChatEntity withoutContent(String content) {
        this.setContent(null);
        return this;
    }

    public ChatEntity withoutParsedContent(String content) {
        this.setContent(null);
        return this;
    }


    @Override
    public int compareTo(ChatEntity otherChat) {
        // define here default comparison criteria 
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ChatEntity)) return false;
        ChatEntity otherChat = (ChatEntity) obj;
        return
                this.getId().equals(otherChat.getId())
                        && this.getFromUserId().equals(otherChat.getFromUserId())
                        && this.getToUserId().equals(otherChat.getToUserId())
                        && this.getSubject().equals(otherChat.getSubject())
                        && this.getContent().equals(otherChat.getContent())
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId()
                , this.getFromUserId()
                , this.getToUserId()
                , this.getSubject()
                , this.getContent()
        );
    }

    @Override
    public String toString() {

        return "{ " +
                "id:" + this.getId() + ", " +
                "fromUserId:" + this.getFromUserId() + ", " +
                "toUserId:" + this.getToUserId() + ", " +
                "subject:" + this.getSubject() + ", " +
                "content:" + this.getContent() +
                " } ";
    }

    public boolean isNew() {
        return this.getId() == null;
    }

    public boolean isEmpty() {
        return (this.getId() == null &&
                this.getFromUserId() == null &&
                this.getToUserId() == null &&
                this.getSubject() == null &&
                this.getContent() == null);
    }

}
