package com.br.tictactoe.domain.enums

enum class PlayerSymbol {
    X, O
}

fun PlayerSymbol.getOtherPlayer() =
    if (this == PlayerSymbol.X) PlayerSymbol.O else PlayerSymbol.X