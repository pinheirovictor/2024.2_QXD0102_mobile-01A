package com.example.crudapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Define o banco de dados Room com a entidade User
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // Declaração abstrata do DAO associado ao banco de dados
    abstract fun userDao(): UserDao

    companion object {
        // Instância do banco de dados que pode ser acessada por múltiplas threads
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Obtém a instância do banco de dados, garantindo que seja criada apenas uma vez (singleton)
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Criação da instância do banco de dados com fallback para destruição em caso de migração
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database" // Nome do arquivo de banco de dados
                )
                    .fallbackToDestructiveMigration() // Apaga dados antigos em caso de mudança de versão
                    .build()
                INSTANCE = instance // Armazena a instância no singleton
                instance
            }
        }
    }
}


//@Database: Declara que a classe é um banco de dados Room.
//
//entities = [User::class]: Define as entidades (tabelas) do banco de dados.
//version = 1: Especifica a versão do banco de dados.
//exportSchema = false: Evita exportar o esquema do banco de dados.
//abstract fun userDao(): Método abstrato que retorna o DAO (UserDao) para acessar os dados.
//
//Singleton com companion object:
//
//    @Volatile: Garante visibilidade de mudanças na instância entre threads.
//synchronized: Evita que múltiplas threads criem mais de uma instância ao mesmo tempo.
//fallbackToDestructiveMigration(): Apaga e recria o banco de dados caso haja mudanças de versão, evitando erros de migração.
//
//Essa estrutura assegura que o banco de dados seja criado de forma eficiente e segura em toda a aplicação.





//package com.example.crudapp
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [User::class], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "user_database"
//                ).fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
