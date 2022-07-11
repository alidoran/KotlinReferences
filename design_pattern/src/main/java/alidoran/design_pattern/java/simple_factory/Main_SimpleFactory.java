package alidoran.design_pattern.java.simple_factory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import alidoran.design_pattern.R;

public class Main_SimpleFactory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__simple_factory);

        Button btnOne = findViewById(R.id.btn1_simple_factory);
        Button btnTwo = findViewById(R.id.btn2_simple_factory);

        btnOne.setOnClickListener(v->{
            String message = Factory_SimpleFactory.create("BranchOne").print();
            Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        });

        btnTwo.setOnClickListener(v->{
            String message = Factory_SimpleFactory.create("BranchTwo").print();
            Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
        });
    }
}
