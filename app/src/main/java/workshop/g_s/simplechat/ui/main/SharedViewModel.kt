package workshop.g_s.simplechat.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class SharedViewModel : ViewModel() {


    lateinit var currentRoomId: String
    lateinit var currentUser: FirebaseUser


}
