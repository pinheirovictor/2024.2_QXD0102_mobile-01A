package br.ufc.quixada.authaula.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.ufc.quixada.authaula.data.AuthRepository

/**
 * Classe responsável por criar instâncias da ViewModel com um repositório injetado.
 * Essa abordagem permite a injeção de dependências, tornando a ViewModel testável.
 */
class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {

    /**
     * Método para criar e fornecer instâncias da ViewModel.
     * Verifica se a classe solicitada é `AuthViewModel` e a instancia com o repositório.
     *
     * @param modelClass Classe da ViewModel que será instanciada
     * @return Retorna uma instância de `AuthViewModel`
     * @throws IllegalArgumentException Se a classe fornecida não for `AuthViewModel`
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class") // Exceção se a classe não for reconhecida
    }
}









//package br.ufc.quixada.authaula.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import br.ufc.quixada.authaula.data.AuthRepository
//
//class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return AuthViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
