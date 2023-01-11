package com.example.pruebaminimo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureButton();
    }

    private void configureButton(){
        Button Button = (Button) findViewById(R.id.button);
        EditText User = (EditText) findViewById(R.id.Usuario);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, Perfil.class));
                Intent i = new Intent(MainActivity.this, Perfil.class);
                i.putExtra("key",User.getText().toString());
                startActivity(i);
                }
        });
    }
}