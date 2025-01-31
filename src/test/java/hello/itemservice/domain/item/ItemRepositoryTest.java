package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    public void save() throws Exception {
        //given
        Item item = new Item("itemA",10000,10);


        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findByID(item.getId());
        assertThat(findItem).isEqualTo(saveItem);

    }

    @Test
    public void findAll() throws Exception {
        //given
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",20000,20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when

        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);

    }
    @Test
    public void updateItem() throws Exception {
        //given
        Item item = new Item("item1", 10000, 10);

        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();
        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId,updateParam);

        //then
        Item findItem = itemRepository.findByID(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertEquals(findItem.getPrice(),updateParam.getPrice());
        assertEquals(findItem.getQuantity(),updateParam.getQuantity());
    }

}