package cursoandroid.cursoandroid.tenda_virtual;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_RecycleViewCardView extends RecyclerView.ViewHolder {

    public TextView item_IDpedido;
    public TextView item_categoria;
    public TextView item_producto;
    public TextView item_cantidad;
    public TextView item_direccion;
    public TextView item_cidade;
    public TextView item_cp;
    public TextView item_usuario;
    public TextView item_estado;
    public Button BTNAdminAceptar;
    public Button BTNAdminRexeitar;
    private BaseDatos baseDatos;
    private RecyclerView recyclerView;


    public ViewHolder_RecycleViewCardView(@NonNull final View itemView,RecyclerView recyclerOrig) {

        super(itemView);
        recyclerView = recyclerOrig;
        item_IDpedido = itemView.findViewById(R.id.IDpedidoCV);
        item_categoria = itemView.findViewById(R.id.categoriaCV);
        item_producto = itemView.findViewById(R.id.productoCV);
        item_cantidad = itemView.findViewById(R.id.cantidadCV);
        item_direccion = itemView.findViewById(R.id.direccionCV);
        item_cidade = itemView.findViewById(R.id.cidadeCV);
        item_cp = itemView.findViewById(R.id.cpCV);
        item_usuario = itemView.findViewById(R.id.usuarioCV);
        item_estado = itemView.findViewById(R.id.estadoCV);
        BTNAdminAceptar = itemView.findViewById(R.id.BTNAdminAceptar);
        BTNAdminRexeitar = itemView.findViewById(R.id.BTNAdminRexeitar);


        BTNAdminAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = getAdapterPosition();
                baseDatos = new BaseDatos(v.getContext());
                SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
                sqlLiteDB.execSQL("UPDATE Pedidos SET estado='Aceptado' where _id="+item_IDpedido.getText().toString());
                //Log.d("HFL","UPDATE Pedidos SET estado='Aceptado' where _id="+item_IDpedido.getText().toString());
                baseDatos.close();
                baseDatos = null;

                //GridLayout includedLayout = v.findViewById(R.id.ConstraintLayout);
                //RecyclerView recyclerView = layoutManager.findViewById(R.id.rvwRecycleView);

                RecycleViewAdapter_RecycleViewCardView rec = (RecycleViewAdapter_RecycleViewCardView)recyclerView.getAdapter();
                rec.borrar(posicion);



            }
        });

        BTNAdminRexeitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = getAdapterPosition();
                baseDatos = new BaseDatos(v.getContext());
                SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
                sqlLiteDB.execSQL("UPDATE Pedidos SET estado='Rexeitado' where _id="+item_IDpedido.getText().toString());
                //Log.d("HFL","UPDATE Pedidos SET estado='Rexeitado' where _id="+item_IDpedido.getText().toString());
                baseDatos.close();
                baseDatos = null;


                RecyclerView recyclerView = v.findViewById(R.id.rvwRecycleView);
                recyclerView.getAdapter().notifyItemRemoved(posicion);
            }
        });
    }


    public void bo() {

    }
}
