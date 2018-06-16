package View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.v4.widget.ImageViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class RoundImageView extends ImageView {

    float width,height;
    int Radius = 10;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (width > Radius && height > Radius) {
            Path path = new Path();
            path.moveTo(Radius, 0);
            path.lineTo(width - Radius, 0);
            path.quadTo(width, 0, width, Radius);
            path.lineTo(width, height);
            path.lineTo(0, height);
            path.lineTo(0, Radius);
            path.quadTo(0, 0, Radius, 0);
            canvas.clipPath(path);
        }

        super.onDraw(canvas);
    }
}
