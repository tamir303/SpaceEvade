package com.example.spaceevade.sprites;

import android.content.Context;
import android.view.ViewGroup;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.stream.IntStream;

public abstract class Sprite {
    protected final Context context;
    protected final ViewGroup layout;
    protected ArrayList<ShapeableImageView> sprites;

    protected Sprite(Context context, ViewGroup layout) {
        this.context = context;
        this.layout = layout;
        this.sprites = new ArrayList<ShapeableImageView>();
    }

    public void setupLayout(int numberOfTiles) {
        IntStream.range(0, numberOfTiles)
                .forEach(index -> {
                    ShapeableImageView sprite = createShapeableImageView(index);
                    sprite.setVisibility(ViewGroup.INVISIBLE); // Initialize invisible
                    layout.addView(sprite);
                    sprites.add(sprite);
                });
    }

    protected abstract ShapeableImageView createShapeableImageView(int id);

    public ArrayList<ShapeableImageView> getShips() {
        return sprites;
    }

    public void setVisibile(int index) {
        sprites.get(index).setVisibility(ViewGroup.VISIBLE);
    }

    public void setInvisibile(int index) { sprites.get(index).setVisibility(ViewGroup.INVISIBLE); }}