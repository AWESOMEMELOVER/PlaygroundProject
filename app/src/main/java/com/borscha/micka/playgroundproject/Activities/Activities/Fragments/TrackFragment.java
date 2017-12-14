package com.borscha.micka.playgroundproject.Activities.Activities.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.BeaconRecycleView.Beacon;
import com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.TrackingRecycleView.Track;
import com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.TrackingRecycleView.TrackAdapter;
import com.borscha.micka.playgroundproject.Activities.Activities.network.VolleySingleton;
import com.borscha.micka.playgroundproject.AddTrackActivity;
import com.borscha.micka.playgroundproject.R;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrackFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList trackList = new ArrayList<>();
    private String test;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn_add_track;
    String testId = "1";
    String URL = "http://unix.trosha.dev.lumination.com.ua/user/";
    String URL2 = "/tracking/";


    private OnFragmentInteractionListener mListener;

    public TrackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackFragment newInstance(String param1, String param2) {
        TrackFragment fragment = new TrackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_track, container, false);
        VolleySingleton.getInstance(getContext()).addToRequestQueue(testHttpMethod());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.trackRecycleView);
        Toast.makeText(getContext(),"Responce is"+ test,Toast.LENGTH_SHORT).show();
        btn_add_track = (Button) view.findViewById(R.id.btn_add_track);
        btn_add_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(), AddTrackActivity.class));
            }
        });
        testMethod();
        TrackAdapter trackAdapter = new TrackAdapter(trackList,getActivity());
        recyclerView.setAdapter(trackAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(context,"Track Fragment attached",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void testMethod() {
        for (int i = 0; i <= 3; i++) {
            Track listItem = new Track(
                    "heading" + (i + 1),
                    "Lorem ipsam",
                    "http://placehold.it/120x120&text=Hi"


            );
            trackList.add(listItem);
        }
    }
    private StringRequest testHttpMethod(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://httpbin.org/ip", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                test = response;
                if (response==null){
                    Log.wtf("testRespone","Responce is empty");
                }
                Log.wtf("http",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        return stringRequest;
    }

    void getUsersTracking(){

        JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET, URL + testId + URL2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("data");
                    fromJson(array);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Responce is", error);
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(myReq);
    }

    private void fromJson(JSONArray array) throws Exception
    {

        trackList = new ArrayList<>();
        for (int i = 0; i < array.length(); ++i)
        {
            JSONObject track = array.getJSONObject(i);
            //trackList.add(new Track(track.getInt("beaconId"), beacon.getString("name"), beacon.getString("imageUrl")));
        }
    }
}
