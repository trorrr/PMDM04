package cursoandroid.cursoandroid.tenda_virtual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cursoandroid.cursoandroid.tenda_virtual.Intents.ZonaAdministrador;
import cursoandroid.cursoandroid.tenda_virtual.Intents.ZonaClientes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickEntrar(View v) {
        final EditText TXTUsuario = (EditText) findViewById(R.id.TXTUsuario);
        final EditText TXTPass = (EditText) findViewById(R.id.TXTPass);
        final RadioGroup Radio = (RadioGroup) findViewById(R.id.radioGrupo);
        int selectedId = Radio.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selectedId);

        if(radioButton.getText().toString().equals("Cliente")) {
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

    }
}
