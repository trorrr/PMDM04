package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cursoandroid.cursoandroid.tenda_virtual.BaseDatos;
import cursoandroid.cursoandroid.tenda_virtual.R;

public class ZonaAdministrador extends AppCompatActivity {
    private BaseDatos baseDatos;
    private String Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zona_administrador);
        final TextView LBLNome = (TextView) findViewById(R.id.LBLAdminNome);
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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.AdminToolbar);
        setSupportActionBar(myToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu_admin_toolbar, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_admin_ver_pedidos_en_tramite:
                onClickVerPedidosEnTramite(null);
                return true;
            case R.id.action_admin_ver_pedidos_aceptados:
                onClickVerPedidosAceptados(null);
                return true;
            case R.id.action_admin_ver_pedidos_rexeitados:
                onClickVerPedidosRexeitados(null);
                return true;
            case R.id.action_admin_atras:
                finish();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }

    }

    public void onClickVerPedidosEnTramite(View v) {
        Intent intent = new Intent(getApplicationContext(), AdminVerPedidosEnTramite.class);
        startActivity(intent);
    }

    public void onClickVerPedidosAceptados(View v) {
        Intent intent = new Intent(getApplicationContext(), AdminVerPedidosAceptados.class);
        startActivity(intent);
    }

    public void onClickVerPedidosRexeitados(View v) {
        Intent intent = new Intent(getApplicationContext(), AdminVerPedidosRexeitados.class);
        startActivity(intent);
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
