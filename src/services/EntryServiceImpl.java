package services;

import data.models.Entry;
import data.repositories.EntryRepository;
import data.repositories.EntryRepositoryImplementation;

import java.util.List;

public class EntryServiceImpl implements  EntryService{
private EntryRepository entryRepository = new EntryRepositoryImplementation();
    @Override
    public void create(String title, String body, int diaryId) {
        Entry entry = new Entry();
        entry.setBody(body);
        entry.setTitle(title);
        entry.setDiaryId(diaryId);
        entryRepository.save(entry);
    }

    @Override
    public List<Entry> findAll() {
        return entryRepository.findAll();
    }
}
