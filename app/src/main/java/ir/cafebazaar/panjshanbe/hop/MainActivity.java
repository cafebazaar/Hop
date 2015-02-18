/*
 * Copyright 2015 Cafe Bazaar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.cafebazaar.panjshanbe.hop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Hop game activity with two buttons and a label.
 *
 * {@link #initGame()} generates a random number as the seed for the game.
 * But you can set this number by setting extra for the intent:
 * <pre>
 *     intent.putExtra(MainActivity.HOP_NUMBER, 2);
 * </pre>
 */
public class MainActivity extends Activity {

    public static final String HOP_NUMER = "HOP_NUMBER";

    Random randomGenerator = new Random();

    /** Seed for the game. Must be greater than two. */
    private int hopNumber;
    private int currentNumber;

    private TextView textView;
    private Button numberButton;
    private Button hopButton;

    /**
     * Initializes the game, by setting {@link #hopNumber}, and resetting
     * {@link #currentNumber} to 1, and announcing the {@link #hopNumber}
     * through the label on the top of the activiy.
     */
    void initGame() {
        Intent intent = getIntent();
        hopNumber = intent.getIntExtra(HOP_NUMER,
                                       randomGenerator.nextInt(5) + 2);

        currentNumber = 1;

        textView.setText(getString(R.string.hop_announcement, hopNumber));
        numberButton.setText(String.valueOf(currentNumber));
        numberButton.setEnabled(true);
        hopButton.setEnabled(true);
    }

    /**
     * Assert the claim of the player. Iff the {@link #currentNumber} is
     * dividable by {@link #hopNumber}, we expect <var>claimedToBeHop</var>
     * to be true.
     *
     * @param claimedToBeHop is true when the Hop button is clicked.
     */
    boolean judge(boolean claimedToBeHop) {
        return (currentNumber % hopNumber == 0) == claimedToBeHop;
    }

    /**
     * Increases the {@link #currentNumber} and updates the number button.
     */
    void increaseNumber() {
        currentNumber += 1;
        numberButton.setText(String.valueOf(currentNumber));
    }

    /**
     * Game over! Announces the value of {@link #currentNumber} as the
     * score of the player.
     */
    void hopOver() {
        numberButton.setEnabled(false);
        hopButton.setEnabled(false);
        textView.setText(getString(R.string.hop_over, currentNumber));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });

        numberButton = (Button) findViewById(R.id.numberButton);
        numberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judge(false)) {
                    increaseNumber();
                } else {
                    hopOver();
                }
            }
        });

        hopButton = (Button) findViewById(R.id.hopButton);
        hopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judge(true)) {
                    increaseNumber();
                } else {
                    hopOver();
                }
            }
        });

        initGame();
    }

}
