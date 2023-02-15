package com.challenge.pixabay.domain

object CommonUtils {

    /*
     * Removed white spaces and replaces them with + character
     * Example:
     * searchTerm "cats and dogs"
     * return "cats+and+dogs"
     */
    fun preprocessSearchTerm(searchTerm: String): String {
        return searchTerm
            .trim()
            .replace("\\s+".toRegex(), " ")
            .replace(" ", "+")
    }

    /*
    To convert tags into a string list for Pixabay tags
     */
    fun commaSeparatedStringToList(input: String): List<String> {
        return input.split(",").map { it.trim() }
    }
}
