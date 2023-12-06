package africa.semicolon.gossipVille.data.repositories;

import africa.semicolon.gossipVille.data.models.Entry;

import java.util.List;

public interface EntryRepository {
    Entry save(Entry entry);
    List<Entry> findAll();
    Entry findById(int id);
    Entry findByEntryIdAndDiaryId(int entryId, int diaryId);
    void delete(int id);
    void delete(Entry entry);
    long count();
    void clear();
}
