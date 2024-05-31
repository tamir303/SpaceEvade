package com.example.spaceevade.sprites.ship;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.spaceevade.R;
import com.example.spaceevade.sprites.Sprite;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;

public final class ShipLaneLayout extends Sprite {
    public ShipLaneLayout(Context context, LinearLayout layout) {
        super(context, layout);
    }

    @Override
    protected ShapeableImageView createShapeableImageView(int id) {
        // Create a ShapeableImageView instance
        ShapeableImageView shipImageView = new ShapeableImageView(context);

        // Set layout parameters with a weight for proportional resizing
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                0, // Width set to 0 for weight-based resizing
                ViewGroup.LayoutParams.WRAP_CONTENT, // Height wraps content
                1 // Weight to distribute available space evenly
        );
        layoutParams.setMargins(16, 16, 16, 16);
        shipImageView.setLayoutParams(layoutParams);

        // Set the image resource for the ShapeableImageView
        shipImageView.setImageResource(R.drawable.ic_spaceship); // Replace with your drawable resource

        // Customize the shape appearance with rounded corners
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 16) // 16dp rounded corners
                .build();
        shipImageView.setShapeAppearanceModel(shapeAppearanceModel);

        // Set a stroke width
        shipImageView.setStrokeWidth(4); // 4dp stroke width

        // Set a unique ID for the ShapeableImageView
        shipImageView.setId(id);

        return shipImageView;
    }
}
