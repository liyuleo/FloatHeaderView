package com.leo.floatheaderview;

import com.leo.floatheaderview.CustomerScrollerView.OnScrollChangerListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by leo on 2015/9/8.
 */
public class HoveringScrollView extends FrameLayout implements OnScrollChangerListener {
	private ViewGroup mContentView;
	private ViewGroup mTopView;
	private int mTopViewTop;
	private View mTopContent;
	private CustomerScrollerView mScrollView;

	public HoveringScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
		setTopView(R.id.top);
	}

	public HoveringScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HoveringScrollView(Context context) {
		this(context, null);
	}

	private void init() {
		post(new Runnable() {
			@Override
			public void run() {
				mContentView = (ViewGroup) getChildAt(0);
				removeAllViews();
				mScrollView = new CustomerScrollerView(getContext());
				mScrollView.setOnScrollChangerListener(HoveringScrollView.this);
				mScrollView.addView(mContentView);
				addView(mScrollView);
			}
		});
	}

	public void setTopView(final int id) {
		post(new Runnable() {
			@Override
			public void run() {
				mTopView = (ViewGroup) mContentView.findViewById(id);
				int height = mTopView.getChildAt(0).getMeasuredHeight();
				ViewGroup.LayoutParams params = mTopView.getLayoutParams();
				params.height = height;
				mTopView.setLayoutParams(params);
				mTopViewTop = mTopView.getTop();
				mTopContent = mTopView.getChildAt(0);
			}
		});
	}

	@Override
	public void onScrollChanged(final int scrollX, final int scrollY, final int oldScrollX, final int oldScrollY) {
		post(new Runnable() {

			@Override
			public void run() {

				if (mTopView == null) {
					return;
				}
				if (scrollY >= mTopViewTop && mTopContent.getParent() == mTopView) {
					mTopView.removeView(mTopContent);
					addView(mTopContent);
				} else if (scrollY < mTopViewTop && mTopContent.getParent() == HoveringScrollView.this) {
					removeView(mTopContent);
					mTopView.addView(mTopContent);
				}

			}
		});
	}
}
