package com.example.zooapp.models

import com.example.zooapp.R


data class Animal (
    val id: Int,
    val name: String,
    val species: String,
    val imageRes: Int,
    val description: String,
    val curiosities: String,
    var isFavorite: Boolean = false
)

val animalList = listOf(
    Animal(
        id = 1,
        name = "Cachorro",
        species = "Canis lupus familiaris",
        imageRes = R.drawable.dog,
        description = "O cão é um dos animais mais antigos domesticados pelo homem.",
        curiosities = "Os cães têm um olfato cerca de 40 vezes mais potente que o dos humanos."
    ),
    Animal(
        id = 2,
        name = "Gato",
        species = "Felis catus",
        imageRes = R.drawable.cat,
        description = "O gato doméstico é conhecido por sua agilidade e independência.",
        curiosities = "Gatos passam cerca de 70% do dia dormindo."
    ),
    Animal(
        id = 3,
        name = "Elefante",
        species = "Loxodonta africana",
        imageRes = R.drawable.elephant,
        description = "Elefantes são os maiores animais terrestres vivos.",
        curiosities = "Elefantes têm uma memória excepcional, sendo capazes de reconhecer amigos após anos de separação."
    ),
    Animal(
        id = 4,
        name = "Tigre",
        species = "Panthera tigris",
        imageRes = R.drawable.tiger,
        description = "O tigre é o maior membro da família dos felinos.",
        curiosities = "Cada tigre tem um padrão único de listras, como uma impressão digital."
    ),
    Animal(
        id = 5,
        name = "Arara",
        species = "Ara ararauna",
        imageRes = R.drawable.arara,
        description = "A arara é uma ave colorida encontrada em florestas tropicais.",
        curiosities = "Araras podem viver até 50 anos na natureza."
    ),
    Animal(
        id = 6,
        name = "Unicórnio",
        species = "Criatura Mítica",
        imageRes = R.drawable.unicornio,
        description = "O unicórnio é um ser mítico frequentemente associado à pureza e magia.",
        curiosities = "Embora seja um animal lendário, o unicórnio é símbolo de força e beleza."
    ),
    Animal(
        id = 7,
        name = "Girafa",
        species = "Giraffa camelopardalis",
        imageRes = R.drawable.girafa,
        description = "A girafa é o animal terrestre mais alto do mundo.",
        curiosities = "O coração da girafa é extremamente forte para bombear sangue até o cérebro."
    ),
    Animal(
        id = 8,
        name = "Leão",
        species = "Panthera leo",
        imageRes = R.drawable.leao,
        description = "O leão é conhecido como o rei da selva.",
        curiosities = "Leões machos descansam cerca de 20 horas por dia, enquanto as fêmeas caçam."
    ),
    Animal(
        id = 9,
        name = "Macaco",
        species = "Primates",
        imageRes = R.drawable.macaco,
        description = "Macacos são conhecidos por sua inteligência e habilidades sociais.",
        curiosities = "Algumas espécies de macacos utilizam ferramentas no ambiente selvagem."
    ),
    Animal(
        id = 10,
        name = "Hipopótamo",
        species = "Hippopotamus amphibius",
        imageRes = R.drawable.hipo,
        description = "O hipopótamo é um dos maiores mamíferos terrestres.",
        curiosities = "Embora pareçam lentos, os hipopótamos podem correr mais rápido que humanos em curtas distâncias."
    ),
    Animal(
        id = 11,
        name = "Zebra",
        species = "Equus quagga",
        imageRes = R.drawable.zebra,
        description = "A zebra é conhecida por suas listras únicas em preto e branco.",
        curiosities = "As listras da zebra ajudam a confundir predadores."
    ),
    Animal(
        id = 12,
        name = "Jacaré",
        species = "Alligator mississippiensis",
        imageRes = R.drawable.jacare,
        description = "O jacaré é um réptil robusto encontrado em ambientes aquáticos.",
        curiosities = "Jacarés podem viver até 50 anos em cativeiro."
    ),
    Animal(
        id = 13,
        name = "Panda",
        species = "Ailuropoda melanoleuca",
        imageRes = R.drawable.panda,
        description = "O panda gigante é conhecido por seu pelo preto e branco e por comer bambu.",
        curiosities = "Pandas passam a maior parte do dia comendo ou dormindo."
    ),
    Animal(
        id = 14,
        name = "Urso Pardo",
        species = "Ursus arctos",
        imageRes = R.drawable.urso,
        description = "O urso pardo é um dos maiores predadores terrestres.",
        curiosities = "Ursos pardos podem hibernar por até 7 meses, sem comer ou beber."
    )
)

