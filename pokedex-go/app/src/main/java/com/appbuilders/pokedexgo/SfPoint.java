package com.appbuilders.pokedexgo;

import java.lang.Object;public class SfPoint extends Object {

    public float x;
    public float y;

    public SfPoint() {
        this.x = 0;
        this.y = 0;
    }

    public SfPoint(float x ,float y) {
        this.setPoint(x, y);
    }

    public void setPoint(float x ,float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty() {
        return (this.x == 0 && this.y == 0);
    }
}
