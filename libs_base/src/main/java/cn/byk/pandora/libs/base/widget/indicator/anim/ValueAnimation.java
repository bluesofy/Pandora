package cn.byk.pandora.libs.base.widget.indicator.anim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Byk on 2018/8/13.
 **/
public class ValueAnimation {

    private ColorAnimation colorAnimation;
    private ScaleAnimation scaleAnimation;
    private WormAnimation wormAnimation;
    private SlideAnimation slideAnimation;

    private UpdateListener updateListener;

    public interface UpdateListener {

        void onColorAnimationUpdated(int color, int colorReverse);

        void onScaleAnimationUpdated(int color, int colorReverse, int radius, int radiusReverse);

        void onWormAnimationUpdated(int leftX, int rightX);

        void onSlideAnimationUpdated(int xCoordinate);
    }

    public ValueAnimation(@Nullable UpdateListener listener) {
        updateListener = listener;
    }

    @NonNull
    public ColorAnimation color() {
        if (colorAnimation == null) {
            colorAnimation = new ColorAnimation(updateListener);
        }

        return colorAnimation;
    }

    @NonNull
    public ScaleAnimation scale() {
        if (scaleAnimation == null) {
            scaleAnimation = new ScaleAnimation(updateListener);
        }

        return scaleAnimation;
    }

    @NonNull
    public WormAnimation worm() {
        if (wormAnimation == null) {
            wormAnimation = new WormAnimation(updateListener);
        }

        return wormAnimation;
    }

    @NonNull
    public SlideAnimation slide() {
        if (slideAnimation == null) {
            slideAnimation = new SlideAnimation(updateListener);
        }

        return slideAnimation;
    }
}
