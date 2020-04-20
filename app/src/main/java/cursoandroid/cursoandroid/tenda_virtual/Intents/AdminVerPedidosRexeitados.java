package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import cursoandroid.cursoandroid.tenda_virtual.R;
import cursoandroid.cursoandroid.tenda_virtual.RecycleViewAdapter_RecycleViewCardView;

public class AdminVerPedidosRexeitados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_ver_pedidos_rexeitados);
        RecyclerView recyclerView = findViewById(R.id.rvwRecycleView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        RecycleViewAdapter_RecycleViewCardView recycleAdapter = new RecycleViewAdapter_RecycleViewCardView(getApplicationContext(), "","Rexeitado",recyclerView);





        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(recycleAdapter);
    }

    public void volver(View v)  {
        finish();
    }
}
