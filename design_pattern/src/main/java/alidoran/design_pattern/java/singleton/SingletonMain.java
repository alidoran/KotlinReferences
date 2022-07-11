package alidoran.design_pattern.java.singleton;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import alidoran.design_pattern.R;


public class SingletonMain extends AppCompatActivity {

    AppCompatEditText edtNumberOne;
    AppCompatEditText edtNumberTwo;
    AppCompatTextView viewAnswer;
    AppCompatButton btnAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_main);

        initView();
        initEvent();


    }

    private void initView() {
        edtNumberOne = findViewById(R.id.singleton_number_one_edt);
        edtNumberTwo = findViewById(R.id.singleton_number_two_edt);
        viewAnswer = findViewById(R.id.singleton_answer_view);
        btnAnswer = findViewById(R.id.singleton_answer_btn);
    }

    private void initEvent() {
        btnAnswer.setOnClickListener(v -> {
            setErrorColor();
            if (!edtNumberOne.getText().toString().isEmpty() && !edtNumberTwo.getText().toString().isEmpty()) {
                double answer = Singleton.getInstance().plus(Long.parseLong(edtNumberOne.getText().toString()), Long.parseLong(edtNumberTwo.getText().toString()));
                viewAnswer.setText(String.valueOf(answer));
            }
        });
    }

    private void setErrorColor() {
        if (edtNumberOne.getText().toString().isEmpty()) {
            edtNumberOne.setTextColor(Color.RED);
        } else
            edtNumberOne.setTextColor(Color.GRAY);
        if (edtNumberTwo.getText().toString().isEmpty()) {
            edtNumberTwo.setTextColor(Color.RED);
        } else
            edtNumberTwo.setTextColor(Color.GRAY);
    }


}
