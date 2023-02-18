package usedbookshop.soobook.review.review;

import lombok.Getter;

@Getter
public enum ReviewScore {

    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private int value;

    ReviewScore(int value) {
        this.value = value;
    }
}
