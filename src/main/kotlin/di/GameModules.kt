package com.br.tictactoe.di

import com.br.tictactoe.data.di.injectGameNetworkMappersModules
import com.br.tictactoe.data.di.injectGameRepositoryModules
import com.br.tictactoe.domain.di.injectGameUseCasesModules
import com.br.tictactoe.domain.di.injectGameDomainModules

val injectGameModules = listOf(
    injectGameDomainModules(),
    injectGameNetworkMappersModules(),
    injectGameRepositoryModules(),
    injectGameUseCasesModules(),
)