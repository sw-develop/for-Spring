package jpabook.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

  @Id @GeneratedValue
  @Column(name = "item_id")
  private Long id;

  private String name;
  private int price;
  private int stockQuantity;

  @ManyToMany(mappedBy = "items")
  private List<Category> categories = new ArrayList<>();

  //==비즈니스 로직==//
  /*stack 증가*/
  public void addStack(int quantity) {
    this.stockQuantity += quantity;
  }

  /*stack 감소*/
  public void removeStack(int quantity) {
    int restStock = this.stockQuantity - quantity;
    if(restStock < 0) {
      throw new NotEnoughStockException("Need more stock");
    }
    this.stockQuantity = restStock;
  }

}
