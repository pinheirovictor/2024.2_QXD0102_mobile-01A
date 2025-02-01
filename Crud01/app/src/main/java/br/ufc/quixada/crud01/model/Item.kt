package br.ufc.quixada.crud01.model







data class Item (
    val id:  String = generatedId().toString(),
    val title: String = "",
    val description: String = ""
){
    companion object{
        private var currentId = 0

        fun generatedId(): Int{
            currentId ++
            return  currentId
        }
    }

}