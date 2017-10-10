package com.example.ggxiaozhi.store.the_basket.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Xfermode;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ggxiaozhi.store.the_basket.R;

/**
 * 详情页底部下载进度自定义Button
 */
public class DownloadProgressButton extends ProgressBar implements View.OnClickListener {
    public static final int STATUS_PROGRESS_BAR_BEGIN = 0;//开始下载
    public static final int STATUS_PROGRESS_BAR_DOWNLOADING = 1;//下载之中
    public static final int STATUS_PROGRESS_BAR_PAUSE = 2;//暂停下载
    public static final int STATUS_PROGRESS_BAR_FINISH = 3;//下载完成
    private int mCurrentState = STATUS_PROGRESS_BAR_BEGIN;//当前下载状态


    private Paint mPaint;//画笔
    private Path mRoundRectPath;//View本身圆角路径
    private Path mProgressPath;//View中的进度路径
    private int mValidWidth;//获取View本身的有效绘制宽度，padding不绘制
    private int mValidHeight;//获取View本身的有效绘制高度，padding不绘制
    private StateChangeListener mStateChangeListener;

    private int mNormalBacgroundColor;
    private int mNormalTextColor;
    private int mLoadingTextColor;
    private int mLoadingBorderColor;
    private int mProgressBarColor;
    private int mInstallColor;
    private float mTextSize;

    private boolean isFinish = false;

    public interface StateChangeListener {
        void onPauseTask();

        void onFinishTask();

        void onLoadingTask();
    }

    public void setStateChangeListener(StateChangeListener mStateChangeListener) {
        this.mStateChangeListener = mStateChangeListener;
    }

    public DownloadProgressButton(Context context) {
        this(context, null);
    }

    public DownloadProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownloadProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        setOnClickListener(this);
    }


    /* 获取用户自定义设置的属性值*/
    private void obtainStyledAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DownloadProgressButton);
        mNormalBacgroundColor = ta.getColor(R.styleable.DownloadProgressButton_normal_background_color, context.getResources().getColor(R.color.blue_text_007dff));
        mNormalTextColor = ta.getColor(R.styleable.DownloadProgressButton_normal_text_color, context.getResources().getColor(R.color.reserve_normal_textcolor));
        mLoadingTextColor = ta.getColor(R.styleable.DownloadProgressButton_loading_text_color, context.getResources().getColor(R.color.detail_dowanload_progressbar_color));
        mLoadingBorderColor = ta.getColor(R.styleable.DownloadProgressButton_loading_border_color, context.getResources().getColor(R.color.category_button_select_stroke));
        mProgressBarColor = ta.getColor(R.styleable.DownloadProgressButton_loading_progress_color, context.getResources().getColor(R.color.detail_downloadbutton_processing));
        mInstallColor = ta.getColor(R.styleable.DownloadProgressButton_install_color, context.getResources().getColor(R.color.detail_install_color));
        mTextSize = ta.getDimension(R.styleable.DownloadProgressButton_download_textSize, context.getResources().getDimension(R.dimen.cs_text_size_13sp));
        ta.recycle();
    }


    @Override/*布局发生变换时调用*/
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mValidWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        mValidHeight = getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        switch (mCurrentState) {
            case STATUS_PROGRESS_BAR_DOWNLOADING://下载状态
                drawProgressOnDownload(canvas);
                break;
            case STATUS_PROGRESS_BAR_PAUSE://暂停状态
                drawProgressOnPause(canvas);
                break;
            case STATUS_PROGRESS_BAR_FINISH://完成状态
                System.out.println("STATUS_PROGRESS_BAR_FINISH.............");
                drawProgressOnFinished(canvas);
                break;
            case STATUS_PROGRESS_BAR_BEGIN://默认状态
                drawProgressOnStart(canvas);
                break;
            default:
                break;
        }
    }

    //下载的时候
    private void drawProgressOnDownload(Canvas canvas) {
        drawProgressRectBackground(canvas, mLoadingBorderColor);
        drawProgressRectWithClip(canvas);
        int progress = (int) (100 * (getProgress() * 1.0f / getMax()));
        String progressValue = TextUtils.concat(String.valueOf(progress), "%").toString();
        drawProgressText(canvas, progressValue);
        if (getProgress() == getMax()) {

            System.out.println("drawProgressOnDownload.100.......");
            mCurrentState = STATUS_PROGRESS_BAR_FINISH;
            postInvalidateDelayed(40);
        }
    }

    //暂停的时候
    private void drawProgressOnPause(Canvas canvas) {
        drawProgressRectBackground(canvas, mLoadingBorderColor);
        drawProgressRectWithClip(canvas);
        drawProgressText(canvas, "继续");
    }

    //下载完成的时候
    private void drawProgressOnFinished(Canvas canvas) {
        drawProgressRectBackground(canvas, mLoadingBorderColor);
        drawFinishText(canvas, "下载完成");
        if (mStateChangeListener != null && !isFinish) {
            mStateChangeListener.onFinishTask();
            isFinish = true;
        }
        setEnabled(false);
    }

    //还没有开始下载的时候
    private void drawProgressOnStart(Canvas canvas) {
        drawProgressRectBackground(canvas, mLoadingBorderColor);

        drawStartBackground(canvas, mNormalBacgroundColor);
        drawStartText(canvas, startText);
    }

    private String startText = "下载";

    public void setStartText(String text) {
        this.startText = text;
        postInvalidate();
    }


    //绘制一个圆角矩形路径
    private void drawRoundRectPath() {
        if (mRoundRectPath == null) {
            mRoundRectPath = new Path();
        } else {
            mRoundRectPath.reset();
        }
        mRoundRectPath.moveTo(mValidHeight / 2, mValidHeight);
        mRoundRectPath.arcTo(new RectF(0, 0, mValidHeight, mValidHeight), 90, 180);//画弧度
        mRoundRectPath.lineTo(mValidWidth - mValidHeight / 2, 0);
        mRoundRectPath.arcTo(new RectF(mValidWidth - mValidHeight, 0, mValidWidth, mValidHeight), -90, 180);
        mRoundRectPath.lineTo(mValidWidth - mValidHeight / 2, mValidHeight);
        mRoundRectPath.lineTo(mValidHeight / 2, mValidHeight);
        mRoundRectPath.close();
    }

    //绘制一个进度路径
    private void drawProgressPath(int progress) {
        if (mProgressPath == null) {
            mProgressPath = new Path();
        } else {
            mProgressPath.reset();
        }
        RectF rectF = new RectF(0, 0, progress, mValidHeight);
        mProgressPath.addRect(rectF, Path.Direction.CCW);
    }


    //  绘制矩形的下载进度(这里使用的是裁剪的方法)
    private void drawProgressRectWithClip(Canvas canvas) {
        mPaint.setColor(mProgressBarColor);
        mPaint.setStyle(Paint.Style.FILL);
        //根据进度比率计算出当前的进度值对应的宽度
        int progress = (int) (mValidWidth * (getProgress() * 1.0f / getMax()));
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        //裁剪圆角矩形路径
        drawRoundRectPath();
        canvas.clipPath(mRoundRectPath);//裁剪之后此时画布就变成了裁剪之后的圆角矩形
        //裁剪进度路径
        drawProgressPath(progress);
        canvas.clipPath(mProgressPath, Region.Op.INTERSECT);
        canvas.drawColor(mProgressBarColor);
        canvas.restore();
    }

    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    //绘制矩形的下载进度(也可以使用的是PorterDuffXfremode的方法)
    private void drawProgressRectWithXfermode(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        //根据进度比率计算出当前的进度值对应的宽度
        int progress = (int) (mValidWidth * (getProgress() * 1.0f / getMax()));
        int layer = canvas.saveLayer(0, 0, progress, getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(getPaddingLeft(), getPaddingTop());
        drawRoundRectPath();
        mPaint.setColor(mProgressBarColor);
        canvas.drawPath(mRoundRectPath, mPaint);
        drawProgressPath(progress);
        mPaint.setXfermode(mXfermode);
        canvas.drawPath(mProgressPath, mPaint);
        canvas.restoreToCount(layer);
        mPaint.setXfermode(null);

    }


    //  绘制背景矩形
    private void drawProgressRectBackground(Canvas canvas, int paintColor) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(paintColor);
        drawRoundRectPath();
        canvas.drawPath(mRoundRectPath, mPaint);
        canvas.restore();
    }

    private void drawStartBackground(Canvas canvas, int paintColor) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(paintColor);
        drawRoundRectPath();
        canvas.drawPath(mRoundRectPath, mPaint);
        canvas.restore();
    }

    //  绘制进度中的文字
    private void drawProgressText(Canvas canvas, String text) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mPaint.setColor(mLoadingTextColor);
        int textWidth = (int) mPaint.measureText(text);
        int textHeight = (int) (mPaint.descent() + mPaint.ascent());
        canvas.drawText(text, mValidWidth / 2 - textWidth / 2, mValidHeight / 2 - textHeight / 2, mPaint);
        canvas.restore();
    }

    private void drawStartText(Canvas canvas, String text) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mPaint.setColor(mNormalTextColor);
        int textWidth = (int) mPaint.measureText(text);
        int textHeight = (int) (mPaint.descent() + mPaint.ascent());//mPaint.descent()当前绘制顶线 mPaint.ascent()当前绘制底线
        canvas.drawText(text, mValidWidth / 2 - textWidth / 2, mValidHeight / 2 - textHeight / 2, mPaint);
        canvas.restore();
    }

    private void drawFinishText(Canvas canvas, String text) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getPaddingTop());
        mPaint.setColor(mInstallColor);
        int textWidth = (int) mPaint.measureText(text);
        int textHeight = (int) (mPaint.descent() + mPaint.ascent());
        canvas.drawText(text, mValidWidth / 2 - textWidth / 2, mValidHeight / 2 - textHeight / 2, mPaint);
        canvas.restore();
    }

    @Override
    public final void onClick(View v) {
        int progress = getProgress();
        int max = getMax();
        if (progress == 0 && mCurrentState == STATUS_PROGRESS_BAR_BEGIN) {//未开始下载的时候
            mCurrentState = STATUS_PROGRESS_BAR_DOWNLOADING;
            if (mStateChangeListener != null) {
                mStateChangeListener.onLoadingTask();
            }
        } else if (progress >= 0 && progress < max && mCurrentState == STATUS_PROGRESS_BAR_DOWNLOADING) {//下载中
            mCurrentState = STATUS_PROGRESS_BAR_PAUSE;
            if (mStateChangeListener != null) {
                mStateChangeListener.onPauseTask();
            }
        } else if (progress >= 0 && progress < max && mCurrentState == STATUS_PROGRESS_BAR_PAUSE) {//暂停下载
            mCurrentState = STATUS_PROGRESS_BAR_DOWNLOADING;
            if (mStateChangeListener != null) {
                mStateChangeListener.onLoadingTask();
            }
        } else if (progress == max) {//完成下载
            mCurrentState = STATUS_PROGRESS_BAR_FINISH;
            if (mStateChangeListener != null) {
                mStateChangeListener.onFinishTask();
            }
        }
        postInvalidateDelayed(40);
    }

    public final void setState(int state) {
        mCurrentState = state;
        postInvalidateDelayed(10);
    }

    public final int getState() {

        postInvalidateDelayed(10);
        return mCurrentState;
    }
}