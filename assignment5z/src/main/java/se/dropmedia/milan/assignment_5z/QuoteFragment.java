package se.dropmedia.milan.assignment_5z;


import android.app.Fragment;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment{

    View view;
    ListView listView;
    QuoteAdapter adapter;
    ArrayList<Quotes> quotesList = new ArrayList<>();
    ProgressBar pb;

    int count = 0;
    boolean[] selection = new boolean[150];

    public QuoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_quote, container, false);
        view = inflater.inflate(R.layout.fragment_quote, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
        pb = (ProgressBar)view.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        adapter = new QuoteAdapter(this.getActivity(), quotesList);
        listView.setAdapter(adapter);

        MultiChoiceQuotes();

        return view;
    }

    public void MultiChoiceQuotes()
    {
        for (int i = 0; i < selection.length; i++)
        {
            selection[i] = true;
        }

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                count = count + 1;

                mode.setTitle(listView.getCheckedItemCount() + " Selected");
                if (checked) {
                    listView.getChildAt(position).setBackgroundColor(Color.BLUE);
                    selection[position] = false;
                } else {
                    listView.getChildAt(position).setBackgroundColor(Color.WHITE);
                    selection[position] = true;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_delete, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_delete:
                        for (int i = listView.getCount() - 1; i >= 0; i--) {
                            if (!selection[i]) {
                                listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                                Object toRemove = adapter.getItem(i);
                                quotesList.remove(toRemove);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

                for (int i = 0; i < selection.length; ++i)
                {
                    selection[i] = true;
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.action_add:
                if (isOnline())
                {
                    RequestData("https://api.github.com/zen?access_token=dd5a2113a9ece3703ee99e258891da11c519c8f3");
                } else
                {
                    Toast.makeText(getActivity(), "NO NETWORK!", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void RequestData(String uri)
    {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(this.getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void PopulateListFromAPi(String message)
    {
        String newQuotes = message;

        Quotes addToQuotes = new Quotes(newQuotes);
        quotesList.add(addToQuotes);

        adapter.notifyDataSetChanged();

    }

    private class MyTask extends AsyncTask<String, String, String>
    {

        @Override
        protected void onPreExecute()
        {
            //Toast.makeText(getActivity(), "Starting Task", Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String content = "";
            try {
                content = HttpManager.getData(params[0]);
                //Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        @Override
        protected void onPostExecute(String result)
        {
            //Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            PopulateListFromAPi(result);
            pb.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            //Toast.makeText(getActivity(), values[0], Toast.LENGTH_SHORT).show();
        }

    }
}
