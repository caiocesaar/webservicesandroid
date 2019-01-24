package com.example.logonrmlocal.buscacep.repository

import com.example.logonrmlocal.buscacep.api.getEnderecoService
import com.example.logonrmlocal.buscacep.model.Endereco
import retrofit2.Call
import retrofit2.Response

class EnderecoRepository {

    fun buscar(
            cep: String,
            onComplete: (Endereco?) -> Unit,
            onError: (Throwable?) -> Unit) {

        getEnderecoService()
                .buscar(cep)
                .enqueue(object : retrofit2.Callback<Endereco> {

                    override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                        onError(t)
                    }

                    override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {

                        if (response?.isSuccessful == true){
                            onComplete(response?.body())
                        } else {
                            onError(Throwable(response?.errorBody()?.string()))
                        }
                    }
                })
    }
}
