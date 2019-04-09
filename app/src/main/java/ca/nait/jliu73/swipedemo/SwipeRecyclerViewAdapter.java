package ca.nait.jliu73.swipedemo;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by Hank on 2019-04-02.
 */

public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.SimpleViewHolder>
{

    private Context context;
    private ArrayList<ItemModel> itemList;

    public SwipeRecyclerViewAdapter(Context context, ArrayList<ItemModel> itemList)
    {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position)
    {
        final ItemModel item = itemList.get(position);

        viewHolder.Item.setText(item.getItemName());

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);


        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));


        // implement methods here as desire
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener()
        {

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, " Click : " + item.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                viewHolder.swipeLayout.setBackgroundColor(Color.parseColor("#BDC3C7"));
                Toast.makeText(v.getContext(),viewHolder.Item.getText().toString() + "is completed", Toast.LENGTH_SHORT).show();
                viewHolder.Item.setText(viewHolder.Item.getText().toString() + " -- " + "Completed");
                new CountDownTimer(3000, 1000)
                {
                    public void onTick(long millisUntilFinished)
                    {

                    }
                    public void onFinish()
                    {
                        mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                        itemList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, itemList.size());
                        mItemManger.closeAllItems();
                    }
                }.start();
            }
        });

        viewHolder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "this is the share body";
                String shareSub = "this is the share subject";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                context.startActivity(Intent.createChooser(shareIntent, "Share using"));
            }
        });

        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", false);
                intent.putExtra("rrule", "FREQ=DAILY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", viewHolder.Item.getText().toString());
                context.startActivity(intent);
            }
        });

        viewHolder.Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                itemList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, itemList.size());
                mItemManger.closeAllItems();
                Toast.makeText(v.getContext(), "Deleted " + viewHolder.Item.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position)
    {
        return R.id.swipe;
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder
    {
        public SwipeLayout swipeLayout;
        public TextView Item;
        public TextView Delete;
        public TextView Edit;
        public TextView Share;
        public ImageButton btnLocation;
        public SimpleViewHolder(View itemView)
        {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            Item = (TextView) itemView.findViewById(R.id.item);
            Delete = (TextView) itemView.findViewById(R.id.Delete);
            Edit = (TextView) itemView.findViewById(R.id.Edit);
            Share = (TextView) itemView.findViewById(R.id.Share);
            btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);
        }
    }
}
