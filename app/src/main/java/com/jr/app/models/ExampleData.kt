package com.jr.app.models

import java.util.*

/**
 * Created by jonathanrichards on 26/07/2017.
 */
data class ExampleData(val id: String = UUID.randomUUID().toString(),
                       val firstName: String,
                       val secondName: String,
                       val profileImages: List<String>,
                       val info: String)