package cursoandroid.cursoandroid.tenda_virtual;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter_RecycleViewCardView extends RecyclerView.Adapter {
    private BaseDatos baseDatos;
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    private RecyclerView recyclerView;


    public RecycleViewAdapter_RecycleViewCardView(Context context,String usuario,String filtro,RecyclerView recyclerOrig) {

        recyclerView=recyclerOrig;
        baseDatos = new BaseDatos(context);
        SQLiteDatabase sqlLiteDB = baseDatos.getWritableDatabase();
        String where = "";
        boolean editable=false;
        if(usuario.length()>0) where = where + " and usuario='"+usuario+"'";
        if(filtro.length()>0) where = where + " and estado='"+filtro+"'";
        if((usuario.length()==0) && (filtro=="En trámite")) editable=true;

        Log.d("HFL","select _id,categoria,producto,cantidad,direccion,cidade,cp,usuario,estado from PEDIDOS where 1=1"+where);
        Cursor cursor = sqlLiteDB.rawQuery("select _id,categoria,producto,cantidad,direccion,cidade,cp,usuario,estado from PEDIDOS where 1=1"+where, null);
        if (cursor.moveToFirst()) { // Por si no hay registros
            while (!cursor.isAfterLast()) {
                //Log.d("HFL","Inserta "+cursor.getInt(0));
                ////// fijamos editable
                pedidos.add(new Pedido(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8),editable));
                cursor.moveToNext();
            }
        }
    }


    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater mInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //View view = (LayoutInflater.from(viewGroup.getContext()))

        View v = mInflater.inflate(R.layout.card_layout_recycleviewcardview,viewGroup,false);

        RecyclerView.ViewHolder viewHolder = new ViewHolder_RecycleViewCardView(v,recyclerView);
        return viewHolder;
    }


    @Override

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder_RecycleViewCardView viewHolderMenu = (ViewHolder_RecycleViewCardView) viewHolder;
        viewHolderMenu.item_IDpedido.setText(String.valueOf(pedidos.get(i).getID()));
        //Log.d("HFL",String.valueOf(pedidos.get(i).getID()));
        viewHolderMenu.item_categoria.setText(pedidos.get(i).getCategoria());
        viewHolderMenu.item_producto.setText(pedidos.get(i).getProducto());
        viewHolderMenu.item_cantidad.setText(String.valueOf(pedidos.get(i).getCantidad()));
        viewHolderMenu.item_direccion.setText(pedidos.get(i).getDireccion());
        viewHolderMenu.item_cidade.setText(pedidos.get(i).getCidade());
        viewHolderMenu.item_cp.setText(String.valueOf(pedidos.get(i).getCp()));
        viewHolderMenu.item_usuario.setText(pedidos.get(i).getUsuario());
        viewHolderMenu.item_estado.setText(pedidos.get(i).getEstado());

        if(pedidos.get(i).getEditable()==false) {
            viewHolderMenu.BTNAdminAceptar.setVisibility(View.GONE);
            viewHolderMenu.BTNAdminRexeitar.setVisibility(View.GONE);
        }
        //Log.d("HFL","Viewholder "+pedidos.get(i).getCategoria());
    }


    @Override

    public int getItemCount() {

        return pedidos.size();

    }

    public final void borrar(int position) {
        pedidos.remove(position);
        notifyItemRemoved(position);
    }

}
