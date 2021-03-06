package com.example.wewantour9;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class My_reservation_agency_adapter extends RecyclerView.Adapter<My_reservation_agency_adapter.ImageViewHolder> {

    private Context mContext;
    private List<Reservation> reservations;
    private Double tour_cost=0.0;
    private Double transport_cost=0.0;
    private String transport_start_place="";
    private String transport_start_hour="";
    private String transport_vhc="";

    public My_reservation_agency_adapter(Context mContext, List<Reservation> reservations) {
        this.mContext = mContext;
        this.reservations=reservations;
    }

    @NonNull
    @Override
    public My_reservation_agency_adapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.my_reservation_row, parent, false);
        return new My_reservation_agency_adapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull My_reservation_agency_adapter.ImageViewHolder holder, int position) {
        final Reservation reservation=reservations.get(position);
        holder.text_tour_name.setText(reservation.getTour().getName());
        Glide.with(mContext)
                .load(reservation.getTour().getFilePath())
                .into(holder.img_tour);
        holder.text_tour_place.setText(reservation.getTour().getStartPlace());
        tour_cost=reservation.getTour().getPrice();
        if(reservation.getTransport()!= null){

            transport_cost=reservation.getTransport().getCost();
            transport_start_place=reservation.getTransport().getStartLocation();
            transport_start_hour=reservation.getTransport().getStartHour();
            transport_vhc=reservation.getTransport().getVehicle();
        }else{
            holder.itemView.findViewById(R.id.textView4).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.transport_info).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.transport_vhc).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.text_start_place).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.text_start_hour).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.imageView10).setVisibility(View.GONE);
            holder.itemView.findViewById(R.id.imageView12).setVisibility(View.GONE);
        }
        holder.text_total_cost.setText("€"+Double.toString((tour_cost+transport_cost)*reservation.getNumberOfPeople()));
        holder.text_transport_start_place.setText(transport_start_place);
        holder.text_transport_start_hour.setText(transport_start_hour);
        holder.text_tour_date.setText(reservation.getTour().getStartDate());
        holder.text_tour_hour.setText(reservation.getTour().getStartHour());
        holder.text_total_people.setText(String.valueOf(reservation.getNumberOfPeople()));

        if(reservation.getTour().getVehicle().equals("bike")){
            Glide.with(mContext)
                    .load(R.drawable.ic_directions_bike_black_24dp)
                    .into(holder.img_tour_vehicle);
        }else{
            Glide.with(mContext)
                    .load(R.drawable.ic_directions_walk_black_24dp)
                    .into(holder.img_tour_vehicle);
        }

        if(transport_vhc.equals("Bus")){
            Glide.with(mContext)
                    .load(R.drawable.ic_directions_bus_black_24dp)
                    .into(holder.img_transport_vehicle);
        }else{
            Glide.with(mContext)
                    .load(R.drawable.car)
                    .into(holder.img_transport_vehicle);
        }

        holder.btn_delete_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new for ANDREA
            }
        });
    }


    @Override
    public int getItemCount() { return reservations.size(); }


    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView text_tour_name;
        public ImageView img_tour;
        public ImageView img_tour_vehicle;
        public ImageView img_transport_vehicle;
        public TextView text_tour_date;
        public TextView text_tour_hour;
        public TextView text_tour_place;
        public TextView text_total_cost;
        public TextView text_total_people;
        public TextView text_transport_start_place;
        public TextView text_transport_start_hour;
        public Button btn_delete_reservation;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            text_tour_name=itemView.findViewById(R.id.tour_name);
            img_tour=itemView.findViewById(R.id.img_tour);
            img_tour_vehicle=itemView.findViewById(R.id.tour_vhc);
            img_transport_vehicle=itemView.findViewById(R.id.transport_vhc);
            text_tour_date=itemView.findViewById(R.id.tour_date);
            text_tour_hour=itemView.findViewById(R.id.tour_hour);
            text_tour_place=itemView.findViewById(R.id.text_destination);
            text_total_people=itemView.findViewById(R.id.num_people);
            text_total_cost=itemView.findViewById(R.id.text_cost);
            text_transport_start_place=itemView.findViewById(R.id.text_start_place);
            text_transport_start_hour=itemView.findViewById(R.id.text_start_hour);
            btn_delete_reservation=itemView.findViewById(R.id.btn_delete_reservation);
        }
    }
}
