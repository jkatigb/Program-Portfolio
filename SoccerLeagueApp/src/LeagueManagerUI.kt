import java.io.IOException
import java.io.Serializable
import java.lang.Integer.parseInt
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

typealias Name = String
typealias Count = Int
typealias Choice = String
typealias Category = String


fun String.isMoreThan(int: Int): Boolean {
    return this.length > int
}

val TOTAL_PLAYERS = Players.players.size

object LeagueManagerUI : Serializable {


    val mMenu: HashMap<Choice, String> = HashMap()
    val leagueTeams: LeagueTeams = LeagueTeams()

    init {
        mMenu.put("1", "Create a soccer team.")
        mMenu.put("2", "Modify soccer teams.")
        mMenu.put("3", "Add player to team.")
        mMenu.put("4", "See League")
        mMenu.put("5", "Save and Quit")
    }

    fun exportTo() {
        val stream = Files.newOutputStream(Paths.get("LeagueManager.txt"))
        try {
            stream.buffered().writer().use { writer ->
                for (t in 0..leagueTeams.teams.size) {
                    writer.write("${leagueTeams.teams.elementAt(t).coachName}|" +
                            "${leagueTeams.teams.elementAt(t).teamName}|" +
                            "${leagueTeams.teams.elementAt(t).team}")
                }
            }
        } catch (ioe: IOException) {
            println("Problem saving")
            ioe.printStackTrace()
        }


    }

    //FIXME need to figure out a good way to import League
//    fun importFrom() {
//       val validatedString = "${fileName.toLowerCase()}.txt"
//        val stream = Files.newInputStream(Paths.get("LeagueManager.txt"))
//        try {
//            stream.buffered().reader().use { reader ->
//                while ((reader.readLines().isNotEmpty())) {
//                    var line = reader.readText()
//                    var args = line.split("\\|")
//                    var p = args[2].split("[\\,\\s\\\"\\-]")
//                    val s: MutableSet<Player> = (0..p.size)
//                            .asSequence()
//                            .map { Player(p[it], p[it + 1], parseInt(p[it + 2]), p[it + 3] == "Pro") }
//                            .toMutableSet()
////                    val s: MutableSet<Player> = (0..p.size)
////                          .map { Player(p[it], p[it +1], parseInt(p[it +2]), p[it +3] == "Pro") }
////                          .toMutableSet()
//                    leagueTeams.addToLeague(Team(args[0], args[1], s))
//                }
//            }
//        } catch(ioe: IOException) {
//            println("Problems loading")
//            ioe.stackTrace
//            run()
//        }
//    }

    fun run(): Unit {
        var choice: Choice?
        do {
            choice = promptAction()
            when (choice) {
                "1" -> {
                    val t: Team = createTeam()
                    leagueTeams.addToLeague(t)
                    println(leagueTeams)

                }
                "2" -> {
                    if (leagueTeams.teams.size > 0) {
                        val t = chooseTeam()
                        actionOnTeam(t)
                    } else {
                        println("We need to create some teams")
                        val t = createTeam()
                        leagueTeams.addToLeague(t)
                        println(leagueTeams)
                    }
                }
                "3" -> {
                    val player: Player = choosePlayer()
                    addToTeam(player)
                }
                "4" -> {
                    chooseReport()
                }
                "5" -> {
                    promptToSave()
                }
            }
        } while (choice != "5")
    }

    private fun promptToSave() {
        println("Would you like to save? 0 for yes, 1 for no")
        val choice = promptForIndex()
        when (choice) {
            -1 -> promptToSave()
            0 -> {
                println("Saving...")
                exportTo()
                System.exit(1)
            }
            1 -> System.exit(1)
        }
    }

    private fun actionOnTeam(t: Team): Team {

        println("Choose an option: \n 1 - add a player\n 2 - remove a player \n 3 - see team \n 4 - go back")
        var choice = promptForIndex()
        if (choice == -1) return actionOnTeam(t)
        else {
            when (choice) {
                1 -> {
                    var p = choosePlayer()
                    t.addPlayer(p)
                    actionOnTeam(t)
                }
                2 -> {
                    println("Choose player to remove: ")
                    for (i in 0..t.team.size - 1) {
                        println("$i - ${t.team.elementAt(i)}")
                    }
                    var c = promptForIndex()
                    if (c == -1) {
                        println("Sorry, didn't quite get that")
                        actionOnTeam(t)
                    } else {
                        t.removePlayer(t.team.elementAt(c))
                    }
                    actionOnTeam(t)
                }
                3 -> {
                    println("Choose option: \n 1 - regular roster\n 2 - roster by height\n 3 - go back")
                    var c = promptForIndex()
                    when (c) {
                        -1 -> actionOnTeam(t)
                        1 -> t.roster()
                        2 -> t.heightChart()
                        3 -> return t
                        else -> actionOnTeam(t)
                    }
                }
                4 -> {
                    return t
                }
            }

            return t

        }


    }

    private fun chooseTeam(): Team {
        println("Choose a team: ")
        leagueTeams.teams.sortedBy(Team::teamName).forEachIndexed({ i, t ->
            println("$i -> $t")
        })
        var choice = promptForIndex()
        if (choice > leagueTeams.teams.size) {
            println("Not a valid option")
            return chooseTeam()
        }
        return leagueTeams.teams.elementAt(choice)


    }

    private fun chooseReport() {
        println("Choose a report: \n 1 - height report\n 2 - team experience report\n 3 - see league teams\n 4 - go back")
        val index = promptForIndex()
        when (index) {
            -1 -> return chooseReport()
            1 -> leagueTeams.computeHeightReport()
            2 -> leagueTeams.computeBalanceReport()
            3 -> println(leagueTeams)
            4 -> return    //exit here
            else -> {
                println("Didn't quite get that")
                chooseReport()
            }
        }
        return chooseReport()
    }


    private fun choosePlayer(): Player {
        for (i in 0..Players.players.size - 1) {
            println("$i - ${Players.players[i]}")
        }
        println("Choose a player: ")
        val index: Int = promptForIndex()
        if (index < 0 || index > TOTAL_PLAYERS) return choosePlayer()
        else return Players.players[index]


    }

    fun promptAction(): String {
        mMenu.forEach({ println("${it.key} - ${it.value}") })
        print("What do you want to do: ")
        val choice: String? = mReader.readLine()
        if (choice.isNullOrEmpty() || choice!!.isMoreThan(1)) {
            println("Sorry, that is not a valid choice")
            return promptAction()
        }
        return choice
    }

    fun createTeam(): Team {
        print("Enter coach's name: ")
        val coach = mReader.readLine()
        var c = coach.toLowerCase()
        if (c.isNullOrEmpty()) return createTeam()
        print("Enter team's name: ")
        val teamName = mReader.readLine()
        var tN = teamName.toUpperCase()
        if (tN.isNullOrEmpty()) return createTeam()
        val t: Team = Team(c, tN, mutableSetOf<Player>())
        return t
    }


    fun addToTeam(player: Player) {
        val options = leagueTeams.teams.toList()
        if (options.isEmpty()) {
            println("There is no team to add ${player.fullName} to - please create a team first.")
            val quickTeam = createTeam()
            println("...Adding ${player.fullName} to Team: ${quickTeam.teamName}")
            quickTeam.addPlayer(player)
            println(quickTeam)
            return leagueTeams.addToLeague(quickTeam)
        } else {
            println("Choose where to add ${player.fullName} to: ")
            for (i in 0..leagueTeams.teams.size - 1) {
                println("$i - ${options[i]}")
            }
            val choice = promptForIndex()
            if (choice == -1 || choice > leagueTeams.teams.size) {
                return addToTeam(player)
            } else {
                println("Adding ${player.fullName} to Team: ${leagueTeams.teams.elementAt(choice).teamName}")
                return leagueTeams.teams.elementAt(choice).addPlayer(player)
            }
        }


    }


    private fun promptForIndex(): Int {
        val choice = mReader.readLine()
        if (choice.isNullOrEmpty()) {
            println("Sorry, I didn't quite get that")
            return -1
        } else {
            try {
                return parseInt(choice)
            } catch (nfe: NumberFormatException) {
                println("Sorry, didn't quite get that")
                return -1

            }
        }


    }
}

