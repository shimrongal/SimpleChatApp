package workshop.g_s.simplechat.data.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import workshop.g_s.simplechat.data.models.ChatMessageModel
import workshop.g_s.simplechat.data.utils.AppConstants

class FirebaseRealTimeDataManager(private val roomId: String) : LiveData<ChatMessageModel>() {
    val firebaseMessagesManager: DatabaseReference =
        Firebase.database.getReference(AppConstants.SIMPLE_CHAT_MESSAGES).child(roomId)


    override fun onActive() {
        super.onActive()
        firebaseMessagesManager.addChildEventListener(messagesListener)
    }

    override fun onInactive() {
        super.onInactive()
        firebaseMessagesManager.removeEventListener(messagesListener)

    }

    fun sendMessage(chatMessageModel: ChatMessageModel) {
        firebaseMessagesManager.push().setValue(chatMessageModel)
    }

    private val messagesListener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val hashMapMessage = snapshot.getValue() as HashMap<String, Any>
            value = ChatMessageModel(
                hashMapMessage.get("uid") as String,
                hashMapMessage.get("msgText") as String, hashMapMessage.get("timeStamp") as Long
            )
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }

}
