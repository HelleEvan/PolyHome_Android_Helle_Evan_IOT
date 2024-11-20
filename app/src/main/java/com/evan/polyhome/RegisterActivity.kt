package com.evan.polyhome

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtp2.Api

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    //bouton lié avec le XML onClick
    public fun login(view: View) {
        finish()
    }

    //fonction liée au bouton "enregistrer" directement sur le XML
    public fun register(view: View) {
        var profil = RegisterData(
            findViewById<EditText>(R.id.registerEmail).text.toString(),
            findViewById<EditText>(R.id.registerPassword).text.toString(),
        )
        Api().post<RegisterData>(
            "https://polyhome.lesmoulinsdudev.com/api/users/register",
            profil,
            ::registerSuccess
        )
    }

    private fun registerSuccess(responseCode: Int) {
        //afficher une boite de dialogue demande de s'executer sur le thread principal
        runOnUiThread {
            if (responseCode == 200) {
                finish();
            } else if (responseCode == 409) {
                AlertDialog.Builder(this)
                    .setTitle("Erreur!")
                    .setMessage("Cet identifiant est déjà utilisé! Veuillez en choisir un autre.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            } else if (responseCode == 500) {
                AlertDialog.Builder(this)
                    .setTitle("Erreur!")
                    .setMessage("Une erreur est survenue côté serveur. Merci de contacter un administrateur.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Erreur!")
                    .setMessage("Erreur inconnue, veuillez réessayer ultérieurement.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}


