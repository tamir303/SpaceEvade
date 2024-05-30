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
        // Create a ShapeableImageView
        ShapeableImageView shipImageView = new ShapeableImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1 // layout_weight
        );
        shipImageView.setLayoutParams(layoutParams);
        shipImageView.setImageResource(R.drawable.ic_spaceship);

        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, 16) // 16dp rounded corners
                .build();
        shipImageView.setShapeAppearanceModel(shapeAppearanceModel);

        shipImageView.setId(id);

        return shipImageView;
    }
}
