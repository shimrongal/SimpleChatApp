package workshop.g_s.simplechat.ui.chat_room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatRoomViewModelFactory(private val currentRoomId: String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(String::class.java).newInstance(currentRoomId)
        } catch (e: InstantiationException) {
            throw RuntimeException("ChatRoomViewModelFactory InstantiationException: ${e}")
        } catch (e: IllegalAccessException) {
            throw RuntimeException("ChatRoomViewModelFactory IllegalAccessException: ${e}")
        }
    }

}
