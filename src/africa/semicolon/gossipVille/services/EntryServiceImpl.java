package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Entry;
import africa.semicolon.gossipVille.data.repositories.EntryRepository;
import africa.semicolon.gossipVille.data.repositories.EntryRepositoryImplementation;

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
