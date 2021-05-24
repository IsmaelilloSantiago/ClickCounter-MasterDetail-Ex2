package es.ulpgc.eite.cleancode.clickcounter.master;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.ulpgc.eite.cleancode.clickcounter.R;
import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

public class MasterAdapter extends ArrayAdapter<CounterData> {

  private final List<CounterData> itemList;
  private final View.OnClickListener clickListener;


  public MasterAdapter(
      Context ctx, List<CounterData> items, View.OnClickListener listener) {

    super(ctx, 0, items);

    itemList = items;
    clickListener = listener;
  }


  @Override
  public int getCount() {
    return itemList.size();
  }

  @Override
  public CounterData getItem(int position) {
    return itemList.get(position);
  }


  @Override
  public long getItemId(int position) {
    return getItem(position).id;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View itemView = convertView;

    if (itemView == null) {
      itemView = LayoutInflater
          .from(parent.getContext())
          .inflate(R.layout.detail_master, parent, false);
    }

    itemView.setTag(itemList.get(position));
    itemView.setOnClickListener(clickListener);

    final TextView contentView = itemView.findViewById(R.id.counter);
    Integer data = itemList.get(position).value;
    contentView.setText(String.valueOf(data));

    return itemView;
  }


}
