package com.kproject.cedronews.domain.model

data class NewsModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: String,
    val date: String,
    val elapsedTime: String
)

val fakeNewsList = listOf(
    NewsModel(
        id = 1,
        title = "Cientistas Descobrem Nova Espécie de Pinguins Gigantes na Antártica",
        imageUrl = "https://images.pexels.com/photos/86405/penguin-funny-blue-water-86405.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        category = "#Ciência",
        date = "2023-10-24",
        elapsedTime = "2 dias atrás"
    ),
    NewsModel(
        id = 2,
        title = "Empresa Lança Teletransportador Pessoal: O Futuro Chegou!",
        imageUrl = "https://images.pexels.com/photos/6499168/pexels-photo-6499168.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        category = "#Tecnologia",
        date = "2023-10-24",
        elapsedTime = "2 dias atrás"
    ),
    NewsModel(
        id = 3,
        title = "Descoberta Cidade Submersa Perdida no Triângulo das Bermudas",
        imageUrl = "https://images.pexels.com/photos/2432208/pexels-photo-2432208.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        category = "#Mistério",
        date = "2023-10-22",
        elapsedTime = "2 dias atrás"
    ),
    NewsModel(
        id = 4,
        title = "Astrônomos Confirmam Existência de Vida Extraterrestre Inteligente",
        imageUrl = "https://images.pexels.com/photos/7180670/pexels-photo-7180670.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
        category = "#Espaço",
        date = "2023-10-21",
        elapsedTime = "3 dias atrás"
    )
)