package com.example.chatmindssign;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;

    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.model_result_text);
        editText = findViewById(R.id.write_prompt_edt);
        progressBar = findViewById(R.id.progress_bar);
        button = findViewById(R.id.Search_promt_button);

        button.setOnClickListener(v -> {
            geminiPro model = new geminiPro();

            String query = editText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);

            textView.setText("");
            editText.setText("");

            model.getResponse(query, new ResponceCallBack(){
                @Override
                public void onResponse(String response) {
                    progressBar.setVisibility(View.GONE);
                    textView.setText(response);
                }

                @Override
                public void onError(Throwable throwable) {
                    Toast.makeText(MainActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        });


    }
}