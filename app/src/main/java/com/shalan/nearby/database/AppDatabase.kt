package com.shalan.nearby.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shalan.nearby.database.dao.VenueDAO
import com.shalan.nearby.network.response.Venue

/**
 * Created by Mohamed Shalan on 2/21/21
 */

@Database(entities = arrayOf(Venue::class), version = 1)
@TypeConverters(VenueTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun venueDao(): VenueDAO

}