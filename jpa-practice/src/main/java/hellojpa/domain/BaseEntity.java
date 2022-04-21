package hellojpa.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

  private String createdBy;
  private LocalDateTime createdDate;
  private String lastModifiedBy;
  private LocalDateTime lastModifiedDate;
}
