# SwipeToReveal

# Demo

This app allows you to use swipe gesture to reveal options

![](https://media.giphy.com/media/W2nUxV7cqRah4QpHcO/source.gif)
Gradle 

```
dependencies {
    compile 'com.android.support:design:25.3.1'
    compile 'com.android.support:recyclerview-v7:25.1.1'
    compile "com.daimajia.swipelayout:library:1.2.0@aar"
}
```

ViewHolder layout

```
<com.daimajia.swipe.SwipeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:id="@+id/swipe"
    app:leftEdgeSwipeOffset="0dp"
    app:rightEdgeSwipeOffset="0dp"
    android:layout_height="wrap_content">


    <LinearLayout
        android:id="@+id/bottom_wraper"
        android:layout_width="240dp"
        android:weightSum="3"
        android:orientation="horizontal"
        android:layout_height="match_parent">
        
    //contents here
    
    </LinearLayout>

    <LinearLayout
        android:id="@+id/bottom_wrapper1"
        android:layout_width="80dp"
        android:layout_height="match_parent"
        android:weightSum="1">
        
         //contents here

    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="?android:selectableItemBackground"
        android:elevation="5dp"
        android:orientation="vertical"
        android:padding="10dp">
        
         //contents here

    </LinearLayout>

</com.daimajia.swipe.SwipeLayout>

```

This is the code for a RecyclerView.ViewHolder
```
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
```

Create an adapter using the view holder
```
public class SwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SwipeRecyclerViewAdapter.SimpleViewHolder>
```

Lastly, inside of onBindViewHolder you can implement listeners
```
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
                new CountDownTimer(3000, 1000)
                {
                    public void onTick(long millisUntilFinished)
                    {
                    }
                    public void onFinish()
                    {
                    }
                }.start();
            }
        });
        viewHolder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            }
        });
        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            }
        });
        viewHolder.Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
        mItemManger.bindView(viewHolder.itemView, position);
```

Action demo

![](https://media.giphy.com/media/ZEO2BGdLcuSprCNjuF/source.gif)
![](https://media.giphy.com/media/mGJWJPJRzmqx4IWHC3/source.gif)
![](https://media.giphy.com/media/UpDHNcjbMdlSVVbsuW/source.gif)
![](https://media.giphy.com/media/M9gSTwpMGPs9BZc8YT/source.gif)

Floating action listener
```
    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        android:clickable="true"
        android:src="@drawable/plus" />
        
     fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });
```
Floating Action Button Demo

![](https://media.giphy.com/media/Jrf2OmhSh5fcb41geI/source.gif)

