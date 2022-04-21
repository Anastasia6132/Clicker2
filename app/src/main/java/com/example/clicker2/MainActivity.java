package com.example.clicker2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int moneycount=0;
    public final int money = 1; //Просто единица, на нее не смотреть
    public static int multiply = 1;
    Button click, store, save;
    static TextView textmoney;
    DBUserProgress dbUP;
    String name,age,age1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbUP = new DBUserProgress(this);
        setContentView(R.layout.activity_main);
        click=(Button) findViewById(R.id.click);
        store=(Button) findViewById(R.id.store);
        save=(Button) findViewById(R.id.saveProgress);
        textmoney=(TextView)findViewById(R.id.money);
        FirstLoad();
        SQLiteDatabase database = dbUP.getWritableDatabase();
        Cursor query = database.rawQuery("SELECT * FROM userProgress;", null);
        //Cursor query = database.rawQuery("SELECT * FROM contacts where login = 'Name' and password = '123' LIMIT 1;", null);

        System.out.println(query.toString());
        while(query.moveToNext()){
            name = query.getString(0);
            age = query.getString(1);
            age1 = query.getString(2);
              System.out.println(name+" "+age+" "+age1 );
        }

        Click();
    }
    void Click(){
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moneycount=money*multiply+moneycount;
                textmoney.setText(moneycount+""+" G");
                StoreActivity2.count = moneycount;
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreActivity2.class);
                startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = dbUP.getWritableDatabase();
                //database.execSQL("INSERT OR IGNORE INTO contacts VALUES ("+"'"+log+"'"+","+"'"+ pas+"'"+");");
                System.out.println(Authorization.userLog+" "+moneycount+" "+multiply);

                // System.out.println(log+" "+pas);
                Cursor query = database.rawQuery("SELECT * FROM userProgress where login = "+"'"+Authorization.userLog+"' LIMIT 1;", null);
                //Cursor query = database.rawQuery("SELECT * FROM contacts where login = 'Name' and password = '123' LIMIT 1;", null);
                String nameloc=null;
                while(query.moveToNext()){
                    nameloc = query.getString(0);
                }
                if (nameloc==null) {
                    database.execSQL("INSERT OR IGNORE INTO userProgress VALUES (" + "'" + Authorization.userLog + "'" + "," + "'" + moneycount + "'" + "," + "'" + multiply + "'" + ");");
                }else {
                    database.execSQL("UPDATE userProgress SET balance=  " + "" + moneycount + "" + ", upgr=" + "" + multiply + "" + " where login= " + "'" + Authorization.userLog + "';");
                }
            }
        });
    }
    void FirstLoad(){
        String nameloc = null;
        int moneycountloc = 0;
        int multiplyloc=1;
        SQLiteDatabase database = dbUP.getWritableDatabase();
        Cursor query = database.rawQuery("SELECT * FROM userProgress where login = "+"'"+Authorization.userLog+"'"+" LIMIT 1;", null);
        //Cursor query = database.rawQuery("SELECT * FROM contacts where login = 'Name' and password = '123' LIMIT 1;", null);

        System.out.println(query.toString());
        while(query.moveToNext()){
            nameloc = query.getString(0);
            moneycountloc = query.getInt(1);
            multiplyloc = query.getInt(2);
        }
        if (nameloc!=null)
        {
            moneycount=moneycountloc;
            multiply=multiplyloc;
            textmoney.setText(moneycount+""+" G");
            StoreActivity2.count = moneycount;
        }



    }


}