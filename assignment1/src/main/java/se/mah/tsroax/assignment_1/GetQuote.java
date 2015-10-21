package se.mah.tsroax.assignment_1;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetQuote extends Fragment {

    private FragmentTransaction ft;
    private Button btnGetQuote;
    private View view;

    public GetQuote() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_get_quote, container, false);
        Log.d("GetQuoteFragment", "onCreateView");
        InitializeComponents();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("GetQuoteFragment", "onCreate");
    }

    private void InitializeComponents()
    {
        FragmentManager fm = getFragmentManager();
        ft = fm.beginTransaction();
        btnGetQuote = (Button)view.findViewById(R.id.btngetquote);
        btnGetQuote.setOnClickListener(new newClick());
    }

    private class newClick implements View.OnClickListener {
        Fragment fragment;
        @Override
        public void onClick(View view) {
            fragment = new ShowQuote();
            ft.replace(R.id.fragmentContainer, fragment, "INPUT");
            ft.addToBackStack("INPUT");
            ft.commit();
        }
    }
}
