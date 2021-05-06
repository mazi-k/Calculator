package com.example.calculator.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.R;
import com.example.calculator.core.CalculatorImpl;

public class MainActivity extends AppCompatActivity implements CalculatorView {
    private Presenter presenter;
    private TextView resultText;
    private final static String keyPresenter = "Presenter";
    public String CODE_THEME = "default";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(keyPresenter, presenter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        presenter = savedInstanceState.getParcelable(keyPresenter);
        showResult(presenter.getExpressionString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this, new CalculatorImpl());
        resultText = findViewById(R.id.textField);


        findViewById(R.id.button_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton0Clicked();
            }
        });

        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton1Clicked();
            }
        });

        findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton2Clicked();
            }
        });

        findViewById(R.id.button_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton3Clicked();
            }
        });

        findViewById(R.id.button_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton4Clicked();
            }
        });

        findViewById(R.id.button_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton5Clicked();
            }
        });

        findViewById(R.id.button_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton6Clicked();
            }
        });

        findViewById(R.id.button_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton7Clicked();
            }
        });

        findViewById(R.id.button_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton8Clicked();
            }
        });

        findViewById(R.id.button_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButton9Clicked();
            }
        });

        findViewById(R.id.button_point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonPointClicked();
            }
        });

        findViewById(R.id.button_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonPlusClicked();
            }
        });

        findViewById(R.id.button_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonMinusClicked();
            }
        });

        findViewById(R.id.button_mul).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonMultiClicked();
            }
        });

        findViewById(R.id.button_div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonDivClicked();
            }
        });

        findViewById(R.id.button_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonEqualClicked();
            }
        });

        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonCancelClicked();
            }
        });

        findViewById(R.id.button_settings).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                intent.putExtra("THEME", CODE_THEME);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void showResult(String result) {
        resultText.setText(result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        super.onActivityResult(requestCode, resultCode, data);
        CODE_THEME = data.getStringExtra("THEME");
    }

    protected void setTheme(){
        if(CODE_THEME.equals("1")){
            setTheme(R.style.CustomTheme);
        } else if (CODE_THEME.equals("2")){
            setTheme(R.style.CustomTheme2);
        } else {
            Toast.makeText(this, "theme is not be changed", Toast.LENGTH_SHORT).show();
        }
    }
}