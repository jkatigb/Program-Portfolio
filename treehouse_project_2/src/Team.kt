data class Team(val coachName: Name, val teamName: Name, val team: MutableSet<Player>) : Comparable<Team> {

    fun addPlayer(player: Player) {
        team.add(player)
        Players.players.remove(player)
        println(" --- Team $teamName: $team")
    }

    fun removePlayer(player: Player) {
        team.remove(player)
        Players.players.add(player)
        println(" --- Available players: ${Players.players}")
    }

    fun roster() {
        team.forEachIndexed({ i, p -> println("$i - $p\n") })
    }

    fun heightChart() {
        team.sortedBy(Player::heightInInches).forEach({ println("${it.heightInInches}\" - ${it.firstName} ${it.lastName}\n") })
    }


    override fun compareTo(other: Team): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this == other) return true
        if (other !is Team) return false
        val eTeam: Team = other

        if (coachName != eTeam.coachName || teamName != eTeam.teamName || team.size != eTeam.team.size) return false
        if (eTeam.team.containsAll(team) && (team.size == eTeam.team.size)) return true
        return teamName == eTeam.teamName
    }

    override fun hashCode(): Int {
        var result = teamName.hashCode()
        result = 31 * result + coachName.hashCode()
        result = 31 * result + team.size
        return result
    }

    override fun toString(): String {
        return "\nTeam: $teamName || Coach: $coachName || Players: $team"
    }
}


