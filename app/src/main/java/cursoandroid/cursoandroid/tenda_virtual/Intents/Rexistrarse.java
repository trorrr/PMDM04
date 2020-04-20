package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cursoandroid.cursoandroid.tenda_virtual.BaseDatos;
import cursoandroid.cursoandroid.tenda_virtual.R;

public class Rexistrarse extends AppCompatActivity {
    private static final int REQUEST_CODE_GRAVACION_OK = 0;
    private static final int CODIGO_IDENTIFICADOR = 1;
    private BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rexistrarse);
        pedirPermiso();
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
        final TextView txtURI = (TextView) findViewById(R.id.txtURI);

        Cursor cursor = sqlLiteDB.rawQuery("select count(*) from USUARIOS where usuario='"+TXTUsuario+"'", null);
        if (cursor.moveToFirst()) { // Por si no hay registros
            int existeUsuario =cursor.getInt(0);
            if(existeUsuario==0) { //No existe el usuario, hay que crearlo
                sqlLiteDB.execSQL("INSERT INTO Usuarios (nome,apelidos,email,usuario,contrasinal,tipo,foto) VALUES ('"+TXTNome.getText().toString()+"','"+TXTApelidos.getText().toString()+"','"+TXTEmail.getText().toString()+"','"+TXTUsuario.getText().toString()+"','"+TXTContrasinal.getText().toString()+"','"+radioButton.getText().toString()+"','"+txtURI.getText().toString()+"')");
                Toast.makeText(getApplicationContext(),"¡Rexistrado satisfactoriamente, xa podes loguearte! ",Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"¡O usuario xa existe! ",Toast.LENGTH_LONG).show();
            }
        }

        baseDatos.close();
        baseDatos = null;
    }

    public void onClickSacarFoto(View v) {
        Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intento, 1);
    }

    public void onClickSeleccionarFoto(View v) {
        Intent intento = new Intent(Intent.ACTION_PICK);
        intento.setType("image/*");
        startActivityForResult(intento, 2);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final TextView txtURI = findViewById(R.id.txtURI);
        Bitmap bitMap= (Bitmap) data.getExtras().get("data");
        final ImageView imgView = findViewById(R.id.imgView);
        imgView.setImageBitmap(bitMap);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File nombre=new File(directory,dateFormat.format(new Date())+".jpg");
        try {
            final FileOutputStream fos = new FileOutputStream(nombre);
            bitMap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        txtURI.setText(nombre.toString());

    }

    public void pedirPermiso(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions( new String[]{Manifest.permission.CAMERA},1);
            requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {


        switch (requestCode) {
            case CODIGO_IDENTIFICADOR: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"PERMISO CONCEDIDO",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"É NECESARIO O PERMISO",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void volver(View v)  {
        finish();
    }
}
