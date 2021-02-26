package idao;

import model.Note;

public interface iNoteTupeDAO {
    public void searchNote(String keywords);

    public  void addNote(Note note);

    public  void removeNoteById(Note typeId);

}

