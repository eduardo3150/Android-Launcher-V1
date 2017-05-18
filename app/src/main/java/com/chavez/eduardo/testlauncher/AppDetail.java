package com.chavez.eduardo.testlauncher;

import android.graphics.drawable.Drawable;

/**
 * Created by Eduardo_Chavez on 25/3/2017.
 */

public class AppDetail {
    CharSequence label;
    CharSequence name;
    Drawable icon;

    public AppDetail(CharSequence label, CharSequence name, Drawable icon) {
        this.label = label;
        this.name = name;
        this.icon = icon;
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
