package p1.comparator;

import p1.card.Card;
import p1.card.CardColor;

import java.util.Comparator;

/**
 * Compares two {@linkplain Card cards}.
 * <p>
 * The cards are first compared by their value and then by their {@link CardColor}.
 *
 * @see Card
 */
public class CardComparator implements Comparator<Card> {

    /**
     * Compares two {@linkplain Card cards}.
     * <p>
     * The cards are first compared by their value and then by their {@link CardColor}.
     * <p>
     * The value of the cards compared by the natural order of the {@link Integer} class.
     * <p>
     * The color of the cards compared using the following order: {@link CardColor#CLUBS} > {@link CardColor#SPADES} >.{@link CardColor#HEARTS} > {@link CardColor#DIAMONDS}.
     *
     * @param o1 the first {@link Card} to compare.
     * @param o2 the second {@link Card} to compare.
     * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     * @throws NullPointerException if either of the {@link Card}s is null.
     *
     * @see Card
     * @see CardColor
     * @see Comparator#compare(Object, Object)
     */
    @Override
    public int compare(Card o1, Card o2) {
        //TODO H1 a): remove if implemented
        //if (o1==null || o2==null) throw new NullPointerException();
        if (myComparator(o1.cardValue(), o2.cardValue())!=0) return myComparator(o1.cardValue(), o2.cardValue());
        else return myComparator(getValue(o1.cardColor()), getValue(o2.cardColor()));
    }
    private int myComparator(int a, int b) {
        return a<b ? -1 : a>b ? 1 : 0;
    }
    private int getValue(CardColor cardColor) {
        return switch (cardColor) {
            case DIAMONDS -> 1;
            case HEARTS -> 2;
            case SPADES -> 3;
            case CLUBS -> 4;
        };
    }
}
