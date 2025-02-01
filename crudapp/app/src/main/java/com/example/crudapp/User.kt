package com.example.crudapp

import androidx.room.Entity
// Importa a anotação Entity para definir uma classe como uma tabela do Room
import androidx.room.PrimaryKey
// Importa a anotação PrimaryKey para definir a chave primária da tabela

// Define a entidade User como uma tabela no banco de dados Room
@Entity(tableName = "user_table")  // Especifica o nome da tabela como "user_table"
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Define a chave primária com autoincremento
    val name: String,  // Campo para armazenar o nome do usuário
    val age: Int  // Campo para armazenar a idade do usuário
)
