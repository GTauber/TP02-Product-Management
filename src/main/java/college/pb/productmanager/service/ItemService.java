package college.pb.productmanager.service;

import college.pb.productmanager.model.entity.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllItems();
    Optional<Item> getItemById(String id);
    Item saveItem(Item item);
    Item updateItem(String id, Item item);
    void deleteItemById(String id);

}
