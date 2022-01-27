package javalib.impworld;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.Color;
import javalib.impworld.*;
import javalib.worldimages.*;

class Deck {
  ArrayList<Card> cards;
  int numberOfSuits;
  int numberOfValues;
  int seed;

  // non seeded constructor
  Deck(ArrayList<Card> cards, int numberOfSuits, int numberOfValues) {
    this.cards = cards;
    this.numberOfSuits = numberOfSuits;
    this.numberOfValues = numberOfValues;
    this.seed = -1;
  }

  // seeded constructor for testing
  Deck(ArrayList<Card> cards, int numberOfSuits, int numberOfValues, int seed) {
    this.cards = cards;
    this.numberOfSuits = numberOfSuits;
    this.numberOfValues = numberOfValues;
    this.seed = seed;
  }

  // general constructor for a deck
  Deck() {
    this.cards = this.makeDeck();
    this.numberOfSuits = 4;
    this.numberOfValues = 13;
  }

  // creates a deck of cards
  ArrayList<Card> makeDeck() {
    // represents value of cards
    ArrayList<String> vals = new ArrayList<String>(
            Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));

    // represents suit of cards
    ArrayList<String> suits = new ArrayList<String>(
            Arrays.asList("♣", "♦", "♥", "♠"));

    // holds deck of cards
    cards = new ArrayList<Card>();

    // builds deck of cards; adds to arraylist cards
    for (int i = 0; i < vals.size(); i++) {
      for (int j = 0; j < suits.size(); j++) {
        cards.add(new Card(vals.get(i), suits.get(j), false));
        // f for faces showing
      }
    }

    if (this.seed == -1) {
      Collections.shuffle(cards, new Random());
    }
    else if (this.seed >= 0) {
      Collections.shuffle(cards, new Random(this.seed));
    }
    return cards;
  }
}