package com.jn.airesponsematcher.utils

/**
 * Parses `key: value, key2: "value2"` text. Keys may be quoted or bare; values may be
 * double-quoted (with \" \\ \n \t \r escapes) or bare tokens.
 */
internal object ArgumentParser {
    private val pair = Regex(
        "\"?([A-Za-z_][A-Za-z0-9_]*)\"?\\s*:\\s*(\"(?:\\\\.|[^\"\\\\])*\"|[^,\\r\\n]+)",
        RegexOption.DOT_MATCHES_ALL
    )

    fun parse(text: String): Map<String, String> =
        pair.findAll(text).associate { it.groupValues[1] to unquote(it.groupValues[2]) }

    private fun unquote(raw: String): String {
        val v = raw.trim()
        return if (v.length >= 2 && v.first() == '"' && v.last() == '"') {
            unescape(v.substring(1, v.length - 1))
        } else v
    }

    private fun unescape(s: String): String = buildString {
        var i = 0
        while (i < s.length) {
            val c = s[i]
            if (c == '\\' && i + 1 < s.length) {
                when (val n = s[i + 1]) {
                    'n' -> append('\n')
                    't' -> append('\t')
                    'r' -> append('\r')
                    '"' -> append('"')
                    '\\' -> append('\\')
                    else -> { append(c); append(n) }
                }
                i += 2
            } else {
                append(c)
                i++
            }
        }
    }
}
