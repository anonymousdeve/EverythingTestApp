import android.util.Log
import test.app.core.BuildConfig

fun Any.logW(msg: String) {
    if (BuildConfig.DEBUG) Log.w(this.javaClass.name, msg)
}


fun Any.logE(msg: String) {
    if (BuildConfig.DEBUG) Log.e(this.javaClass.name, msg)
}


fun Any.logI(msg: String) {
    if (BuildConfig.DEBUG) Log.i(this.javaClass.name, msg)
}


fun Any.logV(msg: String) {
    if (BuildConfig.DEBUG) Log.v(this.javaClass.name, msg)
}

