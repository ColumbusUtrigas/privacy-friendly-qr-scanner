package com.secuso.privacyfriendlycodescanner.qrscanner.ui.activities.generator;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.secuso.privacyfriendlycodescanner.qrscanner.R;
import com.secuso.privacyfriendlycodescanner.qrscanner.generator.Contents;
import com.secuso.privacyfriendlycodescanner.qrscanner.ui.helpers.GeneratorKeyboardListener;

public class TextEnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_enter);

        final EditText qrText = (EditText) findViewById(R.id.editText);

        int maxLength = 1817;
        qrText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

        ExtendedFloatingActionButton generate = (ExtendedFloatingActionButton) findViewById(R.id.btnGenerate);

        ConstraintLayout rootView = (ConstraintLayout) findViewById(R.id.rootView);
        GeneratorKeyboardListener listener = new GeneratorKeyboardListener(rootView, generate, R.id.btnGenerate, getApplicationContext().getResources().getDisplayMetrics().densityDpi);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        generate.setOnClickListener(new View.OnClickListener() {
            String result;

            @Override
            public void onClick(View v) {
                result = qrText.getText().toString();
                if (result.isEmpty()) {
                    Toast.makeText(TextEnterActivity.this, R.string.activity_enter_toast_missing_data, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(TextEnterActivity.this, QrGeneratorDisplayActivity.class);
                i.putExtra("gn", result);
                i.putExtra("type", Contents.Type.TEXT);
                startActivity(i);
            }

        });
    }
}
