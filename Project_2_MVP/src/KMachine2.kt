/**
 * Created by Josh on 3/30/2017.
 */
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Josh on 3/29/2017.
 */
class KMachine2(var mSongBook: gSongBook) {
    var mMenu: HashMap<String, String> = HashMap()
    val mReader: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    init {
        mMenu.put("add", "Add a new song to the song book.")
        mMenu.put("choose", "Choose a song to sing!")
        mMenu.put("quit", "Give up. Exit the program.")
    }

    fun run(): Unit {
        var choice: String = ""
        //prompt user to choose options based on mMenu
        do {
            choice = promptAction()
            when (choice) {


                "add" -> {
                    val song: gSong = addSongChosen()
                    mSongBook.mSongs.
                }
                "quit" -> println("${mMenu["quit"]}")
                else -> println("Sorry, didn't quite get that... ")

            }

        } while (choice != "quit" || choice != "")


    }

    private fun addSongChosen(): gSong {
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
            return gSong(artist!!, title!!, URL!!)

        } catch (npe: NullPointerException) {
            println("Error, try again")
            return addSongChosen()
        }


    }


    private fun promptAction(): String {
        mMenu.forEach({ println("$it - $it") })
        print("What do you want to do: ")
        val choice: String? = try {
            prompt()
        } catch (npe: NullPointerException) {
            println("Can't do that...")
            promptAction()
        }
        return choice!!.trim().toLowerCase()
    }

    private fun prompt(): String? {
        val choice: String? = mReader.readLine()
        if (choice == "") {
            throw NullPointerException()
        }
        return choice!!

    }