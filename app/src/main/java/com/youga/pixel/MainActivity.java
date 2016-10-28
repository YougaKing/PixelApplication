package com.youga.pixel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();

        mToolbar.postDelayed(() -> {
            int height = mToolbar.getHeight();
            Log.i(TAG, "height:" + height);
            Log.i(TAG, "height_dp:" + convertPixelsToDp(height, this));

            int width = mToolbar.getWidth();
            Log.i(TAG, "width:" + width);
            Log.i(TAG, "width_dp:" + convertPixelsToDp(width, this));

        }, 2000);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new InnerAdapter());

        float density = getResources().getDisplayMetrics().density;
        int margin = getResources().getDimensionPixelOffset(R.dimen.x34);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Color.WHITE, getResources().getDimensionPixelOffset(R.dimen.x20),
                LinearLayoutManager.HORIZONTAL));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int itemPosition = parent.getChildAdapterPosition(view);
                int itemCount = parent.getAdapter().getItemCount();
                if (itemPosition == 0) {
                    outRect.set(margin, 0, 0, 0);
                } else if (itemPosition == itemCount - 1) {
                    outRect.set(0, 0, margin, 0);
                }
            }
        }, 0);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    class InnerAdapter extends RecyclerView.Adapter<ViewHolder> {

        int[] mImages = {R.drawable.one, R.drawable.two, R.drawable.three};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_recyclerview, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindPosition(mImages[position]);
        }

        @Override
        public int getItemCount() {
            return 3;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView mImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindPosition(int image) {
            mImageView.setImageResource(image);
        }
    }

}
