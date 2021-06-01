package workshop.g_s.simplechat.ui.chat_room

import androidx.recyclerview.widget.DiffUtil
import workshop.g_s.simplechat.data.models.ChatMessageModel

class ChatMessageComparator : DiffUtil.ItemCallback<ChatMessageModel>() {
    override fun areItemsTheSame(oldItem: ChatMessageModel, newItem: ChatMessageModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChatMessageModel, newItem: ChatMessageModel): Boolean {
        return oldItem.uid == newItem.uid && oldItem.msgText == newItem.msgText && oldItem.timeStamp == newItem.timeStamp
    }

}
