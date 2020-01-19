package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cursoandroid.cursoandroid.tenda_virtual.BaseDatos;
import cursoandroid.cursoandroid.tenda_virtual.R;

public class Rexistrarse extends AppCompatActivity {
    private BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rexistrarse);
    }

    public void onClickRexistrarse(View v) {
        baseDatos = new BaseDatos(getApplicationContext());
        SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
        final EditText TXTUsuario = (EditText) findViewById(R.id.TXTUsuario);
        final EditText TXTNome = (EditText) findViewById(R.id.TXTNome);
        final EditText TXTApelidos = (EditText) findViewById(R.id.TXTApelidos);
        final EditText TXTEmail = (EditText) findViewById(R.id.TXTEmail);
        final EditText TXTContrasinal = (EditText) findViewById(R.id.TXTContrasinal);
        final RadioGroup Radio = (RadioGroup) findViewById(R.id.radioGrupo);
        int selectedId = Radio.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        Cursor cursor = sqlLiteDB.rawQuery("select count(*) from USUARIOS where usuario='"+TXTUsuario+"'", null);
        if (cursor.moveToFirst()) { // Por si no hay registros
            int existeUsuario =cursor.getInt(0);
            if(existeUsuario==0) { //No existe el usuario, hay que crearlo
                sqlLiteDB.execSQL("INSERT INTO Usuarios (nome,apelidos,email,usuario,contrasinal,tipo) VALUES ('"+TXTNome.getText().toString()+"','"+TXTApelidos.getText().toString()+"','"+TXTEmail.getText().toString()+"','"+TXTUsuario.getText().toString()+"','"+TXTContrasinal.getText().toString()+"','"+radioButton.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),"¡Rexistrado satisfactoriamente, podes voltar e loguearte! ",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"¡O usuario xa existe! ",Toast.LENGTH_LONG).show();
            }
        }

        baseDatos.close();
        baseDatos = null;
    }
}
