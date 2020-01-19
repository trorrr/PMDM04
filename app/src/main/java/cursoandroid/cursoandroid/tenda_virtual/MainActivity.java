package cursoandroid.cursoandroid.tenda_virtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cursoandroid.cursoandroid.tenda_virtual.Intents.Rexistrarse;
import cursoandroid.cursoandroid.tenda_virtual.Intents.ZonaAdministrador;
import cursoandroid.cursoandroid.tenda_virtual.Intents.ZonaClientes;

public class MainActivity extends AppCompatActivity {

    private BaseDatos baseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickEntrar(View v) {
        baseDatos = new BaseDatos(getApplicationContext());
        SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
        final EditText TXTUsuario = (EditText) findViewById(R.id.TXTUsuario);
        final EditText TXTPass = (EditText) findViewById(R.id.TXTPass);

        Cursor cursor = sqlLiteDB.rawQuery("select tipo from USUARIOS where usuario='"+TXTUsuario.getText().toString()+"' AND contrasinal='"+TXTPass.getText().toString()+"'", null);
        if (cursor.moveToFirst()) { // Por si no hay registros
                if (cursor.getString(0).equals("Cliente")) {
                    Intent intent = new Intent(getApplicationContext(), ZonaClientes.class);
                    intent.putExtra("Usuario", TXTUsuario.getText().toString());
                    startActivity(intent);
                } else if (cursor.getString(0).equals("Administrador")) {
                    Intent intent = new Intent(getApplicationContext(), ZonaAdministrador.class);
                    intent.putExtra("Usuario", TXTUsuario.getText().toString());
                    startActivity(intent);
                }
            } else {
                Toast.makeText(getApplicationContext(), "¡Contraseña o usuario incorrectos! ", Toast.LENGTH_LONG).show();
            }



                 /*        if(radioButton.getText().toString().equals("Cliente")) {
            if((TXTUsuario.getText().toString().equals("cliente1")) & (TXTPass.getText().toString().equals("abc123"))) {
                Intent intent = new Intent(getApplicationContext(), ZonaClientes.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"¡Contraseña o usuario incorrectos! ",Toast.LENGTH_LONG).show();
            }
        }
        else if(radioButton.getText().toString().equals("Administrador")) {
            if((TXTUsuario.getText().toString().equals("admin")) & (TXTPass.getText().toString().equals("abc123"))) {
                Intent intent = new Intent(getApplicationContext(), ZonaAdministrador.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"¡Contraseña o usuario incorrectos! ",Toast.LENGTH_LONG).show();
            }
        }
*/
    }

    public void onClickRexistrarse(View v) {
        Intent intent = new Intent(getApplicationContext(), Rexistrarse.class);
        startActivity(intent);
    }
}
