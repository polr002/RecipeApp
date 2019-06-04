package com.example.roy.recipeapp;

import android.app.IntentService;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;



public class BackgroundService extends IntentService {
    private ApiService service;
    private List<Recipes> recipesRequestList;
    private static List<Recipe> recipeList;

    public BackgroundService() {
        super("BackgroundService");


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        service = ApiService.retrofit.create(ApiService.class);
        recipesRequestList = new ArrayList<>();
        recipeList = new ArrayList<>();
        Call<Recipes> call = service.getRecipes();
        try {

            Response<Recipes> response = call.execute();

            recipesRequestList.add(response.body());

            recipeList = recipesRequestList.get(0).getRecipes();

        } catch (IOException e) {

        }
    }
}
