package br.com.daniel.syncpad;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.security.AccessController.getContext;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent telaListaTags = new Intent(this, ListaTagsActivity.class);
        startActivity(telaListaTags);
    }
}
