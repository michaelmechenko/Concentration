package javalib.impworld;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.Color;
import javalib.impworld.*;
import javalib.worldimages.*;

class Card {
  String value;
  String suit;
  boolean flipped; // t for face up, f for face down
  boolean clickedOn;

  Card(String value, String suit, boolean flipped) {
    this.value = value;
    this.suit = suit;
    this.flipped = flipped;
    this.clickedOn = false;
  }

  // draws the individual card and whether it is flipped or not
  public WorldImage draw() {
    if (flipped) {
      return new OverlayImage(new RectangleImage(40, 40, "outline", Color.black),
              this.getText());
    }
    else {
      return new RectangleImage(40, 40, "outline", Color.black);
    }
  }

  // draws the color of the card depending on the suit
  public WorldImage getText() {
    if (this.suit.equals("♦") || this.suit.equals("♥")) {
      return new TextImage(this.value + this.suit, Color.red);
    }
    else {
      return new TextImage(this.value + this.suit, Color.black);
    }
  }

  // flips the card if the operator is true
  public void showFace() {
    if (this.flipped) {
      this.flipped = false;
    }
    else {
      this.flipped = true;
    }
  }

  // checks if the value of that card is identical
  public boolean matching(Card card) {
    return card.value.equals(this.value);
  }
}