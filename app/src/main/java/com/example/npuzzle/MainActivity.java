package com.example.npuzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
    }

    public void startGame(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("Enter board size");

        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.get_size_dialog, null);

        final EditText txtInput = dialogView.findViewById(R.id.txtInput);

        builder.setPositiveButton("Start Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int boardSize = Integer.parseInt(String.valueOf(txtInput.getText()));
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("boardSize", boardSize);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setView(dialogView);
        builder.show();
    }
}