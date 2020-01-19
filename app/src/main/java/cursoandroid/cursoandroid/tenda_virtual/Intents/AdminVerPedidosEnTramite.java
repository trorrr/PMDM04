package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import cursoandroid.cursoandroid.tenda_virtual.R;
import cursoandroid.cursoandroid.tenda_virtual.RecycleViewAdapter_RecycleViewCardView;

public class AdminVerPedidosEnTramite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ver_pedidos_en_tramite);
        RecycleViewAdapter_RecycleViewCardView recycleAdapter = new RecycleViewAdapter_RecycleViewCardView(getApplicationContext(), "","En trámite");

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);

        RecyclerView recyclerView = findViewById(R.id.rvwRecycleView);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(recycleAdapter);
    }
}
