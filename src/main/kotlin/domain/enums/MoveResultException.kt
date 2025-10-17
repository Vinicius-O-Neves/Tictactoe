package com.br.tictactoe.domain.enums

sealed class MoveResultException: Exception() {
    data object OutOfRange: MoveResultException() {
        private fun readResolve(): Any = OutOfRange
    }

    data object InvalidTurn: MoveResultException() {
        private fun readResolve(): Any = InvalidTurn
    }

    data object InvalidPosition: MoveResultException() {
        private fun readResolve(): Any = InvalidPosition
    }
}