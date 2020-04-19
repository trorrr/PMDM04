package cursoandroid.cursoandroid.tenda_virtual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDatos extends SQLiteOpenHelper{
    public final static String NOME_BD="basedatos";
    public final static int VERSION_BD=1;

    private String CREAR_TABOA_USUARIOS = "CREATE TABLE USUARIOS (_id  INTEGER PRIMARY KEY AUTOINCREMENT,nome VARCHAR(50)  NOT NULL, apelidos VARCHAR(150),email VARCHAR(150),usuario VARCHAR(50),contrasinal VARCHAR(50),tipo VARCHAR(50),foto TEXT)";
    private String CREAR_TABOA_PEDIDOS = "CREATE TABLE PEDIDOS (_id  INTEGER PRIMARY KEY AUTOINCREMENT,categoria VARCHAR(100), producto VARCHAR(100),cantidad INT,direccion VARCHAR(200),cidade VARCHAR(50),cp INT,usuario VARCHAR(50),estado VARCHAR(50))";

    public BaseDatos(Context context) {
        super(context, NOME_BD, null, VERSION_BD);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREAR_TABOA_USUARIOS);
        db.execSQL(CREAR_TABOA_PEDIDOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS USUARIOS");
        db.execSQL("DROP TABLE IF EXISTS PEDIDOS");
        onCreate(db);
    }

}