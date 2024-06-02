package college.pb.productmanager.service.impl;

import college.pb.productmanager.model.entity.Item;
import college.pb.productmanager.repository.ItemRepository;
import college.pb.productmanager.service.ItemService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        log.info("Fetching all items");
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItemById(String id) {
        log.info("Fetching item by id: {}", id);
        return itemRepository.findById(id);
    }

    @Override
    public Item saveItem(Item item) {
        log.info("Saving item: {}", item);
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(String id, Item item) {
        log.info("Updating item: {}", item);
        return itemRepository.findById(id)
            .map(existingItem -> {
                existingItem.setName(item.getName());
                existingItem.setCreatedAt(item.getCreatedAt());
                existingItem.setUpdatedAt(item.getUpdatedAt());
                return itemRepository.save(existingItem);
            })
            .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    @Override
    public void deleteItemById(String id) {
        log.info("Deleting item by id: {}", id);
        itemRepository.deleteById(id);
    }

}
