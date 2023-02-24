package usedbookshop.soobook.member.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import usedbookshop.soobook.exception.PasswordFailedExceededException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Password {

    @Column(name = "password", nullable = false)
    private String value;

    @Column(name = "password_expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "password_failed_count", nullable = false)
    private int failedCount;

    @Column(name = "password_ttl")
    private long ttl;

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