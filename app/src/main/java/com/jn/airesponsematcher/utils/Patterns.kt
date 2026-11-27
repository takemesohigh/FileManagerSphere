package com.jn.airesponsematcher.utils

/**
 * Local re-implementation of the (no longer available) AIResponseMatcher library.
 * Reconstructed from how FileManagerSphere uses it; not the original source.
 */
object Patterns {
    /** Lazy capture of everything between an operation's delimiters. Group 1 is parsed into arguments. */
    const val BASE_ARGUMENT = "(.*?)"
}
