package com.example.spaceevade;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import androidx.core.content.ContextCompat;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class GameView {
    private final ShapeableImageView[] game_IMG_hearts;
    private final MaterialTextView game_TXT_score;
    private final GridLayout gridLayout;
    private final GameManager gameManager;
    private final Context context;

    public GameView(Context context, ShapeableImageView[] game_IMG_hearts, GridLayout game_LAYOUT_matrix, MaterialTextView game_TXT_score, GameManager gameManager) {
        this.gameManager = gameManager;
        this.game_IMG_hearts = game_IMG_hearts;
        this.game_TXT_score = game_TXT_score;
        this.gridLayout = game_LAYOUT_matrix;
        this.context = context;
    }

    public void showHearts() {
        for (ShapeableImageView heart : game_IMG_hearts) {
            heart.setVisibility(View.VISIBLE);
        }
    }

    public void updateHeroVisibility() {
        int lastIndex = gameManager.getRowSize() - 1;
        for (int i = 0; i < gameManager.getColSize(); i++) {
            gridLayout.getChildAt(lastIndex * gameManager.getColSize() + i).setVisibility(View.INVISIBLE);
        }
        setImage(lastIndex, gameManager.getCurrentIndexHero(), 3);
        gridLayout.getChildAt(lastIndex * gameManager.getColSize() + gameManager.getCurrentIndexHero()).setVisibility(View.VISIBLE);
    }

    public void updateViewObjects() {
        int lastIndex = gameManager.getRowSize() - 2; //
        for (int i = lastIndex; i >= 0; i--) {
            for (int j = gameManager.getColSize() - 1; j >= 0; j--) {
                View childView = gridLayout.getChildAt(i * gameManager.getColSize() + j);
                if (childView.getVisibility() == View.VISIBLE) {
                    childView.setVisibility(View.INVISIBLE);
                    if (i == lastIndex) {
                        continue;
                    }
                    int type = gameManager.getTypeCellInMatrix(i, j);
                    gameManager.setTypeCellInMatrix(i, j, -1);
                    setImage(i + 1, j, type);
                    gameManager.setTypeCellInMatrix(i + 1, j, type);
                    gridLayout.getChildAt((i + 1) * gameManager.getColSize() + j).setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void setImage(int row, int col, int type) {
        ShapeableImageView imageView = (ShapeableImageView) gridLayout.getChildAt(row * gameManager.getColSize() + col);
        if (imageView != null) {
            int drawableResId = 0;
            switch (type) {
                case 0:
                    drawableResId = R.drawable.ic_astroid;
                    break;
                case 1:
                    drawableResId = R.drawable.ic_heart;
                    break;
                case 2:
                    drawableResId = R.drawable.ic_star;
                    break;
                case 3:
                    drawableResId = R.drawable.ic_spaceship;
                    break;
            }
            if (drawableResId != 0) {
                imageView.setBackground(ContextCompat.getDrawable(context, drawableResId));
                gameManager.setTypeCellInMatrix(row, col, type);
            }
        }
    }

    public void detectAndHandleCollision() {
        int lastIndex = gameManager.getRowSize() - 2;
        for (int row = lastIndex; row >= 0; row--) {
            for (int col = 0; col < gameManager.getColSize(); col++) {
                ShapeableImageView imageView = (ShapeableImageView) gridLayout.getChildAt(row * gameManager.getColSize() + col);
                if (row == lastIndex && imageView != null && imageView.getVisibility() == View.VISIBLE && col == gameManager.getCurrentIndexHero()) {
                    handleCollisionWithVillain(row, col);
                }
            }
        }
    }

    private void handleCollisionWithVillain(int row, int col) {
        int mainType = gameManager.getTypeCellInMatrix(row, col);
        switch (mainType) {
            case 0: // Villain
                gameManager.decreaseHealth();
                game_IMG_hearts[gameManager.getHealth()].setVisibility(View.INVISIBLE);
                break;
            case 1: // Heart
                if (gameManager.getHealth() < game_IMG_hearts.length) {
                    gameManager.increaseHealth();
                    game_IMG_hearts[gameManager.getHealth() - 1].setVisibility(View.VISIBLE);
                }
                break;
            case 2: // Bullet (web score)
                gameManager.addScore();
                game_TXT_score.setText(String.valueOf(gameManager.getScore()));
                break;
            default:
                break; // Handle other types gracefully
        }
        gameManager.setTypeCellInMatrix(row, col, -1);
    }

    public void gameOver() {
        gridLayout.setVisibility(View.INVISIBLE);
        gameManager.gameOver();
    }

    public void initMatrix() {
        gridLayout.setColumnCount( gameManager.getColSize());
        gridLayout.setRowCount(gameManager.getRowSize());
        for (int i = 0; i < gameManager.getRowSize(); i++) {
            for (int j = 0; j < gameManager.getColSize(); j++) {
                ShapeableImageView imageView = new ShapeableImageView(context);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = GridLayout.LayoutParams.WRAP_CONTENT;
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);
                imageView.setLayoutParams(params);
                gridLayout.addView(imageView);
            }
        }
    }

    public ViewGroup getGridLayout() {
        return gridLayout;
    }
}
