package workshop.g_s.simplechat.ui.chat_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import workshop.g_s.simplechat.R
import workshop.g_s.simplechat.data.models.ChatMessageModel
import workshop.g_s.simplechat.data.utils.hideKeyboard
import workshop.g_s.simplechat.databinding.ChatRoomLayoutBinding
import workshop.g_s.simplechat.ui.main.SharedViewModel


class ChatRoomFragment : Fragment() {

    private var _binding: ChatRoomLayoutBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()

    private val chatRoomViewModel: ChatRoomViewModel by viewModels<ChatRoomViewModel> {
        ChatRoomViewModelFactory(sharedViewModel.currentRoomId)
    }

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ChatRoomLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rcvMessageList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ChatRoomMessagesListAdapter(sharedViewModel.currentUser.uid)
        }


        binding.btnSendMessage.setOnClickListener {
            binding.root.hideKeyboard(requireContext())
            if (binding.etMessageText.text.isNullOrEmpty()) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.insert_message),
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                binding.etMessageText.clearComposingText()
                chatRoomViewModel.sendMessage(
                    ChatMessageModel(
                        sharedViewModel.currentUser.uid,
                        binding.etMessageText.text.toString()
                    )
                )
            }

        }
        chatRoomViewModel.firebaseDbManager.observe(viewLifecycleOwner, observerRoomMessages)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val observerRoomMessages = Observer<ChatMessageModel> {
        val mutableListOf = mutableListOf<ChatMessageModel>()
        val chatRoomMessagesListAdapter =
            binding.rcvMessageList.adapter as ChatRoomMessagesListAdapter
        if (chatRoomMessagesListAdapter.currentList.isEmpty()) {
            mutableListOf.add(it)
            chatRoomMessagesListAdapter.submitList(mutableListOf)
        } else {
            mutableListOf.addAll(chatRoomMessagesListAdapter.currentList)
            mutableListOf.add(it)
            chatRoomMessagesListAdapter.submitList(mutableListOf)
        }
    }
}