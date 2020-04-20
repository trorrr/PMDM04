package cursoandroid.cursoandroid.tenda_virtual.Intents;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.cursoandroid.tenda_virtual.BaseDatos;
import cursoandroid.cursoandroid.tenda_virtual.R;

public class FinalizarPedido extends AppCompatActivity {
    private String categoria = "";
    private String producto = "";
    private String cantidade = "";
    private BaseDatos baseDatos;
    private String Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_pedido);
        Bundle extras = getIntent().getExtras();
        this.categoria = extras.getString("categoria");
        this.producto = extras.getString("producto");
        this.cantidade = extras.getString("cantidade");
        this.Usuario = extras.getString("Usuario");
    }

    public void clickFinalizar(View v) {
        final EditText direccion = (EditText) findViewById(R.id.TXTDireccion);
        final EditText cidade = (EditText) findViewById(R.id.TXTCidade);
        final EditText cp = (EditText) findViewById(R.id.TXTCP);

        String cadena = "Categoría: "+this.categoria;
        cadena += "\nProducto: "+this.producto;
        cadena += "\nCantidade: "+this.cantidade;
        cadena += "\nDirección: "+direccion.getText().toString();
        cadena += "\nCidade: "+cidade.getText().toString();
        cadena += "\nCódigo Postal: "+cp.getText().toString();



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(cadena)
                .setTitle("¿Confirma o pedido?");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                baseDatos = new BaseDatos(getApplicationContext());
                SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
                sqlLiteDB.execSQL("INSERT INTO Pedidos (categoria,producto,cantidad,direccion,cidade,cp,usuario,estado) VALUES ('"+categoria+"','"+producto+"',"+cantidade+",'"+direccion.getText().toString()+"','"+cidade.getText().toString()+"',"+cp.getText().toString()+",'"+Usuario+"','En trámite')");

                baseDatos.close();
                baseDatos = null;
                Toast.makeText(getApplicationContext(),"Pedido efectuado",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(),"Pedido Cancelado",Toast.LENGTH_LONG).show();
                finish();
            }
        });


        AlertDialog dialog = builder.create();
        builder.show();


    }
    public void volver(View v)  {
        finish();
    }
}
