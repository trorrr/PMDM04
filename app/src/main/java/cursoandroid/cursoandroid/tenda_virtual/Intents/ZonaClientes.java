package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cursoandroid.cursoandroid.tenda_virtual.R;

public class ZonaClientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_clientes);
        ImageView img = (ImageView) findViewById(R.id.IMG);
        //img.setImageResource(R.drawable.clienteIMG);
    }

    public void onClickFacerPedido(View v) {
        Intent intent = new Intent(getApplicationContext(), FacerPedido.class);
        startActivity(intent);
    }

    public void onClickVerComprasRealizadas(View v) {
        Intent intent = new Intent(getApplicationContext(), VerComprasRealizadas.class);
        startActivity(intent);
    }

    public void onClickVerPedidosEnTramite(View v) {
        Intent intent = new Intent(getApplicationContext(), VerPedidosEnTramite.class);
        startActivity(intent);
    }

    public void onClickSair(View v) {
        onBackPressed();
    }
}
