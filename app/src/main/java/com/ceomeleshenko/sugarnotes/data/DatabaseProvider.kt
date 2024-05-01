package com.ceomeleshenko.sugarnotes.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ceomeleshenko.sugarnotes.Database

object DatabaseProvider {
    fun getDatabase(context: Context): Database {
        return Database(
            driver = AndroidSqliteDriver(
                Database.Schema,
                context,
                "sugarnotes.db"
            )
        )
    }
}