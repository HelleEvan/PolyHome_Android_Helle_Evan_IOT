package com.evan.polyhome

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }

    //bouton lié avec le XML onClick
    public fun login(view : View)
    {
        finish()
    }

    //enregistrer un compte quand le bouton est appuyé
    //bouton lié avec onClick XML
    private fun register(){
            var profil = RegisterData(
                findViewById<EditText>(R.id.registerMail).text.toString(),
                findViewById<EditText>(R.id.registerPassword).text.toString()
            )
            //Api().post<RegisterData>("https://mypizza.lesmoulinsdudev.com/register", profil ,::registerSuccess)

    }

}