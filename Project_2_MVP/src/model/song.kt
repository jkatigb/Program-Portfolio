package model

/**
 * Created by Josh on 3/29/2017.
 *
 *
 * MODEL: import com. . .model.Song; & SongBook; -> in src.
 */

data class Song(val mArtist: String, val mTitle: String, val mVideoUrl: String) {


    override fun toString(): String {
        return String.format("Song: \"%s\" by %s", mTitle, mArtist)
    }

}

class SongBook(var mSongs: ArrayList<Song> = ArrayList<Song>()) {

    fun addSong(song: Song): Unit {
        mSongs.add(song)
    }

    fun getSongCount(): Int {
        return mSongs.size
    }

    //TODO cache this for better use
    fun byArtist(): Map<String, HashSet<Song>> {
        var artistList: HashMap<String, HashSet<Song>> = HashMap()
        mSongs.forEach({ u ->
            artistList.putIfAbsent(u.mArtist, hashSetOf(u)).also { it!!.add(u) }
        })
        return artistList
    }

    fun getArtists(): Set<String> {
        return byArtist().keys
    }

    fun getSongsOfArtist(artist: String): HashSet<Song> {
        return byArtist().getOrDefault(artist, hashSetOf<Song>())
    }

}



