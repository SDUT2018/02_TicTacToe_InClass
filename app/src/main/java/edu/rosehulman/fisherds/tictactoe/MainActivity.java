package edu.rosehulman.fisherds.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private TextView mGameStateTextView;
  private Button[] mButtons;
  private TicTacToeGame mGame;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mGame = new TicTacToeGame(this);
    mGameStateTextView = findViewById(R.id.game_state_textview);
    mButtons = new Button[9];
    mButtons[0] = findViewById(R.id.button0);
    mButtons[1] = findViewById(R.id.button1);
    mButtons[2] = findViewById(R.id.button2);
    mButtons[3] = findViewById(R.id.button3);
    mButtons[4] = findViewById(R.id.button4);
    mButtons[5] = findViewById(R.id.button5);
    mButtons[6] = findViewById(R.id.button6);
    mButtons[7] = findViewById(R.id.button7);
    mButtons[8] = findViewById(R.id.button8);
    updateView();
  }

  public void pressedSquare(View view) {
    String tagStr = view.getTag().toString();
    int tag = Integer.parseInt(tagStr);
    mGame.pressedButtonAtIndex(tag);
    updateView();
  }

  private void updateView() {
    mGameStateTextView.setText(mGame.stringForGameState());
    for (int i = 0; i < 9; i++) {
      mButtons[i].setText(mGame.stringForButtonAtIndex(i));
    }
  }

  public void pressedNewGame(View view) {
    mGame = new TicTacToeGame(this);
    updateView();
  }



}
