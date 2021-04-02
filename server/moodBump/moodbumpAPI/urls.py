from django.urls import include, path
from rest_framework import routers
from . import views

urlpatterns = [
    path('api/sentiment/', views.analyse_sentiment)
]
