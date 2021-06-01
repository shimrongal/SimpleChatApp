package workshop.g_s.simplechat.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChatMessageModel(val uid:String , val msgText:String , val timeStamp:Long = System.currentTimeMillis()) {

}
