package com.example.cas.roomtemplate;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class},version=1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public abstract UserDao daoAccess();


}
