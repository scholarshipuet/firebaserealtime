package com.example.firebaserealtime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.firebaserealtime.FireBase.FireBase;
import com.example.firebaserealtime.FireBase.FireBaseCallBack;

public class MainActivity extends AppCompatActivity implements FireBaseCallBack {
    private FireBase mFireBase;
    private ImageView imvLed;
    private EditText etName, etMotor;
    private Button btnEditName, btnEditMotor;
    private boolean mLed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFireBase = new FireBase(this);
        map();
    }

    @Override
    public void onReceiveData(String name, boolean led, int motor) {
        if (led) {
            imvLed.setImageResource(R.drawable.ic_led_on);
        }else{
            imvLed.setImageResource(R.drawable.ic_led_off);
        }
        mLed = led;
        etName.setText(name);
        etMotor.setText(motor + "");

    }

    private void map(){
        imvLed = findViewById(R.id.imv_led);
        etName = findViewById(R.id.et_name);
        etMotor = findViewById(R.id.et_motor);
        btnEditName = findViewById(R.id.btn_edit_name);
        btnEditMotor = findViewById(R.id.btn_edit_motor);

        imvLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFireBase.setLed(!mLed);
            }
        });

        btnEditName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFireBase.setName(etName.getText().toString());
            }
        });

        btnEditMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int motor = Integer.parseInt(etMotor.getText().toString());
                mFireBase.setMotor(motor);
            }
        });
    }
}
