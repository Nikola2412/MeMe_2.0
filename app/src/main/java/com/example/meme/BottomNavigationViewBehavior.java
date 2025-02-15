package com.example.meme;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.core.view.ViewCompat;

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    private int lastDy = 0;
    private ValueAnimator animator;

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        return dependency instanceof androidx.core.widget.NestedScrollView || dependency instanceof androidx.recyclerview.widget.RecyclerView; // Add other scrollable views if needed
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, BottomNavigationView child, View dependency) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (dy > 0 && lastDy <= 0) {
            slideDown(child);
        } else if (dy < 0 && lastDy >= 0) {
            slideUp(child);
        }
        lastDy = dy;
    }

    private void slideUp(BottomNavigationView child) {
        if (animator != null) {
            animator.cancel();
        }
        animator = ValueAnimator.ofInt((int) child.getTranslationY(), 0);
        animator.setDuration(250);
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            child.setTranslationY(value);
        });
        animator.start();
    }

    private void slideDown(BottomNavigationView child) {
        if (animator != null) {
            animator.cancel();
        }
        int targetY = child.getHeight();
        animator = ValueAnimator.ofInt((int) child.getTranslationY(), targetY);
        animator.setDuration(250);
        animator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            child.setTranslationY(value);
        });
        animator.start();
    }
}