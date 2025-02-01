package com.example.crudapp

import android.app.Application
import androidx.lifecycle.* // Importa componentes do Android Jetpack para ViewModel e LiveData
import kotlinx.coroutines.flow.collectLatest // Importa função para coletar os valores mais recentes de Flow
import kotlinx.coroutines.launch // Importa função para executar corrotinas no ViewModel

// ViewModel para gerenciar a lógica da interface de usuário e interagir com o repositório de usuários
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository // Repositório para acessar os dados

    // LiveData para armazenar e observar a lista de usuários
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    // Inicialização do ViewModel
    init {
        val userDao = AppDatabase.getDatabase(application).userDao() // Obtém o DAO do banco de dados
        repository = UserRepository(userDao) // Inicializa o repositório com o DAO
        observeUsers() // Observa as mudanças nos dados
    }

    // Observa mudanças na lista de usuários do repositório e atualiza o LiveData
    private fun observeUsers() {
        viewModelScope.launch {
            repository.getAllUsers().collectLatest { userList ->
                _users.value = userList
            }
        }
    }

    // Adiciona um novo usuário chamando o repositório
    fun addUser(user: User) {
        viewModelScope.launch { repository.insertUser(user) }
    }

    // Atualiza um usuário existente chamando o repositório
    fun updateUser(user: User) {
        viewModelScope.launch { repository.updateUser(user) }
    }

    // Remove um usuário chamando o repositório
    fun deleteUser(user: User) {
        viewModelScope.launch { repository.deleteUser(user) }
    }
}




//AndroidViewModel: Extensão de ViewModel que fornece acesso ao contexto da aplicação, útil para interagir com o banco de dados.
//
//LiveData e MutableLiveData:
//
//MutableLiveData: Usado para armazenar e modificar a lista de usuários dentro do ViewModel.
//LiveData: Permite que a UI observe as mudanças na lista de usuários.
//viewModelScope: Escopo específico para corrotinas no ViewModel, garantindo que corrotinas sejam automaticamente canceladas quando o ViewModel for destruído.
//
//observeUsers: Coleta continuamente as atualizações da lista de usuários emitidas pelo Flow do repositório e atualiza o LiveData.
//
//addUser, updateUser, deleteUser: Métodos que encapsulam as operações de inserção, atualização e exclusão, delegando essas tarefas ao repositório.









//package com.example.crudapp
//
//import android.app.Application
//import androidx.lifecycle.*
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//
//class UserViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository: UserRepository
//
//    private val _users = MutableLiveData<List<User>>()
//    val users: LiveData<List<User>> get() = _users
//
//    init {
//        val userDao = AppDatabase.getDatabase(application).userDao()
//        repository = UserRepository(userDao)
//        observeUsers()
//    }
//
//    private fun observeUsers() {
//        viewModelScope.launch {
//            repository.getAllUsers().collectLatest { userList ->
//                _users.value = userList
//            }
//        }
//    }
//
//    fun addUser(user: User) {
//        viewModelScope.launch { repository.insertUser(user) }
//    }
//
//    fun updateUser(user: User) {
//        viewModelScope.launch { repository.updateUser(user) }
//    }
//
//    fun deleteUser(user: User) {
//        viewModelScope.launch { repository.deleteUser(user) }
//    }
//}
