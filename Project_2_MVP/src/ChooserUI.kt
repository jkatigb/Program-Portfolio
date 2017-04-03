import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * Created by Josh on 3/30/2017.
 */

class GenericMachine<G>(var collection: Collection<G>) {

    val mMenu: HashMap<String, String> = HashMap()
    val mReader: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    //TODO: Add your own options to choose from here
    init {
        mMenu.put("quit", "Exiting the program... Goodbye.")

    }

    fun run(): Unit {
        var choice: String

        //prompt user to choose options based on mMenu
        do {
            choice = promptAction()
            when (choice) {
                "quit" -> println("${mMenu["quit"]}")
                else -> println("Sorry, didn't quite get that... ")

            }

        } while (choice != "quit" || choice != "")


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

}


data class gSong(val mArtist: String, val mTitle: String, val mVideoUrl: String) {
    override fun toString(): String {
        return String.format("Song: \"%s\" by %s", mTitle, mArtist)
    }
}

class gSongBook(var mSongs: MutableList<gSong>) {

    var byArtist: Map<String, MutableList<gSong>> = TreeMap()

    fun sortedBy(i: Int, choice: String): Map<String, MutableList<gSong>> {


    }
    /* artist to title -> 1 to many
    * title to artists -> many to many -> map
    * songbook -> contains both
    *
    * uniqueness vs distinctness = list/map vs set
    * ordered or unordered = tree list/map vs hash
    * ordered by key or by # = map vs list
    *
    * choose -> add/choose/quit
    *               -> by artist -> list of songs -> choose song title -> play
    *               -> by song title -> directly plays
    *               */


}


