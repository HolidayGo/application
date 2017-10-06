package holidaygotest.splashtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * An activity representing a list of Days. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DayDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DayListActivity extends AppCompatActivity
{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        View recyclerView = findViewById(R.id.day_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.day_detail_container) != null)
        {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView)
    {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(UserData.listOfDays));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>
    {

        private final List<Day> days;

        public SimpleItemRecyclerViewAdapter(List<Day> items)
        {
            days = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.day_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position)
        {
            holder.mItem = days.get(position);
            holder.mIdView.setText(days.get(position).dayNumberString);
            holder.mContentView.setText(days.get(position).dayTitle);

            holder.mView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (mTwoPane)
                    {
                        Bundle arguments = new Bundle();
                        arguments.putString(DayDetailFragment.ARG_DAY_ID, holder.mItem.dayNumberString);
                        DayDetailFragment fragment = new DayDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.day_detail_container, fragment)
                                .commit();
                    } else
                    {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, DayDetailActivity.class);
                        intent.putExtra(DayDetailFragment.ARG_DAY_ID, holder.mItem.dayNumberString);

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return days.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Day mItem;

            public ViewHolder(View view)
            {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString()
            {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
