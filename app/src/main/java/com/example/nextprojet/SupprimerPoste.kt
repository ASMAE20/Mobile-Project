package com.example.nextprojet





import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nextprojet.repository.Poste_Repository


// la suppression des postes par long click

class SupprimerPoste : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.supprimer_post)

        Intent
    }
    private val Intent: Unit
        private get() {
            if (intent.hasExtra("post_id") ) {

                val idposte = intent.getStringExtra("post_id")
                val title= intent.getStringExtra("title_poste")
                val textsupprimer: TextView = findViewById<View>(R.id.afficher_text) as TextView
                val errourtext = "le poste de titre $title et d'id  : $idposte est bien supprimé"

                textsupprimer.setText(errourtext)

                if (idposte != null && title!= null) {
                    supprimer(idposte,title)
                }
            }
        }



    private fun supprimer(postId: String,title:String) {
        val repository = Poste_Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.supprimerPoste(postId,title)
    }
}