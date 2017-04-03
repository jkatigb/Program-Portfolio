import java.util.*

/**
 * Created by Josh on 3/28/2017.
 */

data class Tweet(val mAuthor: String, val mDesc: String, val mCreationDate: Date) {


}

fun main(args: Array<String>) {
    val t: Tweet = Tweet("1", "s", Date(1234L))


}

val r: Regex = Regex("[\\s]+")

fun <T> regexFun(): Unit {


    // [    ] = character class
    // [A-Za-z]* -> attempt to match all upper/lowercase letters a-z (range)
    // \w* -> word

    //[^ somepattern] -> ^ is the "not"
    //without a dash it will only match the first letter
    //there's also the . = wildcard

    //VERY different from ^pattern -> defines the beginning of a line
    //pattern$ -> end of a line

    // pattern{#oftimes to repeat}
    // ex. a{4} -> find aaaa
    // ex. a{1, 4} -> a     a     a     a
    // ex [Aab]{4} ->
    // ex. .{2, 6} ->


    // + vs * ->
    // \d* is zero or more of the digit pattern
    // \d+ is one or more of the digit pattern


    // ab?c -> b = optional
    // pattern1?pattern2 = pattern1 is optional

    // \t\ \n or \r == \s for whitespaces

    // \D = \^d
    // \S = \^s
    // \w+\b -> capture word until a boundry


}