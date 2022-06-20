package com.martian.architecture.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.martian.architecture.room.dao.CatDao
import com.martian.architecture.room.dao.DogDao
import com.martian.architecture.room.dao.PersonDao
import com.martian.architecture.room.data.Cat
import com.martian.architecture.room.data.Dog
import com.martian.architecture.room.data.Person

/**
 * Create by：Martian
 * on 2022/6/9
 */
@Database(entities = [Person::class, Dog::class, Cat::class], version = 5)
abstract class AppDataBase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun dogDao(): DogDao
    abstract fun catDao(): CatDao

    companion object {
        @Volatile
        private var mInstance: AppDataBase? = null
        private const val DATABASE_NAME = "martian.db"

        /**
         * update version 1 to 2
         */
        val updateVersion_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql = "create table Dog(id integer primary key autoincrement not null, name text not null,age integer not null)"
                database.execSQL(sql)
            }
        }

        val updateVersion_2_3 = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql = "alter table Person add column dogId integer"
                database.execSQL(sql)
            }
        }
        val updateVersion_3_4 = object : Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql1 = "alter table Person add column city text"
                val sql2 = "alter table Person add column state text"
                val sql3 = "alter table Person add column street text"
                database.execSQL(sql1)
                database.execSQL(sql2)
                database.execSQL(sql3)
            }
        }

        val updateVersion_4_5 = object : Migration(4,5){
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql = "create table Cat(id integer primary key autoincrement not null, name text not null,age integer not null)"
                database.execSQL(sql)
            }
        }

        @JvmStatic
        fun getInstance(context: Context): AppDataBase? {
            if (mInstance == null) {
                synchronized(AppDataBase::class.java) {
                    if (mInstance == null) {
                        mInstance = createInstance(context)
                    }
                }
            }
            return mInstance
        }

        /**
         * //是否允许在主线程进行查询
        .allowMainThreadQueries()
        //数据库创建和打开后的回调
        //.addCallback()
        //设置查询的线程池
        //.setQueryExecutor()
        //.openHelperFactory()
        //room的日志模式
        //.setJournalMode()
        //数据库升级异常之后的回滚
        //.fallbackToDestructiveMigration()
        //数据库升级异常后根据指定版本进行回滚
        //.fallbackToDestructiveMigrationFrom()
        // .addMigrations(CacheDatabase.sMigration)
         */
        private fun createInstance(context: Context): AppDataBase? {
            return Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DATABASE_NAME)
                .addMigrations(updateVersion_1_2,updateVersion_2_3,updateVersion_3_4,updateVersion_4_5).build() as AppDataBase
        }
    }


}