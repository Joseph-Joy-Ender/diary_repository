package africa.semicolon.gossipVille.data.repositories;


import africa.semicolon.gossipVille.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EntryRepositoryImplementationTest {

    @Autowired
    private EntryRepository entryRepository;

    @Test
    public void saveOneEntry_countIsOneTest() {
        Entry entry = new Entry();
        Entry savedEntry = entryRepository.save(entry);
        assertEquals(1, entryRepository.count());
        assertNotNull(savedEntry.getId());

    }

}