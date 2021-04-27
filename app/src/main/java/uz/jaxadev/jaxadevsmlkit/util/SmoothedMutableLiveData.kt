package uz.jaxadev.jaxadevsmlkit.util

import android.os.Handler
import androidx.lifecycle.MutableLiveData
class SmoothedMutableLiveData<T>(private val duration: Long) : MutableLiveData<T>() {
    private var pendingValue: T? = null
    private val runnable = Runnable {
        super.setValue(pendingValue)
    }

    override fun setValue(value: T) {
        if (value != pendingValue) {
            pendingValue = value
            Handler().removeCallbacks(runnable)
            Handler().postDelayed(runnable, duration)
        }
    }
}
