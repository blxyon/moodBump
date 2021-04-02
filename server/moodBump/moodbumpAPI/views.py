from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.

def mood(request):
    text = request.GET["text"]
    print("Mood request with text \"{}\"".format(text))
    #calculate the mood and send back the result
    #echo the request for now
    return HttpResponse(text)