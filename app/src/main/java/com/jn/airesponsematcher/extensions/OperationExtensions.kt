package com.jn.airesponsematcher.extensions

import com.jn.airesponsematcher.operation.OperationBase
import com.jn.airesponsematcher.processor.Output

fun Output.process(): String = processLines()

/** Strips one pair of surrounding double quotes, if present. */
fun String.removeQuotes(): String {
    val v = trim()
    return if (v.length >= 2 && v.first() == '"' && v.last() == '"') v.substring(1, v.length - 1) else v
}

/** Replaces the first match of [operation] with [newValue]. */
fun String.replaceOperationWithNewValue(operation: OperationBase, newValue: String = ""): String =
    operation.regex.replaceFirst(this, Regex.escapeReplacement(newValue))

/** Replaces every match of every operation with [newValue]. */
fun String.replaceAllOperationsWithNewValue(
    operations: List<OperationBase>,
    newValue: String = ""
): String {
    var result = this
    for (operation in operations) {
        result = operation.regex.replace(result, Regex.escapeReplacement(newValue))
    }
    return result
}
