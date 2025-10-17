package com.br.tictactoe.data.di

import com.br.tictactoe.data.local.repository.InMemoryGameRepositoryImpl
import com.br.tictactoe.data.local.repository.InMemoryGameRepository
import org.koin.dsl.module

fun injectGameRepositoryModules() = inMemoryGameRepositoryModules

val inMemoryGameRepositoryModules = module {
    single<InMemoryGameRepository> { InMemoryGameRepositoryImpl() }
}