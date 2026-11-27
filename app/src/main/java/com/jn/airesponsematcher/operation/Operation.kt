package com.jn.airesponsematcher.operation

import com.jn.airesponsematcher.utils.Patterns

/** Something the AI output can contain that should be found by [regex]. */
interface OperationBase {
    val operationName: String

    /**
     * Default syntax: `name("key": "value", "key2": "value2")` on a single line.
     * Group 1 is the argument text. Override for other syntaxes (e.g. `write START ... END`).
     */
    val regex: Regex
        get() = Regex(
            "(?m)\\b${Regex.escape(operationName)}\\s*\\(${Patterns.BASE_ARGUMENT}\\)[ \\t]*$"
        )
}

/** An [OperationBase] that can also act on the matched arguments. */
interface Operation : OperationBase {
    val name: String

    override val operationName: String
        get() = name

    /** Called once per match. Return the (possibly modified) output. */
    fun resolve(output: String, args: Map<String, String>?): String
}
