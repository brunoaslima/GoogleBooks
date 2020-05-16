package br.com.brunolima.googlebooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.brunolima.googlebooks.R
import br.com.brunolima.googlebooks.model.BookHttp
import br.com.brunolima.googlebooks.ui.adapter.BookListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        object: Thread() {
            override fun run() {
                super.run()
                val result = BookHttp.searchBook("Dominando o Android")
                
                runOnUiThread {
                    result?.items?.let {
                        recyclerView.adapter = BookListAdapter(it)
                    }
                }
            }
        }.start()
    }
}
