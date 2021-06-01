package workshop.g_s.simplechat.data.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat

/**
 * Created by Gal Shimron on 01/06/2021.
 */

fun View.hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun convertMillisToDateFormat(dateInMillis: Long): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    val dateString: String = simpleDateFormat.format(dateInMillis)
    return String.format("Date: %s", dateString)
}
