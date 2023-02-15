package com.challenge.pixabay.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CommonUtilsTest {

    private var searchTerms = listOf(
        "fruits and vegetables",
        "fruits and vegetables",
        " fruits and vegetables",
        " fruits  and  vegetables  ",
        "        fruits       and        vegetables"
    )
    private val expected = "fruits+and+vegetables"

    @Test
    fun `preprocess search terms, removes spaces, and separates words by + sign`() {
        searchTerms.forEach { searchTerm ->
            val result = CommonUtils.preprocessSearchTerm(searchTerm = searchTerm)
            assertThat(result == expected).isTrue()
        }
    }

    private var input = "tag1, tag2, tag3"
    private var expectedOutput = listOf("tag1", "tag2", "tag3")

    @Test
    fun `convert comma separated tags to a list of strings`() {
        val resultOutput = CommonUtils.commaSeparatedStringToList(input)
        assertThat(resultOutput == expectedOutput).isTrue()
    }
}
