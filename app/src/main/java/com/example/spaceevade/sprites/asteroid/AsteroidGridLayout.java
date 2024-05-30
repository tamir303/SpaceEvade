package com.example.spaceevade.sprites.asteroid;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.spaceevade.R;
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
        layoutParams.width = 0; // Equivalent to 0dp
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.setMargins(16, 16, 16, 16); // Set margins if needed
        layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        shapeableImageView.setLayoutParams(layoutParams);

        // Set an image resource
        shapeableImageView.setImageResource(R.drawable.ic_astroid); // Replace with your image resource

        // Customize the shape
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 16) // 16dp rounded corners
                .build();
        shapeableImageView.setShapeAppearanceModel(shapeAppearanceModel);
        shapeableImageView.setStrokeWidth(4); // 4dp stroke width
        shapeableImageView.setId(id);

        return shapeableImageView;
    }
}
