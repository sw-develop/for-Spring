package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.respository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  public Item findOne(Long itemId) {
    return itemRepository.findOne(itemId);
  }

  public List<Item> findItems() {
    return itemRepository.findAll();
  }

}
