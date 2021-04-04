from django.shortcuts import render

# Create your views here.
from rest_framework.decorators import api_view
from rest_framework.response import Response
import os
from moodbumpAPI.sentiment import analyse

@api_view(['POST'])
def analyse_sentiment(request):
    sentiment = analyse(request.POST)
    return Response(sentiment)
