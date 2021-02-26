package model;

public class NoteType {
int id;
String name_note;
String description;

    public NoteType(int id, String name_note, String description) {
        this.id = id;
        this.name_note = name_note;
        this.description = description;
    }



    public NoteType(int id, String name_note) {
        this.id = id;
        this.name_note = name_note;
    }
    public NoteType(String name_note, String description) {
        this.name_note = name_note;
        this.description = description;
    }

    public NoteType(int id) {
        this.id = id;
    }

    public NoteType(String name_note) {
        this.name_note = name_note;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_note() {
        return name_note;
    }

    public void setName_note(String name_note) {
        this.name_note = name_note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
