package workshop.g_s.simplechat.ui.chat_room

import androidx.recyclerview.widget.RecyclerView
import workshop.g_s.simplechat.data.models.ChatMessageModel
import workshop.g_s.simplechat.data.utils.convertMillisToDateFormat
import workshop.g_s.simplechat.databinding.OutgoingMessageLayoutBinding

class OutgoingMessageViewHolder(private val layoutBinding: OutgoingMessageLayoutBinding) :
    RecyclerView.ViewHolder(layoutBinding.root) {

        fun bindViewHolder(messageItem:ChatMessageModel){
            layoutBinding.tvOutgoingMessageText.text = messageItem.msgText
            layoutBinding.tvOutgoingMessageDate.text = convertMillisToDateFormat(messageItem.timeStamp)
        }

}
