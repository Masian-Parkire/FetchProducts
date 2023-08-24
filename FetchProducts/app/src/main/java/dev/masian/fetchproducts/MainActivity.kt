package dev.masian.fetchproducts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}



//class PostActivity : AppCompatActivity() {
//    private lateinit var postViewModel: PostViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_post)
//
//        val api = Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(JsonPlaceholderApi::class.java)
//
//        val repository = PostRepository(api)
//        postViewModel = ViewModelProvider(this, PostViewModelFactory(repository))
//            .get(PostViewModel::class.java)
//
//        postViewModel.posts.observe(this, { posts ->
//            // Update UI with the list of posts
//        })
//    }
//}
