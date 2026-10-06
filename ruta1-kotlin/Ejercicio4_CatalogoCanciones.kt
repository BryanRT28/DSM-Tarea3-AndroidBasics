class Song(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int
) {
    val isPopular: Boolean
        get() = playCount >= 1000

    fun printDescription() {
        println("$title, interpretada por $artist, se lanzó en $yearPublished.")
    }
}

fun main() {
    val song = Song(
        title = "Bohemian Rhapsody",
        artist = "Queen",
        yearPublished = 1975,
        playCount = 1500000
    )

    song.printDescription()
    println("¿Es popular?: ${song.isPopular}")
}