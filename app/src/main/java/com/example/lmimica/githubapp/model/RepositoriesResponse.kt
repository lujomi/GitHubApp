package com.example.lmimica.githubapp.model

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(@SerializedName("items")val repositoriesList: List<Repository>)