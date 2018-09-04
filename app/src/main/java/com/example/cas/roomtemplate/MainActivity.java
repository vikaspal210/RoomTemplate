package com.example.cas.roomtemplate;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME="user_db";
    private AppDatabase appDatabase;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize Database object
        appDatabase= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

    }

    //insertIntoDb() NOT ON main Thread
    public void insertIntoDB(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                User user = new User();
                user.setFirstName("Vikas");
                user.setLastName(" Pal");
                user.setAge(21);

                        appDatabase.daoAccess().insertAll(user);
                    }
            }
        }).start();
    }

    //readFromDB() NOT ON main Thread
    public void readFromDB(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                        mName = appDatabase.daoAccess().findByName("Vikas", " Pal").getFirstName();
                    }
            }
        }).start();
    }

    //display read data ON main Thread
    public void display(View view) {
        Toast.makeText(this, mName, Toast.LENGTH_SHORT).show();
    }
}
