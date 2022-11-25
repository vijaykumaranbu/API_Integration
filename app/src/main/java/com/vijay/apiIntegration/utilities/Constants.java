package com.vijay.apiIntegration.utilities;

import com.vijay.apiIntegration.R;

public class Constants {

    private static int[] thumbs;
    private static int[] profiles;

    public static int[] getThumbs() {
        if (thumbs == null) {
            thumbs = new int[]{
                    R.drawable.thumb1,
                    R.drawable.thumb2,
                    R.drawable.thumb3,
                    R.drawable.thumb4,
                    R.drawable.thumb5,
                    R.drawable.thumb6,
                    R.drawable.thumb7,
                    R.drawable.thumb8,
                    R.drawable.thumb9,
                    R.drawable.thumb10
            };
        }
        return thumbs;
    }

    public static int[] getProfiles() {
        if (profiles == null) {
            profiles = new int[]{
                    R.drawable.profile1,
                    R.drawable.profile2,
                    R.drawable.profile3,
                    R.drawable.profile4,
                    R.drawable.profile5,
                    R.drawable.profile6,
                    R.drawable.profile7,
                    R.drawable.profile8,
                    R.drawable.profile9,
                    R.drawable.profile10
            };
        }
        return profiles;
    }
}
