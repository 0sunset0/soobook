package usedbookshop.soobook.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class Date {

    protected LocalDateTime createdDate;
    protected LocalDateTime modifiedDate;
    protected LocalDateTime deletedDate;

}
