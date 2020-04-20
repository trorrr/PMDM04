package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import cursoandroid.cursoandroid.tenda_virtual.BaseDatos;
import cursoandroid.cursoandroid.tenda_virtual.R;

public class ZonaClientes extends AppCompatActivity {
    private BaseDatos baseDatos;
    private String Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_clientes);
        ImageView img = (ImageView) findViewById(R.id.IMG);
        final TextView LBLNome = (TextView) findViewById(R.id.LBLNome);
        final ImageView IMG = (ImageView) findViewById(R.id.IMG);
        Intent intent = getIntent();
        Usuario = intent.getExtras().getString("Usuario");
        baseDatos = new BaseDatos(getApplicationContext());
        SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
        Cursor cursor = sqlLiteDB.rawQuery("select nome,apelidos,foto from USUARIOS where usuario='"+Usuario+"'", null);
        if (cursor.moveToFirst()) { // Por si no hay registros
            LBLNome.setText(cursor.getString(0)+" "+cursor.getString(1));
            IMG.setImageBitmap(BitmapFactory.decodeFile(cursor.getString(2)));
        }
        else {
            LBLNome.setText("Erro: Usuario:"+Usuario);
        }

        baseDatos.close();
        baseDatos = null;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.ClientesToolbar);
        setSupportActionBar(myToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu_clientes_toolbar, menu);
        return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_cliente_facer_pedido:
                onClickFacerPedido(null);
                return true;
            case R.id.action_cliente_ver_pedidos_en_tramite:
                onClickVerPedidosEnTramite(null);
                return true;
            case R.id.action_cliente_ver_compras_realizadas:
                onClickVerComprasRealizadas(null);
                return true;
            case R.id.action_cliente_atras:
                finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }

    }



    public void onClickFacerPedido(View v) {
        Intent intent = new Intent(getApplicationContext(), FacerPedido.class);
        intent.putExtra("Usuario", Usuario);
        startActivity(intent);
    }

    public void onClickVerComprasRealizadas(View v) {
        Intent intent = new Intent(getApplicationContext(), VerComprasRealizadas.class);
        intent.putExtra("Usuario", Usuario);
        startActivity(intent);
    }

    public void onClickVerPedidosEnTramite(View v) {
        Intent intent = new Intent(getApplicationContext(), VerPedidosEnTramite.class);
        intent.putExtra("Usuario", Usuario);
        startActivity(intent);
    }

    public void onClickSair(View v) {
        onBackPressed();
    }

    public void onClickEditar(View v) {
        Intent intent = new Intent(getApplicationContext(), EditarDatos.class);
        intent.putExtra("Usuario", Usuario);
        startActivity(intent);
    }

    public void volver(View v)  {
        finish();
    }
}
