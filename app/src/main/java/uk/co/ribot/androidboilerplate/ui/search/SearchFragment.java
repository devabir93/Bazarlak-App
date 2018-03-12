package uk.co.ribot.androidboilerplate.ui.search;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.base.BaseFragment;

public class SearchFragment extends BaseFragment implements SearchMvpView {
    @BindView(R.id.search_result_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchView)
    SearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this,view);
        searchView.setQueryHint(getString(R.string.search_hint));
/*
        databaseObject = new DbBackend(MainActivity.this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<ItemObject> dictionaryObject = databaseObject.searchDictionaryWords(query);
                SearchAdapter mSearchAdapter = new SearchAdapter(MainActivity.this, dictionaryObject);
                listView.setAdapter(mSearchAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    }
                });
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
*/

        return view;
    }

}
