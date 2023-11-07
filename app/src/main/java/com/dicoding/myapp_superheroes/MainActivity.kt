package com.dicoding.myapp_superheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var superheroes: RecyclerView
    private val list = ArrayList<Superheroes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        superheroes = findViewById(R.id.superheroes)
        superheroes.setHasFixedSize(true)

        list.addAll(getListSuperheroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListSuperheroes(): ArrayList<Superheroes> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataKeterangan = resources.getStringArray(R.array.detail)
        val listSuperheroes = ArrayList<Superheroes>()
        for (i in dataName.indices) {
            val superheroes =
                Superheroes(
                    dataName[i],
                    dataDescription[i],
                    dataPhoto.getResourceId(i, -1),
                    dataKeterangan[i]
                )
            listSuperheroes.add(superheroes)
        }
        return listSuperheroes
    }

    private fun showRecyclerList() {
        superheroes.layoutManager = LinearLayoutManager(this)
        val listSuperheroesAdapter = ListSuperheroesAdapter(list)
        superheroes.adapter = listSuperheroesAdapter

        listSuperheroesAdapter.setOnItemClickCallback(object :
            ListSuperheroesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Superheroes) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra("photo", data.photo)
                detailIntent.putExtra("title", data.name)
                detailIntent.putExtra("description", data.description)
                detailIntent.putExtra("keterangan", data.keterangan)
                startActivity(detailIntent)
            }
        }
        )
    }
}


