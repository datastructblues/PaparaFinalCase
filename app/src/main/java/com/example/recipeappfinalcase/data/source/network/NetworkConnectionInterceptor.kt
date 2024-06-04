package com.example.recipeappfinalcase.data.source.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(private val mContext: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        if (!isOnline(mContext)) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isOnline(context: Context?): Boolean {
        val conMgr: ConnectivityManager? =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = conMgr?.getNetworkCapabilities(conMgr.activeNetwork)
            capabilities?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        } else {
            //NetworkInfo is deprecated in API 29
            val netInfo: NetworkInfo? = conMgr?.activeNetworkInfo
            netInfo != null && netInfo.isConnected
        }
    }
}