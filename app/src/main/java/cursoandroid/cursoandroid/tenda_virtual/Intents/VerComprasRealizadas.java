package cursoandroid.cursoandroid.tenda_virtual.Intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cursoandroid.cursoandroid.tenda_virtual.R;
import cursoandroid.cursoandroid.tenda_virtual.RecycleViewAdapter_RecycleViewCardView;

public class VerComprasRealizadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String Usuario = intent.getExtras().getString("Usuario");
        setContentView(R.layout.activity_ver_compras_realizadas);
        RecyclerView recyclerView = findViewById(R.id.rvwRecycleView);
        RecycleViewAdapter_RecycleViewCardView recycleAdapter = new RecycleViewAdapter_RecycleViewCardView(getApplicationContext(), Usuario,"Aceptado",recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);



        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(recycleAdapter);
    }
    public void volver(View v)  {
        finish();
    }
}
