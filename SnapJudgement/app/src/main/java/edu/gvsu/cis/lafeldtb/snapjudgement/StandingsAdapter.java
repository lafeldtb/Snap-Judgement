package edu.gvsu.cis.lafeldtb.snapjudgement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Benjamin LaFeldt, Joshua Techentin, Andrew Burns
 */

public class StandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Player> data;

    public StandingsAdapter(ArrayList<Player> s) {
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
        tmp.word.setText (data.get(i).getName() + ":\t" + data.get(i).getScore());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyWordHolder extends RecyclerView.ViewHolder {

        public TextView word;

        public MyWordHolder(View itemView) {
            super(itemView);
            word = (TextView) itemView.findViewById (R.id.myword);
        }
    }
}
