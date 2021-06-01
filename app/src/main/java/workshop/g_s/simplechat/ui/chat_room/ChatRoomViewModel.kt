package workshop.g_s.simplechat.ui.chat_room

import androidx.lifecycle.ViewModel
import workshop.g_s.simplechat.data.firebase.FirebaseRealTimeDataManager
import workshop.g_s.simplechat.data.models.ChatMessageModel

class ChatRoomViewModel(private val roomId: String) : ViewModel() {
    fun sendMessage(chatMessageModel: ChatMessageModel) {
        firebaseDbManager.sendMessage(chatMessageModel)
    }

    val firebaseDbManager: FirebaseRealTimeDataManager

    init {
        firebaseDbManager = FirebaseRealTimeDataManager(roomId)
    }
}
