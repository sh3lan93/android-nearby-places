package com.shalan.nearby.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shalan.nearby.network.response.Venue
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Created by Mohamed Shalan on 2/21/21
 */

@Dao
interface VenueDAO {

    @Query("select * from venue")
    fun fetchSavedData(): Single<List<Venue>>

    @Insert
    fun insertData(vararg venue: Venue)

    @Insert
    fun insertData(items: List<Venue>)


    @Query("delete from venue")
    fun nukeTable()

}