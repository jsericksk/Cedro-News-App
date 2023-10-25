package com.kproject.cedronews.domain.model

data class NewsContentModel(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val content: String,
    val date: String
)

val fakeNewsContent = NewsContentModel(
    id = 1,
    title = "Cientistas Descobrem Nova Forma de Vida em Marte",
    subtitle = "Astrônomos ficam surpresos com a revelação de microrganismos marcianos",
    imageUrl = "https://example.com/mars_discovery_image.jpg",
    content = """
        Após anos de especulações e pesquisas, cientistas finalmente anunciaram a descoberta de uma nova forma de vida em Marte. Microrganismos foram encontrados em amostras coletadas pelo rover Perseverance, revelando uma reviravolta empolgante na exploração espacial.

        Os pesquisadores estão animados com a possibilidade de vida em Marte, mesmo que em uma escala microscópica. Essa descoberta levanta questões fundamentais sobre a origem da vida e a possibilidade de existência de formas mais complexas no planeta vermelho.

        A comunidade científica internacional está reunindo esforços para analisar mais detalhadamente esses microrganismos marcianos. Esta descoberta tem o potencial de redefinir nossa compreensão sobre as condições propícias à vida fora da Terra.

        Fique ligado para mais informações sobre essa descoberta marcante!
    """.trimIndent(),
    date = "26 DE OUTUBRO DE 2021"
)