package com.example.realm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.realm.R;
import com.example.realm.model.Info;

import io.realm.Realm;

public class MainActivity2 extends AppCompatActivity {

    private TextView mTvFullName;
    private TextView mTvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mTvAddress = findViewById(R.id.tvAddress);
        mTvFullName = findViewById(R.id.tvFullname);
        Realm myRealm = Realm.getDefaultInstance();

        Info results = myRealm.where(Info.class).findFirst();
        mTvFullName.setText(results.getFullname());
        mTvAddress.setText(results.getAddress());

    }
}