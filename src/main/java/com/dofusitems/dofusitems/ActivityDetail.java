package com.dofusitems.dofusitems;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityDetail extends AppCompatActivity{

    private ImageView image;

    private TextView name;
    private TextView set;
    private TextView type;
    private TextView level;
    private TextView condition;
    private TextView stats;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.imgDetail);
        name = findViewById(R.id.name);
        set = findViewById(R.id.set);
        type = findViewById(R.id.type);
        level = findViewById(R.id.level);
        condition = findViewById(R.id.condition);
        stats = findViewById(R.id.stats);
        description = findViewById(R.id.description);

        Item item = (Item) getIntent().getExtras().getSerializable("ITEM");

        if(item != null){
            Picasso.with(this).load(item.getImgItem()).into(image);
            name.setText(item.getName());
            set.setText(item.getSetName());
            type.setText(item.getType());
            level.setText(item.getLevel());
            condition.setText(item.getCondition());
            stats.setText(item.getStats());
            description.setText(item.getDescription());
        }
    }
}
