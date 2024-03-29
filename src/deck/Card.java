package deck;

import java.util.ArrayList;
import java.util.List;

public record Card(Suit suit, String face, int rank) {
  @Override
  public String toString() {
    int index = face.equals("10") ? 2 : 1;
    String faceString = face.substring(0, index);
    return "%s%c(%d)".formatted(faceString, suit.getImage(), rank);
  }

  public static Card getNumericCard(Suit suit, int cardNumber) {
    if (cardNumber > 1 && cardNumber < 11) {
      return new Card(suit, String.valueOf(cardNumber), cardNumber - 2);
    }
    System.out.println("Invalid Numeric card selected");
    return null;
  }

  public static Card getFaceCard(Suit suit, char abrrev) {
    int charIndex = "JQKA".indexOf(abrrev);

    if (charIndex > -1) {
      return new Card(suit, "" + abrrev, charIndex + 9);
    }
    System.out.println("Invalid Face card selected");
    return null;
  }

  public static List<Card> getStandardDeck() {
    List<Card> deck = new ArrayList<>(52);

    for (Suit suit : Suit.values()) {
      for (int i = 2; i <= 10; i++) {
        deck.add(getNumericCard(suit, i));
      }

      for (char c : new char[] {'J', 'K', 'Q', 'A'}) {
        deck.add(getFaceCard(suit, c));
      }
    }
    return deck;
  }

  public static void printDeck(List<Card> deck, String description, int rows) {
    System.out.println("-".repeat(40));

    if (description != null) {
      System.out.println(description);
    }

    int cardsInRow = deck.size() / rows;

    for (int i = 0; i < rows; i++) {
      int startIndex = i * cardsInRow;
      int endIndex = startIndex + cardsInRow;
      deck.subList(startIndex, endIndex).forEach(c -> System.out.print(c + " "));
      System.out.println();
    }
  }

  public static void printDeck(List<Card> deck) {
    printDeck(deck, "Current Deck", 4);
  }
}
