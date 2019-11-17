package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.cursoandroid.tenda_virtual.R;

public class FinalizarPedido extends AppCompatActivity {
    private String categoria = "";
    private String producto = "";
    private String cantidade = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pedido);
        Bundle extras = getIntent().getExtras();
        this.categoria = extras.getString("categoria");
        this.producto = extras.getString("producto");
        this.cantidade = extras.getString("cantidade");
    }

    public void clickFinalizar(View v) {
        EditText direccion = (EditText) findViewById(R.id.TXTDireccion);
        EditText cidade = (EditText) findViewById(R.id.TXTCidade);
        EditText cp = (EditText) findViewById(R.id.TXTCP);

        String cadena = "Categoría: "+this.categoria;
        cadena += "\nProducto: "+this.producto;
        cadena += "\nCantidade: "+this.cantidade;
        cadena += "\nDirección: "+direccion.getText().toString();
        cadena += "\nCidade: "+cidade.getText().toString();
        cadena += "\nCódigo Postal: "+cp.getText().toString();

        Toast.makeText(getApplicationContext(),cadena,Toast.LENGTH_LONG).show();
    }

}
