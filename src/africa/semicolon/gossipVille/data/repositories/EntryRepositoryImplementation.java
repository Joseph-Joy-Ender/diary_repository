package africa.semicolon.gossipVille.data.repositories;

import africa.semicolon.gossipVille.data.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImplementation implements EntryRepository{
    private int count;
    private List<Entry> entries = new ArrayList<>();

    @Override
    public Entry save(Entry entry) {
        if (isNew(entry)) createNew(entry);
        else update(entry);
        return entry;
    }
    private void createNew(Entry entry) {
        count++;
        entry.setId(generateNewId());
        entries.add(entry);
    }

    private boolean isNew(Entry entry) {
        return entry.getId() == 0;
    }

    private void update(Entry updatedEntry) {
        Entry oldEntry = findById(updatedEntry.getId());
        entries.remove(oldEntry);
        entries.add(updatedEntry);
    }

    private int generateNewId() {
        return count;
    }

    @Override
    public List<Entry> findAll() {
        return entries;
    }

    @Override
    public Entry findById(int id) {
        for (Entry entry: entries) {
            if (entry.getId() == id) return entry;

        }
        return null;
    }

    @Override
    public Entry findByEntryIdAndDiaryId(int entryId, int diaryId) {
        return null;
    }

    @Override
    public void delete(int id) {
        Entry entry = findById(id);
        entries.remove(entry);
        count--;

    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
        count--;

    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void clear() {


    }
}
