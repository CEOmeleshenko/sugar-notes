package com.ceomeleshenko.sugarnotes.di

import com.ceomeleshenko.sugarnotes.data.DatabaseProvider
import com.ceomeleshenko.sugarnotes.data.NoteRepository
import com.ceomeleshenko.sugarnotes.data.NoteRepositoryImpl
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.AddNoteViewModel
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.NotesViewModel
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.ReportViewModel
import com.ceomeleshenko.sugarnotes.presentation.viewmodel.StatisticViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<NoteRepository> { NoteRepositoryImpl(DatabaseProvider.getDatabase(get())) }
    viewModel { NotesViewModel(get()) }
    viewModel { AddNoteViewModel(get()) }
    viewModel { StatisticViewModel(get()) }
    viewModel { ReportViewModel(get()) }
}