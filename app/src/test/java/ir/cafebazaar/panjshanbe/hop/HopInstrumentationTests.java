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

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Sample for Usage of
 * <a href="http://developer.android.com/tools/testing/activity_testing.html#ActivityInstrumentationTestCase2">
 * ActivityInstrumentationTestCase2</a>.
 */
public class HopInstrumentationTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public HopInstrumentationTests() { super(MainActivity.class); }

    private void clickOnView(final View v) throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                v.performClick();
            }
        });
    }

    /**
     * Tests the game like a player interacting with the app.
     * @throws Throwable
     */
    public void testHopGame() throws Throwable {
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
        intent.putExtra(MainActivity.HOP_NUMER, 2);
        setActivityIntent(intent);
        MainActivity activity = getActivity();

        final TextView textView = (TextView) activity.findViewById(R.id.textView);
        final Button numberButton = (Button) activity.findViewById(R.id.numberButton);
        final Button hopButton = (Button) activity.findViewById(R.id.hopButton);

        // Ready, go!
        assertEquals(textView.getText(), activity.getString(R.string.hop_announcement, 2));
        assertEquals(numberButton.getText(), "1");

        clickOnView(numberButton);
        assertEquals(textView.getText(), activity.getString(R.string.hop_announcement, 2));
        assertEquals(numberButton.getText(), "2");

        clickOnView(numberButton); // Wrong button!
        assertEquals(textView.getText(), activity.getString(R.string.hop_over, 2));

        // Buttons should be disabled now.
        assertFalse(numberButton.isEnabled());
        assertFalse(hopButton.isEnabled());

        // Reset
        clickOnView(textView);

        // Ready to play again
        assertEquals(textView.getText(), activity.getString(R.string.hop_announcement, 2));
        assertEquals(numberButton.getText(), "1");
        assertTrue(numberButton.isEnabled());
        assertTrue(hopButton.isEnabled());

        clickOnView(numberButton);
        assertEquals(numberButton.getText(), "2");

        clickOnView(hopButton);
        assertEquals(textView.getText(), activity.getString(R.string.hop_announcement, 2));
        assertEquals(numberButton.getText(), "3");

        clickOnView(hopButton); // Wrong button!
        assertEquals(textView.getText(), activity.getString(R.string.hop_over, 3));
    }

}
