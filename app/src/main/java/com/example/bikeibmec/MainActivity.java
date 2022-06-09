package com.example.bikeibmec;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cadastroButton = findViewById(R.id.cadastroButton);

        setHomeButton();

        Intent intent = getIntent();
        Bundle intentExtras = intent.getExtras();

        cadastroButton.setOnClickListener(v -> {
            Intent cadastroIntent = new Intent(this, CadastroDadosPessoaisActivity.class);

            if (intentExtras != null) {
                ClienteModel clienteModel = intent.getExtras().getParcelable(ClienteModel.ID);
                cadastroIntent.putExtra(ClienteModel.ID, (Parcelable)clienteModel);
            }

            startActivity(cadastroIntent);
        });

        Button pedaladasButton = findViewById(R.id.pedaladasButton);

        pedaladasButton.setOnClickListener(v -> {
            Intent pedaladasIntent = new Intent(this, PedaladasActivity.class);
            startActivity(pedaladasIntent);
        });

        Button agendamentosButton = findViewById(R.id.agendamentosButton);

        agendamentosButton.setOnClickListener(v -> {
            Intent agendamentosIntent = new Intent(this, AgendamentosActivity.class);
            startActivity(agendamentosIntent);
        });

        Button milhasButton = findViewById(R.id.milhasButton);

        milhasButton.setOnClickListener(v -> {
            Intent milhasIntent = new Intent(this, MilhasActivity.class);
            startActivity(milhasIntent);
        });
    }
}