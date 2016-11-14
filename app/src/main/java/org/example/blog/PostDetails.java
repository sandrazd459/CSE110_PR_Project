package org.example.blog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;


public class PostDetails extends AppCompatActivity {
    TextView username, to, from, date, price, comments;
    private Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_post);

        username = (TextView) findViewById(R.id.post_username);
        username.setText(getIntent().getStringExtra("username"));

        to = (TextView) findViewById(R.id.post_to);
        to.setText(getIntent().getStringExtra("to"));

        from = (TextView) findViewById(R.id.post_from);
        from.setText(getIntent().getStringExtra("from"));

        date = (TextView) findViewById(R.id.post_date);
        date.setText(getIntent().getStringExtra("date"));

        price = (TextView) findViewById(R.id.post_price);
        price.setText(getIntent().getStringExtra("price"));

        comments = (TextView) findViewById(R.id.post_comments);
        comments.setText(getIntent().getStringExtra("additional"));

    }
}
