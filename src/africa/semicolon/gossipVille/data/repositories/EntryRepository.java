package africa.semicolon.gossipVille.data.repositories;

import africa.semicolon.gossipVille.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntryRepository extends MongoRepository<Entry, String> {

}
