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
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cursoandroid.cursoandroid.tenda_virtual.BaseDatos;
import cursoandroid.cursoandroid.tenda_virtual.R;

public class EditarDatos extends AppCompatActivity {
    private BaseDatos baseDatos;
    private String Usuario;
    private static final int CODIGO_IDENTIFICADOR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos);
        final ImageView img2 = (ImageView) findViewById(R.id.imgView2);
        final TextView TXTEditNome = (TextView) findViewById(R.id.TXTEditNome);
        final TextView TXTEditApelidos = (TextView) findViewById(R.id.TXTEditApelidos);
        final TextView TXTEditEmail = (TextView) findViewById(R.id.TXTEditEmail);
        final TextView txtURI2 = (TextView) findViewById(R.id.txtURI2);
        final TextView TXTEditContrasinal1 = (TextView) findViewById(R.id.TXTEditContrasinal1);
        final TextView TXTEditContrasinal2 = (TextView) findViewById(R.id.TXTEditContrasinal2);
        Intent intent = getIntent();
        Usuario = intent.getExtras().getString("Usuario");
        baseDatos = new BaseDatos(getApplicationContext());
        SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
        Cursor cursor = sqlLiteDB.rawQuery("select nome,apelidos,foto,email from USUARIOS where usuario='"+Usuario+"'", null);
        if (cursor.moveToFirst()) { // Por si no hay registros
            TXTEditNome.setText(cursor.getString(0));
            TXTEditApelidos.setText(cursor.getString(1));
            TXTEditEmail.setText(cursor.getString(3));
            txtURI2.setText(cursor.getString(2));
            img2.setImageBitmap(BitmapFactory.decodeFile(cursor.getString(2)));
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

    public void onClickEditar(View v) {
        final ImageView img2 = (ImageView) findViewById(R.id.imgView2);
        final TextView TXTEditNome = (TextView) findViewById(R.id.TXTEditNome);
        final TextView TXTEditApelidos = (TextView) findViewById(R.id.TXTEditApelidos);
        final TextView TXTEditEmail = (TextView) findViewById(R.id.TXTEditEmail);
        final TextView txtURI2 = (TextView) findViewById(R.id.txtURI2);
        final TextView TXTEditContrasinal1 = (TextView) findViewById(R.id.TXTEditContrasinal1);
        final TextView TXTEditContrasinal2 = (TextView) findViewById(R.id.TXTEditContrasinal2);

        if(TXTEditContrasinal1.getText().toString().equals(TXTEditContrasinal2.getText().toString())) {
            baseDatos = new BaseDatos(getApplicationContext());
            SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
            sqlLiteDB.execSQL("update USUARIOS set nome='" + TXTEditNome.getText() + "',apelidos='" + TXTEditApelidos.getText() + "',email='" + TXTEditEmail.getText() + "',contrasinal='"+ TXTEditContrasinal1.getText()+"',foto='"+ txtURI2.getText() +"' where usuario='" + Usuario + "'");

            baseDatos.close();
            baseDatos = null;
            Toast.makeText(this,"DATOS MODIFICADOS",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"NO COINCIDEN LAS CONTRASEÑAS",Toast.LENGTH_LONG).show();
            //No coinciden pass
        }

    }

}
