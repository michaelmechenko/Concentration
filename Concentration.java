package javalib.impworld;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.Color;
import javalib.impworld.*;
import javalib.worldimages.*;
import javalib.worldcanvas.*;

public class Concentration extends World {
  Deck deck;
  int cardsFlipped;
  Card tempFlippedCard;
  Card tempFlippedCardTwo;
  int score;
  int winningMessage;

  Concentration(Deck cards) {
    this.deck = cards;
    this.cardsFlipped = 0;
    this.score = 26;
    this.winningMessage = 1;
  }

  Concentration(Deck cards, int cf, Card tfc, Card tfc2, int score, int wm) {
    this.deck = cards;
    this.cardsFlipped = cf;
    this.tempFlippedCard = tfc;
    this.tempFlippedCardTwo = tfc2;
    this.score = score;
    this.winningMessage = wm;
  }

  // renders board
  public <WorldScene> WorldScene makeScene() {
    WorldImage builtDeck = new RectangleImage(1, 1, "solid", Color.white);
    WorldImage row = new RectangleImage(1, 1, "solid", Color.white);
    WorldScene rendered = new WorldScene(600, 200);

    // draws rows & columns
    for (int i = 0; i < this.deck.numberOfSuits + 1; i++) {
      builtDeck = new AboveImage(builtDeck, row);
      row = new RectangleImage(1, 1, "solid", Color.white);
      for (int j = 0; j < this.deck.numberOfValues; j++) {
        if (i == 0) {
          row = new BesideImage(this.deck.cards.get(j).draw(), row);
        }
        else if (i == 1) {
          row = new BesideImage(this.deck.cards.get(j + this.deck.cards.size() / 4).draw(), row);
        }
        else if (i == 2) {
          row = new BesideImage(this.deck.cards.get(j + this.deck.cards.size() / 2).draw(), row);
        }
        else {
          row = new BesideImage(this.deck.cards.get(j + this.deck.cards.size() * 3 / 4).draw(),
                  row);
        }
      }
    }
    if (this.winningMessage == 2) {
      String wonGame = "You have won Concentration! Congratulations!";
      rendered.placeImageXY(new TextImage(wonGame, 20, Color.black), 300, 200);
    }

    rendered.placeImageXY(builtDeck, 300, 100);
    return rendered;
  }

  //changes the cards and does the logic every tick
  public void onTick() {
    if (this.cardsFlipped == 3) {
      //if the two cards are a match
      if (this.tempFlippedCard.matching(this.tempFlippedCardTwo)) {
        //reduce the score, change number of flipped cards to zero
        this.score -= 1;
        this.cardsFlipped = 0;
      }
      else {
        //flip the two cards back over
        this.tempFlippedCard.showFace();
        this.tempFlippedCardTwo.showFace();
        this.cardsFlipped = 0;
      }
    }
  }

  //resets the deck to a new deck when the player hits the key r
  public void onKeyEvent(String s) {
    if (s.equals("r")) {
      deck = new Deck();
    }
  }

  //processes the players clicks on which card
  public void onMouseClicked(Posn mouse) {
    int cardx = mouse.x;
    int cardy = mouse.y;

    //first row
    if (cardy < 60 && cardy > 20) {
      this.deck.cards.get(1).draw();
      this.cardsFlipped += 1;
      if (this.cardsFlipped == 1) {
        this.deck.cards.get(Math.abs((cardx / 40) - 13)).showFace();
        this.tempFlippedCard = deck.cards.get(Math.abs((cardx / 40) - 13));
      }
      else if (this.cardsFlipped == 2) {
        this.deck.cards.get(Math.abs((cardx / 40) - 13)).showFace();
        this.tempFlippedCardTwo = deck.cards.get(Math.abs((cardx / 40) - 13));
      }
    }

    //second row
    else if (cardy < 99 && cardy > 61) {
      this.deck.cards.get(1).draw();
      this.cardsFlipped += 1;
      if (this.cardsFlipped == 1) {
        this.deck.cards.get(Math.abs((cardx / 40) - 26)).showFace();
        this.tempFlippedCard = deck.cards.get(Math.abs((cardx / 40) - 26));
      }
      else if (this.cardsFlipped == 2) {
        this.deck.cards.get(Math.abs((cardx / 40) - 26)).showFace();
        this.tempFlippedCardTwo = deck.cards.get(Math.abs((cardx / 40) - 26));
      }
    }

    //third row
    else if (cardy < 140 && cardy > 100) {
      this.deck.cards.get(1).draw();
      this.cardsFlipped += 1;
      if (this.cardsFlipped == 1) {
        this.deck.cards.get(Math.abs((cardx / 40) - 39)).showFace();
        this.tempFlippedCard = deck.cards.get(Math.abs((cardx / 40) - 39));
      }
      else if (this.cardsFlipped == 2) {
        this.deck.cards.get(Math.abs((cardx / 40) - 39)).showFace();
        this.tempFlippedCardTwo = deck.cards.get(Math.abs((cardx / 40) - 39));
      }
    }

    //fourth row
    else if (cardy < 180 && cardy > 141) {
      this.deck.cards.get(1).draw();
      this.cardsFlipped += 1;
      if (this.cardsFlipped == 1) {
        this.deck.cards.get(Math.abs((cardx / 40) - 52)).showFace();
        this.tempFlippedCard = deck.cards.get(Math.abs((cardx / 40) - 52));
      }
      else if (this.cardsFlipped == 2) {
        this.deck.cards.get(Math.abs((cardx / 40) - 52)).showFace();
        this.tempFlippedCardTwo = deck.cards.get(Math.abs((cardx / 40) - 52));
      }
    }
  }

  @Override
  public WorldImage makeImage() {
    return null;
  }

  //display all the cards flipped over once the player has won
  public WorldEnd worldEnds() {
    if (this.score == 0) {
      this.winningMessage = 2;
      return new WorldEnd(true, this.makeScene());
    }
    return lastWorld;
  }
}
