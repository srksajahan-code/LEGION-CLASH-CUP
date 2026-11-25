package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        UserEntity::class,
        PointTransactionEntity::class,
        TournamentEntity::class,
        TeamEntity::class,
        TournamentRegistrationEntity::class,
        MatchRoomEntity::class,
        MatchResultEntity::class,
        VideoEntity::class,
        AnnouncementEntity::class,
        ReportEntity::class,
        AppealEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ArenaConverters::class)
abstract class ArenaDatabase : RoomDatabase() {
    abstract fun arenaDao(): ArenaDao

    companion object {
        @Volatile
        private var INSTANCE: ArenaDatabase? = null

        fun getInstance(context: Context): ArenaDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ArenaDatabase::class.java,
                    "crimson_arena_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
