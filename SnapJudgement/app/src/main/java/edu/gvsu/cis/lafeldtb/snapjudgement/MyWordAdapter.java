package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Started by Josh Techentin on 3/31/2015.
 */
public class MyWordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> data;

    public MyWordAdapter(ArrayList<String> s) {
        data = s;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell,
                viewGroup, false);
        MyWordHolder holder = new MyWordHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MyWordHolder tmp = (MyWordHolder) viewHolder;
        tmp.word.setText (data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyWordHolder extends RecyclerView.ViewHolder {

        public EditText word;

        public MyWordHolder(View itemView) {
            super(itemView);
            word = (EditText) itemView.findViewById (R.id.myword);
        }
    }
}
