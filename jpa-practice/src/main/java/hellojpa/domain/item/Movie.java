package hellojpa.domain.item;

import hellojpa.domain.item.Item;
import javax.persistence.Entity;

@Entity
public class Movie extends Item {

  private String director;
  private String actor;
}
