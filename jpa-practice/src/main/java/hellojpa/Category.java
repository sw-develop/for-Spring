package hellojpa;

import hellojpa.item.Item;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {

  @Id @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "categories")
  private List<Item> items = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> child = new ArrayList<>();
}
