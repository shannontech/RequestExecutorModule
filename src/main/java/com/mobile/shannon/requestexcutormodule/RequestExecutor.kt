package com.mobile.shannon.requestexcutormodule

import android.app.Application
import javax.net.ssl.SSLContext

/**
 * 网络请求 代理
 */
object RequestExecutor {

    //代理模式，便于日后切换别的网络请求框架
    private lateinit var mProcessor: IRequestProcessor

    fun setProcessor(processor: IRequestProcessor, app: Application) {
        mProcessor = processor
        mProcessor.setApplication(app)
    }

    fun getSSLContext(): SSLContext {
        return mProcessor.getSSLContext()
    }

    /**
     * HTTP POST..JSON
     * @param url:请求的url
     * @param json:post所提交的json字符串
     * @param callback：请求返回时的回调
     */
    fun postRequest(url: String, json: String, callback: RequestCallback) {
        mProcessor.postRequest(url, json, callback)
    }

    /**
     * HTTP POST..JSON
     * @param header:添加的请求头
     * @param url:请求的url
     * @param json:post所提交的json字符串
     * @param callback：请求返回时的回调
     */
    fun postRequest(header: Pair<String, String>, url: String, json: String, callback: RequestCallback) {
        mProcessor.postRequest(header, url, json, callback)
    }

    fun getRequest(url: String, header: Pair<String, String>, callback: RequestCallback) {
        mProcessor.getRequest(url, header, callback)
    }
}