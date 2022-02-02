package com.example.koinz

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * extension fun to  handle livedata from room database
 * */

fun <T> LiveData<T>.getOrAwaitValue(): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            this@getOrAwaitValue.removeObserver(this)
            latch.countDown()
        }

    }
    this.observeForever(observer)
    try {
        if (!latch.await(10, TimeUnit.SECONDS)) {
            throw TimeoutException("Live data won't get the values")
        }

    } finally {
        this.removeObserver(observer)

    }

    return data as T
}