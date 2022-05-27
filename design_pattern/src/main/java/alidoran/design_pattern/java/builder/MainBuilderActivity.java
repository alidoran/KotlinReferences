package alidoran.design_pattern.java.builder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.design_pattern_java_kotlin.R;


public class MainBuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_builder);

        HumanBuilder humanBuilder = new HumanBuilder();
        HumanBuilder humanBuilder2 = new HumanBuilder();


        HumanModel humanModel1 = humanBuilder.setName("Ali").setSex("Man").setWeight(76).setHeight(170).build();
        HumanModel humanModel2 = humanBuilder2.setName("Roya").setSex("Woman").setWeight(65).setHeight(160).build();


        Button btnAliInfo = findViewById(R.id.ali_info_btn);
        Button btnRoyaInfo = findViewById(R.id.roya_info_btn);

        btnAliInfo.setOnClickListener(v-> Toast.makeText(this, humanModel1.toString(), Toast.LENGTH_SHORT).show());
        btnRoyaInfo.setOnClickListener(v-> Toast.makeText(this, humanModel2.toString(), Toast.LENGTH_SHORT).show());
    }
}
