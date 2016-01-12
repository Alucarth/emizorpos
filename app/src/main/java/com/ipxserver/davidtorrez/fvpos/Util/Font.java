package com.ipxserver.davidtorrez.fvpos.Util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by David-Pc on 13/06/2015.
 */
public class Font {
    public static final Font  NINBUS   = new Font("fonts/nimbus-sans-l_regular.ttf");
    public static final Font  FRANKLIN_GOTHIC = new Font("FranklinGothicURWBoo.ttf");
    private final String      assetName;
    private volatile Typeface typeface;

    private Font(String assetName) {
        this.assetName = assetName;
    }

    public void apply(Context context, TextView textView) {
        if (typeface == null) {
            synchronized (this) {
                if (typeface == null) {
                    typeface = Typeface.createFromAsset(context.getAssets(), assetName);
                }
            }
        }
        textView.setTypeface(typeface);
    }
}