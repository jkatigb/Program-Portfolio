import java.io.Serializable

data class Player(val firstName: String, val lastName: String, val heightInInches: Int, val previousExperience: Boolean) : Comparable<Player>, Serializable {

    val fullName: String = firstName + " " + lastName

    override fun compareTo(other: Player): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Player) return false
        val player: Player = other
        if (heightInInches != player.heightInInches
                || previousExperience != player.previousExperience
                || firstName != player.firstName)
            return false
        return lastName == player.lastName
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + heightInInches
        result = 31 * result + if (previousExperience) {
            1
        } else {
            0
        }
        return result
    }

    override fun toString(): String {
        return "$firstName $lastName - $heightInInches\", ${if (previousExperience) {
            "Pro"
        } else {
            "Rookie"
        }}"
    }
}


