package com.jn.airesponsematcher.processor

import com.jn.airesponsematcher.operation.Operation
import com.jn.airesponsematcher.utils.ArgumentParser

/** AI output plus the operations to look for in it. */
class Output(private val text: String, private val operations: List<Operation>) {

    /** Runs every operation's `resolve` once per match and returns the resulting text. */
    fun processLines(): String {
        var result = text
        for (operation in operations) {
            for (match in operation.regex.findAll(text)) {
                val args = ArgumentParser.parse(match.groupValues.getOrElse(1) { "" })
                result = operation.resolve(result, args)
            }
        }
        return result
    }
}
