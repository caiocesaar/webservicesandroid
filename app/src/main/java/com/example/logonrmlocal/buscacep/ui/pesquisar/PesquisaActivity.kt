package com.example.logonrmlocal.buscacep.ui.pesquisar

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.logonrmlocal.buscacep.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pesquisa.*



class PesquisaActivity : AppCompatActivity() {



    lateinit var pesquisaViewModel: PesquisaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)
        Picasso.get().load("https://static.thenounproject.com/png/1565357-200.png").into(ivLogo)

        pesquisaViewModel = ViewModelProviders.of(this).get(PesquisaViewModel::class.java)

        btPesquisar.setOnClickListener {
            pesquisaViewModel.buscar(etCEP.text.toString())
        }

        pesquisaViewModel.endereco.observe(this, Observer {
            tvResultado.text = it?.logradouro
        })

        pesquisaViewModel.mensagemErro.observe(this, Observer {
            if (!it.equals("")){
                Toast.makeText(this,it,Toast.LENGTH_LONG)
            }
        })

        pesquisaViewModel.isLoading.observe(this, Observer {
            isLoading -> if (isLoading == true) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }
        })

    }

}
