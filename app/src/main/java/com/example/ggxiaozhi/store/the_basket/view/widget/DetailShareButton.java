package com.example.ggxiaozhi.store.the_basket.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * 详情页底部分享Button
 */
public class DetailShareButton extends ImageView implements View.OnClickListener {
    private String appId;
    private String icon;
    private boolean isH5App = false;
    private String packageName;
    private String shareUrl;
    private String title;
    private String version;

    public DetailShareButton() {
        this(null);
    }

    public DetailShareButton(Context paramContext) {
        this(paramContext, null);
    }

    public DetailShareButton(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public DetailShareButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public String getAppId() {
        return this.appId;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isH5App() {
        return this.isH5App;
    }

    public void onClick(View paramView) {
        share();
    }

    public void setAppId(String paramString) {
        this.appId = paramString;
    }

    public void setH5App(boolean paramBoolean) {
        this.isH5App = paramBoolean;
    }

    public void setIcon(String paramString) {
        this.icon = paramString;
    }

    public void setPackageName(String paramString) {
        this.packageName = paramString;
    }

    public void setShareUrl(String paramString) {
        this.shareUrl = paramString;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }

    public void setVersion(String paramString) {
        this.version = paramString;
    }

    public void share() {

    }
}