package com.br.tictactoe.domain.di

import com.br.tictactoe.domain.usecases.DeleteGameUseCase
import com.br.tictactoe.domain.usecases.GetServerMessageUseCase
import com.br.tictactoe.domain.usecases.JoinGameUseCase
import com.br.tictactoe.domain.usecases.SendServerMessageUseCase
import org.koin.dsl.module

fun injectGameUseCasesModules() = gameUseCasesModules

val gameUseCasesModules = module {
    factory { DeleteGameUseCase(get()) }
    factory { GetServerMessageUseCase(get(), get()) }
    factory { JoinGameUseCase(get()) }
    single { SendServerMessageUseCase(get(), get(), get(), get()) }
}