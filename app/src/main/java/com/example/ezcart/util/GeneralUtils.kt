package com.example.ezcart.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import java.text.SimpleDateFormat
import java.util.*

fun showToast(context: Context, message: String) {

    val toast = Toast.makeText(context,message, Toast.LENGTH_SHORT)
    toast.show()

}


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}

fun clearStack(fragmentManager: FragmentManager) {
    val backStackEntry: Int = fragmentManager.backStackEntryCount
    if (backStackEntry > 0) {
        for (i in 0 until backStackEntry) {
            fragmentManager.popBackStackImmediate()
        }
    }
}

fun getDate(): String {
    val dateFormat = SimpleDateFormat(" MMM dd", Locale.US)
    val date = Date()
    return dateFormat.format(date)
}

fun getDateForOrder(): String {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    val date = Date()
    return dateFormat.format(date)
}

fun getDisplayDateFormat(date: String): String{
    var spf = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    val newDate = spf.parse(date)
    spf = SimpleDateFormat("dd MMM yyyy", Locale.US)
    return if(newDate != null) spf.format(newDate) else date
}









