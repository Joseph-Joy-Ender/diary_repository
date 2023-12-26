package africa.semicolon.gossipVille.data.repositories;

import africa.semicolon.gossipVille.data.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary, String> {
    Diary findDiaryByUsername(String username);

}
