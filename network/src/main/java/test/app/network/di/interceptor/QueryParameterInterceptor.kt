package test.app.network.di.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import test.app.network.BuildConfig
import test.app.network.di.API_KEY
import javax.inject.Inject



/**
 * Custom Interceptor for adding query parameters to the HTTP request.
 *
 * This class implements the Interceptor interface.
 *
 * @see Interceptor
 */

class QueryParameterInterceptor : Interceptor {

    /**
     * Intercepts the HTTP request and adds query parameters.
     *
     * @param chain The interceptor chain.
     * @return The intercepted response.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(API_KEY, BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}