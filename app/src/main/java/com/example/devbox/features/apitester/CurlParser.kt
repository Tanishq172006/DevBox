package com.example.devbox.features.apitester

object CurlParser {

    fun parseCurl(
        curl: String
    ): CurlRequest {

        val method =

            Regex("-X\\s+(\\w+)")

                .find(curl)

                ?.groupValues?.get(1)

                ?: "GET"

        val url =

            Regex("https?://[^\\s\\\"]+")

                .find(curl)

                ?.value

                ?: ""

        val headers =

            Regex("-H\\s+\"([^\"]+)\"")

                .findAll(curl)

                .map {

                    val header =
                        it.groupValues[1]

                    val split =
                        header.split(":")

                    Pair(

                        split.first().trim(),

                        split.drop(1)
                            .joinToString(":")
                            .trim()
                    )
                }

                .toList()

        val body =

            Regex("-d\\s+'([^']+)'")

                .find(curl)

                ?.groupValues?.get(1)

                ?: ""

        return CurlRequest(

            method = method,

            url = url,

            headers = headers,

            body = body
        )
    }
}