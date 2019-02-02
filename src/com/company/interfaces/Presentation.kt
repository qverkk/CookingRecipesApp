package com.company.interfaces

interface Presentation<T> {
    val viewPath: String
    val controller: T
}