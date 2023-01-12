package com.example.nextprojet




import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nextprojet.folder_adapter.PostAdapter
import com.example.nextprojet.folder_data.data.PostData
import com.example.nextprojet.repository.Poste_Repository
import kotlinx.android.synthetic.main.post_act.*



// l'affichage de poste
class LesPostes : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var layoutManager: LinearLayoutManager
    var loading = false
    private val PostAdapter by lazy { PostAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_act)
        // appel intent
        intentEntr



    }

    private val intentEntr: Unit
        private get() {
            if (intent.hasExtra("post_id")) {
                val postId = intent.getStringExtra("post_id")
                //update
                setupRecyclerview()
                layoutManager = LinearLayoutManager(this)
                recycle_poste.layoutManager = layoutManager
                if (postId != null) {
                    before(postId, 0)
                }
            }
        }

    private fun before(postId: String, delay: Long) {
        loading = true
        val repository = Poste_Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.afficherPost(postId)
        viewModel.responsePoste.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Handler().postDelayed({
                    loading = false
                }, delay)
                val post: List<PostData> = listOf(response.body()) as List<PostData>
                PostAdapter.update_poste_data(this,R.layout.post_layout, post)
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    // update de view
    private fun setupRecyclerview() {
        recycle_poste.adapter = PostAdapter
        recycle_poste.layoutManager = LinearLayoutManager(this)
    }
}

