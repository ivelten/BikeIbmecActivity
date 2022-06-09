package com.example.bikeibmec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MilhasActivity extends BaseActivity {

    private ImageView americanasImageView;
    private ImageView magaluImageView;
    private ImageView netshoesImageView;
    private ImageView pontoImageView;
    private ImageView rennerImageView;
    private ImageView centauroImageView;
    private TextView milhasTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milhas);

        americanasImageView = findViewById(R.id.americanasImageView);
        magaluImageView = findViewById(R.id.magaluImageView);
        netshoesImageView = findViewById(R.id.netshoesImageView);
        pontoImageView = findViewById(R.id.pontoImageView);
        rennerImageView = findViewById(R.id.rennerImageView);
        centauroImageView = findViewById(R.id.centauroImageView);
        milhasTextView = findViewById(R.id.milhasTextView);

        milhasTextView.setText("1.236");

        americanasImageView.setOnClickListener(view -> {
            openWebAddress("https://www.americanas.com.br/");
        });

        magaluImageView.setOnClickListener(view -> {
            openWebAddress("https://www.magazineluiza.com.br/");
        });

        netshoesImageView.setOnClickListener(view -> {
            openWebAddress("https://www.netshoes.com.br/");
        });

        pontoImageView.setOnClickListener(view -> {
            openWebAddress("https://www.pontofrio.com.br/");
        });

        rennerImageView.setOnClickListener(view -> {
            openWebAddress("https://www.lojasrenner.com.br/");
        });

        centauroImageView.setOnClickListener(view -> {
            openWebAddress("https://www.centauro.com.br/");
        });

        setHomeButton();
    }

    void openWebAddress(String address) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
        startActivity(browserIntent);
    }
}