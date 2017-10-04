package com.dcastalia.recylerviewswipedismiss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Item> cartList;
    private CartListAdapter mAdapter;
    private View coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.my_cart));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        cartList = new ArrayList<>();
        mAdapter = new CartListAdapter(this, cartList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        // making http call and fetching menu json
        prepareCart();
    }

    /**
     * method make volley network call and parses json
     */
    private void prepareCart() {


       List<Item> items = new ArrayList<>();

        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        items.add(new Item(1, "Title", " A short description goes here ",1232.232, R.mipmap.ic_launcher));
        cartList.clear();
        cartList.addAll(items);
        mAdapter.notifyDataSetChanged();

                        // adding items to cart list

    }

    /**
     * callback when recycler view is swiped
     * item will be removed on swiped
     * undo option will be provided in snackbar to restore the item
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartListAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = cartList.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Item deletedItem = cartList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

}