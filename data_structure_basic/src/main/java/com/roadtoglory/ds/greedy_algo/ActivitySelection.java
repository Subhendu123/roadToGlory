/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.greedy_algo;
/*
*
*

This class ActivitySelection is created and managed by subhe
Created on 04-03-2026 at 07:55 for the project Udemy Learning

*
*
*/

import java.util.Arrays;
import java.util.List;

public class ActivitySelection {

    public int maxActivity(Activity[] activities) {
        // sort the activities
        List<Activity> sortedActivities = Arrays.stream(activities).toList().stream().sorted((a, b) ->
                a.finish > b.finish ? 1 : -1
        ).toList();

        // Initialize the result with the zero-th element
        Activity[] result = new Activity[sortedActivities.size()];
        int lastIndex = 0;
        result[lastIndex] = sortedActivities.get(0);

        // Run thro' the rest of the activities
        for (int i = 1; i < sortedActivities.size(); i++) {
            Activity act = sortedActivities.get(i);
            // Add the activity which does not have overlapping time
            if (act.start >= result[lastIndex].finish) {
                lastIndex++;
                result[lastIndex] = act;
            }
        }

        System.out.println(Arrays.toString(result));

        return lastIndex + 1;


    }

    public class Activity {
        int start;
        int finish;

        public Activity(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }

        @Override
        public String toString() {
            return "(" + this.start + "-" + this.finish + ")";
        }
    }
}
