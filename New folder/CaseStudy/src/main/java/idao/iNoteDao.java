package idao;

import model.Note;

public interface iNoteDao {
    public void searchNote(String keywords);

    public  void addNote(Note note);

    public  void removeNoteById(Note typeId);

}
