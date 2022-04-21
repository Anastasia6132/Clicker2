package com.example.clicker2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoreActivity2 extends AppCompatActivity {
    public static int count;
    Button buyUpgrade;
    int descCost = 10;
    TextView textmoney;
    TextView currentMultiply;
    TextView multiplyInfo, costInfo;
    public static int money = MainActivity.moneycount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store2);
        textmoney=(TextView)findViewById(R.id.storeMoney2);
        //currentMultiplyLabel
        //multipyInfoLabel
        currentMultiply=(TextView)findViewById(R.id.currentMultiplyLabel);
        multiplyInfo=(TextView)findViewById(R.id.multipyInfoLabel);

        costInfo=(TextView)findViewById(R.id.costInfoLabel);
        buyUpgrade=(Button) findViewById(R.id.buyUpgrade);
        System.out.println(money);
        textmoney.setText(count+" G");
        textmoney.setCursorVisible(true);
       // currentMultiply.setText("Current multiply: "+MainActivity.multiply);

        String hello = getResources().getString(R.string.log_in);
        System.out.println(hello);

        currentMultiply.setText(getResources().getString(R.string.Current_multiply)+": " + MainActivity.multiply);

        multiplyInfo.setText(getResources().getString(R.string.multiply) + ": " +(MainActivity.multiply + 1) );

        costInfo.setText(getResources().getString(R.string.cost) + ": " + descCost  );
        Click();
    }
    void Click(){
        buyUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.moneycount>=descCost) {
                    MainActivity.multiply += 1;
                    MainActivity.moneycount -= descCost;
                    //descCost *=2;
                    money = MainActivity.moneycount;
                    textmoney.setText(money + " G");
                    MainActivity.textmoney.setText(money + " G");
                    currentMultiply.setText(getResources().getString(R.string.Current_multiply)+": " + MainActivity.multiply);
                    multiplyInfo.setText(getResources().getString(R.string.multiply) + ": " +(MainActivity.multiply + 1) );
                    costInfo.setText(getResources().getString(R.string.cost) + ": " + descCost);

                }
            }
        });


    }
}