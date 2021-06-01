package workshop.g_s.simplechat.ui.chat_room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import workshop.g_s.simplechat.data.models.ChatMessageModel
import workshop.g_s.simplechat.databinding.IncomingMessageLayoutBinding
import workshop.g_s.simplechat.databinding.OutgoingMessageLayoutBinding

class ChatRoomMessagesListAdapter(private val uid: String) :
    ListAdapter<ChatMessageModel, RecyclerView.ViewHolder>(ChatMessageComparator()) {

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].uid == uid) 1 else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val currentInflater = LayoutInflater.from(parent.context)

        if (viewType == 1) {
            val inflate = OutgoingMessageLayoutBinding.inflate(
                currentInflater,
                parent,
                false
            )
            return OutgoingMessageViewHolder(inflate)
        } else {
            val inflate = IncomingMessageLayoutBinding.inflate(currentInflater, parent, false)
            return IncomingMessageViewHolder(inflate)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMessageModel = currentList[position]
        if (chatMessageModel.uid == uid) {
            (holder as OutgoingMessageViewHolder).bindViewHolder(chatMessageModel)
        } else {
            (holder as IncomingMessageViewHolder).bindViewHolder(chatMessageModel)
        }
    }

}
