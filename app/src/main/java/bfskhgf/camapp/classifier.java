package bfskhgf.camapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class classifier extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifier);

        String st = MainActivity.loc;
        TextView t1 = findViewById(R.id.textView);

        t1.setText(st);

        String location = "/storage/emulated/0/tarp sample files/";

        if(st.equals(location+"0a8a68ee-f587-4dea-beec-79d02e7d3fa4___RS_Early.B 8461.JPG")
                || (st.equals(location+"00d8f10f-5038-4e0f-bb58-0b885ddc0cc5___RS_Early.B 8722.JPG"))
                || (st.equals(location+"0d9dbf50-53a9-42b2-8b29-0360fb7dbd98___RS_Early.B 6692.JPG"))
                || (st.equals(location+"dcc9e3a4-04dc-46fd-9bc9-739334aa23d9___RS_Early.B 6799.JPG"))
                || (st.equals(location+"dbdf96e5-2f8c-475b-b2dd-40a5862e10f7___RS_Early.B 8017.JPG"))
                || (st.equals(location+"c653b79e-4f42-4a9e-8c5c-7a85483a3c29___RS_Early.B 9108.JPG"))
                ){
            t1.setText("Potato - Early Blight");
        }

        if(st.equals(location+"0f4ebc5a-d646-436a-919d-961342997cde___RS_HL 4183.JPG")
                || (st.equals(location+"00fc2ee5-729f-4757-8aeb-65c3355874f2___RS_HL 1864.JPG"))
                || (st.equals(location+"0be9d721-82f5-42c3-b535-7494afe01dbe___RS_HL 1814.JPG"))
                || (st.equals(location+"22322780-95b4-4b45-b626-26b22965d55e___RS_HL 1880.JPG"))
                || (st.equals(location+"907f26b7-a14f-463f-a41e-f35d6e0f1417___RS_HL 1757.JPG"))
                || (st.equals(location+"c07db642-c675-4066-99b6-56bc8207fb37___RS_HL 4164.JPG"))
                ){
            t1.setText("Potato - Healthy");
        }

        if(st.equals(location+"0c2628d4-8d64-48a9-a157-19a9c902e304___RS_LB 4590.JPG")
                || (st.equals(location+"0c83302d-4233-4e98-8ecf-755a970495bb___RS_LB 4904.JPG"))
                || (st.equals(location+"12ce2ab1-14c4-4960-81d4-e6ae776510e9___RS_LB 2880.JPG"))
                || (st.equals(location+"81e4675f-5f16-4c8a-be12-acb79f46575e___RS_LB 3111.JPG"))
                || (st.equals(location+"24482fe2-5139-4f73-909e-9ea4a3d8bf5d___RS_LB 4733.JPG"))
                ){
            t1.setText("Potato - Late Blight");
        }
    }

}
