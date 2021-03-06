package com.bojie.weatherbo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojie.weatherbo.R;
import com.bojie.weatherbo.ui.MainActivity;
import com.bojie.weatherbo.util.UnitConvert;
import com.bojie.weatherbo.weather.Day;

/**
 * Created by bojiejiang on 3/3/15.
 */
public class DayAdapter extends BaseAdapter {

    private Context mContext;
    private Day[] mDays;

    public DayAdapter(Context context, Day[] days) {
        mContext = context;
        mDays = days;
    }


    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.tv_temperatureLabel);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.tv_dayName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = mDays[position];
        holder.iconImageView.setImageResource(day.getIconId());
        double TempInF = day.getTemperatureMax();
        double TempInC = UnitConvert.fahrenheitToCelsius(TempInF);
        holder.temperatureLabel.setText(Math.round(TempInF) + "");
        if (MainActivity.mButtonUnitConvert.getText() == "F") {
            holder.temperatureLabel.setText(Math.round(TempInF) + "");
        } else if(MainActivity.mButtonUnitConvert.getText() == "C"){
            holder.temperatureLabel.setText(Math.round(TempInC) + "");
        }

        if (position == 0) {
            holder.dayLabel.setText("Today");
        } else {
            holder.dayLabel.setText(day.getDayOfTheWeek());
        }


        return convertView;
    }

    private static class ViewHolder {
        ImageView iconImageView;
        TextView temperatureLabel;
        TextView dayLabel;
    }
}
