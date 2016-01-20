package se.dropmedia.milan.assignment_2;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle b = getArguments();
        ImageView image = (ImageView)view.findViewById(R.id.detail_image);
        TextView movieName = (TextView)view.findViewById(R.id.detail_name);
        TextView movieYear = (TextView)view.findViewById(R.id.detail_year);
        TextView movieDescription = (TextView)view.findViewById(R.id.detail_description);

        image.setImageResource(b.getInt("image", 0));
        movieName.setText(b.getString("name"));
        movieYear.setText(b.getString("year"));
        movieDescription.setText(b.getString("desc"));

        return view;
    }
}
