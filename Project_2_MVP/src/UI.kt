import model.Song
import model.SongBook
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Integer.parseInt

/**
 * Created by Josh on 3/29/2017.
 */
class KaraokeMachine(var mSongBook: SongBook) {
    var mMenu: HashMap<String, String> = HashMap()
    val mReader: BufferedReader = BufferedReader(InputStreamReader(System.`in`))


    init {
        mMenu.put("add", "Add a new song to the song book.")
        mMenu.put("choose", "Choose a song to sing!")
        mMenu.put("quit", "Give up. Exit the program.")
    }

    fun run(): Unit {
        var choice: String?
        do {
            choice = promptAction()
            when (choice) {
                "add" -> {
                    val song: Song = promptForSong()
                    mSongBook.addSong(song)
                    println("$song added!")
                }
                "choose" -> {
                    val artist: String = promptForArtist()
                    val artistSong: Song = promptSongForArtist(artist)
                    println("You chose $artistSong by ${artistSong.mArtist}")
                }
            //TODO add a queue that can accept a varags of songs, and prompt if none are in queue
            //EXPORT SONGBOOOK TODO

                "quit" -> println("~\"Sing\" you later!")
                else -> println("Unknown choice: $choice. Try again. %n%n")
            }
        } while (choice != "quit")

    }

    //1
    @Throws(IOException::class)
    private fun promptAction(): String {

        println("There are ${mSongBook.getSongCount()} available. " +
                "Your options are: \n")
        mMenu.forEach({ t, u -> println("$t - $u") })
        print("What do you want to do: ")
        val choice: String? = try {
            prompt()
        } catch(npe: NullPointerException) {
            println("Can't do that...")
            promptAction()
        }
        return choice!!.trim().toLowerCase()

    }

    //add
    private fun promptForSong(): Song {
        try {
            println("Enter the artist's name: ")
            val artist: String? = prompt()
            println("Enter the title of song: ")
            val title: String? = prompt()
            println("Enter the URL of the song: ")
            val URL: String? = prompt()
            println("Adding \"$title\" by $artist...\n" +
                    "Checking $URL... \n" +
                    "ADDED! ")
            return Song(artist!!, title!!, URL!!)

        } catch (npe: NullPointerException) {
            println("Error, try again")
            return promptForSong()
        }

    }

    private fun prompt(): String {
        val choice: String? = mReader.readLine()
        if (choice == "") {
            throw NullPointerException()
        }
        return choice!!
    }

    private fun promptForArtist(): String {
        println("Available artists: ")
//        println("${mSongBook.mSongs.forEach({ s -> s.mArtist })}")
        val artists: List<String> = mSongBook.mSongs.map(Song::mArtist)
        return artists.elementAtOrElse(promptForIndex(artists) - 1, { "Sorry, can't find artist" })
//        val mBook: MutableList<String> = mSongBook.m.toMutableList()
//        return mBook.elementAtOrElse(promptForIndex(mBook), { s -> "Can't find it" })
    }

    private fun promptForIndex(options: List<String>): Int {
        var counter = 1
        for (option: String in options) {
            println("$counter - $option")
            counter++
        }
        val optionAsString: String = try {
            prompt()
        } catch (npe: NullPointerException) {
            return promptForIndex(options)
        }
        val choice: Int = parseInt(optionAsString.trim())
        println("Your choice: ")
        return choice - 1;

    }

    private fun promptSongForArtist(artist: String): Song {

        val songListOfArtist: HashSet<Song> = mSongBook.getSongsOfArtist(artist)

        return songListOfArtist
                .elementAt(promptForIndex(songListOfArtist
                        .map(Song::mTitle)))
    }

}