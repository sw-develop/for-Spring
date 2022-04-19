package hellojpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member extends BaseEntity {

  @Id @GeneratedValue
  private Long id;

  private String name;

  private String city;
  private String street;
  private String zipcode;

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

}
