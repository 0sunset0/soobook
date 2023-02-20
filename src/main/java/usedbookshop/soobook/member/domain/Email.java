package usedbookshop.soobook.member.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Email {

    @javax.validation.constraints.Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    @Column(name = "email", nullable = false, unique = true)
    private String value;

    public Email(String value) {
        this.value = value;
    }
}
