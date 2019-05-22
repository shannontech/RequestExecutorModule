package com.mobile.shannon.requestexcutormodule

import android.app.Application
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import java.io.IOException
import java.io.InputStream
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class OkHttpRequestProcessor : IRequestProcessor {

    private lateinit var sHttpClient: OkHttpClient
    private lateinit var sslContext: SSLContext
    private lateinit var trustManagerFactory: TrustManagerFactory
    private var mApplication: Application? = null

    init {
        initHttpsClient(Buffer().writeUtf8(SSL_CERTIFICATE_STRING).inputStream()) //https证书配置
    }

    private fun initHttpsClient(vararg certificates: InputStream) {
        try {
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null)
            for ((index, certificate) in certificates.withIndex()) {
                val certificateAlias = Integer.toString(index)
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate))

                try {
                    certificate.close()
                } catch (e: IOException) {
                }
            }
            sslContext = SSLContext.getInstance("TLS")
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)
            sslContext.init(null, trustManagerFactory.trustManagers, SecureRandom())
            sHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .sslSocketFactory(sslContext.socketFactory, trustManagerFactory.trustManagers[0] as X509TrustManager)
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun setApplication(app: Application) {
        mApplication = app
    }

    override fun getSSLContext(): SSLContext {
        return sslContext
    }

    /**
     * HTTP POST..JSON
     * @param url:请求的url
     * @param json:post所提交的json字符串
     * @param callback：请求返回时的回调
     */
    override fun postRequest(url: String, json: String, callback: RequestCallback) {
        sHttpClient.newCall(
            Request.Builder()
                .url(url)//请求的url
                .addHeader("deviceInfo", DeviceInfoUtil.getPhoneStatus(mApplication))
                .post(FormBody.create(MediaType.parse("application/json; charset=utf-8"), json))//提交json
                .build()
        ).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback.onFailure(e, call)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                callback.onResponse(response, call)
            }
        })
    }

    /**
     * HTTP POST..JSON
     * @param header:添加的请求头
     * @param url:请求的url
     * @param json:post所提交的json字符串
     * @param callback：请求返回时的回调
     */
    override fun postRequest(header: Pair<String, String>, url: String, json: String, callback: RequestCallback) {
        sHttpClient.newCall(
            Request.Builder()
                .url(url)//请求的url
                .addHeader(header.first, header.second)
                .addHeader("deviceInfo", DeviceInfoUtil.getPhoneStatus(mApplication))
                .post(FormBody.create(MediaType.parse("application/json; charset=utf-8"), json))//提交json
                .build()
        ).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback.onFailure(e, call)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                callback.onResponse(response, call)
            }
        })
    }

    override fun getRequest(url: String, header: Pair<String, String>, callback: RequestCallback) {
        sHttpClient.newCall(
            Request.Builder()
                .url(url)//请求的url
                .addHeader(header.first, header.second)
                .addHeader("deviceInfo", DeviceInfoUtil.getPhoneStatus(mApplication))
                .build()
        ).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback.onFailure(e, call)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                callback.onResponse(response, call)
            }
        })
    }
}