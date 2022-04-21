package com.example.clicker2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
    TextView login, password, repeatPassword;
    Button registrationBtn;
    DBUser dbClicker;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://userdb-5ea3f-default-rtdb.europe-west1.firebasedatabase.app/");
    public DatabaseReference root =db.getReference().child("Users");
    //let ref = db.database("https://<databaseName><region>.firebasedatabase.app")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_registration);
        registrationBtn=(Button) findViewById(R.id.regNewAccountText);
        login=(TextView)findViewById(R.id.loginRegText);
        password=(TextView)findViewById(R.id.passwordRegText);
        repeatPassword=(TextView)findViewById(R.id.passwordRepRegText);
        dbClicker = new DBUser(this);
        Click();
    }
    private void Click(){

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log = login.getText().toString();
                String pas = password.getText().toString();
                String pasRep = repeatPassword.getText().toString();
                if (pas.equals(pasRep)) {
                    mAuth.createUserWithEmailAndPassword(log, pas)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                private static final String TAG ="UserDB";

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        HashMap<String,String> userMap = new HashMap<>();
                                        userMap.put("login",log);
                                        userMap.put("password",pas);
                                        System.out.println(userMap);
                                        root.setValue(userMap);
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "createUserWithEmail:success");
                                       // FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(Registration.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                       // updateUI(null);
                                    }

                                    // ...
                                }
                            });
/*
                    SQLiteDatabase database = dbClicker.getWritableDatabase();
                    database.execSQL("INSERT OR IGNORE INTO contacts VALUES (" + "'" + log + "'" + "," + "'" + pas + "'" + ");");

                    Cursor query = database.rawQuery("SELECT * FROM contacts;", null);
                    Authorization.userLog = log;
                    while (query.moveToNext()) {
                        String name = query.getString(0);
                        String age = query.getString(1);

                        System.out.println(name + " " + age);
                    }
                    query.close();
                    database.close();*/
                   /* Intent intent = new Intent(Registration.this, MainActivity.class);
                    startActivity(intent);*/
                }
            }
        });

    }
}