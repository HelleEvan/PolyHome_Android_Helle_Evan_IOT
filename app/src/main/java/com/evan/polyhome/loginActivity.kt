package com.evan.polyhome

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidtp2.Api

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //bouton lié avec le XML onClick
    public fun registerNewAccount(view: View)
    {
        val intentToRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentToRegister)
    }

    public fun login(view: View) {
        var connectId = LoginData(
            findViewById<EditText>(R.id.loginMail).text.toString(),
            findViewById<EditText>(R.id.loginPassword).text.toString()
        )

        Api().post<LoginData, String>(
            "https://polyhome.lesmoulinsdudev.com/api/users/auth",
            connectId,
            ::loginSuccess
        )
    }

    private fun loginSuccess(responseCode: Int, token:String?){
        runOnUiThread {
            //response code n'est pas egale à 200, Erreur ICI ! 
            if(responseCode == 200) {
//                    Création de l'Intent explicite
//                val intentToOrdersActivity = Intent(
//                    this, //Contexte de départ
//                    OrdersActivity::class.java //Activité cible indiquée explicitement
//                );
//                //Transmission de data
//                intentToOrdersActivity.putExtra("token",token );
//
//                // Démarrage de la nouvelle activité
//                startActivity(intentToOrdersActivity);
                    AlertDialog.Builder(this)
                        .setTitle("Bravo ! !")
                        .setMessage("Tu es bien connecté.")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
            }
            else{
                AlertDialog.Builder(this)
                    .setTitle("Erreur!")
                    .setMessage("ceci est une erreur.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}