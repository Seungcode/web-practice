package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositotyTest {

    ItemRepositoty itemRepositoty = new ItemRepositoty();

    @AfterEach
    void afterEach(){
        itemRepositoty.clearStore();
    }

    @Test
    void save(){
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepositoty.save(item);

        Item findItem = itemRepositoty.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepositoty.save(item1);
        itemRepositoty.save(item2);

        List<Item> result = itemRepositoty.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem(){
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepositoty.save(item);
        Long itemId = savedItem.getId();
        Item updateParam = new Item("item2", 20000, 20);
        itemRepositoty.update(itemId, updateParam);

        Item findItem = itemRepositoty.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
    }

}