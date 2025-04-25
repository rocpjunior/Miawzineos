package com.rocpjunior.miawzineos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rocpjunior.miawzineos.API.MiawAPI
import com.rocpjunior.miawzineos.API.RetrofitService
import com.rocpjunior.miawzineos.adapter.MiawAdapter
import com.rocpjunior.miawzineos.databinding.ActivityMainBinding
import com.rocpjunior.miawzineos.model.Resultado
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import retrofit2.Response




class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private lateinit var miawAdapter: MiawAdapter
    private var job: Job? = null

    private val miawAPI by lazy {
        RetrofitService.miawAPI
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        miawAdapter = MiawAdapter()
        binding.rvMiawzineos.adapter = miawAdapter
        binding.rvMiawzineos.layoutManager = GridLayoutManager(
            this,
            3,
            RecyclerView.VERTICAL,
            false
        )
    }

    override fun onStart() {
        super.onStart()
        recuperarImagensAPI()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    fun recuperarImagensAPI(){

       job = CoroutineScope(Dispatchers.IO).launch {

            var response: Response<Resultado>? =  null

           try {
               response = miawAPI.pesquisarImagensGatos("cats")
           } catch (e: Exception){
               e.printStackTrace()
           }

           if(response != null && response.isSuccessful){

               val resultado = response.body()
               if(resultado != null){

                   val lista = resultado.data
                   val listaUrlImagens = mutableListOf<String>()
                       lista.forEach { dados->
                       val imagem = dados.images[0]
                           val tipo = imagem.type
                           if(tipo == "image/jpeg"){
                               listaUrlImagens.add(imagem.link)
                           }
                   }
                   withContext(Dispatchers.Main){
                       miawAdapter.adicionarLista(listaUrlImagens)
                   }
               }

           }else{
               Log.i("teste_imagens", "Erro ao recuperar as imagens da API D=")
           }
       }
    }
}