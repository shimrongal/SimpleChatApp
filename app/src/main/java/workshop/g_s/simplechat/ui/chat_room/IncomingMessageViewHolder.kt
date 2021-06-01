package workshop.g_s.simplechat.ui.chat_room

import androidx.recyclerview.widget.RecyclerView
import workshop.g_s.simplechat.data.models.ChatMessageModel
import workshop.g_s.simplechat.data.utils.convertMillisToDateFormat
import workshop.g_s.simplechat.databinding.IncomingMessageLayoutBinding

class IncomingMessageViewHolder(private val layoutBinding: IncomingMessageLayoutBinding) :
    RecyclerView.ViewHolder(
        layoutBinding.root
    ) {

    fun bindViewHolder(chatMessageModel: ChatMessageModel) {
        layoutBinding.tvIncomingMessageText.text = chatMessageModel.msgText
        layoutBinding.tvIncomingMessageDate.text =
            convertMillisToDateFormat(chatMessageModel.timeStamp)
    }

}
