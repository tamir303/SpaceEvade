package com.example.spaceevade.sprites.asteroid;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.spaceevade.R;
import com.example.spaceevade.config.Configuration;
import com.example.spaceevade.sprites.Sprite;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

public final class AsteroidGridLayout extends Sprite {
    public AsteroidGridLayout(Context context, GridLayout layout) {
        super(context, layout);
    }

    @Override
    protected ShapeableImageView createShapeableImageView(int id) {
        // Create ShareableImageView
        ShapeableImageView shapeableImageView = new ShapeableImageView(context);

        // Set layout parameters
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = 0;
        layoutParams.height = 1;
        layoutParams.setMargins(16, 16, 16, 16);

        int row = id / (Configuration.getGameHeight() - 1);
        int col = id % Configuration.getNumberOfLanes();

        layoutParams.columnSpec = GridLayout.spec(col, 1f);
        layoutParams.rowSpec = GridLayout.spec(row, 1f);

        shapeableImageView.setLayoutParams(layoutParams);

        // Set an image resource
        shapeableImageView.setImageResource(R.drawable.ic_astroid); // Replace with your image resource

        // Scale the image to fit within the view bounds
        shapeableImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        // Customize the shape
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 16) // 16dp rounded corners
                .build();

        shapeableImageView.setShapeAppearanceModel(shapeAppearanceModel);
        shapeableImageView.setId(id);

        return shapeableImageView;
    }
}
