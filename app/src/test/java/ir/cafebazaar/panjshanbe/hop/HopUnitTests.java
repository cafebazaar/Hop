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
import android.test.ActivityUnitTestCase;

/**
 * Sample for Usage of
 * <a href="http://developer.android.com/tools/testing/activity_testing.html#ActivityUnitTestCase">
 * ActivityUnitTestCase</a>.
 */
public class HopUnitTests extends ActivityUnitTestCase<MainActivity> {

    public HopUnitTests() {
        super(MainActivity.class);
    }

    /**
     * Tests {@link ir.cafebazaar.panjshanbe.hop.MainActivity#judge(boolean)}
     */
    public void testHopJudge() {
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
        intent.putExtra(MainActivity.HOP_NUMER, 2);
        startActivity(intent, null, null);
        MainActivity activity = getActivity();

        assertEquals(activity.judge(true), false);
        assertEquals(activity.judge(false), true);

        activity.increaseNumber();

        assertEquals(activity.judge(true), true);
        assertEquals(activity.judge(false), false);
    }

}
