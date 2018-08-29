package cn.byk.pandora.libs.base.widget.indicator.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;

import androidx.annotation.NonNull;

/**
 * Created by Byk on 2018/8/13.
 **/
public abstract class BaseAnimation<T extends Animator> {

    public static final int DEFAULT_ANIMATION_TIME = 350;

    protected long animationDuration = DEFAULT_ANIMATION_TIME;
    protected ValueAnimation.UpdateListener listener;
    protected T animator;

    public BaseAnimation(@NonNull ValueAnimation.UpdateListener listener) {
        this.listener = listener;
        animator = createAnimator();
    }

    @NonNull
    public abstract T createAnimator();

    public abstract BaseAnimation progress(float progress);

    public BaseAnimation duration(long duration) {
        animationDuration = duration;

        if (animator instanceof AnimatorSet) {
            int size = ((AnimatorSet) animator).getChildAnimations()
                                               .size();
            long singleDuration = animationDuration / size;
            animator.setDuration(singleDuration);

        } else {
            animator.setDuration(animationDuration);
        }

        return this;
    }

    public void start() {
        if (animator != null) {
            animator.start();
        }
    }

    public void end() {
        if (animator != null) {
            animator.end();
        }
    }
}
