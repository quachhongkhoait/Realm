package com.example.realm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realm.R;
import com.example.realm.model.Info;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Realm myRealm;
    private EditText mEdtFullName, mEdtAddress;
    private Button mBtnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtFullName = findViewById(R.id.tvFullname);
        mEdtAddress = findViewById(R.id.tvAddress);
        mBtnClick = findViewById(R.id.btnclick);

        myRealm = Realm.getDefaultInstance();
//        myRealm.beginTransaction();
//        Info info = myRealm.createObject(Info.class);
//        info.setFullname("Khoa");
//        info.setAddress("Binh Dinh");
//        myRealm.commitTransaction();
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Info info = new Info();
                        info.setFullname(mEdtFullName.getText().toString());
                        info.setAddress(mEdtAddress.getText().toString());
                        realm.copyToRealmOrUpdate(info);
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Log.d("nnn", "onError: "+ error.getMessage());
                        Toast.makeText(MainActivity.this, "Lỗi update", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}