package model;

import java.sql.Date;
import java.util.List;

public class Note {
    int id;
    String title;
    String content;
    Date datetime;
    NoteType noteType;
    int type_id;

    public  Note(){

    }


    public Note(int id, String title, String content, Date datetime, NoteType noteType, int type_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.noteType = noteType;
        this.type_id = type_id;
    }

    public Note(int id, String title, String content, Date datetime, NoteType noteType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.noteType = noteType;
    }

    public Note(int id, String title, String content, NoteType noteType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.noteType = noteType;
    }

    public Note(String title, String content, Date datetime, int type_id) {
        this.title = title;
        this.content = content;
        this.datetime = datetime;
        this.type_id = type_id;
    }

    public Note(int id, String title, String content, Date datetime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.datetime = datetime;
    }

    public Note(String title, String content, Date datetime) {
        this.title = title;
        this.content = content;
        this.datetime = datetime;
    }

    public Note(String title, String content, NoteType noteType) {
        this.title = title;
        this.content = content;
        this.noteType = noteType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "typeId=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
