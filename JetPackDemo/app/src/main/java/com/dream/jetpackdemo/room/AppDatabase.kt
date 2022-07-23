package com.dream.jetpackdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akulaku.chat.model.entity.CombineConversationEntity

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/7/22
 */
@Database(version = 1, entities = [CombineConversationEntity::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun combineConversationDao(): CombineConversationDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDataBase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "conversation_db"
            )
                .build()
                .apply {
                    instance = this
                }
        }
    }
}