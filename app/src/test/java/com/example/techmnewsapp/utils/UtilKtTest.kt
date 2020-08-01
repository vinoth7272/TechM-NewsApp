package com.example.techmnewsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UtilKtTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var imageView: ImageView
    @Mock
    lateinit var textView: TextView

    @Mock
    lateinit var connectivityManager: ConnectivityManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    /**
     * This method helps to test the network connection status
     */
    @Test
    fun isConnectionTestTrue() {
        doReturn(connectivityManager).`when`(context)
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        context.isNetworkConnected()
        assertEquals(true, context.isNetworkConnected())
    }

    /**
     * method to test the image view visibility
     * if URL is empty or null then Image View Gone else ImageView Visible
     *
     */
    @Test
    fun bindImageUrlTest(){
        imageView.bindImageUrl(null)
        assertEquals(imageView.visibility, View.GONE)

        imageView.bindImageUrl("TEST")
        assertEquals(imageView.visibility, View.VISIBLE)
    }

    /**
     * method to test the TextView visibility
     * if string is empty or null then TextView Gone else TextView Visible
     *
     */
    @Test
    fun bindTextVisibilityTest(){
        textView.bindText(null)
        assertEquals(textView.visibility, View.GONE)

        textView.bindText("TEST")
        assertEquals(textView.visibility, View.VISIBLE)
    }
}