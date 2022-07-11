package alidoran.design_pattern.java.adapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import alidoran.design_pattern.R;

public class AdapterMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_adapter);

        AdapterInterface classOne = new AdapterClassOne();
        AdapterInterface classTwo = new AdapterClassAdapter();

        classOne.methodOne();
        classOne.methodTwo();

        classTwo.methodOne();
        classTwo.methodTwo();
    }
}
