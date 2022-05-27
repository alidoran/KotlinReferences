package alidoran.design_pattern.java.memento;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.design_pattern_java_kotlin.R;

public class MementoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memento_main);

        Editor editor = new Editor();
        History history = new History();

        findViewById(R.id.btn_memento_push).setOnClickListener(view -> {
            String lastChar = editor.getContent();
            editor.setContent(lastChar == null || lastChar.equals("X") ? "O" : "X");
            history.push(editor.createState());
            Toast.makeText(this, editor.getContent() + " Added", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btn_memento_get).setOnClickListener(view -> {
            String lastChar = editor.getContent();
            if (lastChar != null) {
                editor.restore(history.pop());
                Toast.makeText(this, editor.getContent() + " Removed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}