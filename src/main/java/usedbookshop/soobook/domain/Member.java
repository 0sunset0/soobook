package usedbookshop.soobook.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import usedbookshop.soobook.exception.PasswordFailedExceededException;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
public class Member {


    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="area", column = @Column(name = "home_area"))
            ,@AttributeOverride(name="roadName", column = @Column(name = "home_roadName"))
            ,@AttributeOverride(name="roadCode", column = @Column(name = "home_roadCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="area", column = @Column(name = "work_area"))
            ,@AttributeOverride(name="roadName", column = @Column(name = "work_roadName"))
            ,@AttributeOverride(name="roadCode", column = @Column(name = "work_roadCode"))
    })
    private Address workAddress;

    @Column(unique = true)
    private String email;

    @Embedded
    private Password password;


    protected Member() {
    }

    private Member(String name, Address homeAddress, Address workAddress, String email, Password password) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.email = email;
        this.password = password;
    }

    public static Member createMember(String name, Address homeAddress, Address workAddress, String email, Password password){
        return new Member(name, homeAddress, workAddress, email, password);
    }

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Password {

        @Column(name = "password", nullable = false)
        private String value;

        @Column(name = "password_expiration_date")
        private LocalDateTime expirationDate;

        @Column(name = "password_failed_count", nullable = false)
        private int failedCount;

        @Column(name = "password_ttl")
        private long ttl;

        @Builder
        public Password(final String value) {
            this.ttl = 3; // 3개월
            this.value = value;
            this.expirationDate = extendExpirationDate();
        }

        public boolean isExpiration() {
            return LocalDateTime.now().isAfter(expirationDate);
        }

        // 만료기간 설정
        private LocalDateTime extendExpirationDate() {
            return LocalDateTime.now().plusMonths(ttl);
        }

        public boolean isMatched(final String rawPassword) {
            if (failedCount >= 5)
                throw new PasswordFailedExceededException("비밀번호 실패 횟수가 초과했습니다.");

            final boolean matches = isMatches(rawPassword);
            updateFailedCount(matches);
            return matches;
        }

        private boolean isMatches(String rawPassword) {
            return this.value.equals(rawPassword);
        }

        // password 변경
        public void changePassword(final String newPassword, final String oldPassword) {
            if (isMatched(oldPassword)) {
                value = newPassword;
                extendExpirationDate();
            }
        }

        private void updateFailedCount(boolean matches) {
            if (matches)
                resetFailedCount();
            else
                increaseFailCount();
        }

        private void resetFailedCount() {
            this.failedCount = 0;
        }

        private void increaseFailCount() {
            this.failedCount++;
        }
    }
}