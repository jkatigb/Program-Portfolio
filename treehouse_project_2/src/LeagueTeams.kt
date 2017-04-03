data class LeagueTeams(val teams: MutableSet<Team> = mutableSetOf<Team>()) {

    fun addToLeague(t: Team) {
        if (teams.size > TOTAL_PLAYERS) println("Sorry, league capacity has been reached")
        else teams.add(t)
    }

    fun computeBalanceReport() {
        val report: MutableMap<Name, Double> = mutableMapOf()
        for ((_, tn, exp) in teams) {
            if (exp.size > 0) report.put(tn, (exp.count(predicate = Player::previousExperience).toDouble() / exp.size.toDouble()))
            else report.put(tn, 0.0)
        }
        report.forEach({ println("Team: ${it.key} -> Pros/Team: ${it.value}%") })
    }

    fun computeHeightReport() {
        //(35-40, 41-46, 47-50 inches)
        //TODO:: add checks for under 35" and over 50"
        val report: MutableMap<Name, List<Int>> = mutableMapOf()
        for ((_, tn, exp) in teams) {
            var between35And40: Count = 0
            var between41And46: Count = 0
            var between47And50: Count = 0
            if (exp.size > 0) {
                exp.forEach({ (_, _, heighIntInches) ->
                    if (heighIntInches in 35..40) {
                        between35And40 += 1
                    } else if (heighIntInches in 41..46) {
                        between41And46 += 1
                    } else if (heighIntInches in 47..50) {
                        between47And50 += 1
                    }
                })

                report.put(tn, listOf(between35And40, between41And46, between47And50))

            } else report.put(tn, listOf(0, 0, 0))
        }
        report.forEach({
            println("--- Team: ${it.key} \n" +
                    "Player Heights: 35\" - 40\": ${it.value[0]}\n" +
                    "41\"-46\":${it.value[1]}\n" +
                    "47\"-50\":${it.value[2]}\n")
        })

    }

    override fun toString(): String {
        return "Teams in League: ${teams.size}\n${teams.sortedBy(Team::teamName)}\n"
    }


}