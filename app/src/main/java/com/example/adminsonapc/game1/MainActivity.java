package com.example.adminsonapc.game1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    private static final SecureRandom secureRandomNumber = new SecureRandom();
    private enum Status{
        NOTSTARTEDYET,PROCEED,WON,LOST
    }

    private static final int TIGER_CLAWS = 2;
    private static final int TREE = 3;
    private static final int CEVEN = 7;
    private static final int WE_LEVEN = 11;
    private static final int PANTHER = 12;


    TextView txtCalculation;
    ImageView imgDice;
    Button btnRestartTheGame;

    String oldTxtCalculationValue = "";
    boolean firstTime = true;
    Status gameStatus = Status.NOTSTARTEDYET;
    int points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtCalculation = (TextView) findViewById(R.id.txtCalculation);
        imgDice = (ImageView) findViewById(R.id.imageView);
        btnRestartTheGame = (Button) findViewById(R.id.btnRestartTheGame);
        final TextView txtGemeStatus = (TextView) findViewById(R.id.txtGameStatus);

        makeBtnRestartTheGameInvisible();
        txtGemeStatus.setText("");
        txtCalculation.setText("");

        imgDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameStatus == Status.NOTSTARTEDYET){

                    int diceSum = letRollTheDice();
                    oldTxtCalculationValue = txtCalculation.getText().toString();
                    points = 0;

                    switch (diceSum){
                        case CEVEN:
                        case WE_LEVEN:
                            gameStatus = Status.WON;
                            txtGemeStatus.setText("you win!");
                            makeImgDiceInvisible();
                            makeBtnRestartTheGameVisible();
                            break;
                        case TIGER_CLAWS:
                        case TREE:
                        case PANTHER:
                            gameStatus = Status.LOST;
                            txtGemeStatus.setText("you lost!");
                            makeImgDiceInvisible();
                            makeBtnRestartTheGameVisible();
                            break;
                        case 4:             // we can use default statement instead of case 4:
                        case 5:             //                                         case 5:
                        case 6 :            //                                          case 6:
                        case 8:             //                                          case 8:
                        case 9:             //                                          case 9:
                        case 10:            //                                          case 10;
                            gameStatus = Status.PROCEED;
                            points = diceSum;
                            txtCalculation.setText(oldTxtCalculationValue + "your point : " + points + "\n");
                            txtGemeStatus.setText("Continue the game");
                            oldTxtCalculationValue = "point : " + points + "\n";
                            break;
                    }
                    return;

                }

                if(gameStatus == Status.PROCEED){
                    int diceSum = letRollTheDice();
                    if(diceSum == points){
                        gameStatus = Status.WON;
                        txtGemeStatus.setText("you won!");
                        makeImgDiceInvisible();
                        makeBtnRestartTheGameVisible();
                    }
                    else if(diceSum == CEVEN){
                        gameStatus = Status.LOST;
                        txtGemeStatus.setText("you lost!");
                        makeImgDiceInvisible();
                        makeBtnRestartTheGameVisible();
                    }
                }
            }
        });


        btnRestartTheGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameStatus = Status.NOTSTARTEDYET;
                txtGemeStatus.setText("");
                txtCalculation.setText("");
                oldTxtCalculationValue = "";
                makeImgDiceVisible();
                makeBtnRestartTheGameVisible();

            }
        });



    }

    private void makeImgDiceInvisible(){
        imgDice.setVisibility(View.INVISIBLE);
    }

    private void makeImgDiceVisible(){
        imgDice.setVisibility(View.VISIBLE);
    }

    private void makeBtnRestartTheGameInvisible(){
        btnRestartTheGame.setVisibility(View.INVISIBLE);
    }

    private void makeBtnRestartTheGameVisible(){
        btnRestartTheGame.setVisibility(View.VISIBLE);
    }

    private int letRollTheDice(){
        int randDice1 = 1 + secureRandomNumber.nextInt(6);
        int randDice2 = 1 + secureRandomNumber.nextInt(6);
        int sum = randDice1 + randDice2;
        txtCalculation.setText(String.format(oldTxtCalculationValue + "you rolled %d + %d = %d \n",
                                                    randDice1,randDice2,sum));
        return sum;
    }



}
