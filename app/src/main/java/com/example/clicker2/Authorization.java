package com.example.clicker2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Authorization extends AppCompatActivity {
    public static String userLog;
    Button loginBtn, registrationBtn;
    TextView login, password;
    DBUser dbClicker;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_authorization);
        loginBtn=(Button) findViewById(R.id.login);
        registrationBtn=(Button) findViewById(R.id.registration);
        login=(TextView)findViewById(R.id.loginText);
        password=(TextView)findViewById(R.id.passwordText);
        dbClicker = new DBUser(this);
        Click();
    }
    void Click() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log = login.getText().toString();
                String pas = password.getText().toString();
                String name = null;
                String age = null;
               /* SQLiteDatabase database = dbClicker.getWritableDatabase();
               // System.out.println(log+" "+pas);
                Cursor query = database.rawQuery("SELECT * FROM contacts where login = "+"'"+log+"'"+" and password = "+"'"+pas+"' LIMIT 1;", null);
                //Cursor query = database.rawQuery("SELECT * FROM contacts where login = 'Name' and password = '123' LIMIT 1;", null);

                System.out.println(query.toString());
                while(query.moveToNext()){
                     name = query.getString(0);
                     age = query.getString(1);
                  //  System.out.println(name+" "+age);
                }
                if (name !=null && age!=null){
                    userLog=name;
                    Intent intent = new Intent(Authorization.this, MainActivity.class);
                    startActivity(intent);
                }
                query.close();
                database.close();
                */
                mAuth.signInWithEmailAndPassword(log,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){//
                            userLog=log;
                            Intent intent = new Intent(Authorization.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Authorization.this,"Wrong password",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        });
        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Authorization.this, Registration.class);
                startActivity(intent);
            }


        });

    }
}