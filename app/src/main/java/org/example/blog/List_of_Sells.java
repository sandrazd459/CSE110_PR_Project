package org.example.blog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class List_of_Sells extends AppCompatActivity {

    private RecyclerView mBlogList;

    private DatabaseReference mDatabase;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Sell Posts");
        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> fbRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_list,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setFrom(model.getStart());
                viewHolder.setTo(model.getDestination());
                viewHolder.setDate(model.getDate());
                viewHolder.setPrice(model.getPrice());
            }
        };
        mBlogList.setAdapter(fbRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setFrom(String fromText){
            TextView post_from = (TextView)mView.findViewById(R.id.post_from);
            post_from.setText(fromText);
        }

        public void setTo(String toText){
            TextView post_to = (TextView)mView.findViewById(R.id.post_to);
            post_to.setText(toText);
        }
        public void setDate(String dateText){
            TextView post_date = (TextView)mView.findViewById(R.id.post_date);
            post_date.setText(dateText);
        }
        public void setPrice(String priceText){
            TextView post_price = (TextView)mView.findViewById(R.id.post_price);
            post_price.setText(priceText);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add){
            startActivity(new Intent(List_of_Sells.this, Selling_Form.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
