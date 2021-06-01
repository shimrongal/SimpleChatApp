package workshop.g_s.simplechat.ui.login


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import workshop.g_s.simplechat.R
import workshop.g_s.simplechat.data.utils.AppConstants
import workshop.g_s.simplechat.data.utils.hideKeyboard
import workshop.g_s.simplechat.databinding.LoginActivityLayoutBinding
import workshop.g_s.simplechat.ui.MainActivity


class LoginActivity : AppCompatActivity() {

    lateinit var currentView: LoginActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentView = LoginActivityLayoutBinding.inflate(layoutInflater)
        setContentView(currentView.root)

        currentView.btnSubmitLogin.setOnClickListener {
            currentView.root.hideKeyboard(this)
            when {
                currentView.etUserEmail.text.isNullOrEmpty() -> Snackbar.make(
                    currentView.root, getString(R.string.please_add_your_email_first),
                    Snackbar.LENGTH_LONG
                ).show()
                currentView.etUserPassword.text.isNullOrEmpty() -> Snackbar.make(
                    currentView.root,
                    getString(R.string.please_insert_your_pass),
                    Snackbar.LENGTH_LONG
                ).show()
                else -> {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        currentView.etUserEmail.text.toString(),
                        currentView.etUserPassword.text.toString()
                    ).addOnSuccessListener {
                        goToMainActivity(it)
                    }.addOnFailureListener {
                        Snackbar.make(
                            currentView.root,
                            "Auth Error : ${it.message}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                }
            }
        }
    }

    private fun goToMainActivity(it: AuthResult) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(AppConstants.FIREBASE_USER, it.user)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        /*
              Mock Login
              Handler().postDelayed(Runnable {
                        currentView.etUserEmail.setText("a@gmail.com")
                        currentView.etUserPassword.setText("123456")
                        currentView.btnSubmitLogin.callOnClick()
                    },1000)*/
    }

}