package alidoran.fundamental

import java.util.regex.Pattern

/*

Regex	Meaning
.	Matches any single character.
?	Matches the preceding element once or not at all.
+	Matches the preceding element once or more times.
*	Matches the preceding element zero or more times.
^	Matches the starting position within the string.
$	Matches the ending position within the string.
|	Alternation operator.
[abc]	Matches a or b, or c.
[a-c]	Range; matches a or b, or c.
[^abc]	Negation, matches everything except a, or b, or c.
\s	Matches white space character.
\w	Matches a word character; equivalent to [a-zA-Z_0-9]
() grouping



^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$
 └─────┬────┘└───┬──┘└─────┬─────┘└─────┬─────┘ └───┬───┘
       │         │         │            │           no _ or . at the end
       │         │         │            │
       │         │         │            allowed characters
       │         │         │
       │         │         no __ or _. or ._ or .. inside
       │         │
       │         no _ or . at the beginning
       │
       username is 8-20 characters long

 */



class Regex(private val mString: String) {
    fun createRegex(){
        val findString = Pattern.compile("y1840St").matcher(mString).find()
        val isExistAnyChars = Pattern.compile("[Sz]").matcher(mString).find()
        val isNotOrChars = Pattern.compile("[^zq]").matcher(mString).find()
        val isOneCharGroup = Pattern.compile("[a-g]").matcher(mString).find()
        val isExistOneNumGroup = Pattern.compile("[0-2]").matcher(mString).find()
        val isExistRepetitive = Pattern.compile("1840{2}").matcher(mString).find()
        val isExistRepetitiveAtLeast = Pattern.compile("1840{2,}").matcher(mString).find()
        val isExistRepetitiveDomain = Pattern.compile("1840{0,2}").matcher(mString).find()
        val isExistCharGroup = Pattern.compile("(400)").matcher(mString).find()
        val isExistMultiChar = Pattern.compile("(0)1|40").matcher(mString).find()
        val isExistSpecialChars = Pattern.compile("(\\*)").matcher(mString).find()
        val isExistStartWithChar =  Pattern.compile("^[n]").matcher(mString).find()
        val isExistEndWithChar =  Pattern.compile("g?").matcher(mString).find()
        val isExistNonSpecialChar =  Pattern.compile("\\w").matcher(mString).find()
        val isExistSpecialChar =  Pattern.compile("\\W").matcher(mString).find()
        val isExistNumChar =  Pattern.compile("\\d").matcher(mString).find()
        val isExistNonNumChar =  Pattern.compile("\\D").matcher(mString).find()
        val isExistSpaceChar =  Pattern.compile("\\s").matcher(mString).find()
        val isExistNonSpaceChar =  Pattern.compile("\\S").matcher(mString).find()
    }

    fun findRegex(){
        val findAllChars = ".".toRegex().findAll(mString).map { it.value }.toList()
        val findOrChars = "[Sz]".toRegex().findAll(mString).map { it.value }.toList()
        val findNotOrChars = "[^mz0481q]".toRegex().findAll(mString).map { it.value }.toList()
        val findOneCharGroup = "[a-g]]".toRegex().findAll(mString).map { it.value }.toList()
        val findOneNumGroup = "[0-2]".toRegex().findAll(mString).map { it.value }.toList()
        val findMultiExistOrNotExist = "[185*40]".toRegex().findAll(mString).map { it.value }.toList()
        val findMultiExistMoreThanOne = "[18+40]".toRegex().findAll(mString).map { it.value }.toList()
        val findWithOrWithoutChar = "[185?40]".toRegex().findAll(mString).map { it.value }.toList()
        val findValueBeforeChar =
            "\\d+(?=String)".toRegex().findAll(mString).map { it.value }.toList()
        val findValueNonBeforeChar =
            "\\d+(?!String)".toRegex().findAll("1234my18400String").map { it.value }.toList()
        val findEndWithChars = ".*00".toRegex().findAll(mString).map { it.value }.toList()
        val findEndWithCharsOnlyFirst = ".*?00".toRegex().findAll(mString).map { it.value }.toList()
    }
}

fun main() {
    Regex("my18400String*").createRegex()
    Regex("my18400String*").findRegex()
}

