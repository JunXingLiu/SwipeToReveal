package ca.nait.jliu73.swipedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InputDialog.InputDialogListener
{

    private RecyclerView recyclerView;
    private ArrayList<ItemModel> itemList;
    private FloatingActionButton fab;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<>();
        imageView = (ImageView)findViewById(R.id.empty_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openDialog();

            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener()
        {

            @Override
            public boolean onLongClick(View v)
            {
                recyclerView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                return true;
            }
        });



        SwipeRecyclerViewAdapter adapter = new SwipeRecyclerViewAdapter(this, itemList);

        adapter.setMode(Attributes.Mode.Single);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void applyText(String tag)
    {
        itemList.add(new ItemModel(tag));
        SwipeRecyclerViewAdapter adapter = new SwipeRecyclerViewAdapter(this, itemList);

        adapter.setMode(Attributes.Mode.Single);

        recyclerView.setAdapter(adapter);
    }


    public void openDialog()
    {
        InputDialog input = new InputDialog();
        input.show(getSupportFragmentManager(), "Input Dialog");
    }



}
