package com.schibsted.spain.barista.sample;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SearchViewActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        TextView searchResult = findViewById(R.id.searchResult);
        android.widget.SearchView searchView = findViewById(R.id.searchview);
        SearchView veryFarSearchView = findViewById(R.id.searchview_very_far_away);

        applyListener(searchResult, searchView);
        applySupportListener(searchResult, veryFarSearchView);

        applyAdapter(searchView);
        applySupportAdapter(veryFarSearchView);
    }

    private void applyListener(TextView searchResult, android.widget.SearchView searchView) {
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchResult.setText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchResult.setText(newText);
                return true;
            }
        });
    }

    private void applySupportListener(TextView searchResult, SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchResult.setText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchResult.setText(newText);
                return true;
            }
        });
    }

    private void applyAdapter(android.widget.SearchView searchView) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"_id", "name"});
        cursor.addRow(new Object[]{0, "a"});
        cursor.addRow(new Object[]{1, "b"});
        android.widget.CursorAdapter adapter = new CustomAdapter(this, cursor, true);
        searchView.setSuggestionsAdapter(adapter);
    }

    private void applySupportAdapter(SearchView searchView) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"_id", "name"});
        cursor.addRow(new Object[]{0, "a"});
        cursor.addRow(new Object[]{1, "b"});
        CursorAdapter adapter = new CustomSupportAdapter(this, cursor, true);
        searchView.setSuggestionsAdapter(adapter);
    }

    private class CustomAdapter extends android.widget.CursorAdapter {

        CustomAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return new TextView(context);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            if (view instanceof TextView) {
                ((TextView) view).setText(cursor.getString(1));
            }
        }
    }

    private class CustomSupportAdapter extends CursorAdapter {

        CustomSupportAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            return new TextView(context);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            if (view instanceof TextView) {
                ((TextView) view).setText(cursor.getString(1));
            }
        }
    }
}
