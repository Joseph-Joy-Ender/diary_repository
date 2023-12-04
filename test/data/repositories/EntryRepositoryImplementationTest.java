package data.repositories;


import data.models.Diary;
import data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryImplementationTest {

    private EntryRepository entryRepository;
    @BeforeEach
    public void startAllTestsWith() {
        entryRepository = new EntryRepositoryImplementation();

    }
    @Test
    public void saveOneEntry_countIsOneTest() {
        Entry entry = new Entry();
        entryRepository.save(entry);
        assertEquals(1, entryRepository.count());

    }

    @Test
    public void saveTwoEntry_countIsTwoTest() {
        Entry entry = new Entry();
        Entry secondEntry = new Entry();
        entryRepository.save(entry);
        entryRepository.save(secondEntry);
        assertEquals(2, entryRepository.count());

    }

    @Test
    public void saveTwoEntry_findAllCountReturnsTwoTest() {
        Entry entry = new Entry();
        Entry secondEntry = new Entry();
        entryRepository.save(entry);
        entryRepository.save(secondEntry);
        assertEquals(2, entryRepository.findAll().size());

    }

    @Test
    public void saveNewEntry_idIsOneTest() {
        Entry entry = new Entry();
        assertEquals(0, entry.getId());
        entryRepository.save(entry);

        assertEquals(1, entry.getId());
    }
    @Test
    public void saveUpdatedEntry_doesNotInfluenceCountTest() {
        Entry entry = new Entry();
        entryRepository.save(entry);

        Entry updatedEntry = new Entry();
        updatedEntry.setId(1);
        entryRepository.save(updatedEntry);

        assertEquals(1, entryRepository.count());
        assertEquals(updatedEntry, entryRepository.findById(1));
    }

    @Test
    public void saveThreeEntry_deleteByEntry(){
        Entry entry = new Entry();
        Entry secondEntry = new Entry();
        Entry thirdEntry = new Entry();
        entryRepository.save(entry);
        entryRepository.save(secondEntry);
        entryRepository.save(thirdEntry);
        assertEquals(3, entryRepository.count());
        entryRepository.delete(entry);
        assertEquals(2, entryRepository.count());

    }

    @Test
    public void saveThreeEntry_deleteById(){
        Entry entry = new Entry();
        Entry secondEntry = new Entry();
        Entry thirdEntry = new Entry();
        entryRepository.save(entry);
        entryRepository.save(secondEntry);
        entryRepository.save(thirdEntry);
        assertEquals(3, entryRepository.count());
        entryRepository.delete(1);
        assertEquals(2, entryRepository.count());

    }

}