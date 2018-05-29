package edu.rosehulman.fisher.tictactoe;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;


public class TicTacToeGame {
  private enum MarkType {
    NONE,
    X,
    O,
  }
  private enum GameState {
    X_TURN,
    O_TURN,
    X_WIN,
    O_WIN,
    TIE_GAME
  }
  public static final int NUM_SQUARES = 9;

  private GameState mGameState;
  private MarkType[] mBoard;
  private Context mContext;


  public TicTacToeGame(Context context) {
    this.mContext = context;
    mBoard = new MarkType[NUM_SQUARES];
    Arrays.fill(mBoard, MarkType.NONE);
    mGameState = GameState.X_TURN;
  }

  public void pressedButtonAtIndex(int buttonIndex) {
    if (buttonIndex < 0 || buttonIndex >= NUM_SQUARES) {
      return;   // Not a valid square location
    }
    if (mBoard[buttonIndex] != MarkType.NONE) {
      return;   // Not empty
    }
    if (mGameState == GameState.X_TURN) {
      mBoard[buttonIndex] = MarkType.X;
      mGameState = GameState.O_TURN;
    } else if (mGameState == GameState.O_TURN) {
      mBoard[buttonIndex] = MarkType.O;
      mGameState = GameState.X_TURN;
    }
    checkForGameOver();
  }

  private void checkForGameOver() {
    if (!(mGameState == GameState.X_TURN || mGameState == GameState.O_TURN)) {
      return;  // The game is already over.
    }
    if (!Arrays.asList(mBoard).contains(MarkType.NONE)) {
      mGameState = GameState.TIE_GAME;
    }
    ArrayList<String> linesOf3 = new ArrayList<>();
    linesOf3.add(getMarkString(new int[]{0, 1, 2}));
    linesOf3.add(getMarkString(new int[]{3, 4, 5}));
    linesOf3.add(getMarkString(new int[]{6, 7, 8}));
    linesOf3.add(getMarkString(new int[]{0, 3, 6}));
    linesOf3.add(getMarkString(new int[]{1, 4, 7}));
    linesOf3.add(getMarkString(new int[]{2, 5, 8}));
    linesOf3.add(getMarkString(new int[]{0, 4, 8}));
    linesOf3.add(getMarkString(new int[]{2, 4, 6}));
    for (String lineOf3 : linesOf3) {
      if (lineOf3.equals("XXX")) {
        mGameState = GameState.X_WIN;
      } else if (lineOf3.equals("OOO")) {
        mGameState = GameState.O_WIN;
      }
    }
  }

  private String getMarkString(int[] indices) {
    String markString = "";
    for (int index : indices) {
      markString += mBoard[index].name();
    }
    return markString;
  }

  public String stringForButtonAtIndex(int buttonIndex) {
    if (buttonIndex < 0 || buttonIndex >= NUM_SQUARES) {
      return "";   // Not a valid square location
    }
    if (mBoard[buttonIndex] == MarkType.NONE) {
      return "";
    }
    return mBoard[buttonIndex].name();
  }

  public String stringForGameState() {
    String gameStateLabel = "";
    Resources r = mContext.getResources();
    switch (mGameState) {
      case X_TURN:
        gameStateLabel = r.getString(R.string.x_turn);
        break;
      case O_TURN:
        gameStateLabel = r.getString(R.string.o_turn);
        break;
      case X_WIN:
        gameStateLabel = r.getString(R.string.x_win);
        break;
      case O_WIN:
        gameStateLabel = r.getString(R.string.o_win);
        break;
      default:
        gameStateLabel =  r.getString(R.string.tie_game);
        break;
    }
    return gameStateLabel;
  }
}
