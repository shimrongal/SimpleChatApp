package workshop.g_s.simplechat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import workshop.g_s.simplechat.R
import workshop.g_s.simplechat.data.utils.hideKeyboard
import workshop.g_s.simplechat.databinding.ChatRoomSelectLayoutBinding
import workshop.g_s.simplechat.ui.main.SharedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ChatRoomSelectFragment : Fragment() {

    private var currentView: ChatRoomSelectLayoutBinding? = null

    val sharedViewModel: SharedViewModel by activityViewModels<SharedViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        currentView = ChatRoomSelectLayoutBinding.inflate(inflater, container, false)

        return currentView!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentView!!.btnSubmitRoom.setOnClickListener {
            if (currentView!!.etSetRoomNumber.text.isNullOrEmpty()) {
                currentView!!.root.hideKeyboard(requireContext())
                Snackbar.make(
                    currentView!!.root,
                    getString(R.string.enter_room_number_first),
                    Snackbar.LENGTH_LONG
                ).show()
            }
            sharedViewModel.currentRoomId = currentView!!.etSetRoomNumber.text.toString()
            findNavController().navigate(R.id.action_ChatRoomSelect_to_ChatRoom)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentView = null
    }
}