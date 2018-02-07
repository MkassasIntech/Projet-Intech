package com.dofusitems.dofusitems;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {

            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if(!((String) cardView.getTag()).equals("urlDofus") && !((String) cardView.getTag()).equals("urlDofusBook")){
                        intent = new Intent(MainActivity.this,ActivityOne.class);
                        intent.putExtra("idName", (String) cardView.getTag());
                    }else{
                        intent = new Intent(Intent.ACTION_VIEW);
                        String url;

                        switch ((String) cardView.getTag()){

                            case "urlDofus":
                                url = "https://www.dofus.com";
                                intent.setData(Uri.parse(url));
                                break;

                            case "urlDofusBook":
                                url = "https://www.dofusbook.net";
                                intent.setData(Uri.parse(url));
                                break;
                        }

                        startActivity(intent);
                    }
                    startActivity(intent);
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url;

        switch (menuItem.getItemId()){

            case R.id.dofus:
                url = "https://www.dofus.com";
                intent.setData(Uri.parse(url));
                break;

            case R.id.dofusbook:
                url = "https://www.dofusbook.net";
                intent.setData(Uri.parse(url));
                break;
        }

        startActivity(intent);

        return true;
    }
}

