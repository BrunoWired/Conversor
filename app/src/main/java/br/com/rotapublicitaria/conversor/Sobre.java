package br.com.rotapublicitaria.conversor;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class Sobre extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        ActionBar ab = getActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

    }
}
