package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import cursoandroid.cursoandroid.tenda_virtual.R;

public class FacerPedido extends AppCompatActivity {
    private String Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facer_pedido);
        final Spinner spinnerTipoProducto = (Spinner) findViewById(R.id.spinner_tipo_producto);
        Intent intent = getIntent();
        Usuario = intent.getExtras().getString("Usuario");


        spinnerTipoProducto.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Spinner spinTipoProducto = (Spinner) findViewById(R.id.spinner_tipo_producto);
                Spinner spinProductos = (Spinner) findViewById(R.id.spinner_productos);
                switch(spinTipoProducto.getSelectedItem().toString()) {
                    case "Informática":
                        ArrayAdapter<CharSequence> adapter_informatica = ArrayAdapter.createFromResource(getApplicationContext(),R.array.productos_informatica, android.R.layout.simple_spinner_item);
                        adapter_informatica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinProductos.setAdapter(adapter_informatica);
                        break;
                    case "Electrónica":
                        ArrayAdapter<CharSequence> adapter_electronica = ArrayAdapter.createFromResource(getApplicationContext(),R.array.productos_electronica, android.R.layout.simple_spinner_item);
                        adapter_electronica.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinProductos.setAdapter(adapter_electronica);
                        break;
                    case "Móbiles":
                        ArrayAdapter<CharSequence> adapter_mobiles = ArrayAdapter.createFromResource(getApplicationContext(),R.array.productos_mobiles, android.R.layout.simple_spinner_item);
                        adapter_mobiles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinProductos.setAdapter(adapter_mobiles);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
            });
    }

    public void clickSeguinte(View v) {
        Spinner spinTipoProducto = (Spinner) findViewById(R.id.spinner_tipo_producto);
        Spinner spinProducto = (Spinner) findViewById(R.id.spinner_productos);
        Spinner spinCantidade = (Spinner) findViewById(R.id.spinner_cantidade);
        Intent intent = new Intent(getApplicationContext(), FinalizarPedido.class);
        intent.putExtra("categoria",spinTipoProducto.getSelectedItem().toString());
        intent.putExtra("producto",spinProducto.getSelectedItem().toString());
        intent.putExtra("cantidade",spinCantidade.getSelectedItem().toString());
        intent.putExtra("Usuario", Usuario);
        startActivity(intent);

    }

    public void volver(View v)  {
        finish();
    }
}
