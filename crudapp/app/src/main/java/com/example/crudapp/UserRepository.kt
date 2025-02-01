package com.example.crudapp

import kotlinx.coroutines.flow.Flow // Importa Flow para operações assíncronas e reativas

// Repositório para gerenciar operações relacionadas a usuários usando o DAO
class UserRepository(private val userDao: UserDao) {

    // Insere um usuário chamando o método do DAO
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    // Recupera todos os usuários chamando o método do DAO, retorna como Flow
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    // Atualiza um usuário existente chamando o método do DAO
    suspend fun updateUser(user: User) = userDao.updateUser(user)

    // Remove um usuário chamando o método do DAO
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
}


//UserRepository: Serve como uma camada intermediária entre o UserDao e a camada de interface (ViewModel ou UI).
//insertUser: Chama o método insertUser do DAO para inserir um novo usuário.
//getAllUsers: Retorna todos os usuários como um Flow, permitindo atualizações em tempo real na interface do usuário.
//updateUser: Chama o método updateUser do DAO para atualizar as informações de um usuário.
//deleteUser: Chama o método deleteUser do DAO para remover um usuário do banco de dados.