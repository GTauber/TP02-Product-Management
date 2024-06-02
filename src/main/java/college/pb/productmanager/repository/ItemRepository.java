package college.pb.productmanager.repository;

import college.pb.productmanager.model.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {

}
