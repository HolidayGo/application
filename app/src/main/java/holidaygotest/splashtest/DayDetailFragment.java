package holidaygotest.splashtest;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Day detail screen.
 * This fragment is either contained in a {@link DayListActivity}
 * in two-pane mode (on tablets) or a {@link DayDetailActivity}
 * on handsets.
 */
public class DayDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_DAY_ID = "day_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Day day;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DayDetailFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_DAY_ID))
        {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            day = UserData.dayMap.get(getArguments().getString(ARG_DAY_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null)
            {
                appBarLayout.setTitle(day.dayTitle);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.day_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (day != null)
        {
            ((TextView) rootView.findViewById(R.id.day_detail)).setText(day.dayTitle);
        }

        return rootView;
    }
}
