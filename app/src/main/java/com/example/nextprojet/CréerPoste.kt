package com.example.nextprojet





import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nextprojet.repository.Poste_Repository
import com.google.android.material.snackbar.Snackbar


// la création de poste
class CréerPoste : AppCompatActivity() {
    // initilisation des zones de poste
    private lateinit var viewModel: MainViewModel
    private lateinit var sbumitButton: Button
    private lateinit var imageUrl: EditText
    private lateinit var tag: EditText
    private lateinit var like: EditText
    private lateinit var owner: EditText
    private lateinit var text: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creer_poste)

        //les données de poste
        sbumitButton = findViewById(R.id.submitButton) as Button
        text = findViewById(R.id.text) as EditText
        tag = findViewById(R.id.tags) as EditText
        owner = findViewById(R.id.owner) as EditText
        imageUrl = findViewById(R.id.imageUrl) as EditText
        like = findViewById(R.id.likes) as EditText


        sbumitButton.setOnClickListener {
            //appel à la fct entrer data
            entrer_data()

                sbumitButton.animate().apply{
                    duration=1000
                    rotationXBy(360f)

                }.start()


            val snack = Snackbar.make(it,"This is a simple Creat Poste", Snackbar.LENGTH_SHORT)
            snack.show()


        }
    }

    private fun entrer_data() {
        val image = imageUrl.text.toString()
        val likes = like.text.toString().toInt()
        val tags = tag.text.toString().split(", ") as ArrayList<String>
        val owner = owner.text.toString()
        val text = text.text.toString()



        val repository = Poste_Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.creerPoste(text, image, likes, tags, owner)
        val intent = Intent(this, PosteCréer::class.java)
        this.startActivity(intent)
    }

}