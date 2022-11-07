package com.mkk.ru.extension

import com.mkk.ru.domain.model.SubdivisionModel

fun List<SubdivisionModel>.mapToListString(): List<String> =
    this.map { subdivisionModel ->
        subdivisionModel.name
    }
