package africa.semicolon.gossipVille.services;

import africa.semicolon.gossipVille.data.models.Entry;
import africa.semicolon.gossipVille.data.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class EntryServiceImpl implements  EntryService{
    @Autowired
private EntryRepository entryRepository;
    @Override
    public void create(String title, String body, String diaryId) {
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
