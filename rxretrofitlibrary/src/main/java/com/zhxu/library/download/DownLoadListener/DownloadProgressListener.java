package com.zhxu.library.download.DownLoadListener;


/**
 * 进度回调处理
 */
public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param read
     * @param count
     * @param done
     */
    void update(long read, long count, boolean done);
}
