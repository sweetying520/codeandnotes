package com.dream.coroutinedemo.common

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/3/14
 */
object SSL {
    fun sslSocketFactory(): SSLSocketFactory? {
        try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, getTrustManager(), SecureRandom())
            return sslContext.socketFactory
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun trustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(
                x509Certificates: Array<X509Certificate>,
                message: String
            ) {
            }

            override fun checkServerTrusted(
                x509Certificates: Array<X509Certificate>,
                message: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }


    private fun getTrustManager(): Array<TrustManager>{
        return arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(
                x509Certificates: Array<X509Certificate>,
                message: String
            ) {
            }

            override fun checkServerTrusted(
                x509Certificates: Array<X509Certificate>,
                message: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
    }



    fun hostnameVerifier(): HostnameVerifier {
        return HostnameVerifier { _: String?, _: SSLSession? -> true }
    }
}