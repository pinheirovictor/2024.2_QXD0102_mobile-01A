package com.example.crudapp

import androidx.room.* // Importa classes e anotações necessárias para operações com Room
import kotlinx.coroutines.flow.Flow // Importa Flow para lidar com streams de dados assíncronos

// Interface DAO (Data Access Object) para acessar os dados da tabela "user_table"
@Dao
interface UserDao {

    // Insere um usuário no banco de dados. Se houver conflito (mesmo ID), substitui o registro.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long // Retorna o ID do usuário inserido

    // Recupera todos os usuários da tabela como um Flow, permitindo atualizações em tempo real.
    @Query("SELECT * FROM user_table")
    fun getAllUsers(): Flow<List<User>> // Retorna uma lista de usuários como um fluxo reativo

    // Atualiza os dados de um usuário existente no banco.
    @Update
    suspend fun updateUser(user: User): Int // Retorna o número de linhas atualizadas

    // Exclui um usuário específico do banco.
    @Delete
    suspend fun deleteUser(user: User): Int // Retorna o número de linhas excluídas
}

//@Dao: Declara que a interface é um DAO, contendo métodos para interagir com a base de dados.
//@Insert: Insere um novo registro. Usa OnConflictStrategy.REPLACE para substituir dados em caso de conflito.
//@Query: Executa uma consulta SQL. O retorno com Flow permite observar as alterações na tabela.
//@Update: Atualiza os dados de um registro existente.
//@Delete: Remove um registro específico da tabela.



//import androidx.room.*
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface UserDao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: User): Long // Retorna o ID do usuário inserido
//
//    @Query("SELECT * FROM user_table")
//    fun getAllUsers(): Flow<List<User>> // Retorna os usuários como Flow
//
//    @Update
//    suspend fun updateUser(user: User): Int // Retorna o número de linhas atualizadas
//
//    @Delete
//    suspend fun deleteUser(user: User): Int // Retorna o número de linhas excluídas
//}
